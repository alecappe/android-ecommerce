package sophia.com.ecommerce2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sophia.com.ecommerce2.R;
import sophia.com.ecommerce2.ShoppingCart;
import sophia.com.ecommerce2.data.Item;

/**
 * Created by archimede on 12/07/17.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>{
    private Context context;
    private OnAdapterItemClickListener listener;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALY);
    private boolean showRemoveButton;

    public ShoppingCartAdapter(Context context, boolean showRemoveButton) {
        this.context = context;
        this.showRemoveButton = showRemoveButton;
    }

    public ShoppingCartAdapter(Context context) {
        this.showRemoveButton = true;
        this.context = context;
        this.listener = (OnAdapterItemClickListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_cart_adapter, parent, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.item_image);
        TextView itemView = (TextView) v.findViewById(R.id.item_name);
        TextView priceView = (TextView) v.findViewById(R.id.item_price);
        Button removeItem = (Button) v.findViewById(R.id.remove_item);



        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListner", v.getTag().toString());
                if(listener != null){
                    listener.OnItemClick((int)v.getTag());

                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.OnItemRemoveToCart((int)v.getTag());
                    notifyDataSetChanged();

                }
            }
        });

        ViewHolder vh = new ViewHolder(v, itemView, priceView, imageView, removeItem);
        if (showRemoveButton)
            vh.removeItem.setVisibility(View.VISIBLE);
        else
            vh.removeItem.setVisibility(View.GONE);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item.setText(ShoppingCart.getInstance().getCart().get(position).getName());
        holder.price.setText(nf.format(ShoppingCart.getInstance().getCart().get(position).getPrice()));
        //setimage
        try{
            Picasso.with(context).load(ShoppingCart.getInstance().getCart().get(position).getPhotoAtIndex(0)).into(holder.imageView);
        }catch (ArrayIndexOutOfBoundsException ex) {

        }
        holder.itemView.setTag(position);
        holder.removeItem.setTag(position);
    }

    @Override
    public int getItemCount() {
        return ShoppingCart.getInstance().getCart().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item;
        public TextView price;
        public ImageView imageView;
        public Button removeItem;

        public ViewHolder(View itemView, TextView item, TextView price, ImageView imageView, Button removeItem) {
            super(itemView);
            this.item = item;
            this.price = price;
            this.imageView = imageView;
            this.removeItem = removeItem;
        }
    }
}
