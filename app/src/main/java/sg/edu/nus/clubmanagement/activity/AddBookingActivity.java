package sg.edu.nus.clubmanagement.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.clubmanagement.ClubFolder.BadBookingException;
import sg.edu.nus.clubmanagement.ClubFolder.Booking;
import sg.edu.nus.clubmanagement.ClubFolder.Facility;
import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.application.App;

public class AddBookingActivity extends AppCompatActivity {

  private Spinner spnMember, spnFacility;
  private EditText etDate, etStrtTime, etEndTime;
  private Button btnSave;
  private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
  private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
  private SimpleDateFormat combinedFormatter = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault());
  Calendar currentCal = Calendar.getInstance();
  Calendar selectedDate = Calendar.getInstance();


  List<Member> memberList = null;
  List<Facility> facilityList =  null;
  Member selMember = null;
  Facility selFacility = null;
  Date selStartDate = null;
  Date selEndDate = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_booking);

    spnMember = (Spinner) findViewById(R.id.spn_member);
    spnFacility = (Spinner) findViewById(R.id.spn_facility);
    etDate = (EditText) findViewById(R.id.et_select_date);
    etStrtTime = (EditText) findViewById(R.id.et_select_strt_time);
    etEndTime = (EditText) findViewById(R.id.et_select_end_time);
    btnSave = (Button) findViewById(R.id.btn_save);

    memberList = App.club.getMembers(this);
    List<String> spnMemList = new ArrayList<>();
    spnMemList.add("<Select Member>");
    for (Member member : memberList) {
      spnMemList.add(member.getMedId(), member.toString());
    }
    ArrayAdapter<String> spnMemAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnMemList);
    spnMember.setAdapter(spnMemAdapter);

    facilityList = App.club.getFacilities(this);
    List<String> spnFacList = new ArrayList<>();
    spnFacList.add("<Select Facility>");
    for (Facility facility : facilityList) {
      spnFacList.add(facility.getFacilityNumber(), facility.toString());
    }
    ArrayAdapter<String> spnFacAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnFacList);
    spnFacility.setAdapter(spnFacAdapter);

    etDate.setText(dateFormatter.format(selectedDate.getTime()));
    etDate.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                selectedDate = calendar;
                etDate.setText(dateFormatter.format(calendar.getTime()));
              }
            };
        DatePickerDialog datePickerDialog =
            new DatePickerDialog(AddBookingActivity.this, onDateSetListener,
                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                currentCal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
      }
    });

    etStrtTime.setText(timeFormatter.format(currentCal.getTime()));
    Calendar timeCalendar = Calendar.getInstance();
    timeCalendar.setTime(currentCal.getTime());
    timeCalendar.set(Calendar.HOUR, currentCal.get(Calendar.HOUR));
    timeCalendar.set(Calendar.MINUTE, currentCal.get(Calendar.MINUTE));
    timeCalendar.set(Calendar.AM_PM, currentCal.get(Calendar.AM_PM));
    timeCalendar.add(Calendar.HOUR, 1);
    etEndTime.setText(timeFormatter.format(timeCalendar.getTime()));

    View.OnClickListener timeClickListener = new View.OnClickListener() {
      @Override public void onClick(final View v) {
        final EditText editText = (EditText) v;
        TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
              @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                editText.setText(timeFormatter.format(calendar.getTime()));
              }
            };
        Calendar timeCalendar = Calendar.getInstance();
        try {
          timeCalendar.setTime(timeFormatter.parse(editText.getText().toString()));
        } catch (ParseException e) {
          Toast.makeText(AddBookingActivity.this, R.string.generic_error, Toast.LENGTH_SHORT)
              .show();
        }
        TimePickerDialog timePickerDialog =
            new TimePickerDialog(AddBookingActivity.this, timeSetListener,
                timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
      }
    };

    etStrtTime.setOnClickListener(timeClickListener);
    etEndTime.setOnClickListener(timeClickListener);

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (isValid()) {
         // Toast.makeText(AddBookingActivity.this, "Proceed booking!", Toast.LENGTH_SHORT).show();

          Member selectedMember = null;
          Facility selectedFacility = null;

          for (Member m : memberList) {
            Long id = spnMember.getSelectedItemId();
            if (id.intValue() == m.getMedId()) {
              //Toast.makeText(AddBookingActivity.this, "Correct Member Selected!", Toast.LENGTH_SHORT).show();
              selectedMember = m;
            }
          }

          for (Facility f : facilityList) {
            Long id = spnFacility.getSelectedItemId();
            if (id.intValue() == f.getFacilityNumber()) {
              //Toast.makeText(AddBookingActivity.this, "Correct Facility Selected!", Toast.LENGTH_SHORT).show();
              selectedFacility = f;
            }
          }

          Calendar selectedStTime = Calendar.getInstance();
          try {
            selectedStTime.setTime(timeFormatter.parse(etStrtTime.getText().toString()));
          } catch (ParseException e) {
            e.printStackTrace();
          }
/*          Calendar convertedStTime = Calendar.getInstance();
          convertedStTime.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
          convertedStTime.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
          convertedStTime.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));*/

          Calendar selectedEtTime = Calendar.getInstance();
          try {
            selectedEtTime.setTime(timeFormatter.parse(etEndTime.getText().toString()));
          } catch (ParseException e) {
            e.printStackTrace();
          }
/*          Calendar convertedEtTime = Calendar.getInstance();
          convertedEtTime.set(Calendar.HOUR, selectedEtTime.get(Calendar.HOUR));
          convertedEtTime.set(Calendar.MINUTE, selectedEtTime.get(Calendar.MINUTE));
          convertedEtTime.set(Calendar.AM_PM, selectedEtTime.get(Calendar.AM_PM));*/

          Calendar selStartDate = Calendar.getInstance();
          selStartDate.set(Calendar.YEAR, selectedDate.get(Calendar.YEAR));
          selStartDate.set(Calendar.MONTH, selectedDate.get(Calendar.MONTH));
          selStartDate.set(Calendar.DATE, selectedDate.get(Calendar.DATE));
          selStartDate.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
          selStartDate.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
          selStartDate.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));

          Calendar selEndDate = Calendar.getInstance();
          selEndDate.set(Calendar.YEAR, selectedDate.get(Calendar.YEAR));
          selEndDate.set(Calendar.MONTH, selectedDate.get(Calendar.MONTH));
          selEndDate.set(Calendar.DATE, selectedDate.get(Calendar.DATE));
          selEndDate.set(Calendar.HOUR, selectedEtTime.get(Calendar.HOUR));
          selEndDate.set(Calendar.MINUTE, selectedEtTime.get(Calendar.MINUTE));
          selEndDate.set(Calendar.AM_PM, selectedEtTime.get(Calendar.AM_PM));

          try {
            Booking b = new Booking(selectedMember.getMedId(), selectedFacility.getFacilityNumber(), selStartDate.getTime(), selEndDate.getTime());

            // Toast for testing only - Delete or comment when go LIVE
            // Toast.makeText(AddBookingActivity.this, b.toString(), Toast.LENGTH_SHORT).show();
            App.club.addBooking(b.getMemberNumber(), b.getFacilityNumber(), b.getStartDate(), b.getEndDate(), getApplicationContext());
          } catch (BadBookingException e) {
            e.printStackTrace();
            Toast.makeText(AddBookingActivity.this, getString(R.string.generic_error),
                    Toast.LENGTH_SHORT).show();
          }
          Toast.makeText(AddBookingActivity.this, getString(R.string.save_successful), Toast.LENGTH_SHORT).show();
          finish();

        }
      }
    });
  }

  private boolean isValid() {
    boolean isValid = true;
    if (spnMember.getSelectedItemPosition() == 0) {
      Toast.makeText(this, R.string.mem_select_validation_msg, Toast.LENGTH_SHORT).show();
      isValid = false;
    } else if (spnFacility.getSelectedItemPosition() == 0) {
      Toast.makeText(this, R.string.fac_select_validation_msg, Toast.LENGTH_SHORT).show();
      isValid = false;
    }

    try {
      Calendar selectedStTime = Calendar.getInstance();
      selectedStTime.setTime(timeFormatter.parse(etStrtTime.getText().toString()));
      Calendar convertedStTime = Calendar.getInstance();
      convertedStTime.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
      convertedStTime.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
      convertedStTime.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));

      Calendar selectedEtTime = Calendar.getInstance();
      selectedEtTime.setTime(timeFormatter.parse(etEndTime.getText().toString()));
      Calendar convertedEtTime = Calendar.getInstance();
      convertedEtTime.set(Calendar.HOUR, selectedEtTime.get(Calendar.HOUR));
      convertedEtTime.set(Calendar.MINUTE, selectedEtTime.get(Calendar.MINUTE));
      convertedEtTime.set(Calendar.AM_PM, selectedEtTime.get(Calendar.AM_PM));

      if (currentCal.getTime().compareTo(selectedDate.getTime()) > 0) {
        Toast.makeText(this, R.string.date_validation_msg, Toast.LENGTH_SHORT).show();
        isValid = false;
      } else if (currentCal.compareTo(convertedStTime) > 0) {
        Toast.makeText(this, R.string.time_validation_msg, Toast.LENGTH_SHORT).show();
        isValid = false;
      } else if (convertedStTime.compareTo(convertedEtTime) >= 0) {
        Toast.makeText(this, R.string.st_time_et_time_validation_msg, Toast.LENGTH_SHORT).show();
        isValid = false;
      }
    } catch (ParseException e) {
      Toast.makeText(this, R.string.generic_error, Toast.LENGTH_SHORT).show();
    }

    return isValid;
  }
}
