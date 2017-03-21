package sg.edu.nus.clubmanagement.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.application.App;

public class AddMemberActivity extends AppCompatActivity {

  private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
  private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
  private SimpleDateFormat combinedFormatter = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault());
  Calendar currentCal = Calendar.getInstance();
  Calendar selectedDate = Calendar.getInstance();

  private EditText etMedicineName, etMedicineDesc, etreminder, etQuantity, etDosage, etThreshold, etDateIssued, etExpireFactor;
  TextView textView;
  Switch aSwitch;
  String selectedCatId = null, reminder;
  int quantity, dosage, threshold, expiryFactor;
  Member medicine;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_member);

    etMedicineName = (EditText) findViewById(R.id.et_medicine_name);
    etMedicineDesc = (EditText) findViewById(R.id.et_medicine_desc);

    Spinner spinner = (Spinner) findViewById(R.id.spinner1);
    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(AddMemberActivity.this,
            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categoryNames));
    categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(categoryAdapter);

      selectedCatId = spinner.getSelectedItem().toString().trim();

    etDateIssued = (EditText) findViewById(R.id.et_date_issued);

   /* etDateIssued.setText(dateFormatter.format(selectedDate.getTime()));
    etDateIssued.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DatePickerDialog.OnDateSetListener onDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    selectedDate = calendar;
                    etDateIssued.setText(dateFormatter.format(calendar.getTime()));
                  }
                };
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(AddMemberActivity.this, onDateSetListener,
                        currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                        currentCal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
      }
    });

    Calendar selectedStTime = Calendar.getInstance();
    try {
      selectedStTime.setTime(timeFormatter.parse(etDateIssued.getText().toString()));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    final Calendar selStartDate = Calendar.getInstance();
    selStartDate.set(Calendar.YEAR, selectedDate.get(Calendar.YEAR));
    selStartDate.set(Calendar.MONTH, selectedDate.get(Calendar.MONTH));
    selStartDate.set(Calendar.DATE, selectedDate.get(Calendar.DATE));
    selStartDate.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
    selStartDate.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
    selStartDate.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));
*/


       /* aSwitch = (Switch) findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(this);*/

    etreminder = (EditText) findViewById(R.id.et_remind_id);
    etQuantity = (EditText) findViewById(R.id.et_quantity);
    etDosage = (EditText) findViewById(R.id.et_dosage);
    etThreshold = (EditText) findViewById(R.id.et_threshold);

    etExpireFactor = (EditText) findViewById(R.id.et_expiry_factor);

    try {
      quantity = Integer.parseInt(etQuantity.getText().toString());
      dosage = Integer.parseInt(etDosage.getText().toString());
      threshold = Integer.parseInt(etThreshold.getText().toString());
      expiryFactor = Integer.parseInt(etExpireFactor.getText().toString());
    }catch (NumberFormatException e){
      e.printStackTrace();
    }


    Button btnSave = (Button) findViewById(R.id.btn_save);
    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (isValid()) {
            Log.d("Niv","In add medicine");
            App.club.addMember(etMedicineName.getText().toString().trim(), etMedicineDesc.getText().toString().trim(),
                    selectedCatId, etreminder.getText().toString().trim(),
                    quantity,
                    dosage, threshold,
                    etDateIssued.getText().toString(), expiryFactor, getApplicationContext());
            Toast.makeText(AddMemberActivity.this, getString(R.string.save_successful),
              Toast.LENGTH_SHORT).show();
          finish();
        }
      }
    });
  }

  private boolean isValid() {
    boolean isValid = true;
    if (TextUtils.isEmpty(etMedicineName.getText().toString().trim())) {
        etMedicineName.setError(getString(R.string.first_name_validation_msg));
      isValid = false;
    }
    if (TextUtils.isEmpty(etMedicineDesc.getText().toString().trim())) {
        etMedicineDesc.setError(getString(R.string.second_name_validation_msg));
      isValid = false;
    }
    if (TextUtils.isEmpty(etDosage.getText().toString().trim())) {
        etDosage.setError(getString(R.string.sur_name_validation_msg));
      isValid = false;
    }
    return isValid;
  }
}
