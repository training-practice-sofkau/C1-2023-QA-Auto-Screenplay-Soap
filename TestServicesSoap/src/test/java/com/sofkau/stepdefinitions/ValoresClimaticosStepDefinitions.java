package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.Matchers.containsString;

import java.nio.charset.StandardCharsets;

import static com.sofkau.models.Headers.headers;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ValoresClimaticosStepDefinitions extends ApiSetUp {
    String body;
    String latitud= "40.7128";
    String longitud= "-74.0060";
    String fecha= "2023-04-01";
    String numDays= "1";
    String unit= "m";
    String format= "24 hourly";
    private static final Logger LOGGER = Logger.getLogger(ValoresClimaticosStepDefinitions.class);

    @Given("I want to know the climatic values of a place")
    public void iWantToKnowTheClimaticValuesOfAPlace() {
        try {
            setUp(SOAP_CLIMA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();

        } catch (Exception e) {

            LOGGER.info(" ha fallado la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();

        }


    }

    @When("I send  request to the API")
    public void iSendRequestToTheAPI() {
        try {

            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CLIMA.getValue())
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

    @Then("I should get the expected result")
    public void iShouldGetTheExpectedResult() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK))


            );
            actor.should(
                    seeThatResponse("el body contiene el texto esperado",
                            response -> response.body(containsString("National Weather Service Forecast by 24 Hour Period&lt")))
            );
            LOGGER.info("SI CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }
    private void loadBody() {
        body = readFile(BODY_CLIMA_PATH.getValue());
        body = String.format(body, latitud,longitud,fecha,numDays,unit,format);
    }

}
