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
            DBA.getPaisDao().create(pais);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pais> buscarTodos(){
        try {
            return DBA.getPaisDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void eliminar(int id){
        try {
            DBA.getPaisDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
