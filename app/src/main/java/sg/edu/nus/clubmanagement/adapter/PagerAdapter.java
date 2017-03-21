package sg.edu.nus.clubmanagement.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import sg.edu.nus.clubmanagement.fragment.BookingFragment;
import sg.edu.nus.clubmanagement.fragment.FacilityFragment;
import sg.edu.nus.clubmanagement.fragment.MemberFragment;

/**
 * Created by Swarna on 8/6/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
  int numOfTabs;

  public PagerAdapter(FragmentManager fm, int numOfTabs) {
    super(fm);
    this.numOfTabs = numOfTabs;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        MemberFragment memberFragment = new MemberFragment();
        return memberFragment;
      case 1:
        FacilityFragment facilityFragment = new FacilityFragment();
        return facilityFragment;
      default:
        BookingFragment bookingFragment = new BookingFragment();
        return bookingFragment;
    }
  }

  @Override public int getCount() {
    return numOfTabs;
  }
}
