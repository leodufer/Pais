package py.edu.facitec.oscar.paises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombreEditText;
    EditText codigoEditText;
    ListView paisListView;
    List<Pais> paises;
    PaisDao paisDao;
    ArrayAdapter<Pais> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreEditText = findViewById(R.id.editTextNombre);
        codigoEditText = findViewById(R.id.editTextCodigo);
        paisListView = findViewById(R.id.paisListView);
        paisDao = new PaisDao(this);
        /*Cargar los datos guardados*/
        paises = paisDao.buscarTodos();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,paises);
        paisListView.setAdapter(adapter);

        paisListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Pais p = paises.get(position);
                paisDao.eliminar(p.getId());

                paises = paisDao.buscarTodos();
                adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,paises);
                paisListView.setAdapter(adapter);
                //TODO AlertDialog para confirmar
                Toast.makeText(MainActivity.this,"Pais "+p.getName()+" Eliminado",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    public void guardar(View view){
        //TODO validar
        Pais p = new Pais();

        p.setName(nombreEditText.getText().toString());
        p.setCode(codigoEditText.getText().toString());
        p.setCreatedAt(new Date());

        paisDao.guardar(p);

        nombreEditText.setText(null);
        codigoEditText.setText(null);
        paises = paisDao.buscarTodos();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,paises);
        paisListView.setAdapter(adapter);
        Log.i("RESULTADO", paises.toString());
    }
}
