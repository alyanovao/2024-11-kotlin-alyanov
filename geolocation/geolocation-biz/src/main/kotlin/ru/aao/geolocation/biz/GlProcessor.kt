package ru.aao.geolocation.biz

import ru.aao.geolocation.biz.general.initStatus
import ru.aao.geolocation.biz.general.operation
import ru.aao.geolocation.biz.general.stubs
import ru.aao.geolocation.biz.stubs.stubDBError
import ru.aao.geolocation.biz.stubs.stubNoCase
import ru.aao.geolocation.biz.stubs.stubReadSuccess
import ru.aao.geolocation.biz.stubs.stubValidateBadDescription
import ru.aao.geolocation.biz.validation.*
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.DeviceId
import ru.aao.geolocation.common.models.GlCommand
import ru.aao.geolocation.common.models.Latitude
import ru.aao.geolocation.common.models.PersonId
import ru.aao.geolocation.lib.cor.ICorExec
import ru.aao.geolocation.lib.cor.rootChain
import ru.aao.geolocation.lib.cor.worker

open class GlProcessor(val corSettings: GlSettings = GlSettings.NONE) {
    private val corrSettings: GlSettings = corSettings
    suspend fun exec(ctx: GeolocationContext) = businessChain.exec(ctx.also{ it.corSettings = corrSettings})

    private val businessChain: ICorExec<GeolocationContext> = rootChain {
        initStatus("Инициализация контекста")

        operation("Создание локации", GlCommand.CREATE) {
            stubs("Режим стаб") {
                stubReadSuccess("Имитация успешного ответа", corSettings)
                stubValidateBadDescription("Не корректное описание", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в geo") {validating = location.copy()}
                worker("Очистка id") {validating.personId = PersonId.NONE}
                worker("Очистка координат") {validating.latitude = Latitude.NONE}
                validateDescriptionNotEmpty("Проверка описания")
                validateLocationIsNotEmpty("Проверка координат")
                finishValidation("Завершили проверки")
            }
        }
        operation("Получение текущих координат", GlCommand.READ_CURRENT) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") {validating = location.copy()}
                worker("Очистка personId") {validating.personId = PersonId(validating.personId.asLong()) }
                worker("Очистка id") {validating.deviceId = DeviceId.NONE }
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsnotEmpty("Проверка на не пустой deviceId")
                finishValidation("Завершили проверки")
            }
        }
        operation("Получение координат", GlCommand.READ_ALL) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") {validating = location.copy()}
                worker("Очистка personId") {validating.personId = PersonId(validating.personId.asLong()) }
                worker("Очистка id") {validating.deviceId = DeviceId.NONE }
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsnotEmpty("Проверка на не пустой deviceId")
                finishValidation("Завершили проверки")
            }
        }
        operation("Изменение текущих координат", GlCommand.UPDATE) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") {validating = location.copy()}
                worker("Очистка personId") {validating.personId = PersonId(validating.personId.asLong()) }
                worker("Очистка id") {validating.deviceId = DeviceId.NONE }
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsnotEmpty("Проверка на не пустой deviceId")
                finishValidation("Завершили проверки")
            }
        }
    }.build()
}