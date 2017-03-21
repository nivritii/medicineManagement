package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.dao.FacilityDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class AddFacility extends AsyncTask<Facility, Void, Long> {
    Facility facility = null;
    private FacilityDAO facilityDAO;

    public AddFacility(Context context) {
        this.facilityDAO = new FacilityDAO(context);
    }

    @Override
    protected Long doInBackground(Facility... params) {
        long result = facilityDAO.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        if (result != -1)

            if (facilityDAO != null)
                facilityDAO.close();
    }
}
