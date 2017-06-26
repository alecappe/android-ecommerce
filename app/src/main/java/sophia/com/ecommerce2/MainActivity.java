package sophia.com.ecommerce2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.data.Category;

public class MainActivity extends AppCompatActivity implements OnAdapterItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private RecyclerView categoryRecyclerView;
    private TextView welcomeMessage;
    List<Category> categoryList = new ArrayList<>();

    private SharedPreferences preferences;

    private EcommerceOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new EcommerceOpenHelper(this);

        preferences = getSharedPreferences("ecommerce",MODE_PRIVATE);

        TextView welcomeMessage = (TextView)findViewById(R.id.welcome_message);
        boolean b = preferences.getBoolean("firstUser",true);

        if (b){
            welcomeMessage.setVisibility(View.VISIBLE);
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstUser",true);
        editor.apply();

        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.recycler_view),
                "BENVENUTO!!!", Snackbar.LENGTH_LONG);

        mySnackbar.setDuration(3000);
        mySnackbar.show();

        categoryRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager((this));
        categoryRecyclerView.setLayoutManager((mLayoutManager));


        categoryList = mDB.getAllCategory();

        categoryRecyclerView.setHasFixedSize(true);

        Context context;
        CategoryAdapter categoryRecyclerViewAdapter = new CategoryAdapter(this, categoryList);

        categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
    }


    @Override
    public void OnItemClick(int position) {
        Log.d("click", String.valueOf(position));
        String title = categoryList.get(position).getTitle();

        Intent i = new Intent(this, ProductListActivity.class);

        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Snackbar snackbar = Snackbar.make(categoryRecyclerView, "property changed" + key, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    public void buttonOnClick(View view) {
        SharedPreferences preferences = getSharedPreferences("ecommerce", MODE_PRIVATE);
        boolean b = preferences.getBoolean("firstUser",true);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstUser", !b);
        editor.apply();





    }
}
