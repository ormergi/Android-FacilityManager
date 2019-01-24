package com.hit.or_oded.facility_manager.committee_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.logics.Logics;

public class ApartmentMonthlyPaymentsInputActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_monthly_payments_input);
    }

    public void buttonOK_clicked(View view)
    {
        String apartmentNumber = ((EditText)findViewById(R.id.editTextApartmentNumber)).getText().toString();

        Logics.getInstance().askApartmentMonthlyPayments(Integer.parseInt(apartmentNumber));
        this.finish();
    }

    public void buttonCancel_clicked(View view)
    {
        this.finish();
    }
}
