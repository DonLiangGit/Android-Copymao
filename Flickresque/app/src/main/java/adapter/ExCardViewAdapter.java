package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import don.com.flickresque.R;
import pojo.Photo;
import util.OnItemClickListener;

/**
 * Created by new on 1/7/15.
 */
public class ExCardViewAdapter extends RecyclerView.Adapter<ExCardViewAdapter.ViewHolder> {

    private final Context mContext;
    private List<Photo> mPhotos;
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    static OnItemClickListener mItemClickListener;

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
        ViewHolder viewHolder = null;
        View itemLayoutView = null;
        itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_general_row, null);
        viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    // Invoked by the LayoutManger
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Photo item = getItem(position);
        // Load the data into the views from the PhotoViewHolder
        switch (viewHolder.getItemViewType()) {
            case TYPE_ONE:
                Picasso.with(mContext).load(item.getFlickrPhotoUrl().getLargeUrl()).into(viewHolder.explorePicture);
            case TYPE_TWO:
                Picasso.with(mContext).load(item.getFlickrPhotoUrl().getLargeUrl()).into(viewHolder.explorePicture);
        }
//        viewHolder.name.setText(item.getTitle());

    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(position % 2 == 0) {
            viewType = TYPE_ONE;
        } else {
            viewType = TYPE_TWO;
        }
        return viewType;
    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView owner;
        public ImageView explorePicture;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
//            name = (TextView)itemLayoutView.findViewById(R.id.title_textview);
//            owner = (TextView)itemLayoutView.findViewById(R.id.photographer_textview);
            explorePicture = (ImageView)itemLayoutView.findViewById(R.id.imageView);
//            explorePicture.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(view.getContext(), ImageDetailActivity.class);
//                    i.putExtra(Constants.KEY_IMAGE_URL, item.getFlickrPhotoUrl().getLargeUrl());
//                    view.getContext().startActivity(i);
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

   public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
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


