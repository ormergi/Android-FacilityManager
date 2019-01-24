package com.hit.or_oded.facility_manager.tenant_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.activities.LoginActivity;
import com.hit.or_oded.facility_manager.logics.IServerResponseListener;
import com.hit.or_oded.facility_manager.logics.Logics;

import com.projects.persons.Person;
import com.projects.server_messages.IServerResponse;
import com.projects.server_messages.TenantPaymentsResponse;

public class TenantMenuActivity extends AppCompatActivity implements IServerResponseListener
{
    public static final String PAYMENTS_ARRAY = "PAYMENTS_ARRAY";
    private Logics mLogics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_menu);

        Intent intent = getIntent();
        Person connectedPerson = (Person)intent.getSerializableExtra(LoginActivity.CONNECTED_PERSON);
        TextView titleTextView = findViewById(R.id.textViewTitle);

        titleTextView.setText("Hi " + connectedPerson.firstName());

        mLogics = Logics.getInstance();
        mLogics.setListener(this);
    }

    public void buttonShowMyPayments_clicked(View view)
    {
        mLogics.askTenantPayments();
    }

    @Override
    public void onServerResponse(IServerResponse response)
    {
        if (response instanceof TenantPaymentsResponse)
        {
            showTenantPayments(((TenantPaymentsResponse)response).payments());
        }
    }

    private void showTenantPayments(int[] payments)
    {
        Intent intent = new Intent(this, TenantPaymentsActivity.class);

        intent.putExtra(PAYMENTS_ARRAY, payments);
        startActivity(intent);
    }
}
