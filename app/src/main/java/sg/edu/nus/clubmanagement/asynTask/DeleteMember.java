package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.dao.MemberDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class DeleteMember extends AsyncTask<Member, Void, Integer> {
    Member member = null;
    private MemberDAO memberDAO;

    public DeleteMember(Context context) {
        this.memberDAO = new MemberDAO(context);
    }

    @Override
    protected Integer doInBackground(Member... params) {
        int result = memberDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != -1)

            if (memberDAO != null)
                memberDAO.close();
    }
}
