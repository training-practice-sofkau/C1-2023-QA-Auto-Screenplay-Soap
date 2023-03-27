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

public class NumerosDolaresStepDefinition extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(NumerosDolaresStepDefinition.class);

    @Given("que el usuario quiere obtener el resultado de la conversion del numero a dolares")
    public void queElUsuarioQuiereObtenerElResultadoDeLaConversionDelNumeroADolares() {
        try {
            setUp(SOAP_NUMEROS_DOLARES_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("ingreso el numero {int} en el campo de entrada")
    public void ingresoElNumeroEnElCampoDeEntrada(Integer int1) {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_NUMEROS_DOLARES.getValue())
                            .withTheHeaders(headers().getHeadersZipCode())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("el servicio responde con un estado {int} OK")
    public void elServicioRespondeConUnEstadoOK(Integer int1) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
            LOGGER.info("CUMPLE EL CODIGO DE ESTADO");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @Then("el servicio muestra el resultado de la conversion en dolares {string}")
    public void elServicioMuestraElResultadoDeLaConversionEnDolares(String string) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThat(" El numero convertido y escrito es: ",
                            responseSoap(), containsString("seventy dollars"))
            );
            LOGGER.info("CUMPLE!!");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }
    private void loadBody() {
        body = readFile(BODY_NUMEROS_DOLARES_PATH.getValue());
        body = String.format(body, "70");
    }
}
