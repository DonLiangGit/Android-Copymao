package don.com.flickresque;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.viewpagerindicator.CirclePageIndicator;

import adapter.GuidePagerAdapter;
import don.com.customizedeffects.ParallaxPageTransformer;

public class GuideActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private GuidePagerAdapter mPagerAdapter;
    private LayoutInflater panelInflater = null;

    private final int AUTO_MSG = 1;
    private final int HANDLE_MSG = AUTO_MSG + 1;
    private static final int PHOTO_CHANGE_TIME = 2500;//定时变量
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // Viewpager
        mViewPager = (ViewPager)findViewById(R.id.guidepager);
        mPagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(false, new ParallaxPageTransformer());

        // Infinite Viewpager
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Viewpagerindicator
        CirclePageIndicator guideIndicator = (CirclePageIndicator) findViewById(R.id.guide_indicator);
        guideIndicator.setViewPager(mViewPager);

        // Autoplay
//        mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);

        // Overlay Inflater
        panelInflater = LayoutInflater.from(getBaseContext());
        final View staticPagerView = panelInflater.inflate(R.layout.guide_activity_layout, null);
        ViewGroup.LayoutParams layoutParamsControl = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.addContentView(staticPagerView, layoutParamsControl);

        FlatButton loginBtn = (FlatButton)findViewById(R.id.signin_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.bottom_top, R.anim.activity_parallax);
            }
        });

        FlatButton signupBtn = (FlatButton)findViewById(R.id.signup_button);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AUTO_MSG:
                    if(index <= 2) {
                        mViewPager.setCurrentItem(index++);
                    } else {
                        mViewPager.setCurrentItem(0);
                        index = 0;
                    }
                    mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                    break;
                case HANDLE_MSG:
                    mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
