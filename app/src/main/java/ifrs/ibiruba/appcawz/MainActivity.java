package ifrs.ibiruba.appcawz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button acesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acesso = findViewById(R.id.btn_aceesolivre);


        Intent it = new Intent(this, cadastro_not_jog.class);
        startActivity(it);

    }
}
