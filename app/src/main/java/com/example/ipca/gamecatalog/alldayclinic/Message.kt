package com.example.ipca.gamecatalog.alldayclinic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Message (
        val text : String? = null,
               val fromId : String? = null,
               val toId : String? = null,
               val timeStamp : Long? = null): Parcelable {
    constructor(): this("","","",-1)
}