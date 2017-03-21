package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.dao.FacilityDAO;

/**
 * Created by rama on 3/21/2017.
 */

public class UpdateFacility extends AsyncTask<Facility, Void, Long> {

    Facility facility = null;
    private FacilityDAO facilityDAO;

    public UpdateFacility (Context context) {
        this.facilityDAO = new FacilityDAO(context);
    }

    @Override
    protected Long doInBackground(Facility... params) {
        long result = facilityDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        if (result != -1)

            if (facilityDAO != null)
                facilityDAO.close();
    }



}
