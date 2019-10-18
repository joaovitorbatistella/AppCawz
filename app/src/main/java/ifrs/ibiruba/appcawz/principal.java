package ifrs.ibiruba.appcawz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class principal extends AppCompatActivity {

    TextView Titulo1, Titulo2, Titulo3, Titulo4, Titulo5, Data5, Data4, Data3, Data2, Data1, Conteudo5, Conteudo4, Conteudo3, Conteudo2, Conteudo1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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
    }

    public void testeHTTP(View view){
        ReqHttp requisicao = new ReqHttp("https://cawz.000webhostapp.com/consulta_noticia.php", "");

        Titulo1.setText(requisicao.resposta);
    }


}
