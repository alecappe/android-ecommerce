package sophia.com.ecommerce2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ShoppingCartAdapter;
import sophia.com.ecommerce2.data.Item;

public class ShoppingCartActivity extends AppCompatActivity implements OnAdapterItemClickListener {
    private List<Item> itemList;
    private RecyclerView shoppingCartRecyclerView;
    private static NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALY);


    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        totalPrice = (TextView)findViewById(R.id.total_price);
        itemList = ShoppingCart.getInstance().getCart();

        shoppingCartRecyclerView = (RecyclerView)findViewById(R.id.shopping_cart_recycler);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager((this));
        shoppingCartRecyclerView.setLayoutManager(mLayoutManager);
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this);
        shoppingCartRecyclerView.setAdapter(shoppingCartAdapter);

        totalPrice.setText(String.valueOf(nf.format(ShoppingCart.getInstance().totalCart())));

    }


    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void OnItemAddToCart(int position) {

    }

    @Override
    public void OnItemRemoveToCart(int position) {
        ShoppingCart.getInstance().removeProduct(itemList.get(position));
        totalPrice.setText(String.valueOf(nf.format(ShoppingCart.getInstance().totalCart())));


    }

    public void buttonBuyClick(View view) {
        Intent i = new Intent(this, ConfirmOrderActivity.class);
        startActivity(i);
    }
}
