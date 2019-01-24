package com.hit.or_oded.facility_manager.committee_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.logics.Logics;

import com.projects.enums.eProvidersCategories;
import com.projects.persons.Provider;

public class AddNewProviderActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_provider);
    }

    public void buttonOK_clicked(View view)
    {
        String id = ((EditText)findViewById(R.id.editTextProviderId)).getText().toString();
        String firstName = ((EditText)findViewById(R.id.editTextProviderFirstName)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.editTextProviderLastName)).getText().toString();
        String availability = ((EditText)findViewById(R.id.editTextAvailability)).getText().toString();
        String quality = ((EditText)findViewById(R.id.editTextQuality)).getText().toString();
        String price = ((EditText)findViewById(R.id.editTextPrice)).getText().toString();
        String phone = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();
        eProvidersCategories category = getCategory();

        Provider newProvider = new Provider(
                id,
                firstName,
                lastName,
                category,
                Integer.valueOf(availability),
                Integer.valueOf(quality),
                Integer.valueOf(price),
                phone);

        Logics.getInstance().askToAddOrUpdateProvider(newProvider);

        this.finish();
    }

    private eProvidersCategories getCategory()
    {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        int selectedId = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = findViewById(selectedId);

        eProvidersCategories category = eProvidersCategories.valueOf(radioButton.getText().toString());

        return category;
    }

    public void buttonCancel_clicked(View view)
    {
        this.finish();
    }
}
