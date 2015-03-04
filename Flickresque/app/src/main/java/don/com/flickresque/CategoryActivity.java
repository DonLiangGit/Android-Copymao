package don.com.flickresque;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;

import adapter.CategoryListAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pojo.CategoryItem;
import util.DonListView;
import util.OnDetectScrollListener;


public class CategoryActivity extends ActionBarActivity {

    @InjectView(R.id.main_toolbar)
    public Toolbar toolbar;

    private DonListView categoryListView;
    private Integer[] itemBackGround = new Integer[]{R.drawable.interests, R.drawable.recent, R.drawable.about, R.drawable.random};
    private String[] itemTitle = new String[] {"Interestingness!", "Most Recent", "Random Photos", "About Developers"};
    private String[] itemDetails = new String[] {"Explore and discover some of Flickr's Finest within the last 7 days ",
                                "Get recent photos uploaded to Flickr ",
                                "Enjoy a list of random photos ",
                                "Peek the developers' daily life "};
    private ArrayList<CategoryItem> categoryItemArrayList;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // ButterKnife Injection
        ButterKnife.inject(this);

        // Material Design Toolbar(Actionbar)
        if (toolbar != null)
            setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flickresque");

//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        initDrawer();

        categoryItemArrayList = new ArrayList<CategoryItem>();
        for(int i = 0; i < itemBackGround.length; i++) {
            CategoryItem categoryItem = new CategoryItem();
            categoryItem.setImgId(itemBackGround[i]);
            categoryItem.setCategoryItemTitle(itemTitle[i]);
            categoryItem.setCategoryItemDetails(itemDetails[i]);
            categoryItemArrayList.add(categoryItem);
        }

        categoryListView = (DonListView) findViewById(R.id.category_list);
        categoryListView.setAdapter(new CategoryListAdapter(CategoryActivity.this, getApplicationContext(), categoryItemArrayList));
        categoryListView.setOnDetectScrollListener(new OnDetectScrollListener() {
            Matrix imageMatrix;
            @Override
            public void onUpScrolling() {
                int first = categoryListView.getFirstVisiblePosition();
                int last = categoryListView.getLastVisiblePosition();
                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = (ImageView) categoryListView.getChildAt(i).findViewById(R.id.categoryImageView);
                    imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, -0.5f);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }
            @Override
            public void onDownScrolling() {
                int first = categoryListView.getFirstVisiblePosition();
                int last = categoryListView.getLastVisiblePosition();
                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = (ImageView) categoryListView.getChildAt(i).findViewById(R.id.categoryImageView);
                    imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, 0.5f);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }
        });

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ImageView imgView = (ImageView)view.findViewById(R.id.categoryImageView);
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initDrawer() {

        // Drawer Initialization
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("Flickresque");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Explore");
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_parallax_in, R.anim.top_bottom);
    }
}
