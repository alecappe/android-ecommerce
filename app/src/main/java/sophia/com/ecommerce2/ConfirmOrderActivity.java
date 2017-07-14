package sophia.com.ecommerce2;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sophia.com.ecommerce2.data.ConfirmOrder;
import sophia.com.ecommerce2.data.Item;
import sophia.com.ecommerce2.network.EcommerceService;

public class ConfirmOrderActivity extends AppCompatActivity {
    private ConfirmOrder order;
    private String name;
    private String surname;
    private String address;
    private String city;
    private Button confirmOrder;
    private EditText readName;
    private EditText readSurname;
    private EditText readAddress;
    private EditText readCity;
    private TextView responce;
    private OrdersTask mTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        responce = (TextView)findViewById(R.id.responce);
        responce.setVisibility(View.INVISIBLE);



        readName = (EditText)findViewById(R.id.name);
        readSurname = (EditText)findViewById(R.id.surname);
        readAddress = (EditText)findViewById(R.id.address);
        readCity = (EditText)findViewById(R.id.city);

        confirmOrder = (Button)findViewById(R.id.confirm_order);
        order = new ConfirmOrder();

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(readCity.getWindowToken(), 0);

                name = readName.getText().toString();
                surname = readSurname.getText().toString();
                address = readAddress.getText().toString();
                city = readCity.getText().toString();

                order.setName(name);
                order.setSurname(surname);
                order.setAddress(address);
                order.setCity(city);
                List<Item> listItem = ShoppingCart.getInstance().getCart();
                order.setCart(listItem);


                mTask = new OrdersTask();
                mTask.execute(order);



            }
        });


    }

    public class OrdersTask extends AsyncTask<ConfirmOrder, Void, Boolean> {
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){

                Intent i = new Intent(ConfirmOrderActivity.this, OrderDone.class);
                i.putExtra("name", name);
                i.putExtra("surname", surname);
                i.putExtra("address", address);
                i.putExtra("city", city);

                startActivity(i);
            }
            else{
                Snackbar snackbar = Snackbar.make(responce, "ERRORE",-1);
                snackbar.show();
            }
        }

        @Override
        protected Boolean doInBackground(ConfirmOrder... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ale-ecommerce.getsandbox.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EcommerceService service = retrofit.create(EcommerceService.class);


            Call<ConfirmOrder> orderCall = service.confirmOrder(params[0]);

            try{
                Response<ConfirmOrder> orderResponse = orderCall.execute();
                if (orderResponse.isSuccessful()) {
                    return true;
                }
                else{
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
