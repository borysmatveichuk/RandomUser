package net.borkiss.randomuser.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum Gender implements Serializable {
    @SerializedName("male") Male, @SerializedName("female") Female
}
