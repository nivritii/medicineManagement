package sg.edu.nus.clubmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.clubmanagement.ClubFolder.Booking;
import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.application.App;

public class BookingListAdapter extends ArrayAdapter<Booking> {
  private Context context;
  private List<Booking> bookings = new ArrayList<>();

  // for searching
  private ArrayList<Booking> arraylist;

  public BookingListAdapter(Context context) {
    super(context, R.layout.mem_fac_row_layout);
    this.context = context;

    // for searching
    this.arraylist = new ArrayList<Booking>();
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    BookingListAdapter.ViewHolder viewHolder;
    if (convertView == null) {
      LayoutInflater inflater =
              (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.mem_fac_row_layout, parent, false);
      viewHolder = new BookingListAdapter.ViewHolder();
      viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_catName);
      viewHolder.btnRemove = (Button) convertView.findViewById(R.id.btn_update);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (BookingListAdapter.ViewHolder) convertView.getTag();
    }

    final Booking booking = bookings.get(position);
    viewHolder.tvName.setText(booking.toString());
    viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        App.club.removeBooking(booking);
        refreshBookings();
      }
    });
    return convertView;
  }

  public void refreshBookings() {
    bookings.clear();
    bookings.addAll(App.club.getBookings(this.context));

    List<Member> lMembers = App.club.getMembers(this.context);
    List<Facility> lFacilities = App.club.getFacilities(this.context);

    for (Booking b : bookings) {
      for (Member m : lMembers) {
        if (b.getMemberNumber() == m.getMedId()) {
          b.setMember(m);
        }
      }

      for (Facility f : lFacilities) {
        if (b.getFacilityNumber() == f.getFacilityNumber()) {
          b.setFacility(f);
        }
      }
    }

    notifyDataSetChanged();

    // for searching
    this.arraylist.addAll(bookings);
  }

  @Override
  public int getCount() {
    return bookings.size();
  }

  static class ViewHolder {
    TextView tvName;
    Button btnRemove;
  }

  // for searching
  // Filter Class
  public void filter(String charText) {
    charText = charText.toLowerCase(Locale.getDefault());
    bookings.clear();

    if (charText.length() == 0) {
      bookings.addAll(arraylist);
    } else {
      for (Booking wp : arraylist) {
        if (wp.toString().toLowerCase(Locale.getDefault()).contains(charText)) {
          bookings.add(wp);
        }
      }
    }
    notifyDataSetChanged();
  }
}
