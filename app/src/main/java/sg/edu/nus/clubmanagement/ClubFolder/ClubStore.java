package sg.edu.nus.clubmanagement.ClubFolder;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.clubmanagement.R;


/**
 * Created by Swarna on 8/6/2016.
 */
public class ClubStore {
  private Club club;

  public ClubStore(Club club) {
    this.club = club;
  }

  /**
   * Load the club information from files
   *
   * @param context: Application context
   */
  public void loadClub(Context context) {
    //loadMembers(context);
    loadFacilities(context);
  }

  /**
   * Save the club to files
   *
   * @param context: Application context
   */
  public void saveAll(Context context) {
    //saveMembers(context);
  }

  /**
   * Load members from file
   *
   * @param context: Application context
   */
/*  private void loadMembers(Context context) {
    String memFileName = "member.txt";
    List<String> strMemList = readFromFile(context, memFileName);
    for (String str : strMemList) {
      String[] strList = str.split(",");
      if (strList.length > 2) {
        club.addMember(strList[2], strList[0], strList[1]);
      }
    }
  }*/

  /**
   * Load members from file
   *
   * @param context: Application context
   */
  private void loadFacilities(Context context) {
    Facility fac1 = new Facility("Supplement", "SUP", "Set reminder", "Y");
    Facility fac2 = new Facility("Chronic", "CHR", "Set reminder", "Y");
    Facility fac3 = new Facility("Incidental", "INC", "Set reminder", "Y");

    club.addFacility(fac1.getName(), fac1.getDescription(), fac1.getCode(), fac1.getReminder());
    club.addFacility(fac2.getName(), fac2.getDescription(),fac2.getCode(), fac2.getReminder());
    club.addFacility(fac3.getName(), fac3.getDescription(),fac3.getCode(), fac3.getReminder());
  }

  /**
   * Read contents from file and return string list
   *
   * @param context: Application context
   * @param fileName: File name
   * @return str: List<String>
   */
  private List<String> readFromFile(Context context, String fileName) {
    List<String> listStr = new ArrayList<>();
    File path = context.getFilesDir();
    File file = new File(path, fileName);
    try {
      FileInputStream in = new FileInputStream(file);
      InputStreamReader isr = new InputStreamReader(in);
      BufferedReader bufferedReader = new BufferedReader(isr);
      String strLine = bufferedReader.readLine();
      while (strLine != null && !strLine.trim().isEmpty()) {
        listStr.add(strLine);
        strLine = bufferedReader.readLine();
      }
      isr.close();
      in.close();
    } catch (FileNotFoundException e) {
      Log.e("CLUB STORE", e.toString());
    } catch (IOException e) {
      Log.e("CLUB STORE", e.toString());
      Toast.makeText(context, context.getString(R.string.generic_error), Toast.LENGTH_SHORT).show();
    }
    return listStr;
  }

  /**
   * Save members to file
   *
   * @param context: Application context
   */
/*  private void saveMembers(Context context) {
    StringBuilder str = new StringBuilder();
    for (Member member : club.getMembers(context)) {
      str.append(member.getFirstName())
          .append(",")
          .append(member.getSecondName())
          .append(",")
          .append(member.getSurname())
          .append("\n");
    }

    String memFileName = "member.txt";
    saveToFile(context, memFileName, str.toString());
  }*/

  /**
   * @param context: Application context
   * @param fileName: File name
   * @param str: String to save
   */
  private void saveToFile(Context context, String fileName, String str) {
    File path = context.getFilesDir();
    File file = new File(path, fileName);
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      
      FileOutputStream stream = new FileOutputStream(file, false);
      stream.write(str.getBytes());
      stream.close();
    } catch (IOException e) {
      Log.e("CLUB STORE", e.toString());
      Toast.makeText(context, context.getString(R.string.generic_error), Toast.LENGTH_SHORT).show();
    }
  }
}
