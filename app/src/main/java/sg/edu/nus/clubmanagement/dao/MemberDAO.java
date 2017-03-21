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

import sg.edu.nus.clubmanagement.ClubFolder.Member;

public class MemberDAO extends DBDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.MED_ID + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public MemberDAO(Context context) {
        super(context);
    }

    public long save(Member member) {
        ContentValues values = new ContentValues();
        //values.put(DataBaseHelper.MID_COLUMN, member.getMemberNumber());
        values.put(DataBaseHelper.MED_NAME, member.getMedName());
        values.put(DataBaseHelper.MED_DESC, member.getMedDesc());
        values.put(DataBaseHelper.MED_CATEGORY, member.getCategory());
        values.put(DataBaseHelper.MED_REMIND, member.getReminder());
        values.put(DataBaseHelper.MED_QUANTITY, member.getQuantity());
        values.put(DataBaseHelper.MED_DOSAGE, member.getDosage());
        values.put(DataBaseHelper.MED_THRESHOLD, member.getThreshold());
        values.put(DataBaseHelper.MED_DATE_ISSUED, member.getDateIssued());
        values.put(DataBaseHelper.MED_EXPIRY_FACTOR, member.getExpireFactor());


        return database.insert(DataBaseHelper.MEMBER_TABLE, null, values);
    }

    public long update(Member member) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.MED_NAME, member.getMedName());
        values.put(DataBaseHelper.MED_DESC, member.getMedDesc());
        values.put(DataBaseHelper.MED_CATEGORY, member.getCategory());
        values.put(DataBaseHelper.MED_REMIND, member.getReminder());
        values.put(DataBaseHelper.MED_QUANTITY, member.getQuantity());
        values.put(DataBaseHelper.MED_DOSAGE, member.getDosage());
        values.put(DataBaseHelper.MED_THRESHOLD, member.getThreshold());
        values.put(DataBaseHelper.MED_DATE_ISSUED, member.getDateIssued());
        values.put(DataBaseHelper.MED_EXPIRY_FACTOR, member.getExpireFactor());


        long result = database.update(DataBaseHelper.MEMBER_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(member.getMedId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Member member) {
        return database.delete(DataBaseHelper.MEMBER_TABLE, WHERE_ID_EQUALS,
                new String[] { member.getMedId() + "" });
    }

    //USING query() method
   public ArrayList<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<Member>();

        Cursor cursor = database.query(DataBaseHelper.MEMBER_TABLE,
                DataBaseHelper.MED_TABLE_COLUMNS, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String mName = cursor.getString(1);
            String mDesc = cursor.getString(2);
            String mCategory = cursor.getString(3);
            String mReminder = cursor.getString(4);
            int mQuantity = cursor.getInt(5);
            int mDosage = cursor.getInt(6);
            int mThreshold = cursor.getInt(7);
            String mDateIssued = cursor.getString(8);
            int mExpiryFactor = cursor.getInt(9);

            Member member = new Member(id, mName, mDesc, mCategory, mReminder, mQuantity, mDosage, mThreshold, mDateIssued, mExpiryFactor);
            members.add(member);
        }
        return members;
    }

    //USING rawQuery() method
    public ArrayList<Member> getMembersRQ() {
        ArrayList<Member> members = new ArrayList<Member>();

        String sql = "SELECT " + DataBaseHelper.MED_ID + ","
                + DataBaseHelper.MED_DESC + ","
                + DataBaseHelper.MED_CATEGORY + ","
                + DataBaseHelper.MED_REMIND + ","
                + DataBaseHelper.MED_QUANTITY + ","
                + DataBaseHelper.MED_DOSAGE + ","
                + DataBaseHelper.MED_THRESHOLD + ","
                + DataBaseHelper.MED_DATE_ISSUED + ","
                + DataBaseHelper.MED_EXPIRY_FACTOR + " FROM "
                + DataBaseHelper.MEMBER_TABLE;

        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String mName = cursor.getString(1);
            String mDesc = cursor.getString(2);
            String mCategory = cursor.getString(3);
            String mReminder = cursor.getString(4);
            int mQuantity = cursor.getInt(5);
            int mDosage = cursor.getInt(6);
            int mThreshold = cursor.getInt(7);
            String mDateIssued = cursor.getString(8);
            int mExpiryFactor = cursor.getInt(9);

            Member member = new Member(id, mName, mDesc, mCategory, mReminder, mQuantity, mDosage, mThreshold, mDateIssued, mExpiryFactor);
            members.add(member);
        }
        return members;
    }

    //Retrieves a single member record with the given id
    public Member getMember(long id) {
        Member member = null;

        String sql = "SELECT * FROM " + DataBaseHelper.MEMBER_TABLE
                + " WHERE " + DataBaseHelper.MED_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            int mid = cursor.getInt(0);
            String mName = cursor.getString(1);
            String mDesc = cursor.getString(2);
            String mCategory = cursor.getString(3);
            String mReminder = cursor.getString(4);
            int mQuantity = cursor.getInt(5);
            int mDosage = cursor.getInt(6);
            int mThreshold = cursor.getInt(7);
            String mDateIssued = cursor.getString(8);
            int mExpiryFactor = cursor.getInt(9);

            member = new Member(mid, mName, mDesc, mCategory, mReminder, mQuantity, mDosage, mThreshold, mDateIssued, mExpiryFactor);

        }
        return member;
    }
}
