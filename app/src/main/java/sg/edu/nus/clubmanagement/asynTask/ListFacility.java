package sg.edu.nus.clubmanagement.asynTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.dao.FacilityDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class ListFacility extends AsyncTask<Void, Void, ArrayList<Facility>>  {
    ArrayList<Facility> facilities;
    private FacilityDAO facilityDAO;

    public ListFacility(Context context) {
        this.facilityDAO = new FacilityDAO(context);
    }

    @Override
    protected ArrayList<Facility> doInBackground(Void... arg0) {
        ArrayList<Facility> facilityList = facilityDAO.getFacilities();
        return facilityList;
    }

    @Override
    protected void onPostExecute(ArrayList<Facility> facList) {
        Log.d("facilities", facList.toString());
        facilities = facList;

        if (facList == null) {
            facilities = new ArrayList<Facility>();
        }
    }
}
