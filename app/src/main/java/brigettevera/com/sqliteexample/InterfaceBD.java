package brigettevera.com.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BrigetteLorena on 10/11/2016.
 */
public class InterfaceBD extends SQLiteOpenHelper {

    private final static String NOMBREBD = "mybd";
    private final static int VERSIONBD = 1;
    private String Script = "CREATE TABLE PERSONA (NOMBRE TEXT, TELEFONO TEXT)";
    private String ScriptBorrar = "DROP TABLE PERSONA";

    public InterfaceBD(Context context) {
        super(context, NOMBREBD, null, VERSIONBD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(Script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL(ScriptBorrar);
        bd.execSQL(Script);
    }

    public boolean insertarPersona(Persona nuevaPersona){

        ContentValues valores = new ContentValues();
        valores.put("NOMBRE", nuevaPersona.getNombre());
        valores.put("TELEFONO", nuevaPersona.getTelefono());

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert("PERSONA", null, valores);
            db.close();
            return true;
        }catch (Exception E){
            db.close();
            return false;
        }
    }

    public List<Persona> consultarTodos(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Persona> resultados = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM PERSONA", null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    Persona temporal = new Persona(cursor.getString(0),cursor.getString(1));
                    resultados.add(temporal);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();

        return resultados;
    }

}