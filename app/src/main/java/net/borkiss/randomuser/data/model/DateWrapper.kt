package net.borkiss.randomuser.data.model

import java.io.Serializable
import java.util.Date

class DateWrapper(
        val date: Date,
        val age: Int) : Serializable
