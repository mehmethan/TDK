package mehmet.tdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeSearch(View v){

        EditText keywordText = (EditText) findViewById(R.id.keyword);


        TdkAsyncTask asyncTask = new TdkAsyncTask(c, keywordText.getText().toString());
        asyncTask.execute();
    }

    final Callback c = new Callback() {
        @Override
        public void onProgress() {

        }

        @Override
        public void onResult(String http_result) {
            TextView resultText = (TextView) findViewById(R.id.result);

            Document document = Jsoup.parse(http_result);
            resultText.setText(Html.fromHtml(document.select("#hor-minimalist-a").html()));
        }
    };
}
