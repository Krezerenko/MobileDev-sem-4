package com.example.pract3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable
{
    private final String firstName;
    private final String secondName;
    private final String thirdName;

    public User(String firstName, String secondName, String thirdName)
    {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
    }

    protected User(Parcel in)
    {
        firstName = in.readString();
        secondName = in.readString();
        thirdName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };

    public String getFirstName()
    {
        return firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public String getThirdName()
    {
        return thirdName;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags)
    {
        dest.writeString(firstName);
        dest.writeString(secondName);
        dest.writeString(thirdName);
    }
}
