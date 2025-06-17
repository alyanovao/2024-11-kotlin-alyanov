package ru.aao.geolocation.repository.common

import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.repository.IRepository

interface IRepoInitialize: IRepository {
    fun save(gls: Collection<BaseGeolocation>): Collection<BaseGeolocation>
}