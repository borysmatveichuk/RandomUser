package net.borkiss.randomuser.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public final class User {

    //private long id;
    @SerializedName("gender")
    private Gender gender;
    @SerializedName("location")
    private Location location;
    @SerializedName("name")
    private Name name;

    private String email;

    @SerializedName("dob")
    private Date dateOfBirth;
    private Date registered;
    private String phone;
    private String cell;

    private Picture picture;

    @SerializedName("nat")
    private String nationality;

    public String getTitle() {
        return name.title;
    };

    public String getFirstName() {
        return name.first;
    }

    public String getLastName() {
        return name.last;
    }

    public String getStreet() {
        return location.street;
    }

    public String getCity() {
        return location.city;
    }

    public String getState() {
        return location.state;
    }

    public String getPostcode() {
        return location.postcode;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLargePicture() {
        return picture.large;
    }

    public String getMediumPicture() {
        return picture.medium;
    }

    public String getThumbnailPicture() {
        return picture.thumbnail;
    }

    private static class Name {
        String title;
        String first;
        String last;
    }

    private static class Location {
        String street;
        String city;
        String state;
        String postcode;
    }

    private static class Picture {
        String large;
        String medium;
        String thumbnail;
    }
}
