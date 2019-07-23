package colvera.edu.oxxito.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import colvera.edu.oxxito.model.Producto;

public class ProductoDAO {
    private Context context;

    public ProductoDAO(Context context) {
        this.context = context;
    }

    /**
     * Método insertar un objeto producto a la BD
     *
     * @param obj
     * @throws Exception
     */
    public void insert(Producto obj) throws Exception {
        String comando = "INSERT INTO PRODUCTOS(codigo, nombre, " +
                "precio, existencias, fecha_caducidad) " +
                "VALUES ('" + obj.getCodigo() + "', '" +
                obj.getNombre() + "', " + obj.getPrecio() +
                ", " + obj.getExistencias() + ", '" +
                obj.getFechaCaducidad() + "')";
        // Se crea un objeto de conexión
        Conexion con = new Conexion(context);
        try {
            con.ejecutarSentencia(comando);
        } catch (Exception e) {
            throw new Exception("Error al insertar: " +
                    e.getMessage());
        }
    }

    /**
     * Método Actualizar
     *
     * @param obj
     */
    public void update(Producto obj) throws Exception {
        String comando = "UPDATE PRODUCTOS SET " +
                "nombre = '" + obj.getNombre() + "'," +
                "precio = " + obj.getPrecio() + "," +
                "existencias= " + obj.getExistencias() + ", '" +
                "fecha_caducidad='" + obj.getFechaCaducidad() + "' " +
                "WHERE codigo= '" + obj.getCodigo() + "'";
        Conexion con = new Conexion(context);
        try {
            con.ejecutarSentencia(comando);
        } catch (Exception e) {
            //Lanzamos la excepción, en caso de error
            throw new Exception("Error al actualizar: " +
                    e.getMessage());
        }
    }

    public void delete(Producto obj) throws Exception {
        //Se crea la sentencia DELETE
        String comando = "DELETE FROM PRODUCTOS WHERE codigo='" +
                obj.getCodigo() + "'";
        Conexion con = new Conexion(context);
        try {
            con.ejecutarSentencia(comando);
        } catch (Exception e) {
            //Lanzamos la excepción, en caso de error
            throw new Exception("Error al eliminar: " +
                    e.getMessage());
        }
    }

    public List<Producto> getAll() throws Exception {
        String tabla = "Productos";
        String campos[] = new String[]{"codigo", "nombre", "precio", "existencias", "fecha_caducudad"};
        List<Producto> listaProductos = new ArrayList<Producto>();
        Conexion con = new Conexion(context);
        List<HashMap<String, String>> resultado;
        resultado = con.ejecitarConsulta(tabla, campos, null);

        Producto pro;
        for (HashMap<String, String> reg : resultado) {
            pro = new Producto();
            pro.setCodigo(reg.get("codigo"));
            pro.setNombre((reg.get("nombre")));
            pro.setPrecio(Double.valueOf(reg.get("precio")));
            pro.setExistencias(Integer.valueOf(reg.get("existencias")));
            pro.setFechaCaducidad(reg.get("fecha_caducidad"));
            listaProductos.add(pro);
        }
        return listaProductos;
    }

    public Producto getById(Producto obj) throws Exception {
        String tabla = "PRODUCTOS";
        String campos[] = new String[]{"codigo", "nombre", "precio", "existencias", "fecha_caducudad"};
        String condicion = "codigo = '" + obj.getCodigo() + "'";
        Conexion con = new Conexion(context);
        List<HashMap<String, String>> resultado;
        resultado = con.ejecitarConsulta(tabla, campos, condicion);

        Producto pro = null;
        for (HashMap<String, String> reg : resultado) {
            pro = new Producto();
            pro.setCodigo(reg.get("codigo"));
            pro.setNombre((reg.get("nombre")));
            pro.setPrecio(Double.valueOf(reg.get("precio")));
            pro.setExistencias(Integer.valueOf(reg.get("existencias")));
            pro.setFechaCaducidad(reg.get("fecha_caducidad"));
        }
        return pro;
    }
}