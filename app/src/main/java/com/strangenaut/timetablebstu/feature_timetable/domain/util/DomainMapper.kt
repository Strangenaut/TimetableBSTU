package com.strangenaut.timetablebstu.feature_timetable.domain.util

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel
}