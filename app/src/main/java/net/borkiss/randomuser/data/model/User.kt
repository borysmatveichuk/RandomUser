package net.borkiss.randomuser.data.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

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
        val nationality: String) : Serializable {


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

    class Name : Serializable {

        var title: String? = null
        var first: String? = null
        var last: String? = null
    }

    class Location : Serializable {

        var street: String? = null
        var city: String? = null
        var state: String? = null
        var postcode: String? = null

    }

    class Picture : Serializable {

        var large: String? = null
        var medium: String? = null
        var thumbnail: String? = null

    }
}
