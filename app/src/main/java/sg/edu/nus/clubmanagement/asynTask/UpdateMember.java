package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.dao.MemberDAO;

/**
 * Created by rama on 3/21/2017.
 */

public class UpdateMember extends AsyncTask<Member, Void, Long> {

    Member member = null;
    private MemberDAO memberDAO;

    public UpdateMember(Context context) {
        this.memberDAO = new MemberDAO(context);
    }

    @Override
    protected Long doInBackground(Member... params) {
        long result = memberDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        if (result != -1)

            if (memberDAO != null)
                memberDAO.close();
    }



}
