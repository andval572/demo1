package com.LordOfTheRings.demo


import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class DemoApplication


val personajes = Json.decodeFromString<Personajes>(File("personajes.json").readText())

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)

	personajes.docs.forEach {
		println(it.name)
	}
}

