import example.Berechtigung
import example.FirmenAdmin
import example.Mandant
import example.Nutzer
import example.NutzerAdmin
import example.Recht
import example.Vertrag
import example.VertragId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BerechtigungsTest {

    @Test
    fun `ein unberechtigter NutzerAdmin hat keine Rechte am Vertrag`() {
        val vertrag = Vertrag(VertragId("1"))
        val nutzerAdmin = NutzerAdmin()

        assertThat(nutzerAdmin.rechteFür(vertrag)).isEmpty()
    }

    @Test
    fun `ein FirmenAdmin gibt einem NutzerAdmin das Anmelderecht auf einen Vertrag`() {
        val vertrag = Vertrag(VertragId("1"))
        val firmenAdmin = FirmenAdmin(Mandant(setOf(vertrag)))
        val nutzerAdmin = NutzerAdmin()

        firmenAdmin.berechtige(nutzerAdmin, vertrag, Recht.Anmeldung)

        assertThat(nutzerAdmin.rechteFür(vertrag)).contains(Recht.Anmeldung)
    }

    @Test
    fun `ein NutzerAdmin mit Anmelderecht auf einen Vertrag gibt einem Nutzer das Anmelderecht auf diesen Vertrag`() {
        val nutzerAdmin = NutzerAdmin()
        val vertrag = Vertrag(VertragId("1"))

        val berechtigung: Berechtigung = nutzerAdmin.berechtige(vertrag, Recht.Anmeldung)

        assertThat(berechtigung).isEqualTo(Berechtigung(vertrag, Recht.Anmeldung))
    }
}
