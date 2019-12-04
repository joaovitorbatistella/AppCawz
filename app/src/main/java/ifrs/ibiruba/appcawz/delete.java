package ifrs.ibiruba.appcawz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class delete extends AppCompatActivity {

    String parametros="";
    String resposta="";
    String resulta="";
    TextView Titulo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Titulo1 = findViewById(R.id.titulo1);
        parametros = "titulo=teste&corpo_noticia=teste com espaco&dia=10/10/2019";
    }

    public void enviaRequisicao(){
        new delete.MakeNetworkCall().execute("https://cawz.000webhostapp.com/consulta_delete.php", "Post");
    }

    InputStream ByPostMethod(String ServerURL) {

        InputStream DataInputStream = null;
        try {

            //Post parameters
            String PostParam = parametros;

            //Preparing
            URL url = new URL(ServerURL);

            HttpURLConnection cc = (HttpURLConnection)
                    url.openConnection();
            //set timeout for reading InputStream
            cc.setReadTimeout(5000);
            // set timeout for connection
            cc.setConnectTimeout(5000);
            //set HTTP method to POST
            cc.setRequestMethod("POST");
            //set it to true as we are connecting for input
            cc.setDoInput(true);
            //opens the communication link
            cc.connect();

            //Writing data (bytes) to the data output stream
            DataOutputStream dos = new DataOutputStream(cc.getOutputStream());
            dos.writeBytes(PostParam);
            //flushes data output stream.
            dos.flush();
            dos.close();

            //Getting HTTP response code
            int response = cc.getResponseCode();

            //if response code is 200 / OK then read Inputstream
            //HttpURLConnection.HTTP_OK is equal to 200
            if(response == HttpURLConnection.HTTP_OK) {
                DataInputStream = cc.getInputStream();
            }

        } catch (Exception e) {
            // Log.e(LOG_TAG, "Error in GetData", e);
        }
        return DataInputStream;

    }

    String ConvertStreamToString(InputStream stream) {

        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();

        String line = null;
        try {

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } catch (IOException e) {
            //Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } catch (Exception e) {
            // Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } finally {

            try {
                stream.close();
            } catch (IOException e) {
                // Log.e(LOG_TAG, "Error in ConvertStreamToString", e);

            } catch (Exception e) {
                //Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
            }
        }
        return response.toString();


    }

    @Override
    public String toString() {
        return resposta;
    }

    public void DisplayMessage(String a) {

        // TxtResult = findViewById(R.id.response);

        /*Titulo1.setText(a);

        //this.resposta = a;


        System.out.println(a);*/

        String tudo[] = a.split("¢");
        String noticias1[] = tudo[0].split("£");
        String noticias2[] = tudo[1].split("£");
        String noticias3[] = tudo[2].split("£");
        String noticias4[] = tudo[3].split("£");
        String noticias5[] = tudo[4].split("£");
        Toast.makeText(this, noticias1[0], Toast.LENGTH_LONG);
        Toast.makeText(this, noticias2[1], Toast.LENGTH_LONG);

        resulta="" + noticias1[0]+ " " + " " + noticias1[1];

        Titulo1.setText(resulta);
    }



    private class MakeNetworkCall extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DisplayMessage("Please Wait ...");
        }

        @Override
        protected String doInBackground(String... arg) {

            InputStream is = null;
            String URL = arg[0];
            // Log.d(LOG_TAG, "URL: " + URL);
            String res = "";


            if (arg[1].equals("Post")) {

                is = ByPostMethod(URL);

            }

            if (is != null) {
                res = ConvertStreamToString(is);
            } else {
                res = "Something went wrong";
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            result = String.valueOf(result);

            DisplayMessage(result);
            //Log.d(LOG_TAG, "Result: " + result);
            // return result;
        }
    }

}
