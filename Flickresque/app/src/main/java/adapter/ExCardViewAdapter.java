package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import don.com.flickresque.R;

/**
 * Created by new on 1/7/15.
 */
public class ExCardViewAdapter extends RecyclerView.Adapter<ExCardViewAdapter.ViewHolder> {

    public String[] mDataset;

    // Constructor
    public ExCardViewAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create Views
    @Override
    public ExCardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_general_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Invoked by the LayoutManger
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.name.setText(mDataset[position].toString());
//        viewHolder.name.setBackgroundResource(R.drawable.fragment_three);
    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView explorePicture;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView)itemLayoutView.findViewById(R.id.photographer_textview);
//            explorePicture = (ImageView)itemLayoutView.findViewById(R.id.cardview_img);
        }
    }
}
