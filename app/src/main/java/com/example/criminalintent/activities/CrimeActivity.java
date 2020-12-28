package com.example.criminalintent.activities;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.fragments.CrimeFragment;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {


    private static final String EXTRA_CRIME_ID = "com.ruasvivien.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);   //Add extended data to the intent.
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }


}