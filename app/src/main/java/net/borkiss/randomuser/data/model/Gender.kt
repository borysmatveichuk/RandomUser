package net.borkiss.randomuser.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
enum class Gender : Parcelable {
    @SerializedName("male")
    Male,
    @SerializedName("female")
    Female
}
