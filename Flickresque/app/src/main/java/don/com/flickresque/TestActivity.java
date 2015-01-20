package don.com.flickresque;

import android.app.Service;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import adapter.ExCardViewAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import flickr.FlickresqueClient;
import pojo.PhotoResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TestActivity extends ActionBarActivity {

    private static final String TAG = "Flickresque Activity";
    private static final String PHOTOS_PER_PAGE = "30";

    @InjectView(R.id.btnSearch)
    public Button btnSearch;
    @InjectView(R.id.txtSearch)
    public EditText txtSearch;
    @InjectView(R.id.explore_recycler_view)
    public RecyclerView exRecyclerView;

    private ExCardViewAdapter exCardViewAdapter;
    private RecyclerView.LayoutManager exLayoutManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.inject(this);

        final FlickresqueClient.FlickresqueService mFlickrService = new FlickresqueClient().getFlickrService();

        exLayoutManager = new LinearLayoutManager(this);
        exRecyclerView.setLayoutManager(exLayoutManager);
        exCardViewAdapter = new ExCardViewAdapter(this, null);
        exRecyclerView.setAdapter(exCardViewAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtSearch.getWindowToken(), 0);

                String searchTerms = txtSearch.getText().toString();

                // Empty search term
//                if (searchTerms.length() == 0) {
//                    Toast.makeText(TestActivity.this, "Oh no", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                // API Call to search photos with user provided search term
//                mFlickrService.searchPhotos(searchTerms, PHOTOS_PER_PAGE, "1", new Callback<PhotoResponse>() {
//                mFlickrService.getRecent(PHOTOS_PER_PAGE, "1", new Callback<PhotoResponse>() {
                mFlickrService.getInterest(PHOTOS_PER_PAGE, "1", new Callback<PhotoResponse>() {
                    @Override
                    public void success(PhotoResponse photoResponse, Response response) {
                        Log.d(TAG, photoResponse.toString());
                        displayPhotos(photoResponse.getPhotos().getList());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        if (retrofitError.isNetworkError()) {
                            Toast.makeText(TestActivity.this, "Error_network", Toast.LENGTH_SHORT).show();
                        }
                        Log.e(TAG, "Error retrieving photos", retrofitError);
                    }
                });

            }
        });
    }

    private void displayPhotos(List<pojo.Photo> photos) {
        exCardViewAdapter.setPhotos(photos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
