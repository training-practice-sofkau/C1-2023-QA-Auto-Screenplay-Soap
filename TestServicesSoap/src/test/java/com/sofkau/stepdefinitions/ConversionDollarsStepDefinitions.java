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
import static com.sofkau.utils.PathNumberConversion.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class ConversionDollarsStepDefinitions extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(NumbersLettersStepDefinitions.class);

    @Given("a user that wants to know the numbers {string} in dollares letters")
    public void aUserThatWantsToKnowTheNumbersInDollaresLetters(String number) {
        try {
            setUp(SOAP_NUMBER_CONVERSION_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody2(number);
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends the request whit numbers to the api  Conversion dollares")
    public void theUserSendsTheRequestWhitNumbersToTheApiConversionDollares() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_NUMBER_CONVERSION.getValue())
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

    @Then("the user gets the numbers  in dollares in letters {string} and get status code {int}")
    public void theUserGetsTheNumbersInDollaresInLettersAndGetStatusCode(String letters, Integer code) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + code,
                            response -> response.statusCode(code))

            );
            if(code==200){
                actor.should(
                        seeThat(" El valor en dolares es",
                                responseSoap(), containsString(letters)));
            }
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody2(String numero) {

        body = readFile(BODY_PATH_NUMBER_CON_DOLLAR.getValue());
        body = String.format(body, numero);
    }
}

