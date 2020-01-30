package com.globalsurveys.app

class LoginDto {

    private var correcto: Boolean = true
    private var listaEncuesta: List<EncuestaDto> = emptyList()

    fun getCorrecto(): Boolean {
        return correcto
    }

    fun  setCorrecto(correcto: Boolean) {
        this.correcto = correcto
    }

    fun getListaEncuesta(): List<EncuestaDto> {
        return listaEncuesta
    }

    fun setListaEncuesta(listaEncuesta: List<EncuestaDto>) {
        this.listaEncuesta = listaEncuesta
    }

    fun LoginDto() {}

}