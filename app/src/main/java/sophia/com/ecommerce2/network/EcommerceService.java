package sophia.com.ecommerce2.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sophia.com.ecommerce2.data.Category;
import sophia.com.ecommerce2.data.ConfirmOrder;
import sophia.com.ecommerce2.data.Item;
import sophia.com.ecommerce2.data.User;
import sophia.com.ecommerce2.data.UserRequest;

/**
 * Created by archimede on 05/07/17.
 */

public interface EcommerceService {

    //http://ecommerce.getsandbox.com/products
    @Headers("Content-Type: application/json")
    @GET("item")
    Call<List<Item>> listItem();

    @POST("login")
    Call<User> login(@Body UserRequest user);


    @Headers("Content-Type: application/json")
    @GET("category")
    Call<List<Category>> listCategory();

    @Headers("Content-Type: application/json")
    @GET("item/{id}")
    Call<Item> item(@Path("id") int id);

    @POST("order")
    Call<ConfirmOrder> confirmOrder(@Body ConfirmOrder order);




}
