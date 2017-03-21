package sg.edu.nus.clubmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.activity.AddBookingActivity;
import sg.edu.nus.clubmanagement.adapter.BookingListAdapter;


// for searching - implements SearchView.OnQueryTextListener
public class BookingFragment extends Fragment implements SearchView.OnQueryTextListener {
  private TextView tvEmpty;
  private BookingListAdapter bookingListAdapter;

  // for searching
  SearchView editsearch;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View fragmentView = inflater.inflate(R.layout.fragment_booking, container, false);
    ListView bookingList = (ListView) fragmentView.findViewById(R.id.lv_booking_list);
    tvEmpty = (TextView) fragmentView.findViewById(R.id.tv_empty_value);

    // for searching
    // Locate SearchView
    editsearch = (android.widget.SearchView) fragmentView.findViewById(R.id.search);
    editsearch.setOnQueryTextListener(this);

    bookingListAdapter = new BookingListAdapter(getActivity());
    bookingList.setAdapter(bookingListAdapter);
    FloatingActionButton floatingActionButton =
        (FloatingActionButton) fragmentView.findViewById(R.id.fab);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), AddBookingActivity.class));
      }
    });

    return fragmentView;
  }

  @Override public void onResume() {
    super.onResume();
    bookingListAdapter.refreshBookings();
    tvEmpty.setVisibility(bookingListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
  }

  // for searching - OnQueryTextListener implement methods
  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  // for searching - OnQueryTextListener implement methods
  @Override
  public boolean onQueryTextChange(String newText) {
    String text = newText;
    bookingListAdapter.filter(text);
    return false;
  }
}
