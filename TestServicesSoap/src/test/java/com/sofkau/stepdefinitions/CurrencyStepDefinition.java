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

public class CurrencyStepDefinition extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(CurrencyStepDefinition.class);

    @Given("a user have the code of the country {string}")
    public void aUserHaveTheCodeOfTheCountry(String code) {
        try {
            setUp(SOAP_CURRECNCY_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody(code);
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends the code to the api")
    public void theUserSendsTheCodeToTheApi() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(SOAP_CURRENCY_RESOURCE.getValue())
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
    @Then("the user gets the currency of the country {string}")
    public void theUserGetsTheCurrencyOfTheCountry(String result) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("La divisa es:",
                            responseSoap(), containsString(result))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody(String value) {
        body = readFile(BODY_PATH_CURRENCY.getValue());
        body = String.format(body, value);
    }
}
