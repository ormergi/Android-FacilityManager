package com.hit.or_oded.facility_manager.committee_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.logics.Logics;

public class AddTenantPaymentActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant_payment);
    }

    public void buttonOK_clicked(View view)
    {
        String tenantID = ((EditText)findViewById(R.id.editTextApartmentNumber)).getText().toString();
        String month =  ((EditText)findViewById(R.id.editTextMonth)).getText().toString();
        String amount =  ((EditText)findViewById(R.id.editTextAmount)).getText().toString();

        Logics.getInstance().addTenantPayment(tenantID, Integer.parseInt(month), Integer.parseInt(amount));
        this.finish();
    }

    public void buttonCancel_clicked(View view)
    {
        this.finish();
    }
}
