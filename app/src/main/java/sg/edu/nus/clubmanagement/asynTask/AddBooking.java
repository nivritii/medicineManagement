package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.clubmanagement.ClubFolder.Booking;
import sg.edu.nus.clubmanagement.dao.BookingDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class AddBooking extends AsyncTask<Booking, Void, Long> {
    Booking booking = null;
    private BookingDAO bookingDAO;

    public AddBooking(Context context) {
        this.bookingDAO = new BookingDAO(context);
    }

    @Override
    protected Long doInBackground(Booking... params) {
        long result = bookingDAO.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        if (result != -1)

            if (bookingDAO != null)
                bookingDAO.close();
    }
}
