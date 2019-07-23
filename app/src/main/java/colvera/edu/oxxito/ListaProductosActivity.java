package colvera.edu.oxxito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import colvera.edu.oxxito.dao.ProductoDAO;
import colvera.edu.oxxito.model.Producto;

public class ListaProductosActivity extends AppCompatActivity {

    private Button btnNuevo;
    private ListView lsvProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inflate
        btnNuevo = (Button)findViewById(R.id.btn_agregar_producto);
        lsvProductos = (ListView)findViewById(R.id.lsv_productos);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se crea un intento para iniciar la actividad.
                Intent intNuevoProd = new Intent(getApplicationContext(),
                        NuevoProductoActivity.class);
                // Se arranca la actividad
                startActvity(intNuevoProd);
            }
        });

        ProductoDAO dao = new ProductoDAO(getApplicationContext());
        List<Producto> listaProductos;
        try {
            listaProductos = dao.getAll();
            List<HashMap<String,String> filas = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> registro;
            for (Producto prod: listaProductos){
                registro = new HashMap<String, String>();
                registro.put("codigo", prod.getCodigo());
                registro.put("nombre", prod.getNombre());
                registro.put("precio", String.valueOf(prod.getPrecio()));
                filas.add(registro);
            }

            SimpleAdapter adaptador = new SimpleAdapter(getApplicationContext(),
                    filas, R.layout.activity_registro_producto,
                    new String[]{"codigo", "nombre", "precio"},
                    new int[]{R.id.codigo, R.id.producto, R.id.precio});
            lsvProductos.setAdapter(adaptador);
            lsvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    TextView txtCodigo = (TextView)arg1.findViewById(R.id.codigo);
                    Bundle bundle = new Bundle();
                    bundle.putString("codigo", txtCodigo.getText().toString());
                    Intent intGestionProducto = new Intent(getApplicationContext(),
                            GestionProductoActivity.class);
                    intGestionProducto.putExtras(bundle);

                }
            });


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}
