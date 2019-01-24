package com.hit.or_oded.facility_manager.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.committee_activities.CommitteeMenuActivity;
import com.hit.or_oded.facility_manager.logics.IServerResponseListener;
import com.hit.or_oded.facility_manager.logics.Logics;
import com.hit.or_oded.facility_manager.tenant_activities.TenantMenuActivity;
import com.hit.or_oded.facility_manager.test_activities.CommitteeMenuTestActivity;

import com.projects.enums.eClientKind;
import com.projects.persons.Person;
import com.projects.server_messages.ConnectedPersonDetailsResponse;
import com.projects.server_messages.IServerResponse;
import com.projects.server_messages.LoginResponse;

/**
 * A login screen that offers login via username & password.
 */
public class LoginActivity extends AppCompatActivity implements IServerResponseListener
{
    public static final String CONNECTED_PERSON = "CONNECTED_PERSON";

    private eClientKind mClientKind;
    private Logics mLogics;
    private ProgressDialog proDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Get the Intent that started this activity and extract the tenant type
        Intent intent = getIntent();
        mClientKind = (eClientKind)intent.getSerializableExtra(ClientKindActivity.TENANT_TYPE);
        ((TextView)findViewById(R.id.textViewTitle)).setText(String.format("%s Login", mClientKind));

        if (mClientKind == eClientKind.Provider)
        {
            findViewById(R.id.buttonSignUp).setVisibility(View.GONE); // provider can't sign up, only committee can add new provider
        }

        mLogics = Logics.getInstance();
        mLogics.setListener(this);
    }

    public void buttonSignUp_clicked(View view)
    {
        Intent intent = new Intent(this, NewUserActivity.class);

        intent.putExtra(ClientKindActivity.TENANT_TYPE, mClientKind);
        startActivity(intent);
    }

    public void buttonForgotPassword_clicked(View view)
    {
        // TODO
    }

    public void buttonSignIn_clicked(View view)
    {
        String id =  getValidId();
        String password = getValidPassword();

        if (id != null && password != null)
        {
            mLogics = Logics.getInstance();
            mLogics.setListener(this);
            mLogics.askLogin(mClientKind, id, password);
            showProgress(true);
        }
    }

    private String getValidId()
    {
        EditText idEditText = findViewById(R.id.editTextId);
        String id = idEditText.getText().toString();
        String error = null;

        idEditText.setError(null);

        if (TextUtils.isEmpty(id))
        {
            error = getString(R.string.error_field_required);

        }
        else if (id.length() < 8)
        {
            error = getString(R.string.error_invalid_username);
        }

        if (error != null)
        {
            idEditText.setError(error);
            idEditText.requestFocus();
            id = null;
        }

        return id;
    }

    private String getValidPassword()
    {
        EditText passwordEditText = findViewById(R.id.editTextPassword);
        String password = passwordEditText.getText().toString();

        passwordEditText.setError(null);

        if (TextUtils.isEmpty(password) || (password.length() <= 4))
        {
            passwordEditText.setError(getString(R.string.error_invalid_password));
            passwordEditText.requestFocus();
            password = null;
        }

        return password;
    }

    private void showProgress(final boolean show)
    {
        if (show)
        {
            proDialog = ProgressDialog.show(this, "Logging In", "Please wait...");
        }
        else
        {
            proDialog.hide();
        }
    }

    @Override
    public void onServerResponse(IServerResponse response)
    {
        if (response instanceof LoginResponse)
        {
            handleLoginResponse((LoginResponse)response);
        }
        else if (response instanceof ConnectedPersonDetailsResponse)
        {
            moveToMenuActivity(((ConnectedPersonDetailsResponse)response).provider());
        }
    }

    private void moveToMenuActivity(Person connectedPerson)
    {
        Intent intent = null;

        switch(mClientKind)
        {
            case Committee:
                intent = new Intent(this, CommitteeMenuActivity.class);
                break;
            case Tenant:
                intent = new Intent(this, TenantMenuActivity.class);
                break;
            case Provider:
                //intent = new Intent(this, ProviderMenuActivity.class);
                break;
        }

        if (intent != null)
        {
            intent.putExtra(CONNECTED_PERSON, connectedPerson);
            startActivity(intent);
        }
    }

    private void handleLoginResponse(LoginResponse response)
    {
        showProgress(false);

        if (response.isAuthenticated())
        {
            mLogics.askConnectedPersonDetails();
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setTitle("Wrong ID or Password");
            alertDialog.setMessage("Please try again");
            alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL,
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void buttonShowTenantMonthPaid_clicked(View view) {
    }

    public void buttonShowApartmentPayments_clicked(View view) {
    }

    public void TestLogin(View view)
    {
        Intent intent = new Intent(this, CommitteeMenuTestActivity.class);
        showProgress(true);
        startActivity(intent);
    }
}