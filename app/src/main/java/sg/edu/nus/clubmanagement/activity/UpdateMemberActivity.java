package sg.edu.nus.clubmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sg.edu.nus.clubmanagement.ClubFolder.Member;
import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.application.App;

/**
 * Created by rama on 3/21/2017.
 */

public class UpdateMemberActivity extends AppCompatActivity{

    EditText editName,editDescription,editReminder,editCategory,editRemind,editQuantity,editDosage,editThreshold,editDateIssued,
            editExpiryFactor;
    View mView;
    Button updateBtn;
    int quantity, dosage, threshold, expiryFactor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        mView = getLayoutInflater().inflate(R.layout.fragment_member, null);

        Intent intent = getIntent();

        final int medId = intent.getExtras().getInt("medId");
        final String medName = intent.getExtras().getString("medName");
        final String medDesc = intent.getExtras().getString("medDesc");
        final String medCat = intent.getExtras().getString("medCat");
        final String medRemind = intent.getExtras().getString("medRemind");
        final String medQuantity = intent.getExtras().getString("medQuantity");
        final String medDosage = intent.getExtras().getString("medDosage");
        final String medThreshold = intent.getExtras().getString("medThreshold");
        final String medDateIssued = intent.getExtras().getString("medDateIssued");
        final String medExpiryFactor = intent.getExtras().getString("medExpiryFactor");

        editName = (EditText) findViewById(R.id.et_medName);
        editDescription = (EditText) findViewById(R.id.et_medDesc);
        editCategory = (EditText) findViewById(R.id.et_medCat);
        editRemind = (EditText) findViewById(R.id.et_medRemind);
        editQuantity = (EditText) findViewById(R.id.et_medQuantity);
        editDosage = (EditText) findViewById(R.id.et_medDosage);
        editThreshold = (EditText) findViewById(R.id.et_medThreshold);
        editDateIssued = (EditText) findViewById(R.id.et_medDateIssued);
        editExpiryFactor = (EditText) findViewById(R.id.et_medExpiryFactor);


        editName.setText(medName);
        editDescription.setText(medDesc);
        editCategory.setText(medCat);
        editRemind.setText(medRemind);
        editQuantity.setText(medQuantity);
        editDosage.setText(medDosage);
        editThreshold.setText(medThreshold);
        editDateIssued.setText(medDateIssued);
        editExpiryFactor.setText(medExpiryFactor);

        try {
            quantity = Integer.parseInt(editQuantity.getText().toString());
            dosage = Integer.parseInt(editDosage.getText().toString());
            threshold = Integer.parseInt(editThreshold.getText().toString());
            expiryFactor = Integer.parseInt(editExpiryFactor.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }


        updateBtn = (Button) findViewById(R.id.btn_med_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Member m = new Member(medId, editName.getText().toString(), editDescription.getText().toString(),
                        editCategory.getText().toString(),editRemind.getText().toString(),quantity,dosage,threshold,
                        editDateIssued.getText().toString(),expiryFactor);

                App.club.updateMember(m, getApplicationContext());
                finish();
            }
        });
    }

}
