package sg.edu.nus.clubmanagement.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by darryl on 26/12/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "category";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME="category_table";
    public static final int COLNO__ID = 0;
    public static final int COLNO_MODEL_NAME = 1;
    public static final int COLNO_RELEASE_YEAR = 2;
    public static final String CAT_ID = "cat_id";
    public static final String CAT_NAME = "cat_name";
    public static final String CAT_CODE = "cat_code";
    public static final String CAT_DESCRIPTION = "cat_desc";
    public static final String CAT_REMINDER = "cat_reminder";


    public static final String MEMBER_TABLE = "medicine_table";
    public static final String MED_ID = "med_id";
    public static final String MED_NAME = "med_name";
    public static final String MED_DESC = "med_desc";
    public static final String MED_CATEGORY = "med_category";
    public static final String MED_REMIND = "med_remind";
    public static final String MED_QUANTITY = "med_quantity";
    public static final String MED_DOSAGE = "med_dosage";
    public static final String MED_THRESHOLD = "med_threshold";
    public static final String MED_DATE_ISSUED = "med_date_issued";
    public static final String MED_EXPIRY_FACTOR = "med_expiry_factor";

    /*public static final String[] TABLE_COLUMNS =
            new String[]{COLNAME_ID,COLNAME_NAME,COLNAME_CODE,COLNAME_DESC};
*/
    private static final String DBFILENAME="medicine.db";
    private static final int DBVERSION = 1;

    //CATEGORY COLUMNS
    public static final String[] TABLE_COLUMNS =
            new String[]{CAT_ID,CAT_NAME,CAT_CODE,CAT_DESCRIPTION,CAT_REMINDER};

    //MEDICINE COLUMN
    public static final String[] MED_TABLE_COLUMNS =
            new String[]{MED_ID,MED_NAME,MED_DESC,MED_CATEGORY,MED_REMIND,MED_QUANTITY,MED_DOSAGE,MED_THRESHOLD,MED_DATE_ISSUED,MED_EXPIRY_FACTOR};


    //Note that for the following two SQL Strings, normally use the Strings
    //declared above to avoid typos etc.  These are presented as text here
    //for readability's sake in this example
    private static final String INITIAL_SCHEMA=
            "create table category_table (" +
                    "cat_id integer primary key autoincrement," +
                    "cat_name varchar(100) not null," +
                    "cat_code varchar(100) not null," +
                    "cat_desc varchar(100) not null," +
                    "cat_reminder varchar(100) not null" +
                    ")";

    private static final String CREATE_MEMBER_TABLE=
            "create table medicine_table (" +
                    "med_id integer primary key autoincrement," +
                    "med_name varchar(100) not null," +
                    "med_desc varchar(100) not null," +
                    "med_category varchar(100) not null," +
                    "med_remind varchar(100) not null," +
                    "med_quantity varchar(100) not null," +
                    "med_dosage varchar(100) not null," +
                    "med_threshold varchar(100) not null," +
                    "med_date_issued varchar(100) not null," +
                    "med_expiry_factor varchar(100) not null" +
                    ")";

    private static final String INITIAL_DATA_INSERT=
            "insert into category_table (cat_name, cat_code, cat_desc, cat_reminder) values " +
                    "('Supplement','SUP','Set reminder option for consumption of supplement is optional','0')," +
                    "('Incidental','INC','For unplanned incidents with prescription from general practitioners','0')," +
                    "('Complete Course','COM','For antibiotics medication like sinus infection, pneumonia, bronchitis, acne, strep throat, cellulitis, etc','0')," +
                    "('Self Apply','SEL','To note down any self-prescribed medication','0')," +
                    "('Chronic','CHR','To categorise medication for long-term/life-time consumption for diseases','1')" ;





/*    public static final String CREATE_MEMBER_TABLE = "CREATE TABLE "
            + MEMBER_TABLE + "(" + MID_COLUMN + " INTEGER PRIMARY KEY, "
            + SUR_NAME + " TEXT, " + FIRST_NAME + " TEXT, "
            + SEC_NAME + " TEXT" + ")";*/

    public static final String FACILITY_TABLE = "facility";
    public static final String FID_COLUMN = "fid";
    public static final String FAC_NAME = "fname";
    public static final String FAC_DESC = "fdesc";

    public static final String CREATE_FACILITY_TABLE = "CREATE TABLE "
            + FACILITY_TABLE + "(" + FID_COLUMN + " INTEGER PRIMARY KEY, "
            + FAC_NAME + " TEXT, " + FAC_DESC + " TEXT" + ")";

    public static final String BOOKING_TABLE = "booking";
    public static final String BID_COLUMN = "bid";
    // public static final String MID_COLUMN = "mid";
    // public static final String FID_COLUMN = "fid";
    public static final String START_DATE = "sdate";
    public static final String END_DATE = "edate";

    public static final String CREATE_BOOKING_TABLE = "CREATE TABLE "
            + BOOKING_TABLE + "(" + BID_COLUMN + " INTEGER PRIMARY KEY, "
            + MED_ID + " INTEGER, " + FID_COLUMN + " INTEGER, "
            + START_DATE + " DATE, " + END_DATE + " DATE" + ")";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEMBER_TABLE);
        db.execSQL(INITIAL_SCHEMA);
        db.execSQL(INITIAL_DATA_INSERT);
        db.execSQL(CREATE_BOOKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + MEMBER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FACILITY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BOOKING_TABLE);
        onCreate(db);
    }
}
