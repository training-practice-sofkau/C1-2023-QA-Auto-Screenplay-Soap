package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.models.HeaderDivisas.headerDivisas;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class CambioDivisaStepDefinitions extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(CambioDivisaStepDefinitions.class);

    @Given("que tengo acceso al servicio web de CurrencySystem para la conversion de divisas")
    public void queTengoAccesoAlServicioWebDeCurrencySystemParaLaConversionDeDivisas() {
        try {
            setUp(SOAP_DIVISA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio {int} {string} a {string} al servicio")
    public void envioAAlServicio(Integer cantidad, String divisaOrigen, String divisaDestino) {
        try {
            body = String.format(body, divisaOrigen, divisaDestino, cantidad);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_DIVISA.getValue())
                            .withTheHeaders(headerDivisas().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("deberia recibir el resultado de la conversion {string}")
    public void deberiaRecibirElResultadoDeLaConversion(String resultadoEsperado) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion es correcto",
                            responseSoap(), CoreMatchers.containsString(resultadoEsperado))
            );
            LOGGER.info("El valor esperado es: " + resultadoEsperado);
            LOGGER.info("El valor actual es: " + LastResponse.received().answeredBy(actor).asString());
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void loadBody() {
        body = readFile(BODY_PATH1.getValue());
    }
}
