package com.LordOfTheRings.demo

import org.springframework.web.bind.annotation.GetMapping

class PersonajesController {
    @GetMapping("getHumans")
    fun getHumans(): List<Doc> {
        val listaHumanos = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race == "Human")
                listaHumanos.add(it)
        }
        return listaHumanos
    }

    @GetMapping("getElfs")
    fun getElfs(): List<Doc> {
        val listaElfs = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race == "Elf")
                listaElfs.add(it)
        }
        return listaElfs
    }

    @GetMapping("getOrcs")
    fun getOrcs(): List<Doc> {
        val listaOrcs = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race == "Orc"||it.race == "Orcs")
                listaOrcs.add(it)
        }
        return listaOrcs
    }

    @GetMapping("getDragons")
    fun getDragons(): List<Doc> {
        val listaDragons = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race == "Dragon"||it.race == "Dragons")
                listaDragons.add(it)
        }
        return listaDragons
    }

    @GetMapping("getGoodGuys")
    fun getGoodGuys(): List<Doc> {
        val listaGoodGuys = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race == "Human" || it.race == "Elf")
                listaGoodGuys.add(it)
        }
        return listaGoodGuys
    }

    @GetMapping("getBadGuys")
    fun getBadGuys(): List<Doc> {
        val listaBadGuys = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race == "Orc" || it.race == "Dragon")
                listaBadGuys.add(it)
        }
        return listaBadGuys
    }

    @GetMapping("getOthers")
    fun getOthers(): List<Doc> {
        val listaOthers = mutableListOf<Doc>()
        personajes.docs.forEach {
            if (it.race != "Orc" && it.race != "Dragon" && it.race != "Elf" && it.race != "Human")
                listaOthers.add(it)
        }
        return listaOthers
    }
}