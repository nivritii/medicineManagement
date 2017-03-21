package sg.edu.nus.clubmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.activity.UpdateFacilityActivity;
import sg.edu.nus.clubmanagement.application.App;

/**
 * Created by darryl on 26/12/2016.
 */

public class FacilityListAdapter extends ArrayAdapter<Facility> {
    private Context context;
    private List<Facility> facilities = new ArrayList<>();

    public FacilityListAdapter(Context context) {
        super(context, R.layout.mem_fac_row_layout);
        this.context = context;
        refreshFacilities();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FacilityListAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mem_fac_row_layout, parent, false);

            viewHolder = new FacilityListAdapter.ViewHolder();
            viewHolder.tvCatName = (TextView) convertView.findViewById(R.id.tv_catName);
            viewHolder.tvCatDesc = (TextView) convertView.findViewById(R.id.tv_catDesc);
            viewHolder.tvCatCode = (TextView) convertView.findViewById(R.id.tv_catCode);
            viewHolder.tvCatReminder = (TextView) convertView.findViewById(R.id.tv_catReminder);
            viewHolder.btnUpdate = (Button) convertView.findViewById(R.id.btn_update);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FacilityListAdapter.ViewHolder) convertView.getTag();
        }

        final Facility facility = facilities.get(position);
        final int id = facility.getCatId();
        final String catName = facility.getName();
        final String catCode = facility.getCode();
        final String catDesc = facility.getDescription();
        final String catRemind = facility.getReminder();

        viewHolder.tvCatName.setText(facility.getName().toString());
        viewHolder.tvCatDesc.setText(facility.getDescription().toString());
        viewHolder.tvCatCode.setText(facility.getCode().toString());
        viewHolder.tvCatReminder.setText(facility.getReminder().toString());

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Intent intent = new Intent(context, UpdateFacilityActivity.class);
                intent.putExtra("Id", facility.getCatId());
                intent.putExtra("Name", facility.getName());
                intent.putExtra("Code", catCode);
                intent.putExtra("Description", facility.getDescription());
                intent.putExtra("Reminder", facility.getReminder());

                context.startActivity(intent);

                /*App.club.removeFacility(facility.getName());
                refreshFacilities();*/
            }
        });
        return convertView;
    }


    public void refreshFacilities() {
        facilities.clear();
        facilities.addAll(App.club.getFacilities(this.context));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return facilities.size();
    }

    static class ViewHolder {
        TextView tvCatName, tvCatDesc, tvCatCode, tvCatReminder;
        Button btnUpdate;
    }
}
