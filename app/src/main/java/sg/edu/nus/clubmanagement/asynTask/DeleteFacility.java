package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.dao.FacilityDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class DeleteFacility extends AsyncTask<Facility, Void, Integer> {
    Facility member = null;
    private FacilityDAO facilityDAO;

    public DeleteFacility(Context context) {
        this.facilityDAO = new FacilityDAO(context);
    }

    @Override
    protected Integer doInBackground(Facility... params) {
        int result = facilityDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != -1)

            if (facilityDAO != null)
                facilityDAO.close();
    }
}
