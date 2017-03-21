package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */
public class BadBookingException extends Exception {

    private static final long serialVersionUID = 1L;

    public BadBookingException () {
    }

    public BadBookingException (String msg) {
        super (msg);
    }
}
