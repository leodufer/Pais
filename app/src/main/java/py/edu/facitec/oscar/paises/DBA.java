package py.edu.facitec.oscar.paises;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.oss.datahelper.DataBaseManager;

public class DBA<T> {
	private static final String DB_NAME = "country.sqlite";
	private static final int DB_VERSION = 1;
	private Dao<T, Integer> dao;
	
	public void init(Context context, Class clazz){
		DataBaseManager.init(context, DB_NAME, DB_VERSION);
		
		ConnectionSource source = DataBaseManager.getInstance()
												 .getHelper()
												 .getConnectionSource();
		try {
			TableUtils.createTableIfNotExists(source, clazz);
			//Demas tablas
			dao = DataBaseManager.getInstance().getHelper().getDao(clazz);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public  Dao<T, Integer> getDao(){
            return dao;
    }
}
