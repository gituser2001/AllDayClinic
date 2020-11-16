package com.example.ipca.gamecatalog.alldayclinic

class profile ( name: String,  dta_nasc: Int) {

    private var name: String? = name
    private var dta_nasc: Int? = dta_nasc

    fun setName(Name: String) {
        this.name = Name
    }
    fun getName(): String? {
        return name
    }
    fun setDtaNasc(dta_nasc: Int) {
        this.dta_nasc = dta_nasc
    }
    fun getDtaNasc(): Int? {
        return dta_nasc
    }
}