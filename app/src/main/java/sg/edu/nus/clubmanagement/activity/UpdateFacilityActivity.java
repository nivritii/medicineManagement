package sg.edu.nus.clubmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.application.App;

/**
 * Created by rama on 3/21/2017.
 */

public class UpdateFacilityActivity extends AppCompatActivity{

    EditText editName, editCode, editDescription, editReminder;
    View mView;
    Button updateBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_facility);

        mView = getLayoutInflater().inflate(R.layout.fragment_facility, null);

        Intent intent = getIntent();

        final int id = intent.getExtras().getInt("Id");
        final String code = intent.getExtras().getString("Code");
        final String name = intent.getExtras().getString("Name");
        final String description = intent.getExtras().getString("Description");
        final String reminder = intent.getExtras().getString("Reminder");

        editName = (EditText) findViewById(R.id.et_catName);
        editCode = (EditText) findViewById(R.id.et_catCode);
        editDescription = (EditText) findViewById(R.id.et_catDesc);
        editReminder = (EditText) findViewById(R.id.et_catReminder);

        editName.setText(name);
        editCode.setText(code);
        editDescription.setText(description);
        editReminder.setText(reminder);

        updateBtn = (Button) findViewById(R.id.btn_cat_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.club.updateFacility(id, editName.getText().toString(), editCode.getText().toString(), editDescription.getText().toString(),editReminder.getText().toString(),
                        getApplicationContext());

                finish();
            }
        });
    }

}
