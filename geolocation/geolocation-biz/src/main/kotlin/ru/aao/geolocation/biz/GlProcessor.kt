package ru.aao.geolocation.biz

import ru.aao.geolocation.biz.general.initStatus
import ru.aao.geolocation.biz.general.operation
import ru.aao.geolocation.biz.repo.*
import ru.aao.geolocation.biz.stubs.*
import ru.aao.geolocation.biz.validation.*
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.GlCommand
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.chain
import ru.aao.geolocation.lib.cor.rootChain
import ru.aao.geolocation.lib.cor.worker

open class GlProcessor(
    private val corSettings: GlSettings = GlSettings.NONE) {
    suspend fun exec(ctx: GeolocationContext) = businessChain.exec(
        ctx.also { it.corSettings = corSettings}
    )

    private val businessChain = rootChain {
        initStatus("Инициализация контекста")
        initRepo("Инициализация репозитория")

        operation("Создание локации", GlCommand.CREATE) {
            stubs("Режим стаб") {
                stubReadSuccess("Имитация успешного ответа", corSettings)
                stubValidateBadDescription("Не корректное описание", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в geo") { validating = location.copy() }
                validateDescriptionNotEmpty("Проверка описания")
                validateLocationIsNotEmpty("Проверка координат")
                finishValidation("Завершили проверки")
            }
            chain {
                title = "Логика сохранения"
                repoPrepareCreate("Подготовка объекта для сохранения")
                repoCreate("Создание объекта в БД")
            }
            prepareResult("Подготовка ответа")
        }
        operation("Получение текущих координат", GlCommand.READ_CURRENT) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") { validating = location.deepCopy() }
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsNotEmpty("Проверка на не пустой deviceId")
                finishValidation("Завершили проверки")
            }
            chain {
                description = "Логика чтения"
                repoReadCurrent("Чтение из БД")
                worker {
                    title = "Подготовка ответа для ReadCurrent"
                    active { state == GlState.RUNNING }
                    handle { glRepoDone = glRepoRead }
                }
            }
            prepareResult("Подготовка ответа")
        }
        operation("Получение координат", GlCommand.READ_ALL) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") {validating = location.deepCopy()}
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsNotEmpty("Проверка на не пустой deviceId")
                finishValidation("Завершили проверки")
            }
            chain {
                title = "Логика чтения"
                repoReadAll("Чтение из БД")
                worker {
                    description = "Подготовка ответа для ReadAll"
                    active { state == GlState.RUNNING }
                    handle {
                        glRepoDone = glRepoRead
                    }
                }
            }
            prepareResult("Подготовка ответа")
        }
        operation("Изменение текущих координат", GlCommand.UPDATE) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") {validating = location.deepCopy()}
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsNotEmpty("Проверка на не пустой deviceId")
                finishValidation("Завершили проверки")
            }
            chain {
                description = "Логика сохранения"
                repoReadCurrent("Чтение из БД")
                repoPrepareUpdate("Подготовка объекта для обновления")
                repoUpdate("Обновление объекта")
            }
            prepareResult("Подготовка объекта")
        }
        operation("Изменение текущих координат", GlCommand.DELETE) {
            stubs("Обработка стабов") {
                stubReadSuccess("Успешное получение", corSettings)
                stubDBError("Ошибка базы данных", corSettings)
                stubNoCase("Ошибка :: запрошенный stub не доступен")
            }
            validation {
                worker("Копируем поля в validating") {validating = location.deepCopy()}
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                validateDeviceIdIsNotEmpty("Проверка на не пустой deviceId")
                validatePersonIdIsNotEmpty("Проверка на не пустой personId")
                finishValidation("Завершили проверки")
            }
            chain {
                description = "Логика сохранения"
                repoReadCurrent("Чтение из БД")
                repoDelete("Обновление объекта")
            }
            prepareResult("Подготовка объекта")
        }
    }.build()
}