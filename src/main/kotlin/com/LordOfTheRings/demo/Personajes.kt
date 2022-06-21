package com.LordOfTheRings.demo

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json       = Json(JsonConfiguration.Stable)
// val personajes = json.parse(Personajes.serializer(), jsonString)


import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class Personajes (
    val docs: List<Doc>,

)

@Serializable
data class Doc (
    @SerialName("_id")
    val id: String,
    var conVida: Boolean = true,
    var usuarioElegido: String? = null,
    val height: String,
    val race: String,
    val gender: Gender? = null,
    val birth: String,
    val spouse: String,
    val death: String,
    val realm: String,
    val hair: String,
    val name: String,

    @SerialName("wikiUrl")
    val wikiURL: String? = null
)

@Serializable
enum class Gender(val value: String) {
    Empty(""),
    Female("Female"),
    GenderMale("male"),
    Male("Male"),
    Males("Males"),
    MostLikelyMale("Most likely male"),
    NaN("NaN");

    companion object : KSerializer<Gender> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Gender", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Gender = when (val value = decoder.decodeString()) {
            ""                 -> Empty
            "Female"           -> Female
            "male"             -> GenderMale
            "Male"             -> Male
            "Males"            -> Males
            "Most likely male" -> MostLikelyMale
            "NaN"              -> NaN
            else               -> throw IllegalArgumentException("Gender could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Gender) {
            return encoder.encodeString(value.value)
        }
    }
}
