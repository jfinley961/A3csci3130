package com.acme.a3csci3130;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, businessField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.e_name);
        addressField = (EditText) findViewById(R.id.e_address);
        provinceField = (EditText) findViewById(R.id.e_province);
        numberField = (EditText) findViewById(R.id.e_number);
        businessField = (EditText) findViewById(R.id.e_business);
    }

    public void submitInfoButton(View v) throws InterruptedException {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String number = numberField.getText().toString();
        String name = nameField.getText().toString();
        String business = businessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        BusinessData b = new BusinessData(businessID, number, name, business, address, province);

        appState.firebaseReference.child(businessID).setValue(b).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(appState, "Successfully added to database", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(appState, "Improperly Formatted Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
