package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import java.nio.charset.StandardCharsets;
import static com.sofkau.models.Headers.headers;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class BanderaPorCodigoISOStepDefinition  extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(CodigoISOIdiomaStepDefinition.class);

    String body;
    private void loadBody(String pais) {
        body = readFile(BODY_PATH_ISO_BANDERA.getValue());
        body = String.format(body, pais );
    }

    @Given("El servicio de Country Info Service SOAP esta disponible")
    public void elServicioDeCountryInfoServiceSOAPEstaDisponible() {


        try {
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("Envio el codigo ISO del pais {string}")
    public void envioElCodigoISODelPais(String pais) {


        try {
            loadBody(pais);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_BANDERA_ISO_CODE.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @Then("deberia obtener la {string}  y el {int} de la respuesta")
    public void deberiaObtenerLaYElDeLaRespuesta(String rutaBandera, Integer Statushttp) {


        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(

                    seeThatResponse("el codigo de respuesta es: " + Statushttp,
                            response -> response.statusCode(Statushttp)),
                    seeThat("la ruta para la imagen de la bandera es:",
                            responseSoap(), containsString(rutaBandera))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }
}

