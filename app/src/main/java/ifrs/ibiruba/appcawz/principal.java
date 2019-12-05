package ifrs.ibiruba.appcawz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class principal extends AppCompatActivity {

    TextView Titulo1, Titulo2, Titulo3, Titulo4, Titulo5, Data5, Data4, Data3, Data2, Data1, Conteudo5, Conteudo4, Conteudo3, Conteudo2, Conteudo1, Jogo, Adversario, Competicao;
    String parametros;
    String resposta = "";
    TextView txtResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

       parametros = "titulo=teste&corpo_noticia=teste com espaco&dia=10/10/2019";

        Titulo1 = findViewById(R.id.txtTitulo1);
        Titulo2 = findViewById(R.id.txtTitulo2);
        Titulo3 = findViewById(R.id.txtTitulo3);
        Titulo4 = findViewById(R.id.txtTitulo4);
        Titulo5 = findViewById(R.id.txtTitulo5);

        Data1 = findViewById(R.id.txtData1);
        Data2 = findViewById(R.id.txtData2);
        Data3 = findViewById(R.id.txtData3);
        Data4 = findViewById(R.id.txtData4);
        Data5 = findViewById(R.id.txtData5);

        Conteudo1 = findViewById(R.id.txtConteudo1);
        Conteudo2 = findViewById(R.id.txtConteudo2);
        Conteudo3 = findViewById(R.id.txtConteudo3);
        Conteudo4 = findViewById(R.id.txtConteudo4);
        Conteudo5 = findViewById(R.id.txtConteudo5);

        Jogo = findViewById(R.id.txtJogo);
        Competicao = findViewById(R.id.txtCompeticao);
        Adversario = findViewById(R.id.txtAdversario);

        enviaRequisicao();
    }

    public void editar(View view){
        Intent cadastro = new Intent(this, cadastro_not_jog.class);
        startActivity(cadastro);
    }


    public void enviaRequisicao(){
        new MakeNetworkCall().execute("https://cawz.000webhostapp.com/consulta_noticia.php", "Post");
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

        Titulo1.setText(a);

        //this.resposta = a;


        System.out.println(a);
    }

    public void DisplayMessageFinal(String a) {

        if (a != null){

        } else {

        }

        String tudo[] = a.split("§");

        String noticias[] = tudo[0].split("¢");

        String jogo[] = tudo[1].split("¢");
        String jogo1[] = jogo[0].split("£");

        Jogo.setText(jogo1[0]);
        Competicao.setText(jogo1[2]);
        Adversario.setText(jogo1[1]);


        Toast.makeText(this, jogo1[0], Toast.LENGTH_LONG);
        Toast.makeText(this, jogo1[1], Toast.LENGTH_LONG);
        Toast.makeText(this, jogo1[2], Toast.LENGTH_LONG);

        int noticiasTamanho = noticias.length;

        if (noticiasTamanho == 5){

            String noticia1[] = noticias[0].split("£");
            String noticia2[] = noticias[1].split("£");
            String noticia3[] = noticias[2].split("£");
            String noticia4[] = noticias[3].split("£");
            String noticia5[] = noticias[4].split("£");

            Titulo1.setText(noticia1[0]);
            Titulo2.setText(noticia2[0]);
            Titulo3.setText(noticia3[0]);
            Titulo4.setText(noticia4[0]);
            Titulo5.setText(noticia5[0]);

            Data1.setText(noticia1[1]);
            Data2.setText(noticia2[1]);
            Data3.setText(noticia3[1]);
            Data4.setText(noticia4[1]);
            Data5.setText(noticia5[1]);

            Conteudo1.setText(noticia1[2]);
            Conteudo2.setText(noticia2[2]);
            Conteudo3.setText(noticia3[2]);
            Conteudo4.setText(noticia4[2]);
            Conteudo5.setText(noticia5[2]);


            Toast.makeText(this, noticia1[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia4[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia5[0], Toast.LENGTH_LONG);

            Toast.makeText(this, noticia1[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia4[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia5[1], Toast.LENGTH_LONG);

            Toast.makeText(this, noticia1[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia4[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia5[2], Toast.LENGTH_LONG);



        } else if (noticiasTamanho == 4){

            String noticia1[] = noticias[0].split("£");
            String noticia2[] = noticias[1].split("£");
            String noticia3[] = noticias[2].split("£");
            String noticia4[] = noticias[3].split("£");
            //String noticia5[] = noticias[4].split("£");

            Titulo1.setText(noticia1[0]);
            Titulo2.setText(noticia2[0]);
            Titulo3.setText(noticia3[0]);
            Titulo4.setText(noticia4[0]);
            //Titulo5.setText(noticia5[0]);

            Data1.setText(noticia1[1]);
            Data2.setText(noticia2[1]);
            Data3.setText(noticia3[1]);
            Data4.setText(noticia4[1]);
            //Data5.setText(noticia5[1]);

            Conteudo1.setText(noticia1[2]);
            Conteudo2.setText(noticia2[2]);
            Conteudo3.setText(noticia3[2]);
            Conteudo4.setText(noticia4[2]);
            //Conteudo5.setText(noticia5[2]);

            Toast.makeText(this, noticia1[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia4[0], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia4[1], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia4[2], Toast.LENGTH_LONG);



        } else if (noticiasTamanho == 3){

            String noticia1[] = noticias[0].split("£");
            String noticia2[] = noticias[1].split("£");
            String noticia3[] = noticias[2].split("£");
            //String noticia4[] = noticias[3].split("£");
            //String noticia5[] = noticias[4].split("£");

            Titulo1.setText(noticia1[0]);
            Titulo2.setText(noticia2[0]);
            Titulo3.setText(noticia3[0]);
            //Titulo4.setText(noticia4[0]);
            //Titulo5.setText(noticia5[0]);

            Data1.setText(noticia1[1]);
            Data2.setText(noticia2[1]);
            Data3.setText(noticia3[1]);
            //Data4.setText(noticia4[1]);
           // Data5.setText(noticia5[1]);

            Conteudo1.setText(noticia1[2]);
            Conteudo2.setText(noticia2[2]);
            Conteudo3.setText(noticia3[2]);
            //Conteudo4.setText(noticia4[2]);
           // Conteudo5.setText(noticia5[2]);

            Toast.makeText(this, noticia1[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[0], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[1], Toast.LENGTH_LONG);

            Toast.makeText(this, noticia1[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia3[2], Toast.LENGTH_LONG);


        } else if (noticiasTamanho == 2){

            String noticia1[] = noticias[0].split("£");
            String noticia2[] = noticias[1].split("£");
           // String noticia3[] = noticias[2].split("£");
         //   String noticia4[] = noticias[3].split("£");
          //  String noticia5[] = noticias[4].split("£");

            Titulo1.setText(noticia1[0]);
            Titulo2.setText(noticia2[0]);
            //Titulo3.setText(noticia3[0]);
            //Titulo4.setText(noticia4[0]);
           // Titulo5.setText(noticia5[0]);

            Data1.setText(noticia1[1]);
            Data2.setText(noticia2[1]);
           // Data3.setText(noticia3[1]);
            //Data4.setText(noticia4[1]);
           // Data5.setText(noticia5[1]);

            Conteudo1.setText(noticia1[2]);
            Conteudo2.setText(noticia2[2]);
           // Conteudo3.setText(noticia3[2]);
           // Conteudo4.setText(noticia4[2]);
           // Conteudo5.setText(noticia5[2]);

            Toast.makeText(this, noticia1[0], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[0], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[1], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[1], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[2], Toast.LENGTH_LONG);
            Toast.makeText(this, noticia2[2], Toast.LENGTH_LONG);


        } else if (noticiasTamanho == 1){

            String noticia1[] = noticias[0].split("£");
            /*String noticia2[] = noticias[1].split("£");
            String noticia3[] = noticias[2].split("£");
            String noticia4[] = noticias[3].split("£");
            String noticia5[] = noticias[4].split("£");*/

            Titulo1.setText(noticia1[0]);
            /*Titulo2.setText(noticia2[0]);
            Titulo3.setText(noticia3[0]);
            Titulo4.setText(noticia4[0]);
            Titulo5.setText(noticia5[0]);*/

            Data1.setText(noticia1[1]);
            /*Data2.setText(noticia2[1]);
            Data3.setText(noticia3[1]);
            Data4.setText(noticia4[1]);
            Data5.setText(noticia5[1]);*/

            Conteudo1.setText(noticia1[2]);
            /*Conteudo2.setText(noticia2[2]);
            Conteudo3.setText(noticia3[2]);
            Conteudo4.setText(noticia4[2]);
            Conteudo5.setText(noticia5[2]);*/

            Toast.makeText(this, noticia1[0], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[1], Toast.LENGTH_LONG);


            Toast.makeText(this, noticia1[2], Toast.LENGTH_LONG);

        }








        //this.resposta = a;


        System.out.println(a);
    }



    class MakeNetworkCall extends AsyncTask<String, Void, String> {

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

            DisplayMessageFinal(result);
            //Log.d(LOG_TAG, "Result: " + result);
            // return result;
        }
    }




}
