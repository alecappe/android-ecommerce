package sophia.com.ecommerce2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sophia.com.ecommerce2.data.Item;
import sophia.com.ecommerce2.network.EcommerceService;

public class ProductViewActivity extends AppCompatActivity {
    private EcommerceOpenHelper mDB;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALY);
    private Item item;
    private TextView title;

    private ItemTask mTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        mTask = new ItemTask();
        mTask.execute((Void) null);

        mDB = new EcommerceOpenHelper(this);
        int id =  getIntent().getIntExtra("itemidselected",-1);
        item = mDB.queryItem(id);
        title = (TextView)findViewById(R.id.title_item);

        Button addToCart = (Button)findViewById(R.id.add_to_cart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart.getInstance().addProduct(item);

                Snackbar snackbar = Snackbar.make(title,"Articolo Aggiunto",Snackbar.LENGTH_LONG);
                snackbar.setAction("Annulla", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShoppingCart.getInstance().removeProduct(item);
                    }
                });
                snackbar.show();
            }
        });
    }


    public class ItemTask extends AsyncTask<Void, Void, Item>{
        @Override
        protected Item doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ale-ecommerce.getsandbox.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EcommerceService service = retrofit.create(EcommerceService.class);

            int id =  getIntent().getIntExtra("itemidselected",-1);

            Call<Item> call = service.item(id);

            try{
                Response<Item> response = call.execute();
                if(response.isSuccessful()){
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Item item) {

            TextView title = (TextView)findViewById(R.id.title_item);
            TextView description = (TextView)findViewById(R.id.description_item);
            TextView price = (TextView)findViewById(R.id.price);
            ImageView imagePath = (ImageView)findViewById(R.id.image_item);

            title.setText(item.getName());
            description.setText(item.getDescription());
            price.setText(nf.format(item.getPrice()));
            try{
                Picasso.with(ProductViewActivity.this).load(item.getPhotoItem()).into(imagePath);
            }catch (ArrayIndexOutOfBoundsException ex) {

            }
        }
    }
}
