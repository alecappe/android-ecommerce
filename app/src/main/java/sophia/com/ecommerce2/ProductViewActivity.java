package sophia.com.ecommerce2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.data.Item;
import sophia.com.ecommerce2.network.EcommerceService;

import static android.R.attr.id;

public class ProductViewActivity extends AppCompatActivity {
    private Item itemSelected;
    private EcommerceOpenHelper mDB;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALIAN);

    private ItemTask mTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

//        mDB = new EcommerceOpenHelper(this);

        mTask = new ItemTask();
        mTask.execute((Void) null);
        //TODO -- da mettere il risultato della richiesta nella view
//        itemSelected = mDB.queryItem(id);

        TextView title = (TextView)findViewById(R.id.title_item);
        TextView description = (TextView)findViewById(R.id.description_item);
        TextView price = (TextView)findViewById(R.id.price);
        ImageView imagePath = (ImageView)findViewById(R.id.image_item);

        title.setText(itemSelected.getName());
        description.setText(itemSelected.getDescription());
        price.setText(nf.format(itemSelected.getPrice()));
//        try{
//            Picasso.with(this).load(itemSelected.getPhotoAtIndex(0)).into(imagePath);
//        }catch (ArrayIndexOutOfBoundsException ex) {
//
//        }
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
    }
}
