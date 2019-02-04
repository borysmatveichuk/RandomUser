package net.borkiss.randomuser.data.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

public final class User implements Serializable {

    private static final String TAG = User.class.getSimpleName();
    private static final long serialVersionUID = 6637065493861445979L;

    @SerializedName("gender")
    private Gender gender;

    @SerializedName("location")
    private Location location;

    @SerializedName("name")
    private Name name;

    private String email;

    @SerializedName("dob")
    private DateWrapper dateOfBirth;

    private DateWrapper registered;

    private String phone;

    private String cell;

    private Picture picture;

    @Property
    @SerializedName("nat")
    private String nationality;

    public User(Gender gender, Location location, Name name, String email,
            Date dateOfBirth, Date registered, String phone, String cell, Picture picture,
            String nationality) {
        this.gender = gender;
        this.location = location;
        this.name = name;
        this.email = email;
        this.dateOfBirth.date = dateOfBirth;
        this.registered.date = registered;
        this.phone = phone;
        this.cell = cell;
        this.picture = picture;
        this.nationality = nationality;
    }


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
        return dateOfBirth.date;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public String getNationality() {
        return nationality;
    }


    static class Name implements Serializable {
        private static final long serialVersionUID = -1782894845200254951L;

        String title;
        String first;
        String last;
    }

    static class Location implements Serializable {
        private static final long serialVersionUID = -6247658520019395959L;

        String street;
        String city;
        String state;
        String postcode;
    }

    static class Picture implements Serializable {
        private static final long serialVersionUID = -7577001532817752742L;

        String large;
        String medium;
        String thumbnail;
    }

    /**
     * Data type converters
     */

    public static class LocationConverter implements PropertyConverter<Location, String> {

        @Override
        public Location convertToEntityProperty(String databaseValue) {
            if (databaseValue == null)
                return null;

            Gson gson = new Gson();
            Type type = new TypeToken<Location>() {}.getType();
            try {
                return gson.fromJson(databaseValue, type);
            } catch (JsonSyntaxException je) {
                Log.e(TAG, "Error parsing " + je.getLocalizedMessage());
                return null;
            }
        }

        @Override
        public String convertToDatabaseValue(Location entityProperty) {
            if (entityProperty == null)
                return null;

            Gson gson = new Gson();
            return gson.toJson(entityProperty);
        }
    }

    public static class NameConverter implements PropertyConverter<Name, String> {

        @Override
        public Name convertToEntityProperty(String databaseValue) {
            if (databaseValue == null)
                return null;

            Gson gson = new Gson();
            Type type = new TypeToken<Name>() {}.getType();
            try {
                return gson.fromJson(databaseValue, type);
            } catch (JsonSyntaxException je) {
                Log.e(TAG, "Error parsing " + je.getLocalizedMessage());
                return null;
            }
        }

        @Override
        public String convertToDatabaseValue(Name entityProperty) {
            if (entityProperty == null)
                return null;

            Gson gson = new Gson();
            return gson.toJson(entityProperty);
        }
    }

    public static class PictureConverter implements PropertyConverter<Picture, String> {

        @Override
        public Picture convertToEntityProperty(String databaseValue) {
            if (databaseValue == null)
                return null;

            Gson gson = new Gson();
            Type type = new TypeToken<Picture>() {}.getType();
            try {
                return gson.fromJson(databaseValue, type);
            } catch (JsonSyntaxException je) {
                Log.e(TAG, "Error parsing " + je.getLocalizedMessage());
                return null;
            }
        }

        @Override
        public String convertToDatabaseValue(Picture entityProperty) {
            if (entityProperty == null)
                return null;

            Gson gson = new Gson();
            return gson.toJson(entityProperty);
        }
    }

    public static class GenderConverter implements PropertyConverter<Gender, String> {

        @Override
        public Gender convertToEntityProperty(String databaseValue) {
            if (databaseValue == null)
                return null;

            Gson gson = new Gson();
            Type type = new TypeToken<Gender>() {}.getType();
            try {
                return gson.fromJson(databaseValue, type);
            } catch (JsonSyntaxException je) {
                Log.e(TAG, "Error parsing " + je.getLocalizedMessage());
                return null;
            }
        }

        @Override
        public String convertToDatabaseValue(Gender entityProperty) {
            if (entityProperty == null)
                return null;

            Gson gson = new Gson();
            return gson.toJson(entityProperty);
        }
    }
}
