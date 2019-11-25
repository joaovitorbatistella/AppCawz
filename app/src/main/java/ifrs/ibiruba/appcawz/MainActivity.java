package ifrs.ibiruba.appcawz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button acesso;
    TextView txtNoticias, txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acesso = findViewById(R.id.btn_aceesolivre);
        txtNoticias = findViewById(R.id.txtNoticias);
       // txtTeste = findViewById(R.id.txtTeste);



       // Intent it = new Intent(this, cadastro_not_jog.class);
      //  startActivity(it);

    }

    public void semConta(View view){
        Intent principal = new Intent(this, principal.class);
        startActivity(principal);
    }


}
