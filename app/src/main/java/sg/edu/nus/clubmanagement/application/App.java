package sg.edu.nus.clubmanagement.application;

import android.app.Application;
import sg.edu.nus.clubmanagement.ClubFolder.Club;
import sg.edu.nus.clubmanagement.ClubFolder.ClubStore;

/**
 * Created by swarna on 9/8/16.
 */
public class App extends Application {
  public static Club club;
  public static ClubStore clubStore;

  @Override public void onCreate() {
    super.onCreate();
    club = new Club();
   // clubStore = new ClubStore(club);
   // clubStore.loadClub(getApplicationContext());
  }
}
