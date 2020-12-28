package com.example.criminalintent.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {


    private static CrimeLab crimeLab;   //singleton pattern
    private Map<UUID, Crime> crimes;

    private CrimeLab(Context context) {

        crimes = new LinkedHashMap<>(); //the compiler infers the type of items
        //fake data
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            crime.setRequiresPolice(i % 3 == 0);
            crimes.put(crime.getId(), crime);
        }

    }

    public static CrimeLab getCrimeLab(Context context) {

        if (crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;

    }


    public List<Crime> getCrimes() {
        return new ArrayList<>(crimes.values());
    }


    public Crime getCrime(UUID id) {

        return crimes.get(id);  //Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.

    }

}
