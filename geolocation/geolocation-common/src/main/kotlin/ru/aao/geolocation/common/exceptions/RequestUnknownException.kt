package ru.aao.geolocation.common.exceptions

class RequestUnknownException(clazz: Class<*>) : ApplicationException("Class $clazz can not be mapped to ru.aao.geolocation.common.GeolocationContext") {
}