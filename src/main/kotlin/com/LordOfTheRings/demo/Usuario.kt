package com.LordOfTheRings.demo

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Usuario (@Id var nombre: String, var pass: String) {
    val token = nombre + pass
    var nivelFacil = false
    var nivelMedio = false
    var nivelDificil = false


    @ElementCollection
    var personajesElegidos = mutableListOf<String>()

}