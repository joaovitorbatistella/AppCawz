package ifrs.ibiruba.appcawz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button acesso;
    TextView txtNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acesso = findViewById(R.id.btn_aceesolivre);
        txtNoticias = findViewById(R.id.txtNoticias);



       // Intent it = new Intent(this, cadastro_not_jog.class);
      //  startActivity(it);

    }

    public void testeHTTP(View view){
        ReqHttp requisicao = new ReqHttp("https://cawz.000webhostapp.com/consulta_noticia.php", "titulo=teste&corpo_noticia=teste com espaco&dia=10/10/2019");

       // txtNoticias.setText(requisicao.resposta);
    }
}
