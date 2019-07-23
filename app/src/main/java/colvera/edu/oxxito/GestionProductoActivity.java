package colvera.edu.oxxito;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import colvera.edu.oxxito.dao.ProductoDAO;
import colvera.edu.oxxito.model.Producto;

public class GestionProductoActivity  extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtPrecio;
    private EditText txtExistencias;
    private EditText txtFechaCaducidad;
    private Button btnActualizar;
    private Button btnCancelar;
    private Button btnEliminar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_producto);
        txtCodigo = (EditText)findViewById(R.id.txt_codigo);
        txtNombre = (EditText)findViewById(R.id.txt_nombre);
        txtPrecio = (EditText)findViewById(R.id.txt_precio);
        txtExistencias = (EditText)findViewById(R.id.txt_existencias);
        txtFechaCaducidad = (EditText)findViewById(R.id.txt_fecha_caducidad);
        btnActualizar = (Button)findViewById(R.id.btn_actualizar);
        btnCancelar = (Button)findViewById(R.id.btn_cancelar);
        btnEliminar = (Button)findViewById(R.id.btn_eliminar);

        String codigo = getIntent().getExtras().getString("codigo");
        Producto p = new Producto();
        p.setCodigo(codigo);
        ProductoDAO dao = new ProductoDAO(getApplicationContext());
        try{
            p = dao.getById(p);
            txtCodigo.setText(p.getCodigo());
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(""+p.getPrecio());
            txtExistencias.setText(p.getExistencias());
            txtFechaCaducidad.setText(p.getFechaCaducidad());

        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: "
                    + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto();
                p.setCodigo(txtCodigo.getText().toString());
                p.setNombre(txtNombre.getText().toString());
                p.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
                p.setExistencias(Integer.valueOf(txtExistencias.getText().toString()));
                p.setFechaCaducidad(txtFechaCaducidad.getText().toString());

                ProductoDAO dao = new ProductoDAO(getApplicationContext());
                try{
                    dao.update(p);
                    Toast.makeText(getApplicationContext(), "Producto actualizado",
                            Toast.LENGTH_LONG).show();
                    System.exit(0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto();
                p.setCodigo(txtCodigo.getText().toString());
                ProductoDAO dao = new ProductoDAO(getApplicationContext());
                try{
                    dao.delete(p);
                    Toast.makeText(getApplicationContext(), "Producto Eliminado",
                            Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
