package mehmet.tdk;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mehmet on 20.09.2015.
 */
public class TdkAsyncTask extends AsyncTask<String, Void, String>{

    private Callback c;
    private String keyword;

    public  TdkAsyncTask(Callback cb, String key){
        this.c = cb;
        this.keyword = key;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection connection;
        InputStream inputStream;
        String response = "";

        if(keyword != null && keyword != ""){
            try {
                url = new URL("http://www.tdk.gov.tr/index.php?option=com_gts&kelime="+keyword);
                connection = (HttpURLConnection) url.openConnection();
                inputStream = new BufferedInputStream(connection.getInputStream());

                if(inputStream != null){
                    response = convertInputStreamToString(inputStream);
                }

                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }


        return "Error";
    }

    @Override
    protected void onPostExecute(String result){
        c.onResult(result);
    }

    @Override
    protected  void  onProgressUpdate(Void... voids){
        c.onProgress();
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

}
