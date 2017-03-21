package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */
import java.util.*;
import java.text.*;

public class Booking {

    public static final String DATE_FORMAT = "d-MMM-yyyy";
    public static final String TIME_FORMAT = "d-MMM-yyyy H:mm";

    private int bookingNumber;
    private int mid;
    private int fid;
    private Member   member;
    private Facility facility;
    private Date     startDate;
    private Date     endDate;

    public Booking (Member member, Facility facility, Date startDate, Date endDate)
            throws BadBookingException {

        String error = null;
        if (member == null)
            error = "No member specified";
        else if (facility == null)
            error = "No facility specified";
        else if ((startDate == null) || (endDate == null))
            error = "Missing date";
        else if (startDate.after(endDate))
            error = "Start date is earlier than end date";
        if (error != null)
            throw new BadBookingException (error);

        this.member = member;
        this.facility = facility;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking (int mid, int fid, Date startDate, Date endDate)
            throws BadBookingException {

        String error = null;
        if (mid == 0)
            error = "No member specified";
        else if (fid == 0)
            error = "No facility specified";
        else if ((startDate == null) || (endDate == null))
            error = "Missing date";
        else if (startDate.after(endDate))
            error = "Start date is earlier than end date";
        if (error != null)
            throw new BadBookingException (error);


        this.mid = mid;
        this.fid = fid;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking (Member member, Facility facility, Date startDate, Date endDate, int bid) throws BadBookingException {

        String error = null;

        if (error != null)
            throw new BadBookingException (error);

        this.member = member;
        this.facility = facility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingNumber = bid;
    }

    public Booking (int mid, int fid, Date startDate, Date endDate, int bid) throws BadBookingException {

        String error = null;

        if (error != null)
            throw new BadBookingException (error);

        this.mid = mid;
        this.fid = fid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingNumber = bid;
    }

    public void setMember(Member m) {
        this.member = m;
    }

    public void setFacility(Facility f) {
        this.facility = f;
    }

    public int getBookingNumber() { return bookingNumber; }

    public Member getMember () {
        return member;
    }

    public Facility getFacility () {
        return facility;
    }

    public int getMemberNumber() { return mid; }

    public int getFacilityNumber() { return fid; }

    public Date getStartDate () {
        return startDate;
    }

    public Date getEndDate () {
        return endDate;
    }

    public boolean overlaps (Booking other) {
        boolean status = true;
        if (this.facility != other.getFacility ()) {
            status = false;
        } else if (startDate.getTime() >= other.getEndDate().getTime()) {
            status = false;
        } else if (other.getStartDate().getTime() >= endDate.getTime()) {
            status = false;
        }
        return (status);
    }

    private static DateFormat tf = new SimpleDateFormat (TIME_FORMAT);

    public String toString () {
        return ("Booking: " + bookingNumber + " - " + facility.getName()
                + "\r\n(" + member.getMedName() + " " + member.getMedDesc() + ") " + "\r\nFrom: " + tf.format(startDate)
                + "\r\nTo: " + tf.format(endDate));
    }

    public void show () {
        System.out.println (this);
    }
}

