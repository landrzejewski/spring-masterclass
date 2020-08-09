package pl.training.search.wikipedia;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikipediaApi {

    @GET("api.php?action=query&format=json&list=search")
    Observable<Response> getArticles(@Query("srsearch") String query);

}
