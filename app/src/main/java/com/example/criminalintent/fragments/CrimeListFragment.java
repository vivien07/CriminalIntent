package com.example.criminalintent.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.criminalintent.R;
import com.example.criminalintent.activities.CrimeActivity;
import com.example.criminalintent.data.Crime;
import com.example.criminalintent.data.CrimeLab;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class CrimeListFragment extends Fragment {


    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView dateTextView;
        private ImageView solvedImageView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            //itemView is the view for the entire row
            itemView.setOnClickListener(this); //CrimeHolder is set as the receiver of click events
            titleTextView = itemView.findViewById(R.id.crime_title);
            dateTextView = itemView.findViewById(R.id.crime_date);
            solvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        //called in onBindViewHolder
        public void bind(Crime crime) throws ParseException {
            this.crime = crime;
            titleTextView.setText(crime.getTitle());
            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE);
            String date = df.format(crime.getDate()); //formats a Date into a date/time string.
            dateTextView.setText(date);
            solvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Intent intent = CrimeActivity.newIntent(getActivity(), crime.getId()); //explicit intent
            startActivity(intent);
        }

    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        //called by the RecyclerView when it needs a new ViewHolder to display an item with
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); //Obtains the LayoutInflater from the given context.
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            try {
                holder.bind(crimes.get(position));  //Returns the element at the specified position in this list
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);
        recyclerView = v.findViewById(R.id.crime_recycler_view);
        //the LayoutManager positions every item and also defines how scrolling works
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {

        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (crimeAdapter == null) {
            crimeAdapter = new CrimeAdapter(crimes);
            recyclerView.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.notifyDataSetChanged();
        }

    }


}