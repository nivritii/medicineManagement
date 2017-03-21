package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sg.edu.nus.clubmanagement.asynTask.AddBooking;
import sg.edu.nus.clubmanagement.asynTask.AddFacility;
import sg.edu.nus.clubmanagement.asynTask.AddMember;
import sg.edu.nus.clubmanagement.asynTask.DeleteMember;
import sg.edu.nus.clubmanagement.asynTask.ListBooking;
import sg.edu.nus.clubmanagement.asynTask.ListFacility;
import sg.edu.nus.clubmanagement.asynTask.ListMember;
import sg.edu.nus.clubmanagement.asynTask.UpdateFacility;
import sg.edu.nus.clubmanagement.asynTask.UpdateMember;

public class Club {

    private int                       numMembers;
    private ArrayList<Member>         members;
    private ArrayList<Facility>       facilities;
    private HashMap<String, Facility> facilities1;
    private ArrayList<Booking>        bookings;
    private BookingRegister           bookings1;

    private AddMember taskMemberAdd;
    private ListMember taskMemberList;
    private AddFacility taskFacilityAdd;
    private ListFacility taskFacilityList;
    private AddBooking taskBookingAdd;
    private ListBooking taskBookingList;

    private UpdateFacility taskFacilityUpdate;
    private DeleteMember taskDeleteMember;

    private UpdateMember taskUpdateMember;

    public Club () {
        numMembers = 0;
        members = new ArrayList<Member> ();
        facilities = new ArrayList<Facility> ();
        //facilities = new HashMap<String, Facility> ();
        bookings = new ArrayList<Booking>();
        //bookings1 = new BookingRegister ();
    }

    public void updateFacility(int id, String name, String code, String description, String reminder, Context context){
        Facility f = new Facility(id, name, code, description, reminder);
        taskFacilityUpdate = new UpdateFacility(context);
        taskFacilityUpdate.execute(f);

    }

    public void updateMember(Member member, Context context){
        taskUpdateMember = new UpdateMember(context);
        taskUpdateMember.execute(member);

    }

    public Member getMember (int memberNum) {
        Iterator<Member> i = members.iterator ();
        while (i.hasNext ()) {
            Member m = i.next();
            if (m.getMedId() == memberNum) {
                return m;
            }
        }
        return null;
    }

    public List<Member> getMembers (Context context) {
        // SQLite - Start
        taskMemberList = new ListMember(context);
        taskMemberList.execute((Void) null);
        try {
            members = taskMemberList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (members == null) { members = new ArrayList<Member>(); }
        // SQLite - End

        return new ArrayList<Member> (members);
    }

    public Member addMember(String medName, String medDesc, String catId, String remindID,
                            int quantity, int dosage, int threshold, String dateIssued, int expiryFactor) {
        numMembers++;
        Log.d("Niv", "inserting medicine");
        Member m = new Member (medName, medDesc, catId, remindID, quantity, dosage, threshold, dateIssued, expiryFactor);
        members.add (m);
        return m;
    }

    // SQLite
    public Member addMember (String medName, String medDesc, String catId, String remindID,
    int quantity, int dosage, int threshold, String dateIssued, int expiryFactor, Context context) {
        Member m = new Member (medName, medDesc, catId, remindID, quantity, dosage, threshold, dateIssued, expiryFactor);

        taskMemberAdd = new AddMember(context);
        taskMemberAdd.execute(m);
        return m;
    }

    public void removeMember (Member m, Context context) {
/*        Member m = getMember (memberNum);
        if (m != null) {
            members.remove (m);
        }*/
        taskDeleteMember = new DeleteMember(context);
        taskDeleteMember.execute(m);

    }

    public void showMembers () {
        Iterator<Member> i = members.iterator ();
        while (i.hasNext ()) {
            i.next();
        }
    }

    /*
    public Facility getFacility (String name) {

        return facilities.get (name);
    }*/

    public Facility getFacility (int facilityNum) {
        Iterator<Facility> i = facilities.iterator ();
        while (i.hasNext ()) {
            Facility m = i.next();
            if (m.getFacilityNumber() == facilityNum) {
                return m;
            }
        }
        return null;
    }

    public List<Facility> getFacilities (Context context) {
        // SQLite - Start
        taskFacilityList = new ListFacility(context);
        taskFacilityList.execute((Void) null);
        try {
            facilities = taskFacilityList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (facilities == null) { facilities = new ArrayList<Facility>(); }
        // SQLite - End
        return (new ArrayList<Facility>(facilities));
    }

    public void addFacility (String name, String description, String code, String reminder) {
        if (name == null) {
            return;
        }
        Facility f = new Facility (name, description, code, reminder);
        facilities1.put (name, f);
    }

    // SQLite
    public Facility addFacility (String name, String description, String code, String reminder, Context context) {
        if (name == null) {
            return null;
        }
        Facility f = new Facility (name, description, code, reminder);


        taskFacilityAdd = new AddFacility(context);
        taskFacilityAdd.execute(f);
        return f;
    }

    public void removeFacility (String name) {

        facilities.remove (name);
    }

    /*
    public void showFacilities () {
        Iterator<Facility> i = getFacilities().iterator ();
        while (i.hasNext ()) {
            i.next().show ();
        }
    }
    */


    public Booking addBooking (int memberNumber, int facilityNumber, Date startDate, Date endDate, Context context)
            throws BadBookingException {
        //bookings1.addBooking (getMember (memberNumber), getFacility (facName), startDate, endDate);

        Booking b = new Booking(memberNumber, facilityNumber, startDate, endDate);
        taskBookingAdd = new AddBooking(context);
        taskBookingAdd.execute(b);
        return b;
    }


    public void removeBooking (Booking booking) {

        bookings.remove(booking);
    }

    public ArrayList<Booking> getBookings (Context context) {
        //return bookings.getBookings (getFacility (facName), startDate, endDate);

        // SQLite - Start
        taskBookingList = new ListBooking(context);
        taskBookingList.execute((Void) null);
        try {
            bookings = taskBookingList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (bookings == null) { bookings = new ArrayList<Booking>(); }
        // SQLite - End
        return (new ArrayList<Booking>(bookings));
    }

    /*
    public void showBookings (String facName, Date startDate, Date endDate) {
        ArrayList<Booking> b = getBookings (facName, startDate, endDate);
        Iterator<Booking> i = b.iterator();
        while (i.hasNext()) {
            i.next().show();
        }
    }
    */


    public void show () {
        System.out.println ("Current Members:");
        showMembers ();
        System.out.println ();
        System.out.println ("Facilities:");
        //showFacilities ();
    }

}
