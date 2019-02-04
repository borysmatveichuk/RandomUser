package net.borkiss.randomuser.data.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

enum class Gender : Serializable {
    @SerializedName("male")
    Male,
    @SerializedName("female")
    Female
}
