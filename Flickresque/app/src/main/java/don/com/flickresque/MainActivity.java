package don.com.flickresque;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    @InjectView(R.id.main_toolbar)
    public Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private String[] leftSliderData = {"Explore", "Recent", "Search", "About"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ButterKnife Injection
        ButterKnife.inject(this);

        // Material Design Toolbar(Actionbar)
        if (toolbar != null)
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Flickresque");


        initView();
        initDrawer();

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

    private void initView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationDrawerAdapter=new ArrayAdapter<String>( MainActivity.this, android.R.layout.simple_list_item_1, leftSliderData);
        leftDrawerList.setAdapter(navigationDrawerAdapter);
    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("Flickresque");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Drawer Opened");
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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
        if(drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.activity_parallax_in, R.anim.top_bottom);
        }

    }
}
