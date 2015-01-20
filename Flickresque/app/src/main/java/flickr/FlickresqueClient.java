package flickr;

import pojo.PhotoResponse;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import util.Constants;

/**
 * Created by new on 1/11/15.
 */
public class FlickresqueClient {

    // The REST Endpoint URL
    private static final String FLICKR_API_URL= "https://api.flickr.com/services/rest";
    private static final String FLICKR_API_KEY= "abdbda3f5eca657219bbc5947e476448";

    public static final String NO_JSON_CALLBACK = "&nojsoncallback=1";

    public interface FlickresqueService {

        // APIs

        // flickr.photos.search in Asynchronously
        @GET("/?method=flickr.photos.search&extras=original_format" + NO_JSON_CALLBACK)
        void searchPhotos(@Query("text") String searchText, @Query("per_page") String perPage,
                          @Query("page") String page, Callback<PhotoResponse> callback);

        // flickr.photos.getRecent
        @GET("/?method=flickr.photos.getRecent"+ NO_JSON_CALLBACK)
        void getRecent(@Query("per_page") String perPage, @Query("page") String page, Callback<PhotoResponse> callback);

        // flickr.interestingness.getList
        @GET("/?method=flickr.interestingness.getList"+ NO_JSON_CALLBACK)
        void getInterest(@Query("per_page") String perPage, @Query("page") String page, Callback<PhotoResponse> callback);
    }

    private FlickresqueService mFlickrService;
    public synchronized FlickresqueService getFlickrService() {

        if(mFlickrService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(FlickresqueClient.FLICKR_API_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            requestFacade.addQueryParam("format", "json");
                            requestFacade.addQueryParam("api_key", FlickresqueClient.FLICKR_API_KEY);

                            requestFacade.addHeader(Constants.HTTP_HEADER_ACCEPT, Constants.ACCEPT_APPLICATION_JSON);
                        }
                    })
                    .build();

            mFlickrService = restAdapter.create(FlickresqueService.class);
        }
        return mFlickrService;
    }



}
