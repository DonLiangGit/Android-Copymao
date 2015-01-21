package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import don.com.flickresque.ImageDetailActivity;
import don.com.flickresque.R;
import pojo.Photo;
import util.Constants;

/**
 * Created by new on 1/7/15.
 */
public class ExCardViewAdapter extends RecyclerView.Adapter<ExCardViewAdapter.ViewHolder> {

    private final Context mContext;
    private List<Photo> mPhotos;

    // Constructor
    public ExCardViewAdapter(Context context, List<pojo.Photo> photos) {
        mContext = context;
        setPhotos(photos);
    }

    public pojo.Photo getItem(int position) {
        return mPhotos.get(position);
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
        final Photo item = getItem(position);
        // Load the data into the views from the PhotoViewHolder
        Picasso.with(mContext).load(item.getFlickrPhotoUrl().getLargeUrl()).into(viewHolder.explorePicture);
        viewHolder.owner.setText(item.getOwner());
        viewHolder.name.setText(item.getTitle());

        viewHolder.explorePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ImageDetailActivity.class);
                i.putExtra(Constants.KEY_IMAGE_URL, item.getFlickrPhotoUrl().getLargeUrl());
                view.getContext().startActivity(i);
            }
        });

    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView owner;
        public ImageView explorePicture;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView)itemLayoutView.findViewById(R.id.title_textview);
            owner = (TextView)itemLayoutView.findViewById(R.id.photographer_textview);
            explorePicture = (ImageView)itemLayoutView.findViewById(R.id.imageView);

        }
    }

    public void setPhotos(List<Photo> photos) {
        if (photos == null) {
            this.mPhotos = new ArrayList<Photo>(0);
        } else {
            this.mPhotos = photos;
        }
        notifyDataSetChanged();
    }
}
