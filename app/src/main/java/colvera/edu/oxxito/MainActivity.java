package colvera.edu.oxxito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import colvera.edu.oxxito.dao.Conexion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister, btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnRegister = findViewById(R.id.btn_register);

        // Register listenner
        btnStart.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            try {
                Conexion conn = new Conexion(getApplication());
                conn.inicializarBD();
                Toast.makeText(getApplicationContext(), "DB Starting...", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else if(v.getId() == R.id.btn_register){
            Intent iproduct = new Intent(getApplicationContext(), ListaProductosActivity.class);
            startActivity(iproduct);
        }
    }
}
