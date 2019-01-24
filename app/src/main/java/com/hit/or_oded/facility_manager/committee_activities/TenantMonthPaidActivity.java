package com.hit.or_oded.facility_manager.committee_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.logics.Logics;


public class TenantMonthPaidActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_month_paid);
    }

    public void buttonOK_clicked(View view)
    {
        String tenantID = ((EditText)findViewById(R.id.editTextTenantId)).getText().toString();

        Logics.getInstance().askTenantMonthsPaid(tenantID);
        this.finish();
    }

    public void buttonCancel_clicked(View view)
    {
        this.finish();
    }
}
