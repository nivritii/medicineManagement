package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */
import java.util.*;

public class BookingRegister {

    private HashMap<Facility, ArrayList<Booking>> bookings;


    public BookingRegister() {
        bookings = new HashMap<Facility, ArrayList<Booking>> ();
    }

    public void addBooking (Member member, Facility facility, Date startDate, Date endDate)
            throws BadBookingException {
        Booking b = new Booking (member, facility, startDate, endDate);
        ArrayList<Booking> bList = bookings.get (facility);
        if (bList == null) {
            bList = new ArrayList<Booking> ();
            bookings.put (facility, bList);
        } else {
            Iterator<Booking> i = bList.iterator ();
            while (i.hasNext ()) {
                Booking other = i.next();
                if (b.overlaps(other)) {
                    throw new BadBookingException ("New booking overlaps with existing one");
                }
            }
        }
        bList.add (b);
    }

    public void removeBooking (Booking booking) {
        Facility f = booking.getFacility();
        ArrayList<Booking> bList = bookings.get (f);
        if (bList != null) {
            bList.remove (booking);
        }
    }

    public ArrayList<Booking> getBookings (Facility facility,
                                           Date startDate, Date endDate) {
        ArrayList<Booking> selected = new ArrayList<Booking> ();
        ArrayList<Booking> bList = bookings.get (facility);
        if (bList != null) {
            Iterator<Booking> i = bList.iterator ();
            while (i.hasNext ()) {
                Booking b = i.next();
                boolean earlier = startDate.after (b.getEndDate());
                boolean later = endDate.before (b.getStartDate());
                if (!(earlier || later)) {
                    selected.add (b);
                }
            }
        }
        return selected;
    }

}

