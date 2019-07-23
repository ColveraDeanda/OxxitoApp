package colvera.edu.oxxito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import colvera.edu.oxxito.dao.ProductoDAO;
import colvera.edu.oxxito.model.Producto;

public class NuevoProductoActivity extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtProducto;
    private EditText txtPrecio;
    private EditText txtExistencias;
    private EditText txtFechaCaducidad;
    private Button btnGuardar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inflate
        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        txtProducto = (EditText)findViewById(R.id.txtProducto);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);
        txtExistencias = (EditText)findViewById(R.id.txtExistencias);
        txtFechaCaducidad = (EditText)findViewById(R.id.txtFechaCaducidad);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto();
                p.setCodigo(txtCodigo.getText().toString());
                p.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
                p.setExistencias(Integer.valueOf(txtExistencias.getText().toString()));
                p.setFechaCaducidad(txtFechaCaducidad.getText().toString());
                ProductoDAO dao = new ProductoDAO(getApplicationContext());

                try{
                    dao.insert(p);
                    Toast.makeText(getApplicationContext(), "Producto guardado!!", Toast.LENGTH_LONG).show();
                    System.exit(0);
                }catch (Exception e ){
                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(),
                        "Guardado", Toast.LENGTH_LONG).show();
                limpiarRegistro();
                System.exit(0);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Cancelando...", Toast.LENGTH_LONG).show();
                limpiarRegistro();
                System.exit(0);
            }
        });
    }

    public void limpiarRegistro(){
        txtCodigo.setText("");
        txtFechaCaducidad.setText("");
        txtExistencias.setText("");
        txtPrecio.setText("");
        txtProducto.setText("");
    }
}
