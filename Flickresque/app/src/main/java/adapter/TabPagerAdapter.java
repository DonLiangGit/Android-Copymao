package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.AboutFrag;
import fragment.InterestFrag;
import fragment.RecentFrag;

/**
 * Created by new on 3/3/15.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                return new InterestFrag();
            case 1:
                return new RecentFrag();
            case 2:
                return new AboutFrag();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
