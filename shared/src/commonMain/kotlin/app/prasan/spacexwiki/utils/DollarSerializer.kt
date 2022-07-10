package app.prasan.spacexwiki.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class DollarSerializer : KSerializer<Dollar> {
    override fun deserialize(decoder: Decoder): Dollar {
        return Dollar(decoder.decodeLong())
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "kotlinx.serialization.DollarAmountSerializer",
            PrimitiveKind.DOUBLE
        )

    override fun serialize(encoder: Encoder, value: Dollar) {
        encoder.encodeLong(value.rawAmount)
    }
}