package ru.aao.geolocation.biz.stubs

import GeolocationStub
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.stubs.GlStubs
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.stubReadSuccess(description: String, corSettings: GlSettings) = worker {
    this.description = description
    active { stubCase == GlStubs.SUCCESS && state == GlState.RUNNING}
    //val log = corSettings.logProvider.logger(this::class)
    //val logger = corSetting.logProvider.logger("stubReadSuccess")
    except {
        state = GlState.FINISHED
        val stub = GeolocationStub.prepareResult {
            location.personId.takeIf { it != PersonId.NONE }?.also { this.personId = it }
            location.deviceId.takeIf { it != DeviceId.NONE }?.also { this.deviceId = it }
            location.latitude.takeIf { it != Latitude.NONE }?.also { this.latitude = it }
            location.longitude.takeIf { it != Longitude.NONE }?.also { this.longitude = it }
            location.bearing.takeIf { it != Bearing.NONE }?.also { this.bearing = it }
            location.altitude.takeIf { it != Altitude.NONE }?.also { this.altitude = it }
            location.batteryLevel.takeIf { it != BatteryLevel.NONE }?.also { this.batteryLevel = it }
        }
        glResponse = stub
    }
}