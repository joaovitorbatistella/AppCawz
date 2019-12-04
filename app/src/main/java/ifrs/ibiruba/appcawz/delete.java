package ifrs.ibiruba.appcawz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class delete extends AppCompatActivity {

    String parametros="";
    String resposta="";
    String resulta1="";
    String resulta2="";
    String resulta3="";
    String resulta4="";
    String resulta5="";
    TextView Title1, Title2, Title3, Title4, Title5;
    ListView listView;
    List<String> noticias;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        listView = findViewById(R.id.listView);


        parametros = "titulo=teste&corpo_noticia=teste com espaco&dia=10/10/2019";

        enviaRequisicao();
        //System.out.println("OKOKOK");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noticia = (String) parent.getItemAtPosition(position);
                //parametros = "titulo=" +noticia;
                //enviaRequisicaoDelete();
                Toast.makeText(delete.this, noticia, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void enviaRequisicao(){
        new delete.MakeNetworkCall().execute("https://cawz.000webhostapp.com/consulta_delete.php", "Post");
    }
    public void enviaRequisicaoDelete(){
        new delete.MakeNetworkCall().execute("https://cawz.000webhostapp.com/exclui_not.php", "Post");
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
        Toast.makeText(this,"Excluido", Toast.LENGTH_LONG);

            Intent principal = new Intent(this, delete.class);
            startActivity(principal);


    }

    public void DisplayMessageListView(String a) {
        //Toast.makeText(this,a, Toast.LENGTH_LONG);



        // TxtResult = findViewById(R.id.response);

        /*Titulo1.setText(a);

        //this.resposta = a;


        System.out.println(a);*/

        String tudo[] = a.split("¢");
        Toast.makeText(this,a, Toast.LENGTH_LONG);


        int qnt = tudo.length;

        Toast.makeText(this, String.valueOf(qnt), Toast.LENGTH_LONG);

        noticias = new ArrayList<String>();
        for(int i=0; i<qnt; i++){
            String noticia[] = tudo[i].split("£");
           //if (noticia[1] != null)
                noticias.add(noticia[1]);


        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noticias);
        listView.setAdapter(adapter);


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



            DisplayMessageListView(result);
            //Log.d(LOG_TAG, "Result: " + result);
            // return result;
        }
    }




}
