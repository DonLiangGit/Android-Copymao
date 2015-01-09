package don.com.customizedeffects;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import don.com.flickresque.R;

/**
 * Created by new on 1/4/15.
 */
public class ParallaxPageTransformer implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            TextView mTitle = (TextView)view.findViewById(R.id.parallaxText);
            mTitle.setTranslationX((float)((position) * (pageWidth / 0.8)));

            TextView mDetails = (TextView) view.findViewById(R.id.parallaxText_details);
            mDetails.setTranslationX((float)((position) * (pageWidth / 0.8)));

            // The 0.5, 1.5, 1.7 values you see here are what makes the view move in a different speed.
            // The bigger the number, the faster the view will translate.
            // The result float is preceded by a minus because the views travel in the opposite direction of the movement.
            view.setAlpha((float)(1 - position));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
