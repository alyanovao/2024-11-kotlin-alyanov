package ru.aao.geolocation.common.repository.exception

import ru.aao.geolocation.common.models.PersonId

class RepoEmptyLockException(id: PersonId): RepoGlException(id, "Lock is empty in DB")