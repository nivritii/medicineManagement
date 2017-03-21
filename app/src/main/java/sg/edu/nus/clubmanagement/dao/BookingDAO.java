package sg.edu.nus.clubmanagement.dao;

/**
 * Created by darryl on 26/12/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import sg.edu.nus.clubmanagement.ClubFolder.BadBookingException;
import sg.edu.nus.clubmanagement.ClubFolder.Booking;

public class BookingDAO extends DBDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.BID_COLUMN + " =?";
    // private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.ENGLISH);

    public BookingDAO(Context context) {
        super(context);
    }

    public long save(Booking booking) {
        ContentValues values = new ContentValues();
        // values.put(DataBaseHelper.BID_COLUMN, booking.getBookingNumber());
        values.put(DataBaseHelper.MED_ID, booking.getMemberNumber());
        values.put(DataBaseHelper.FID_COLUMN, booking.getFacilityNumber());
        values.put(DataBaseHelper.START_DATE, formatter.format(booking.getStartDate()));
        values.put(DataBaseHelper.END_DATE, formatter.format(booking.getEndDate()));

        return database.insert(DataBaseHelper.BOOKING_TABLE, null, values);
    }

    public long update(Booking booking) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.MED_ID, booking.getMemberNumber());
        values.put(DataBaseHelper.FID_COLUMN, booking.getFacilityNumber());
        values.put(DataBaseHelper.START_DATE, formatter.format(booking.getStartDate()));
        values.put(DataBaseHelper.END_DATE, formatter.format(booking.getEndDate()));


        long result = database.update(DataBaseHelper.BOOKING_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(booking.getBookingNumber()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Booking booking) {
        return database.delete(DataBaseHelper.BOOKING_TABLE, WHERE_ID_EQUALS,
                new String[] { booking.getBookingNumber() + "" });
    }

    //USING query() method
    public ArrayList<Booking> getBookings() {
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        Cursor cursor = database.query(DataBaseHelper.BOOKING_TABLE,
                new String[] { DataBaseHelper.BID_COLUMN,
                        DataBaseHelper.MED_ID,
                        DataBaseHelper.FID_COLUMN,
                        DataBaseHelper.START_DATE,
                        DataBaseHelper.END_DATE }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            int bid = cursor.getInt(0);
            int mid = cursor.getInt(1);
            int fid = cursor.getInt(2);
            Date sdate = null;
            Date edate = null;
            try {
                sdate = formatter.parse(cursor.getString(3));
                edate = formatter.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Booking booking = null;
            try {
                booking = new Booking(mid, fid, sdate, edate, bid);
            } catch (BadBookingException e) {
                e.printStackTrace();
            }
            bookings.add(booking);
        }
        return bookings;
    }

    //USING rawQuery() method
    public ArrayList<Booking> getBookingsRQ() {
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        String sql = "SELECT " + DataBaseHelper.BID_COLUMN + ","
                + DataBaseHelper.MED_ID + ","
                + DataBaseHelper.FID_COLUMN + ","
                + DataBaseHelper.START_DATE + ","
                + DataBaseHelper.END_DATE + " FROM "
                + DataBaseHelper.BOOKING_TABLE;

        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int bid = cursor.getInt(0);
            int mid = cursor.getInt(1);
            int fid = cursor.getInt(2);
            Date sdate = null;
            Date edate = null;
            try {
                sdate = formatter.parse(cursor.getString(3));
                edate = formatter.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Booking booking = null;
            try {
                booking = new Booking(mid, fid, sdate, edate, bid);
            } catch (BadBookingException e) {
                e.printStackTrace();
            }
            bookings.add(booking);
        }
        return bookings;
    }

    //Retrieves a single booking record with the given id
    public Booking getBooking(long id) {
        Booking booking = null;

        String sql = "SELECT * FROM " + DataBaseHelper.BOOKING_TABLE
                + " WHERE " + DataBaseHelper.BID_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            int bid = cursor.getInt(0);
            int mid = cursor.getInt(1);
            int fid = cursor.getInt(2);
            Date sdate = null;
            Date edate = null;
            try {
                sdate = formatter.parse(cursor.getString(3));
                edate = formatter.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                booking = new Booking(mid, fid, sdate, edate, bid);
            } catch (BadBookingException e) {
                e.printStackTrace();
            }
        }
        return booking;
    }
}
