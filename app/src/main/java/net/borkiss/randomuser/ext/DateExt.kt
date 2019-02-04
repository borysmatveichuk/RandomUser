package net.borkiss.randomuser.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH)
    return dateFormat.format(this)
}