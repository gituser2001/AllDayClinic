package com.example.ipca.gamecatalog.alldayclinic

import com.google.firebase.auth.FirebaseUser
import com.google.type.DateTime
import java.sql.Array
import java.sql.Timestamp

class consulta {

    var idUser : String? = null
    var idMedico : String? = null
    //var data : Long? = null
    var sala : String? = null
    var tipoConsulta : String? = null

    constructor(
            idUser: String?,
            idMedico: String?,
            //data: Long?,
            sala: String?,
            tipoConsulta: String?

    ) {
        this.idUser = idUser
        this.idMedico = idMedico
        //this.data    =   data
        this.sala = sala
        this.tipoConsulta = tipoConsulta

    }

    fun toHashMap() : HashMap<String, Any?> {
        val hasMap = HashMap<String, Any?>()
        hasMap["idUser"] = idUser
        hasMap["nomeMedico"] = idMedico
        //hasMap["data"] = data
        hasMap["sala"] = sala
        hasMap["tipoConsulta"] = tipoConsulta

        return hasMap
    }

    companion object{
        fun fromHash(hashMap: HashMap<String, Any>) : consulta{
            val item = consulta(
                    hashMap["idUser"].toString(),
                    hashMap["idMedico"].toString(),
                    //hashMap["data"] as Long,
                    hashMap["sala"].toString(),
                    hashMap["tipoConsulta"].toString()
            )
            return item
        }
    }



}