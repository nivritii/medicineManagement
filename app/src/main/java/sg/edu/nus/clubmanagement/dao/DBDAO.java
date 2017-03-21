package sg.edu.nus.clubmanagement.dao;

/**
 * Created by darryl on 26/12/2016.
 */
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBDAO {
    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public DBDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }
}
