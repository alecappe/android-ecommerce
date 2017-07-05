package sophia.com.ecommerce2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ProductListAdapter;
import sophia.com.ecommerce2.data.Item;
import sophia.com.ecommerce2.network.EcommerceService;

import static sophia.com.ecommerce2.R.layout.product_row_adapter;

public class ProductListActivity extends AppCompatActivity implements OnAdapterItemClickListener{
    private RecyclerView productRecyclerView;
    private EcommerceOpenHelper mDB;
    private List<Item> itemList;

    private ItemsTask mTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //Esegue l' ItemTask all'avvio dell'Intent
        mTask = new ItemsTask();
        mTask.execute((Void) null);


//        mDB = new EcommerceOpenHelper(this);

        //String title =  getIntent().getStringExtra("title");
        //setTitle(title);
        productRecyclerView = (RecyclerView) findViewById(R.id.product_listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        int categoryId =  getIntent().getIntExtra("CategoryId",-1);

//        itemList = mDB.getAllItem(categoryId);

//        ProductListAdapter productListAdapter = new ProductListAdapter(this, itemList);
       productRecyclerView.setHasFixedSize(true);
//        productRecyclerView.setAdapter(productListAdapter);
    }

    @Override
    public void OnItemClick(int position) {
        Log.d("click item", String.valueOf(position));
        //INTENT
        int id = itemList.get(position).getmId();
        Intent i = new Intent(this, ProductViewActivity.class);

        i.putExtra("itemidselected", id);
        startActivity(i);
    }

    public class ItemsTask extends AsyncTask<Void, Void, List<Item>>{
        @Override
        protected List<Item> doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ale-ecommerce.getsandbox.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EcommerceService service = retrofit.create(EcommerceService.class);

            Call<List<Item>> listCall = service.listItem();

            try{
                Response<List<Item>> listResponse = listCall.execute();
                if(listResponse.isSuccessful()){
                    return listResponse.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            if (items == null) return;

            itemList = items;
            ProductListAdapter productListAdapter = new ProductListAdapter(ProductListActivity.this, itemList);
            productRecyclerView.setAdapter(productListAdapter);
        }
    }
}
