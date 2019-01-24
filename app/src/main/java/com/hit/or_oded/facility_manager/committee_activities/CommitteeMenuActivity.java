package com.hit.or_oded.facility_manager.committee_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.activities.LoginActivity;
import com.hit.or_oded.facility_manager.logics.IServerResponseListener;
import com.hit.or_oded.facility_manager.logics.Logics;

import com.projects.persons.Person;
import com.projects.persons.Provider;
import com.projects.server_messages.AddOrUpdateProviderResponse;
import com.projects.server_messages.AddTenantPaymentResponse;
import com.projects.server_messages.ApartmentMonthlyPaymentsResponse;
import com.projects.server_messages.ApartmentsPaymentsResponse;
import com.projects.server_messages.IServerResponse;
import com.projects.server_messages.OptimalProviderResponse;
import com.projects.server_messages.ProviderByCategoryResponse;
import com.projects.server_messages.TenantMonthsPaidResponse;

public class CommitteeMenuActivity extends AppCompatActivity implements IServerResponseListener
{
    private Logics mLogics;
    private String mUserInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_menu);

        Intent intent = getIntent();
        Person connectedPerson = (Person)intent.getSerializableExtra(LoginActivity.CONNECTED_PERSON);
        TextView titleTextView = findViewById(R.id.textViewTitle);

        titleTextView.setText("Hi " + connectedPerson.firstName());

        mLogics = Logics.getInstance();
        mLogics.setListener(this);
    }

    public void buttonShowTenantMonthPaid_clicked(View view)
    {
        Intent intent = new Intent(this, TenantMonthPaidActivity.class);

        startActivity(intent);
    }

    public void buttonShowApartmentPayments_clicked(View view)
    {
        mLogics.askApartmentsPayments();
    }

    public void buttonAddTenantPayment_clicked(View view)
    {
        Intent intent = new Intent(this, AddTenantPaymentActivity.class);

        startActivity(intent);
    }

    public void buttonShowApartmentMonthlyPayments_clicked(View view)
    {
        Intent intent = new Intent(this, ApartmentMonthlyPaymentsInputActivity.class);

        startActivity(intent);
    }

    public void buttonShowProviders_clicked(View view)
    {
        Intent intent = new Intent(this, GetProviderCategoryActivity.class);

        intent.putExtra(GetProviderCategoryActivity.INCLUDE_ALL_CATEGORY, true);

        startActivity(intent);
    }

    public void buttonFindOptimalProvider_clicked(View view)
    {
        Intent intent = new Intent(this, GetProviderCategoryActivity.class);

        intent.putExtra(GetProviderCategoryActivity.INCLUDE_ALL_CATEGORY, false);

        startActivity(intent);
    }

    public void buttonAddOrUpdateProvider_clicked(View view)
    {
        Intent intent = new Intent(this, AddNewProviderActivity.class);

        startActivity(intent);
    }

    @Override
    public void onServerResponse(IServerResponse response)
    {
        if(response != null)
        {
            if (response instanceof TenantMonthsPaidResponse)
            {
                showTenantMonthPaid((TenantMonthsPaidResponse) response);
            }
            else if (response instanceof ApartmentsPaymentsResponse)
            {
                showApartmentsPayments((ApartmentsPaymentsResponse) response);
            }
            else if (response instanceof AddTenantPaymentResponse)
            {
                handleAddTenantPaymentResponse((AddTenantPaymentResponse) response);
            }
            else if (response instanceof ApartmentMonthlyPaymentsResponse)
            {
                showApartmentMonthlyPayments((ApartmentMonthlyPaymentsResponse) response);
            }
            else if (response instanceof ProviderByCategoryResponse)
            {
                showProvidersList((ProviderByCategoryResponse) response);
            }
            else if (response instanceof OptimalProviderResponse)
            {
                showOptimalProvider((OptimalProviderResponse) response);
            }
            else if (response instanceof AddOrUpdateProviderResponse)
            {
                handleAddOrUpdateProviderResponse((AddOrUpdateProviderResponse) response);
            }
        }
        else{
            Toast.makeText(this, "Server is not responding",Toast.LENGTH_SHORT).show();
        }
    }

    private void showTenantMonthPaid(TenantMonthsPaidResponse response)
    {
        showMessageDialog("Paid Months Are", response.tenantPayments());
    }

    private void showApartmentsPayments(ApartmentsPaymentsResponse response)
    {
        String[] apartmentsPayments = response.apartmentsPayments();

        for (String currentApartment : apartmentsPayments)
        {
            showMessageDialog("Apartment " + currentApartment.substring(0, 1),
                    "Months been Paid: \n\n" +
                            currentApartment.substring(2) + ", ");
        }
    }

    private void handleAddTenantPaymentResponse(AddTenantPaymentResponse response)
    {
        boolean success = response.isSuccessed();
        String message = success ? "Payment added successfully" : "Error while adding payment";

        showMessageDialog("Add Tenant Payment", message);
    }

    private void showApartmentMonthlyPayments(ApartmentMonthlyPaymentsResponse response)
    {
        Intent intent = new Intent(this, ApartmentMonthlyPaymentsOutputActivity.class);

        intent.putExtra(ApartmentMonthlyPaymentsOutputActivity.MONTHLY_PAYMENTS_ARG, response.monthlyPayments());
        startActivity(intent);
    }

    private void showProvidersList(ProviderByCategoryResponse response)
    {
        Provider[] providers = response.providers();
        Integer providerNumber = 1;
        for(Provider provider: providers)
        {
            showMessageDialog("Provider Number: " + providerNumber++, provider.toString());
        }
    }

    private void showOptimalProvider(OptimalProviderResponse response)
    {
        showMessageDialog("Optimal Provider (" + response.provider().category() + ")", response.provider().toString());
    }

    private void handleAddOrUpdateProviderResponse(AddOrUpdateProviderResponse response)
    {
        boolean success = response.isSucceeded();
        String message = success ? "Added new provider successfully" : "Error while adding new provider";

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
