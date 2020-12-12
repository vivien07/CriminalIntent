package com.example.criminalintent.data;

import java.util.Date;
import java.util.UUID;

public class Crime {

    //member variables
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //constructor
    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date(); //sets to the current date
    }

    //getters
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    //setters
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

}
