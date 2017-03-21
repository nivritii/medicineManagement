package sg.edu.nus.clubmanagement.dao;

/**
 * Created by darryl on 26/12/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import sg.edu.nus.clubmanagement.ClubFolder.Facility;

public class FacilityDAO extends DBDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.CAT_ID + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public FacilityDAO(Context context) {
        super(context);
    }

    public long save(Facility facility) {
        ContentValues values = new ContentValues();
        //values.put(DataBaseHelper.FID_COLUMN, facility.getFacilityNumber());
        values.put(DataBaseHelper.FAC_NAME, facility.getName());
        values.put(DataBaseHelper.FAC_DESC, facility.getDescription());

        return database.insert(DataBaseHelper.FACILITY_TABLE, null, values);
    }

    public long update(Facility facility) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.CAT_NAME, facility.getName());
        values.put(DataBaseHelper.CAT_CODE, facility.getCode());
        values.put(DataBaseHelper.CAT_DESCRIPTION, facility.getDescription());
        values.put(DataBaseHelper.CAT_REMINDER, facility.getReminder());
        //values.put(DataBaseHelper.FAC_DESC, formatter.format(facility.getDescription()));

        long result = database.update(DataBaseHelper.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(facility.getFacilityNumber()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Facility facility) {
        return database.delete(DataBaseHelper.FACILITY_TABLE, WHERE_ID_EQUALS,
                new String[] { facility.getFacilityNumber() + "" });
    }

    //USING query() method
    public ArrayList<Facility> getFacilities() {
        ArrayList<Facility> facilities = new ArrayList<Facility>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_NAME,
                DataBaseHelper.TABLE_COLUMNS, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fname = cursor.getString(1);
            String fdesc = cursor.getString(2);
            String fcode = cursor.getString(3);
            String freminder = cursor.getString(4);

            Facility facility = new Facility(id, fname, fdesc, fcode, freminder);
            facilities.add(facility);
        }
        return facilities;
    }

    //USING rawQuery() method
    public ArrayList<Facility> getFacilitiesRQ() {
        ArrayList<Facility> facilities = new ArrayList<Facility>();

        String sql = "SELECT " + DataBaseHelper.FID_COLUMN + ","
                + DataBaseHelper.FAC_NAME + ","
                + DataBaseHelper.FAC_DESC + " FROM "
                + DataBaseHelper.FACILITY_TABLE;

        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fname = cursor.getString(1);
            String fdesc = cursor.getString(2);
            String fcode = cursor.getString(3);
            String freminder = cursor.getString(4);

            Facility facility = new Facility(id, fname, fdesc, fcode, freminder);
            facilities.add(facility);
        }
        return facilities;
    }

    //Retrieves a single facility record with the given id
    public Facility getFacility(long id) {
        Facility facility = null;

        String sql = "SELECT * FROM " + DataBaseHelper.FACILITY_TABLE
                + " WHERE " + DataBaseHelper.FID_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            int fid = cursor.getInt(0);
            String fname = cursor.getString(1);
            String fdesc = cursor.getString(2);
            String fcode = cursor.getString(3);
            String freminder = cursor.getString(4);

            facility = new Facility(fid, fname, fdesc, fcode, freminder);

        }
        return facility;
    }
}
