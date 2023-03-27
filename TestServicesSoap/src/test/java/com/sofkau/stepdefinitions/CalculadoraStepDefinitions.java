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
import static com.sofkau.models.HeaderSuma.headerSuma;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CalculadoraStepDefinitions extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(CalculadoraStepDefinitions.class);

    @Given("que tengo acceso al servicio web de DNE Online para la suma de numeros")
    public void queTengoAccesoAlServicioWebDeDNEOnlineParaLaSumaDeNumeros() {
        try {
            setUp(SOAP_CALCULADORA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio los numeros {int} y {int} al servicio")
    public void envioLosNumerosYAlServicio(Integer num1, Integer num2) {
        try {
            body = String.format(body,num1,num2);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CALCULADORA.getValue())
                            .withTheHeaders(headerSuma().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la petición");
        } catch (Exception e) {
            LOGGER.info("Fallo al momento de realizar la petición");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("deberia recibir el resultado de la suma {int}")
    public void deberiaRecibirElResultadoDeLaSuma(Integer resultado) {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la suma es " + resultado,
                            responseSoap(), CoreMatchers.containsString(String.valueOf(resultado)))
            );
            LOGGER.info("El valor esperado es: " + resultado);
            LOGGER.info("El valor actual es: " + LastResponse.received().answeredBy(actor).asString());
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparación");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void loadBody() {
        body = readFile(BODY_PATH2.getValue());
    }

}
