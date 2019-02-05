package net.borkiss.randomuser.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
class DateWrapper(
        val date: Date,
        val age: Int) : Parcelable
