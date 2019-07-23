package colvera.edu.oxxito.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Conexion {
    //Se define una variable Context.
    // S partir de esta, se crea la base de datos
    private Context contexto;

    public Conexion(Context contexto){
        this.contexto = contexto;
    }

    /**
     * Método para abrir la conexión de datos
     * @return
     */
    private SQLiteDatabase abrirConexion(){
        // Se  crea un objeto SQLiteDatabase
        SQLiteDatabase conexion = contexto.openOrCreateDatabase(
                "oxxito.db",
                SQLiteDatabase.OPEN_READWRITE, // Modo de lectura
                null); // Factory para la BD
        return conexion;
    }

    /**
     * Método para cerrar la conexión
     * @param conexion
     */
    private void cerrarConexion(SQLiteDatabase conexion){
        if(conexion!=null){
            conexion.close();
        }
    }

    /**
     * Método para ejecutar sentencias de SQL(Insert, Update y Delete)
     * @param sentencia Enunciado SQL válido
     * @return
     */
    public boolean ejecutarSentencia(String sentencia) throws Exception{
        //Se abre la conexión con la base de datos
        SQLiteDatabase conexion = abrirConexion();
        try {
            // Se ejecuta la sentencia sobre la conexión
            conexion.execSQL(sentencia);
            // Se cierra la conexión
            conexion.close();
        }catch(Exception e){
            //Lanzamos la excepción al método que la invoca
            throw new Exception("Error en la sentencia" + e.getMessage());
        }
        return true;
    }

    public List<HashMap<String,String>> ejecitarConsulta(String tabla,
                                                         String[] campos,
                                                         String condicion) throws  Exception{
        //Se crea una lista de objetos HashMap,
        //donde cada HashMap representará un objeto de la BD
        List<HashMap<String, String>>datos = new ArrayList<HashMap<String, String>>();
        try {
            //Se abre la conexión
            SQLiteDatabase conexion = abrirConexion();
            //Se consulta la base de datos
            //de acuerdo a los parámetros señalados
            Cursor resultado = conexion.query(tabla, campos, condicion, null,null, null,null);
            HashMap<String,String> registro;
            // Se itera sobre cada uno de los registros arrojados por la consulta
            while(resultado.moveToNext()){
                //Para cada registro en la BD
                //Se crea un HashMap
                registro = new HashMap<String, String>();
                for(int i = 0; i < campos.length; i++){
                    //Por cada campo se inserta el nombre y el valor
                    registro.put(campos[i], resultado.getString(i));
                }
                //Agregamos el registro a la lista
                datos.add(registro);
            }
            //Se cierra la conexión
            conexion.close();

        }catch (Exception e){
            //Lanzamos excepción
            throw new Exception("Error al ejecutar" +
                    e.getMessage());
        }
        return datos;
    }

    /**
     * Método para inicializar
     */
    public void inicializarBD() throws Exception{
        SQLiteDatabase conexixion = abrirConexion();
        //Se ejecuta sentencia
        conexixion.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        // Se ejecuta la sentencia para crear la tabla Productos
        conexixion.execSQL("CREATE TABLE PRODUCTOS(codigo TEXT,)" +
                "nombre TEXT, precio REAL, existencias INTEGER," +
                "fecha_caducidad TEXT");
        conexixion.close();
    }

}
