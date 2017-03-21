package sg.edu.nus.clubmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.activity.AddMemberActivity;
import sg.edu.nus.clubmanagement.adapter.MemberListAdapter;

public class MemberFragment extends Fragment {
  private MemberListAdapter memberListAdapter;
  private TextView tvEmpty;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View fragmentView = inflater.inflate(R.layout.fragment_member, container, false);
    ListView memberList = (ListView) fragmentView.findViewById(R.id.lv_member_list);
    tvEmpty = (TextView) fragmentView.findViewById(R.id.tv_empty_value);
    memberListAdapter = new MemberListAdapter(getActivity());
    memberList.setAdapter(memberListAdapter);
    FloatingActionButton floatingActionButton =
        (FloatingActionButton) fragmentView.findViewById(R.id.fab);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), AddMemberActivity.class));
      }
    });
    return fragmentView;
  }

  @Override public void onResume() {
    super.onResume();
    memberListAdapter.refreshMembers();
    tvEmpty.setVisibility(memberListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
  }
}
