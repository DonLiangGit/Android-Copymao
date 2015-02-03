package don.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import don.com.kamcord.R;
import don.com.pojo.videoClass;

/**
 * Created by new on 1/31/15.
 */
public class videolistadapter extends ArrayAdapter<videoClass> {

    ArrayList<videoClass> videoArrayList;
    LayoutInflater layoutInflater;
    int Resource;
    ViewHolder holder;

    private final Context mContext;

    public videolistadapter(Context context, int resource, ArrayList<videoClass> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        videoArrayList = objects;
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            holder = new ViewHolder();
            v = layoutInflater.inflate(Resource, null);
            holder.itemTitle = (TextView) v.findViewById(R.id.video_title);
            holder.itemUserName = (TextView) v.findViewById(R.id.username);
            holder.thumbNail = (ImageView) v.findViewById(R.id.thumbnail);

            v.setTag(holder);
        } else {
            holder = (ViewHolder)v.getTag();
        }

        // Get POJO Item
        videoClass videoClassItem = super.getItem(position);

        // Set Item TextView
        holder.itemTitle.setText(videoClassItem.getTitle());
        holder.itemUserName.setText(videoClassItem.getUserName());

        Picasso.with(mContext)
                .load(videoClassItem.getThumbnails())
                .placeholder(R.drawable.icon_kamcord)
                .into(holder.thumbNail);

        return v;
    }

    @Override
    public int getCount() {
        if(videoArrayList != null) {
            return videoArrayList.size();
        }
        return 0;
    }

    static class ViewHolder {
        public ImageView thumbNail;
        public TextView itemTitle;
        public TextView itemUserName;
    }
}
