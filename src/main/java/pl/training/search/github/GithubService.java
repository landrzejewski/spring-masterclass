package pl.training.search.github;

import io.reactivex.Observable;
import pl.training.search.GitHubApi;
import retrofit2.Retrofit;

import java.util.List;

public class GithubService {

    private final GitHubApi gitHubApi;

    public GithubService(Retrofit retrofit) {
        gitHubApi = retrofit.create(GitHubApi.class);
    }

    public Observable<List<Repository>> getUserRepositories(String username) {
        return gitHubApi.getUserRepositories(username);
    }

    public Observable<List<Repository>> getRepositories(String query) {
        System.out.println("Get repositories...");
        return gitHubApi.getRepositories(query)
                .map(QueryResult::getItems);
    }

}
