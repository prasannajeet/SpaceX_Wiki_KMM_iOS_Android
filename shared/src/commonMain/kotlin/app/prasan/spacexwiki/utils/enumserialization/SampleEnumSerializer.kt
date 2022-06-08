package app.prasanpani.dogsapi.utils.enumserialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class SampleEnumSerializer: KSerializer<SampleEnum> {
    override fun deserialize(decoder: Decoder): SampleEnum {
        return SampleEnum.values()[decoder.decodeEnum(descriptor)]
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "kotlinx.serialization.SampleEnumSerializer",
            PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: SampleEnum) {
        encoder.encodeString(value.value)
    }
}