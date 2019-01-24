package com.hit.or_oded.facility_manager.tenant_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hit.or_oded.facility_manager.R;

public class TenantPaymentsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_payments);

        Intent intent = getIntent();

        int[] payments = intent.getIntArrayExtra(TenantMenuActivity.PAYMENTS_ARRAY);

        ((TextView)findViewById(R.id.textViewJanuaryAmount)).setText(String.valueOf(payments[0]));
        ((TextView)findViewById(R.id.textViewFebruaryAmount)).setText(String.valueOf(payments[1]));
        ((TextView)findViewById(R.id.textViewMarchAmount)).setText(String.valueOf(payments[2]));
        ((TextView)findViewById(R.id.textViewAprilAmount)).setText(String.valueOf(payments[3]));
        ((TextView)findViewById(R.id.textViewMayAmount)).setText(String.valueOf(payments[4]));
        ((TextView)findViewById(R.id.textViewJuneAmount)).setText(String.valueOf(payments[5]));
        ((TextView)findViewById(R.id.textViewJulyAmount)).setText(String.valueOf(payments[6]));
        ((TextView)findViewById(R.id.textViewAugustAmount)).setText(String.valueOf(payments[7]));
        ((TextView)findViewById(R.id.textViewSeptemberAmount)).setText(String.valueOf(payments[8]));
        ((TextView)findViewById(R.id.textViewOctoberAmount)).setText(String.valueOf(payments[9]));
        ((TextView)findViewById(R.id.textViewNovemberAmount)).setText(String.valueOf(payments[10]));
        ((TextView)findViewById(R.id.textViewDecemberAmount)).setText(String.valueOf(payments[11]));
    }

    public void buttonClose_clicked(View view)
    {
        this.finish();
    }
}
