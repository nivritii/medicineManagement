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

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.activity.UpdateMemberActivity;
import sg.edu.nus.clubmanagement.application.App;

/**
 * Created by Swarna on 8/6/2016.
 */
public class MemberListAdapter extends ArrayAdapter<Member> {
  private Context context;
  private List<Member> members = new ArrayList<>();

  public MemberListAdapter(Context context) {
    super(context, R.layout.medicine_row_layout);
    this.context = context;
    refreshMembers();
  }

  @Override public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.medicine_row_layout, parent, false);
      viewHolder = new ViewHolder();
      viewHolder.tvMedName = (TextView) convertView.findViewById(R.id.tv_medName);
      viewHolder.tvMedDesc = (TextView) convertView.findViewById(R.id.tv_medDesc);
      viewHolder.tvMedCat = (TextView) convertView.findViewById(R.id.tv_medCode);
      viewHolder.btnMedUpdate = (Button) convertView.findViewById(R.id.btn_med_update);
      viewHolder.btnMedRemove = (Button) convertView.findViewById(R.id.btn_med_delete);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    final Member member = members.get(position);
    viewHolder.tvMedName.setText(member.getMedName().toString());
    viewHolder.tvMedDesc.setText(member.getMedDesc().toString());
    viewHolder.tvMedCat.setText(member.getCategory().toString());
    /*viewHolder.tvMedReminder.setText(member.getReminder().toString());
    viewHolder.tvMedQuantity.setText(member.getQuantity());
    viewHolder.tvMedDosage.setText(member.getDosage());
    viewHolder.tvMedThreshold.setText(member.getThreshold());
    viewHolder.tvMedDateIssued.setText(member.getDateIssued());
    viewHolder.tvMedExpireFac.setText(member.getExpireFactor());
*/
      final String msg = "Medicine is deleted";
      final String msgRestore = "Medicine restored";

    viewHolder.btnMedRemove.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        App.club.removeMember(member, getContext());
        refreshMembers();

/*        Snackbar snackbar = Snackbar.make(coordinatorLayout,,Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout,@,Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                  }
                });

        snackbar.show();*/
      }
    });


      viewHolder.btnMedUpdate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent = new Intent(context, UpdateMemberActivity.class);
              intent.putExtra("medId", member.getMedId());
              intent.putExtra("medName", member.getMedName());
              intent.putExtra("medDesc", member.getMedDesc());
              intent.putExtra("medCat", member.getCategory());
              intent.putExtra("medRemind", member.getReminder());
              intent.putExtra("medQuantity", member.getQuantity());
              intent.putExtra("medDosage", member.getDosage());
              intent.putExtra("medThreshold", member.getThreshold());
              intent.putExtra("medDateIssued", member.getDateIssued());
              intent.putExtra("medExpiryFactor", member.getExpireFactor());

              context.startActivity(intent);


              /*App.club.updateMember(member,getContext());
              refreshMembers();*/
          }
      });


    return convertView;
  }

  public void refreshMembers() {
    members.clear();
    members.addAll(App.club.getMembers(this.context));
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return members.size();
  }

  static class ViewHolder {
    TextView tvMedName, tvMedDesc, tvMedCat, tvMedReminder, tvMedQuantity, tvMedDosage, tvMedThreshold, tvMedDateIssued, tvMedExpireFac;
    Button btnMedUpdate, btnMedRemove;
  }
}
