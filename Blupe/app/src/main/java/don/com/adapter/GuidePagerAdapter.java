package don.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import don.com.fragment.FragmentFour;
import don.com.fragment.FragmentOne;
import don.com.fragment.FragmentThree;
import don.com.fragment.FragmentTwo;

/**
 * Created by new on 2/17/15.
 */
public class GuidePagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> Fragments = new ArrayList<>();

    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
        Fragments.add(new FragmentOne());
        Fragments.add(new FragmentTwo());
        Fragments.add(new FragmentThree());
        Fragments.add(new FragmentFour());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return Fragments.get(0);
            case 1: return Fragments.get(1);
            case 2: return Fragments.get(2);
            case 3: return Fragments.get(3);
        }
        return Fragments.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
