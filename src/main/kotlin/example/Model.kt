package example

data class FirmenAdmin(private val mandant: Mandant): Entity {
    fun berechtige(nutzerAdmin: NutzerAdmin, vertrag: Vertrag, recht: Recht) {
        nutzerAdmin.add(Berechtigung(vertrag, recht))
    }
}

data class NutzerAdmin(
    private val berechtigungen: MutableSet<Berechtigung> = mutableSetOf()
) : Entity {

    fun add(berechtigung: Berechtigung) {
        berechtigungen.add(berechtigung)
    }

    fun berechtige(vertrag: Vertrag, recht: Recht): Berechtigung =
        Berechtigung(vertrag, recht)

    fun rechteFür(vertrag: Vertrag): Set<Recht> =
        berechtigungen.filter { it.vertrag == vertrag }.map { it.recht }.toSet()
}

class Nutzer(private val rechte: Set<Berechtigung> = emptySet()) : Entity

data class Mandant(private val verträge: Set<Vertrag>)

data class Vertrag(
    private val id: VertragId,
    private val label: String = ""
) : Entity

enum class Recht: Value {
    Anmeldung,
    Tarifieren,
    Inkasso
}

data class Berechtigung(
    val vertrag: Vertrag,
    val recht: Recht
) : Aggregate

data class VertragId(
    private val value: String
) : Value

interface Entity

interface Aggregate

interface Value
