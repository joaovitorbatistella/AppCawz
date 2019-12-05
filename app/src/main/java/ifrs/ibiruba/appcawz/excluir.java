package ifrs.ibiruba.appcawz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class excluir extends AppCompatActivity {
    String parametros="";
    String resposta="";
    TextView Tteste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        Tteste = findViewById(R.id.tituloteste);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String noticia = extras.getString("noticia");
            parametros = "titulo=" + noticia;

            enviaRequisicao();
        }


    }



    public void enviaRequisicao(){
        new excluir.MakeNetworkCall().execute("https://cawz.000webhostapp.com/exclui_not.php", "Post");
    }


    public void voltar(View view){
        Intent voltar = new Intent(excluir.this, principal.class);
        startActivity(voltar);
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
        Toast.makeText(this, a, Toast.LENGTH_LONG);
        Tteste.setText(a);

        //this.resposta = a;


        System.out.println(a);

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



            DisplayMessage(result);
            //Log.d(LOG_TAG, "Result: " + result);
            // return result;
        }
    }


}
