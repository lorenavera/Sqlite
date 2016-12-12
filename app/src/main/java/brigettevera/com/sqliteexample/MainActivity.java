package brigettevera.com.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextTelefono;
    InterfaceBD bd = new InterfaceBD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarDatos();
    }

    private void inicializarDatos() {
        editTextNombre = (EditText) findViewById(R.id.edit1);
        editTextTelefono = (EditText) findViewById(R.id.edit2);
    }

    public void guardarDatos(View view) {
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(editTextNombre.getText().toString());
        nuevaPersona.setTelefono(editTextTelefono.getText().toString());

        if (bd.insertarPersona(nuevaPersona)){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.msg1), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.msg2), Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarDatos(View view) {
        List<Persona> personas = bd.consultarTodos();
        for (int i=0; i<personas.size(); i++){
            String texto = "REGISTRO #:" + (i+1) + "\nNOMBRE: " +  personas.get(i).getNombre() + "\nTELÉFONO: " + personas.get(i).getTelefono();
            Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();
        }
    }
}
