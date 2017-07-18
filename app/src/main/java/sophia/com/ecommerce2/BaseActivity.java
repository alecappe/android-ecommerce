package sophia.com.ecommerce2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by archimede on 18/07/17.
 */

public class BaseActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cart:{
                Intent i = new Intent(this, ShoppingCartActivity.class);
                startActivity(i);
                return true;
            }
            case R.id.logout:{
                Intent i = new Intent(this, LoginActivity.class);

                preferences = getSharedPreferences("ecommerce", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("userId", -1);
                editor.apply();

                startActivity(i);
                finish();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
