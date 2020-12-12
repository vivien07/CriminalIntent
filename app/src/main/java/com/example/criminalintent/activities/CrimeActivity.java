package com.example.criminalintent.activities;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.fragments.CrimeFragment;


public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }


}