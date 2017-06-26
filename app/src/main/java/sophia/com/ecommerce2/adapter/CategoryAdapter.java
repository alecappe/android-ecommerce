package sophia.com.ecommerce2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sophia.com.ecommerce2.R;
import sophia.com.ecommerce2.data.Category;
import sophia.com.ecommerce2.data.Item;

/**
 * Created by archimede on 06/06/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<Category> mDataSet;
    private Context context;
    private OnAdapterItemClickListener listener;


    public CategoryAdapter(Context context, List<Category> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
        this.listener = (OnAdapterItemClickListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row_adapter, parent, false);
        TextView titleView = (TextView) v.findViewById(R.id.category);
        TextView subtitleView = (TextView) v.findViewById(R.id.subtitle_category);
        ImageView imageView = (ImageView) v.findViewById(R.id.image_category);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListner", v.getTag().toString());
                if(listener != null){
                    listener.OnItemClick((int)v.getTag());

                }
            }
        });

        ViewHolder vh = new ViewHolder(v, titleView, subtitleView, imageView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mDataSet.get(position).getTitle());
        holder.subTitle.setText(mDataSet.get(position).getSubTitle());
        //setimage
        try{
            Picasso.with(context).load(mDataSet.get(position).getPhotoAtIndex(0)).into(holder.imageView);
        }catch (ArrayIndexOutOfBoundsException ex) {

        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subTitle;
        public ImageView imageView;

        public ViewHolder(View v, TextView title, TextView subtitle, ImageView imageView) {
            super(v);
            this.title = title;
            this.subTitle = subtitle;
            this.imageView = imageView;

        }
    }

}
