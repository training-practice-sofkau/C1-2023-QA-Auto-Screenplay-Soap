package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
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


public class CodigoISOIdiomaStepDefinition  extends ApiSetUp {
    String body;
    private void loadBody(String pais) {
        body = readFile(BODY_PATH_ISO_CODE.getValue());
        body = String.format(body, pais );
    }
    private static final Logger LOGGER = Logger.getLogger(CodigoISOIdiomaStepDefinition.class);
    @Given("El servicio de Conuntri Info Service SOAP esta disponible")
    public void elServicioDeConuntriInfoServiceSOAPEstaDisponible() {

        try {
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @When("Consulto el codigo ISO del {string}")
    public void consultoElCodigoISODel(String pais) {
        try {
            loadBody(pais);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_ISO_LENGUAJE_CODE.getValue())
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

    @Then("deberia obtener el {string}  y el {int} de la respuesta")
    public void deberiaObtenerElYElDeLaRespuesta(String codigoISO, Integer Statushttp) {

        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(

                    seeThatResponse("el codigo de respuesta es: " + Statushttp,
                            response -> response.statusCode(Statushttp))

            );
            if (Statushttp == 200) {
                actor.should(
                        seeThat("la ruta para la imagen de la bandera es:",
                                responseSoap(), containsString(codigoISO))
                );
            }

            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
