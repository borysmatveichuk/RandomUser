package net.borkiss.randomuser.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
        val gender: Gender,
        val location: Location,
        val name: Name,
        val email: String,
        @SerializedName("dob")
        val dateOfBirth: DateWrapper,
        val registered: DateWrapper,
        val phone: String,
        val cell: String,
        private val picture: Picture,
        @field:SerializedName("nat")
        val nationality: String) : Parcelable {


    val title: String?
        get() = name.title

    val firstName: String?
        get() = name.first

    val lastName: String?
        get() = name.last

    val street: String?
        get() = location.street

    val city: String?
        get() = location.city

    val state: String?
        get() = location.state

    val postcode: String?
        get() = location.postcode

    val largePicture: String?
        get() = picture.large

    val mediumPicture: String?
        get() = picture.medium

    @Parcelize
    class Name(
            val title: String,
            val first: String,
            val last: String) : Parcelable

    @Parcelize
    class Location(
            val street: String,
            val city: String,
            val state: String,
            val postcode: String) : Parcelable

    @Parcelize
    class Picture(
        var large: String,
        var medium: String,
        var thumbnail: String) : Parcelable

}
