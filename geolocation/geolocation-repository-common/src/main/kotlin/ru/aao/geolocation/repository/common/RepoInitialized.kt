package ru.aao.geolocation.repository.common

import ru.aao.geolocation.common.models.BaseGeolocation

class RepoInitialized(
    private val repo: IRepoInitialize,
    initObjects: Collection<BaseGeolocation> = emptyList()
): IRepoInitialize by repo{
    val initObjects: List<BaseGeolocation> = save(initObjects).toList()
}