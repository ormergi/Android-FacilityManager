package com.hit.or_oded.facility_manager.committee_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hit.or_oded.facility_manager.R;

public class ApartmentMonthlyPaymentsOutputActivity extends AppCompatActivity
{
    public final static String MONTHLY_PAYMENTS_ARG = "MONTHLY_PAYMENTS_ARG";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_monthly_payments_output);

        Intent intent = getIntent();
        String monthlyPayments = intent.getStringExtra(MONTHLY_PAYMENTS_ARG);

        String[] splittedString = monthlyPayments.split(" ");

        for (int i = 0; i < splittedString.length; i += 2)
        {
            addMonthPayment(splittedString[i], splittedString[i+1]);
        }
    }

    private void addMonthPayment(String month, String amount)
    {
        switch (month)
        {
            case "1":
                ((TextView)findViewById(R.id.textViewJanuaryAmount)).setText(String.valueOf(amount));
                break;
            case "2":
                ((TextView)findViewById(R.id.textViewFebruaryAmount)).setText(String.valueOf(amount));
                break;
            case "3":
                ((TextView)findViewById(R.id.textViewMarchAmount)).setText(String.valueOf(amount));
                break;
            case "4":
                ((TextView)findViewById(R.id.textViewAprilAmount)).setText(String.valueOf(amount));
                break;
            case "5":
                ((TextView)findViewById(R.id.textViewMayAmount)).setText(String.valueOf(amount));
                break;
            case "6":
                ((TextView)findViewById(R.id.textViewJuneAmount)).setText(String.valueOf(amount));
                break;
            case "7":
                ((TextView)findViewById(R.id.textViewJulyAmount)).setText(String.valueOf(amount));
                break;
            case "8":
                ((TextView)findViewById(R.id.textViewAugustAmount)).setText(String.valueOf(amount));
                break;
            case "9":
                ((TextView)findViewById(R.id.textViewSeptemberAmount)).setText(String.valueOf(amount));
                break;
            case "10":
                ((TextView)findViewById(R.id.textViewOctoberAmount)).setText(String.valueOf(amount));
                break;
            case "11":
                ((TextView)findViewById(R.id.textViewNovemberAmount)).setText(String.valueOf(amount));
                break;
            case "12":
                ((TextView)findViewById(R.id.textViewDecemberAmount)).setText(String.valueOf(amount));
                break;
        }
    }

    public void buttonClose_clicked(View view)
    {
        this.finish();
    }
}
