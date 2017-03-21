package sg.edu.nus.clubmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.adapter.FacilityListAdapter;

public class FacilityFragment extends Fragment {
    private FacilityListAdapter facilityListAdapter;
    private TextView tvEmpty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_facility, container, false);

        View fragmentView = inflater.inflate(R.layout.fragment_facility, container, false);
        ListView facilityList = (ListView) fragmentView.findViewById(R.id.lv_facility_list);
        tvEmpty = (TextView) fragmentView.findViewById(R.id.tv_empty_value2);
        facilityListAdapter = new FacilityListAdapter(getActivity());
        facilityList.setAdapter(facilityListAdapter);
        /*FloatingActionButton floatingActionButton =
                (FloatingActionButton) fragmentView.findViewById(R.id.fabf);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AddFacilityActivity.class));
            }
        });*/
        return fragmentView;
    }

    @Override public void onResume() {
        super.onResume();
        facilityListAdapter.refreshFacilities();
        tvEmpty.setVisibility(facilityListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
