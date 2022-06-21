package com.LordOfTheRings.demo


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    @GetMapping("registrarUsuario/{nombre}/{pass}")
    @Synchronized
    fun registrarUsuario(@PathVariable nombre: String, @PathVariable pass: String): String {
        val userOptional = usuarioRepository.findById(nombre)

        return if (userOptional.isPresent) {
            val user = userOptional.get()
            if (user.pass == pass) {
                user.token
            } else {
                "Pass invalida"
            }
        } else {
            val user = Usuario(nombre, pass)
            usuarioRepository.save(user)
            user.token
        }

    }

    @PostMapping("asignarEquipo/{token}")
    fun asignarEquipo(@PathVariable token: String): Any {
        val equipo = mutableListOf<String>()
        var user: Usuario? = null
        var personaje: Doc
        var cantidad = 0
        var tamano = 5
        usuarioRepository.findAll().forEach { currentUser ->
            if (currentUser.token == token) {
                user = currentUser
                return@forEach
            }
        }
        if (user == null)
            return "Error: Token invalido"

        return equipo
    }

    @PostMapping("liberarPersona/{personajeId}/{token}")
    fun liberarPersona(@PathVariable personajeId: String, @PathVariable token: String): Any {
        var user: Usuario? = null
        var personaje: Doc? = null
        usuarioRepository.findAll().forEach { currentUser ->
            if (currentUser.token == token) {
                user = currentUser
                return@forEach
            }
        }
        if (user == null)
            return "Error: Token invalido"
        print(user?.personajesElegidos)
        personajes.docs.forEach { personajes ->
            if(personajes.id == personajeId)
                personaje = personajes
            return@forEach
        }

        return if (personaje == null || personaje?.usuarioElegido != token)
            "El personaje no pertenece al jugador"
        else {
            personaje?.usuarioElegido = null
            user?.personajesElegidos?.remove(personaje?.id)
            user?.let {
                usuarioRepository.save(it)
                print(user?.personajesElegidos)
            }
            "Personaje liberado"
        }

    }
    @PostMapping("nivelFacil/{token}")
    fun nivelfacil(@PathVariable token: String): Any {
        var user: Usuario? = null
        val equipoFinal = mutableListOf<Doc>()
        val probabilidad = Random.nextInt(0,100)
        usuarioRepository.findAll().forEach { currentUser ->
            if (currentUser.token == token) {
                user = currentUser
                return@forEach
            }
        }
        if (user == null)
            return "Error: Usuario no encontrado"

        if (user?.nivelFacil == true)
            return "Error: Mazmorra ya superada"

        else
            user?.personajesElegidos?.forEach { personaje ->
                personajes.docs.forEach { personajes ->
                    if (personaje == personajes.id && probabilidad < 25) {
                        personajes.conVida= false
                        equipoFinal.add(personajes)
                        user?.personajesElegidos?.remove(personaje)
                    }
                    else {
                        user?.nivelFacil = true
                    }
                }
                user?.let {
                    usuarioRepository.save(it)
                    print(user?.personajesElegidos)
                }
            }

        return if (user?.personajesElegidos?.size == 0)
            "Equipo muerto"
        else
            equipoFinal
    }

    @PostMapping("nivelMedio/{token}")
    fun nivelMedio(@PathVariable token: String): Any {
        var user: Usuario? = null
        val equipoFinal = mutableListOf<Doc>()
        val probabilidad = Random.nextInt(0,100)
        usuarioRepository.findAll().forEach { currentUser ->
            if (currentUser.token == token) {
                user = currentUser
                return@forEach
            }
        }
        if (user == null)
            return "Error: Usuario no encontrado"

        if (user?.nivelMedio == true)
            return "Error: Mazmorra ya superada"

        else
            user?.personajesElegidos?.forEach { personaje ->
                personajes.docs.forEach { character ->
                    if (personaje == character.id && probabilidad < 50) {
                        character.conVida = false
                        equipoFinal.add(character)
                        user?.personajesElegidos?.remove(personaje)
                    }
                    else {
                        user?.nivelMedio = true
                    }
                }
                user?.let {
                    usuarioRepository.save(it)
                    print(user?.personajesElegidos)
                }
            }

        return if (user?.personajesElegidos?.size == 0)
            "Equipo muerto"
        else
            equipoFinal
    }

    @PostMapping("nivelDificil/{token}")
    fun nivelDificil(@PathVariable token: String): Any {
        var user: Usuario? = null
        val equipoFinal = mutableListOf<Doc>()
        val probabilidad = Random.nextInt(0,100)
        usuarioRepository.findAll().forEach { currentUser ->
            if (currentUser.token == token) {
                user = currentUser
                return@forEach
            }
        }

        if (user == null)
            return "Error: Usuario no encontrado"


        user?.personajesElegidos?.forEach { personaje ->
            personajes.docs.forEach { personajes ->
                if (personaje == personajes.id && probabilidad < 75) {
                    personajes.conVida = false
                    equipoFinal.add(personajes)
                    user?.personajesElegidos?.remove(personaje)
                }
                else {
                    user?.nivelDificil = true
                }
            }
            user?.let {
                usuarioRepository.save(it)
                print(user?.personajesElegidos)
            }
        }

        return if (user?.personajesElegidos?.size == 0)
            "Equipo muerto"
        else
            equipoFinal
    }

}