package app.prasanpani.dogsapi.utils.datetime

import kotlinx.datetime.toInstant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Date time serializer
 * Value always deserialized in format "2010-06-01T22:19:44.475Z"
 * @constructor Create empty Date time serializer
 */
class DateTimeSerializer : KSerializer<DateTime> {
    override fun deserialize(decoder: Decoder): DateTime {
        return DateTime(decoder.toString().toInstant().toEpochMilliseconds())
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "kotlinx.serialization.DateTimeSerializer",
            PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeString(value.toString())
    }
}