package com.hit.or_oded.facility_manager.test_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.committee_activities.AddNewProviderActivity;
import com.hit.or_oded.facility_manager.committee_activities.AddTenantPaymentActivity;
import com.hit.or_oded.facility_manager.committee_activities.ApartmentMonthlyPaymentsOutputActivity;
import com.hit.or_oded.facility_manager.committee_activities.TenantMonthPaidActivity;

public class CommitteeMenuTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_menu_test);
    }

    public void buttonShowTenantMonthPaid_clicked(View view) {
        Intent intent = new Intent(this, TenantMonthPaidActivity.class);

        startActivity(intent);

        showMessageDialog(
                "Paid Months Are",
                "10, 11, 12");
    }

    public void buttonShowApartmentPayments_clicked(View view) {
        showMessageDialog(
                "Apartment " + 1,
                "Months been paid:\n\n" +"5, 6, 7, 8");
        showMessageDialog(
                "Apartment " + 2,
                "Months been paid:\n\n" +"10, 11, 12");
    }

    public void buttonAddTenantPayment_clicked(View view) {
        Intent intent = new Intent(this, AddTenantPaymentActivity.class);
        startActivity(intent);

        boolean success = true;
        String message = "Payment added successfully";

        showMessageDialog("Add Tenant Payment", message);
    }

    public void buttonShowApartmentMonthlyPayments_clicked(View view) {
        Intent intent = new Intent(this, ApartmentMonthlyPaymentsOutputActivity.class);
        String data = "1 5200 2 5100 3 5300 4 5100";
        intent.putExtra(ApartmentMonthlyPaymentsOutputActivity.MONTHLY_PAYMENTS_ARG, data);
        startActivity(intent);
    }

    public void buttonShowProviders_clicked(View view) {
        showMessageDialog(
                "Provider Number: 1",
                "Name: Dudu Israel \n" +
                        "Category: Electric \n" +
                        "Price: 7200 \n" +
                        "Avilabilty: Sunday, Tuseday, Friday \n"
                );
    }

    public void buttonFindOptimalProvider_clicked(View view) {
        showMessageDialog(
                "Provider Number: 17",
                "Name: Yair Yakobi \n" +
                        "Category: Handyman \n" +
                        "Price: 5600 \n" +
                        "Avilabilty: Sunday, Monday, Friday \n"
        );
    }

    public void buttonAddOrUpdateProvider_clicked(View view) {
        Intent intent = new Intent(this, AddNewProviderActivity.class);

        startActivity(intent);
        String message = "Added new provider successfully";

        showMessageDialog("Add New Provider", message);
    }

    private void showMessageDialog(String title, String message)
    {
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);

        builder.setTitle(title)//"Paid Months Are")
                .setMessage(message)//response.tenantPayments())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
