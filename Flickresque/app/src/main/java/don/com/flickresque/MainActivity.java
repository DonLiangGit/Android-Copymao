package don.com.flickresque;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import adapter.ExCardViewAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import flickr.FlickresqueClient;
import pojo.Photo;
import pojo.PhotoResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import util.Constants;
import util.RecyclerItemClickListener;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "Flickresque Activity";
    private static final String PHOTOS_PER_PAGE = "30";

    @InjectView(R.id.explore_recycler_view)
    public RecyclerView exRecyclerView;
    @InjectView(R.id.progress_bar)
    public ProgressBar mProgressbar;

    private ExCardViewAdapter exCardViewAdapter;
    private RecyclerView.LayoutManager exLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Material Design Toolbar(Actionbar)
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Flickresque");

        // ButterKnife Injection
        ButterKnife.inject(this);

        final FlickresqueClient.FlickresqueService mFlickrService = new FlickresqueClient().getFlickrService();

        // Material CardView
        exLayoutManager = new LinearLayoutManager(this);
        exRecyclerView.setLayoutManager(exLayoutManager);
        exCardViewAdapter = new ExCardViewAdapter(this, null);
        exRecyclerView.setAdapter(exCardViewAdapter);

        mFlickrService.getInterest(PHOTOS_PER_PAGE, "1", new Callback<PhotoResponse>() {
            @Override
            public void success(PhotoResponse photoResponse, Response response) {
                Log.d(TAG, photoResponse.toString());
                displayPhotos(photoResponse.getPhotos().getList());
                mProgressbar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                if (retrofitError.isNetworkError()) {
                    Toast.makeText(MainActivity.this, "Network isn't stable.", Toast.LENGTH_SHORT).show();
                }
                mProgressbar.setVisibility(View.GONE);
                Log.e(TAG, "Error retrieving photos", retrofitError);
            }
        });

        exRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {

//                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void displayPhotos(List<Photo> photos) {
        exCardViewAdapter.setPhotos(photos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_parallax_in, R.anim.top_bottom);
    }
}
