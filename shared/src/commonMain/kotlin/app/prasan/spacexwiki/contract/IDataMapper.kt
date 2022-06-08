package app.prasan.spacexwiki.contract

interface IDataMapper<in MapFrom: Any, out MapTo: Any> {
    operator fun invoke(input: MapFrom): MapTo
}