package py.edu.facitec.oscar.paises;

import android.content.Context;


import java.sql.SQLException;
import java.util.List;

public class PaisDao extends DBA<Pais> {

    public PaisDao(Context context){
        this.init(context,Pais.class);
    }

    public void guardar(Pais pais){
        try {
            this.getDao().create(pais);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pais> buscarTodos(){
        try {
            return this.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
