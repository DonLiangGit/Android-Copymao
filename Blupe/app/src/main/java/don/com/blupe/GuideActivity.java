package don.com.blupe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import don.com.blupe.adapter.GuidePagerAdapter;


public class GuideActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private GuidePagerAdapter mPagerAdapter;
    private LayoutInflater panelInflater = null;
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // ViewPager
        mViewPager = (ViewPager)findViewById(R.id.guidepager);
        mPagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        // Overlay Inflater
        panelInflater = LayoutInflater.from(getBaseContext());
        final View staticPagerView = panelInflater.inflate(R.layout.guide_inflater, null);
        ViewGroup.LayoutParams layoutParamsControl = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.addContentView(staticPagerView, layoutParamsControl);


        btn1 = (Button)staticPagerView.findViewById(R.id.btn1);
        btn2 = (Button)staticPagerView.findViewById(R.id.btn2);
        btn3 = (Button)staticPagerView.findViewById(R.id.btn3);
        btn4 = (Button)staticPagerView.findViewById(R.id.btn4);

//        // Viewpagerindicator
//        CirclePageIndicator guideIndicator = (CirclePageIndicator) staticPagerView.findViewById(R.id.guide_indicator);
//        guideIndicator.setViewPager(mViewPager);

        final TextSwitcher mSwitcher, mSubSwitcher, mDetailSwitcher;
        mSwitcher = (TextSwitcher)staticPagerView.findViewById(R.id.textSwitcher);
        mSubSwitcher = (TextSwitcher)staticPagerView.findViewById(R.id.textSwitcherTwo);
        mDetailSwitcher = (TextSwitcher)staticPagerView.findViewById(R.id.textSwitcherDetails);
        final String titleText[] = {"Image a world","Find your crowd","Meet in groups","Get in the loop,"};
        final String titleSubText[] = {"with no walls", "here and now","gather in life","Blupe on."};
        final String detailText[] = {"Interests, Groups, Conversations. Your Choice, your world.",
            "Discover people who share your passions. In your hood, in real time.",
            "Chat with your groups, use location and photos to take your experience offline.",
            "Join a group and start exploring."};
        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textTitle = new TextView(GuideActivity.this);
                textTitle.setGravity(Gravity.RIGHT);
                textTitle.setTextSize(24);
                textTitle.setTextAppearance(getApplicationContext(), R.style.boldText);
                textTitle.setTextColor(Color.WHITE);
                textTitle.setText(titleText[0]);
                return textTitle;
            }
        });
        mSubSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textTitle = new TextView(GuideActivity.this);
                textTitle.setGravity(Gravity.RIGHT);
                textTitle.setTextSize(24);
                textTitle.setTextAppearance(getApplicationContext(), R.style.boldText);
                textTitle.setTextColor(Color.WHITE);
                textTitle.setText(titleSubText[0]);
                return textTitle;
            }
        });
        mDetailSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textTitle = new TextView(GuideActivity.this);
                textTitle.setGravity(Gravity.RIGHT);
                textTitle.setTextSize(15);
                textTitle.setTextColor(Color.WHITE);
                textTitle.setText(detailText[0]);
                return textTitle;
            }
        });

        // Declare the in and out animations and initialize them
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        // set the animation type of textSwitcher
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        mSubSwitcher.setInAnimation(in);
        mSubSwitcher.setOutAnimation(out);
        mDetailSwitcher.setInAnimation(in);
        mDetailSwitcher.setOutAnimation(out);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSwitcher.setText(titleText[position]);
                mSubSwitcher.setText(titleSubText[position]);
                mDetailSwitcher.setText(detailText[position]);
                circleChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void circleChanged(int action) {
        switch (action) {
            case 0:
                setButton(btn1, R.drawable.circlewhite);
                setButton(btn2, R.drawable.circleshallow);
                setButton(btn3, R.drawable.circleshallow);
                setButton(btn4, R.drawable.circleshallow);
                break;
            case 1:
                setButton(btn2, R.drawable.circlewhite);
                setButton(btn1, R.drawable.circleshallow);
                setButton(btn3, R.drawable.circleshallow);
                setButton(btn4, R.drawable.circleshallow);
                break;
            case 2:
                setButton(btn3, R.drawable.circlewhite);
                setButton(btn1, R.drawable.circleshallow);
                setButton(btn2, R.drawable.circleshallow);
                setButton(btn4, R.drawable.circleshallow);
                break;
            case 3:
                setButton(btn4, R.drawable.circlewhite);
                setButton(btn1, R.drawable.circleshallow);
                setButton(btn2, R.drawable.circleshallow);
                setButton(btn3, R.drawable.circleshallow);
                break;
        }
    }

    private void setButton(Button btn, int c) {
        btn.setBackgroundResource(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
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
