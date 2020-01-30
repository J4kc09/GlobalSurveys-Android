package com.globalsurveys.app


class EncuestaDto  {

    private val serialVersionUID = 1L
    private var idEncuesta: Long = -1
    internal var nomEncuesta: String = ""
    internal var descripcionEncuesta: String = ""

    fun EncuestaDto() {}

    fun EncuestaDto(idEncuesta: Long) {
        this.idEncuesta = idEncuesta
    }

    fun EncuestaDto(
        idEncuesta: Long,
        nomEncuesta: String,
        descripcionEncuesta: String
    ) {
        this.idEncuesta = idEncuesta
        this.nomEncuesta = nomEncuesta
        this.descripcionEncuesta = descripcionEncuesta
    }

    fun getIdEncuesta(): Long {
        return idEncuesta
    }

    fun setIdEncuesta(idEncuesta: Long) {
        this.idEncuesta = idEncuesta
    }

    fun getNomEncuesta(): String {
        return nomEncuesta
    }

    fun setNomEncuesta(nomEncuesta: String) {
        this.nomEncuesta = nomEncuesta
    }

    fun getDescripcionEncuesta(): String {
        return descripcionEncuesta
    }

    fun setDescripcionEncuesta(descripcionEncuesta: String) {
        this.descripcionEncuesta = descripcionEncuesta
    }

}