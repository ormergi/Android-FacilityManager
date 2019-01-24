package com.hit.or_oded.facility_manager.committee_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hit.or_oded.facility_manager.R;
import com.hit.or_oded.facility_manager.logics.Logics;

import com.projects.enums.eProvidersCategories;

public class GetProviderCategoryActivity extends AppCompatActivity
{
    public final static String INCLUDE_ALL_CATEGORY = "INCLUDE_ALL_CATEGORY";

    private boolean mIncludeAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_provider_category);

        Intent intent = getIntent();

        mIncludeAll = intent.getBooleanExtra(INCLUDE_ALL_CATEGORY, false);

        findViewById(R.id.radioButtonAll).setVisibility(mIncludeAll ? View.VISIBLE : View.GONE);
    }
    
    public void buttonOK_clicked(View view)
    {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        int selectedId = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        eProvidersCategories category = eProvidersCategories.valueOf(radioButton.getText().toString());

        if (mIncludeAll)
        {
            Logics.getInstance().askProvidersByCategory(category);
        }
        else
        {
            Logics.getInstance().askOptimalProvider(category);
        }

        this.finish();
    }

    public void buttonCancel_clicked(View view)
    {
        this.finish();
    }
}
