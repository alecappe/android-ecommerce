package sophia.com.ecommerce2;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ProductListAdapter;
import sophia.com.ecommerce2.data.Item;

import static sophia.com.ecommerce2.R.layout.product_row_adapter;

public class ProductListActivity extends AppCompatActivity implements OnAdapterItemClickListener{
    private RecyclerView productRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        //String title =  getIntent().getStringExtra("title");
        //setTitle(title);
        RecyclerView productRecyclerView = (RecyclerView) findViewById(R.id.product_listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        String photo = "http://lorempixel.com/400/200/abstract/2";

        List<Item> itemList = new ArrayList<>();
        for(int i=0; i<10; i++){
            itemList.add(new Item("Item di prova per Vincenzo Nano " + i , "description di prova " + i, 5.00, photo));
        }

        ProductListAdapter productListAdapter = new ProductListAdapter(this, itemList);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setAdapter(productListAdapter);
    }

    @Override
    public void OnItemClick(int position) {

    }
}
