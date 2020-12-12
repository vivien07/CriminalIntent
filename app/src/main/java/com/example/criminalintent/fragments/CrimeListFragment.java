package com.example.criminalintent.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.criminalintent.R;
import com.example.criminalintent.data.Crime;
import com.example.criminalintent.data.CrimeLab;

import java.util.List;

public class CrimeListFragment extends Fragment {


    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;



    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView dateTextView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            //itemView is the view for the entire row
            itemView.setOnClickListener(this); //CrimeHolder is set as the receiver of click events
            titleTextView = itemView.findViewById(R.id.crime_title);
            dateTextView = itemView.findViewById(R.id.crime_date);
        }

        //called in onBindViewHolder
        public void bind(Crime crime) {
            this.crime = crime;
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            String text = crime.getTitle();
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
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
            holder.bind(crimes.get(position));  //Returns the element at the specified position in this list
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

    private void updateUI() {

        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        crimeAdapter = new CrimeAdapter(crimes);
        recyclerView.setAdapter(crimeAdapter);

    }


}