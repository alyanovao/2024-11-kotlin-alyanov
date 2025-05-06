package logger

import net.logstash.logback.marker.EmptyLogstashMarker
import org.slf4j.Marker

class CustomMarker(
    private val subMarkers: List<Marker> = listOf(EmptyLogstashMarker())
): Marker {

    override fun getName(): String = name

    override fun add(reference: Marker?){}

    override fun remove(reference: Marker?): Boolean = false

    override fun hasChildren(): Boolean = hasReferences()

    override fun hasReferences(): Boolean = subMarkers.isNotEmpty()

    override fun iterator(): Iterator<Marker> = subMarkers.iterator()

    override fun contains(other: Marker?): Boolean = subMarkers.contains(other)

    override fun contains(name: String?): Boolean = subMarkers.any() {it.name == name}
}