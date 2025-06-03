package ru.aao.geolocation.common.repository.exception

import ru.aao.geolocation.common.models.GeolocationId

class RepoEmptyLockException(id: GeolocationId): RepoGlException(id, "Lock is empty in DB")