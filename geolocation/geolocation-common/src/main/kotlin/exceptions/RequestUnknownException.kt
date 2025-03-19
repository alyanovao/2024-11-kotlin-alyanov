package exceptions

class RequestUnknownException(clazz: Class<*>) : ApplicationException("Class $clazz can not be mapped to GeolocationContext") {
}