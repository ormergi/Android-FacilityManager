package com.hit.or_oded.facility_manager.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.committee_activities.CommitteeMenuActivity;
import com.hit.or_oded.facility_manager.logics.IServerResponseListener;
import com.hit.or_oded.facility_manager.logics.Logics;
import com.hit.or_oded.facility_manager.tenant_activities.TenantMenuActivity;

import com.projects.enums.eClientKind;
import com.projects.persons.Committee;
import com.projects.persons.Person;
import com.projects.persons.Tenant;
import com.projects.server_messages.IServerResponse;
import com.projects.server_messages.NewUserResponse;

public class NewUserActivity extends AppCompatActivity implements IServerResponseListener
{
    private eClientKind mClientKind;
    private Logics mLogics;
    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Intent intent = getIntent();
        mClientKind = (eClientKind)intent.getSerializableExtra(ClientKindActivity.TENANT_TYPE);

        EditText option1EditText = (EditText)findViewById(R.id.editTextOption1);
        EditText option2EditText = (EditText)findViewById(R.id.editTextOption2);

        if (mClientKind == eClientKind.Committee)
        {
            option1EditText.setHint("Seniority");
            option2EditText.setVisibility(View.GONE);
        }
        else if (mClientKind == eClientKind.Tenant)
        {
            option1EditText.setHint("Apartment Number");
            option2EditText.setHint("Monthly Payment Amount");
        }

        mLogics = Logics.getInstance();
        mLogics.setListener(this);
    }

    public void buttonOK_clicked(View view)
    {
        String id = ((EditText)findViewById(R.id.editTextId)).getText().toString();
        String firstName = ((EditText)findViewById(R.id.editTextFirstName)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.editTextLastName)).getText().toString();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        String option1 = ((EditText)findViewById(R.id.editTextOption1)).getText().toString();
        String option2 = ((EditText)findViewById(R.id.editTextOption2)).getText().toString();

        if (mClientKind == eClientKind.Committee)
        {
            mPerson = new Committee(id, firstName, lastName, Integer.valueOf(option1));
        }
        else if (mClientKind == eClientKind.Tenant)
        {
            mPerson = new Tenant(id, firstName, lastName, Integer.valueOf(option1), Integer.valueOf(option2));
        }

        mLogics.askToCreateNewUser(mPerson, password);
    }

    public void buttonCancel_clicked(View view)
    {
        this.finish();
    }

    @Override
    public void onServerResponse(IServerResponse response)
    {
        NewUserResponse newUserResponse = (NewUserResponse)response;

        if (newUserResponse.isSucceeded())
        {
            moveToMenuActivity(mPerson);
        }
        else
        {
            showMessageDialog("Error Creating New User", "Please try again");
        }
    }

    private void moveToMenuActivity(Person connectedPerson)
    {
        Intent intent = null;

        if (mClientKind == eClientKind.Committee)
        {
            intent = new Intent(this, CommitteeMenuActivity.class);
        }
        else if (mClientKind == eClientKind.Tenant)
        {
            intent = new Intent(this, TenantMenuActivity.class);
        }

        if (intent != null)
        {
            intent.putExtra(LoginActivity.CONNECTED_PERSON, connectedPerson);
            startActivity(intent);
        }
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
