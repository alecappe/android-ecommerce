package sophia.com.ecommerce2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SampleTaskActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_task);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        AsyncTaskSample sample = new AsyncTaskSample();
        sample.execute("sample async task start");
    }

    public class AsyncTaskSample extends AsyncTask<String, Integer, Object> {
        @Override
        protected Object doInBackground(String... params) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return params;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent i = new Intent(SampleTaskActivity.this, MainActivity.class);
            startActivity(i);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0].intValue());
        }
    }
}
