package app.prasanpani.dogsapi.utils.enumserialization

import kotlinx.serialization.Serializable

@Serializable(with = SampleEnumSerializer::class)
enum class SampleEnum(val value: String) {
    ONE("one"),
    TWO("two")
}