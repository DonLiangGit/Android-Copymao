package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import don.com.flickresque.R;

public class ImageFragment extends Fragment {

    private Context mContext;
    private EditText mEmail;
    public ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = (ImageView)v.findViewById(R.id.imageFragmentView);
        mContext = v.getContext();
        return v;
    }

}
