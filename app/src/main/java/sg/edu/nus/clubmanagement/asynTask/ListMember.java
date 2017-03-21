package sg.edu.nus.clubmanagement.asynTask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.dao.MemberDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class ListMember extends AsyncTask<Void, Void, ArrayList<Member>> {
    //private final WeakReference<Activity> activityWeakRef;

    ArrayList<Member> members;
    private MemberDAO memberDAO;

    public ListMember(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context);
        this.memberDAO = new MemberDAO(context);
    }

    @Override
    protected ArrayList<Member> doInBackground(Void... arg0) {
        ArrayList<Member> memberList = memberDAO.getMembers();
        return memberList;
    }

    @Override
    protected void onPostExecute(ArrayList<Member> memList) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
            Log.d("members", memList.toString());
            members = memList;

        if (memList == null) {
            members = new ArrayList<Member>();
        }

        /*
            if (memList != null) {
                if (memList.size() != 0) {
                    // do something if required

                    //memberListAdapter = new ReminderListAdapter(getApplicationContext(), remList);
                    //reminderListView.setAdapter(reminderListAdapter);
                } else {
                    Toast.makeText(context, "No member", Toast.LENGTH_LONG).show();
                }
            }
            */
        //}
    }

    public ArrayList<Member> getMemberList() {
        return this.members;
    }
}
