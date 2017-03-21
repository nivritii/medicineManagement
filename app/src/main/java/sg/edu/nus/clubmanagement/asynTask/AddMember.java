package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.dao.MemberDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class AddMember extends AsyncTask<Member, Void, Long> {
    //private final WeakReference<Activity> activityWeakRef;

    Member member = null;
    private MemberDAO memberDAO;

    public AddMember(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context.getApplicationContext());
        this.memberDAO = new MemberDAO(context);
    }

    @Override
    protected Long doInBackground(Member... params) {
        long result = memberDAO.save(params[0]);
        Log.d("Niv", "Saving medicine in background");
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
            if (result != -1)
                //Toast.makeText(context, "Member Saved", Toast.LENGTH_LONG).show();

            if (memberDAO != null)
                memberDAO.close();
        //}
    }
}
