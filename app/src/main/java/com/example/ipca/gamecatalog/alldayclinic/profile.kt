package com.example.ipca.gamecatalog.alldayclinic

class profile {

    var nome    : String?   = null
    var dtaNasc : String?   = null

    constructor(
        nome: String?,
        dtaNasc: String?

    ) {
        this.nome       = nome
        this.dtaNasc    = dtaNasc
    }

    fun toHashMap() : HashMap<String, Any?>{
        val hasMap = HashMap<String, Any?>()
        hasMap["nome"] = nome
        hasMap["dtaNasc"] = dtaNasc

        return hasMap
    }

    companion object{
        fun fromHash(hashMap:  HashMap<String, Any?>) : profile{
            val item = profile(
                hashMap["nome"].toString(),
                hashMap["dtaNasc"].toString()
            )
            return item
        }
    }
}