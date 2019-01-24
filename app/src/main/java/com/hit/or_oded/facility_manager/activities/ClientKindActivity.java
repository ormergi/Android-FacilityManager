package com.hit.or_oded.facility_manager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hit.or_oded.facility_manager.R;

import com.projects.enums.eClientKind;

public class ClientKindActivity extends AppCompatActivity
{
    public static final String TENANT_TYPE = "TENANT_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_kind);
    }

    public void chooseClientKind(View view)
    {
        eClientKind tenantType = null;
        switch (view.getId())
        {
            case R.id.buttonCommittee:
                tenantType = eClientKind.Committee;
                break;
            case R.id.buttonTenant:
                tenantType = eClientKind.Tenant;
                break;
            case R.id.buttonProvider:
                tenantType = eClientKind.Provider;
                break;
        }
        moveToLoginScreen(tenantType);
    }

    private void moveToLoginScreen(eClientKind tenantType)
    {
        // send the tenant type selected to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(TENANT_TYPE, tenantType);
        startActivity(intent);
    }
}
