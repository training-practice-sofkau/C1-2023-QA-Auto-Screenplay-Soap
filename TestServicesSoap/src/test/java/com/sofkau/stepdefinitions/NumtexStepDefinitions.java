package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.models.Headers.headers;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class NumtexStepDefinitions extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(NumtexStepDefinitions.class);

    @Given("que tengo acceso al servicio web de Data Access para la conversion de numeros a texto")
    public void queTengoAccesoAlServicioWebDeDataAccessParaLaConversionDeNumerosATexto() {
        try {
            setUp(SOAP_NUMTEX_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("envio el numero {int} al servicio")
    public void envioElNumeroAlServicio(int numero) {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_NUMTEX.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(String.format(body, numero))
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @Then("deberia recibir el resultado en texto {string}")
    public void deberiaRecibirElResultadoEnTexto(String resultado) {
        try {
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" el resultado de la conversion es",
                            responseSoap(), containsString(resultado))
            );
            LOGGER.info("El valor esperado es: " + resultado);
            LOGGER.info("El valor actual es: " + LastResponse.received().answeredBy(actor).asString());
            LOGGER.info("CUMPLE");

        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void loadBody() {
        body = readFile(BODY_PATH3.getValue());
    }
}
