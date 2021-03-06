package com.example.ipca.gamecatalog.alldayclinic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class profile(var uid: String?,var nome: String?, var dtaNasc: String?) : Parcelable {

    fun toHashMap() : HashMap<String, Any?>{
        val hasMap = HashMap<String, Any?>()
        hasMap["uid"] = uid
        hasMap["nome"] = nome
        hasMap["dtaNasc"] = dtaNasc

        return hasMap
    }

    companion object{
        fun fromHash(hashMap:  HashMap<String, Any?>) : profile{
            val item = profile(
                hashMap["uid"].toString(),
                hashMap["nome"].toString(),
                hashMap["dtaNasc"].toString()
            )
            return item
        }
    }
}

