package com.example.ipca.gamecatalog.alldayclinic

import com.google.type.DateTime
import java.sql.Array

class consulta {

    var nomeMedico : String? = null
    var data : DateTime? = null
    var sala : String? = null
    var tipoconsulta : String? = null
    var idMedico = arrayOf( nomeMedico, data, sala, tipoconsulta)

    constructor(
            nomeMedico: String?,
            data: DateTime,
            sala: String?,
            tipoconsulta: String?,
            idMedico: Array

    ) {
        this.nomeMedico = nomeMedico
        this.data    =   data
        this.sala = sala
        this.tipoconsulta = tipoconsulta
        this.idMedico = arrayOf(idMedico)
    }

    fun toHashMap() : HashMap<String, Any?>{
        val hasMap = HashMap<String, Any?>()
        hasMap["nomeMedico"] = nomeMedico
        hasMap["data"] = data
        hasMap["sala"] = sala
        hasMap["tipoconsulta"] = tipoconsulta
        hasMap["idMedico"] = idMedico
        return hasMap
    }

    companion object{
        fun fromHash(hashMap:  HashMap<String, Any?>) : consulta{
            val item = consulta(
                    hashMap["nomeMedico"].toString(),
                    hashMap["data"] as DateTime,
                    hashMap["sala"].toString(),
                    hashMap["tipoconsulta"].toString(),
                    hashMap["idMedico"] as Array
            )
            return item
        }
    }
}