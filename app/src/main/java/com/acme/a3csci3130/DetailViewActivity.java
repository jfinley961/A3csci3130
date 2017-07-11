package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, businessField, addressField, provinceField;
    BusinessData receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (BusinessData)getIntent().getSerializableExtra("Business");

        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.e_name);
        addressField = (EditText) findViewById(R.id.e_address);
        provinceField = (EditText) findViewById(R.id.e_province);
        numberField = (EditText) findViewById(R.id.e_number);
        businessField = (EditText) findViewById(R.id.e_business);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setText(receivedBusinessInfo.province);
            numberField.setText(receivedBusinessInfo.number);
            businessField.setText(receivedBusinessInfo.business);
        }
    }

    public void updateContact(View v){
        final Intent i = new Intent(this, MainActivity.class);
        appState.firebaseReference.child(receivedBusinessInfo.uid).setValue(
                new BusinessData(receivedBusinessInfo.uid, numberField.getText().toString(),
                        nameField.getText().toString(), businessField.getText().toString(),
                        addressField.getText().toString(), provinceField.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(appState, "Successfully updated database", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(appState, "Improperly Formatted Data", Toast.LENGTH_SHORT).show();
            }
        });;
    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedBusinessInfo.uid).removeValue();
        Toast.makeText(appState, "Successfully deleted from database", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
