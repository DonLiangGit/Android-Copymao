package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import don.com.flickresque.R;
import pojo.CategoryItem;

/**
 * Created by new on 2/28/15.
 */
public class CategoryListAdapter extends BaseAdapter {

    private Context mContext;
    private Activity mActivity;
    private LayoutInflater inflater;
    private ArrayList<CategoryItem> mArrayList;

    public CategoryListAdapter(Activity activity, Context context, ArrayList<CategoryItem> arrayList) {
        this.mContext = context;
        this.mActivity = activity;
        this.mArrayList = arrayList;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(inflater == null || convertView == null) {
            inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_list_item, null);
        }
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.categoryImageView);
            imageView.setImageResource(mArrayList.get(position).getimgId());
            Matrix matrix = imageView.getImageMatrix();
//            matrix.postTranslate(0, -100);
            imageView.setImageMatrix(matrix);

            imageView.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

            TextView textView = (TextView) convertView.findViewById(R.id.catetextView);
            String strTitle = mArrayList.get(position).getCategoryItemTitle();
            Spannable spannaTitle = new SpannableString(strTitle);
            spannaTitle.setSpan(new BackgroundColorSpan(Color.parseColor("#CC000000")),0, strTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannaTitle);

            TextView itemDetailTextView = (TextView) convertView.findViewById(R.id.catedetailstextView);
            String str = mArrayList.get(position).getCategoryItemDetails();
            Spannable spanna = new SpannableString(str);
            spanna.setSpan(new BackgroundColorSpan(Color.parseColor("#CC000000")),0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            itemDetailTextView.setText(spanna);

            viewHolder = new ViewHolder();
            viewHolder.imageBGView = imageView;
            viewHolder.categoryTitle = textView;
            viewHolder.categoryDetails = itemDetailTextView;

//            viewHolder.imageBGView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent event) {
//                    switch ((event.getAction())) {
//                        case MotionEvent.ACTION_DOWN:
//                            imageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
//                        case MotionEvent.ACTION_UP:
//                            imageView.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
//                        default:
//                            break;
//                    }
//                    return true;
//                }
//            });
            convertView.setTag(viewHolder);


        viewHolder = (ViewHolder) convertView.getTag();
        return convertView;
    }

    private static class ViewHolder {
        private ImageView imageBGView;
        private TextView categoryTitle;
        private TextView categoryDetails;
    }
}
