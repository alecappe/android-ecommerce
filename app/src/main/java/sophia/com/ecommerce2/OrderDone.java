package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import sophia.com.ecommerce2.adapter.ShoppingCartAdapter;
import sophia.com.ecommerce2.data.Item;

public class OrderDone extends AppCompatActivity {
    private String name;
    private String surname;
    private String address;
    private String city;
    private List<Item> cart;

    private RecyclerView shoppingCartRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_done);

        TextView userName = (TextView)findViewById(R.id.user);
        TextView userAddress = (TextView)findViewById(R.id.address);
        name = getIntent().getStringExtra("name");
        surname = getIntent().getStringExtra("surname");
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        cart = ShoppingCart.getInstance().getCart();
        userName.setText(name + " " + surname + " il tuo ordine è avvenuto con successo ");
        userAddress.setText("e sarà spedito all'indirizzo " + address + " " + city);

        shoppingCartRecyclerView = (RecyclerView)findViewById(R.id.order_recycler);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager((this));
        shoppingCartRecyclerView.setLayoutManager(mLayoutManager);
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this, false);
        shoppingCartRecyclerView.setAdapter(shoppingCartAdapter);

    }
}
