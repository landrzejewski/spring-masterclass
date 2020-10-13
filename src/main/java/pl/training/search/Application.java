package pl.training.search;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.training.search.github.GithubService;
import pl.training.search.github.Repository;
import pl.training.search.wikipedia.Article;
import pl.training.search.wikipedia.WikipediaService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {

    private final GithubService githubService = new GithubService(retrofitBuilder("https://api.github.com/"));
    private final WikipediaService wikipediaService = new WikipediaService(retrofitBuilder("https://en.wikipedia.org/w/"));
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Retrofit retrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                        .build())
                .build();
    }

    private List<String> combine(List<String> result, String value) {
        List<String> newResult = new ArrayList<>(result);
        newResult.add(value);
        return newResult;
    }

    private List<String> combineResults(List<String> results, List<String> otherResults) {
        List<String> newResults = new ArrayList<>();
        newResults.addAll(results);
        newResults.addAll(otherResults);
        return newResults;
    }

    private Observable<List<String>> sendWikipediaQuery(String query) {
        return wikipediaService.getArticles(query)
                .flatMap(Observable::fromIterable)
                .map(Article::getTitle)
                .reduce(new ArrayList<>(), this::combine)
                .toObservable()
                .subscribeOn(Schedulers.io());
    }

    private Observable<List<String>> sendGithubQuery(String query) {
        return githubService.getRepositories(query)
                .flatMap(Observable::fromIterable)
                .map(Repository::getName)
                .reduce(new ArrayList<>(), this::combine)
                .toObservable()
                .subscribeOn(Schedulers.io());
    }

    private void start() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(compositeDisposable::dispose));

        /*compositeDisposable.add(
                ObservableReader.from(System.in)
                    .flatMap(this::sendWikipediaQuery)
                    .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"))
        );*/


        compositeDisposable.add(
                ObservableReader.from(System.in)
                        .debounce(5, TimeUnit.SECONDS)
                        .flatMap(query -> Observable.zip(sendWikipediaQuery(query), sendGithubQuery(query), this::combineResults))
                        .flatMap(Observable::fromIterable)
                        .map(String::toLowerCase)
                        .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"))
        );
    }


    public static void main(String[] args) throws InterruptedException {
        new Application().start();
        Thread.sleep(10_000);
    }

}
