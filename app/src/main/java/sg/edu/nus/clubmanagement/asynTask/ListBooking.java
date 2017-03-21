package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.nus.clubmanagement.ClubFolder.Booking;
import sg.edu.nus.clubmanagement.dao.BookingDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class ListBooking extends AsyncTask<Void, Void, ArrayList<Booking>> {
    ArrayList<Booking> bookings;
    private BookingDAO bookingDAO;

    public ListBooking(Context context) {
        this.bookingDAO = new BookingDAO(context);
    }

    @Override
    protected ArrayList<Booking> doInBackground(Void... arg0) {
        ArrayList<Booking> bookingList = bookingDAO.getBookings();
        return bookingList;
    }

    @Override
    protected void onPostExecute(ArrayList<Booking> bookList) {
        Log.d("facilities", bookList.toString());
        bookings = bookList;

        if (bookList == null) {
            bookings = new ArrayList<Booking>();
        }
    }
}
