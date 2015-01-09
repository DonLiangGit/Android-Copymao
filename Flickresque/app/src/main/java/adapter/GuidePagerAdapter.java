package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import fragment.FragmentOne;
import fragment.FragmentThree;
import fragment.FragmentTwo;

/**
 * Created by new on 1/4/15.
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> Fragments = new ArrayList<>();

    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
        Fragments.add(new FragmentOne());
        Fragments.add(new FragmentTwo());
        Fragments.add(new FragmentThree());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return Fragments.get(0);
            case 1: return Fragments.get(1);
            case 2: return Fragments.get(2);
        }
        return Fragments.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
