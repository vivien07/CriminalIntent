package com.example.criminalintent.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {


    private static CrimeLab crimeLab;   //singleton pattern
    private List<Crime> crimes;

    private CrimeLab(Context context) {
        crimes = new ArrayList<>(); //the compiler infers the type of items
        //fake data
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            crimes.add(crime);
        }

    }

    public static CrimeLab getCrimeLab(Context context) {

        if (crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;

    }


    public List<Crime> getCrimes() {
        return crimes;
    }


    public Crime getCrime(UUID id) {

        for (Crime crime: crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;

    }

}
