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

import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.models.Headers.headers;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;


public class NumberToWordsSetepDefinition extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(NumberToWordsSetepDefinition.class);
    String body;

    @Given("a user that wants to know the value of a number in words")
    public void aUserThatWantsToKnowTheValueOfANumberInWords() {
        try {
            setUp(SOAP_NUMBER_TO_WORDS_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends a number {string} to the service")
    public void theUserSendsANumberToTheService(String number) {
        try {
            loadBody(number);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_NUMBER_TO_WORDS.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets the value of the number in words and a status code response {int}")
    public void theUserGetsTheValueOfTheNumberInWordsAndAStatusCodeResponse(Integer code) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + code,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("Retorna una conversion a palabras",
                            responseSoap(), notNullValue())
            );
            LOGGER.info("CUMPLE!");
        } catch (Exception e){
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody(String number) {
        body = readFile(BODY_PATH_NUMBER_TO_WORDS.getValue());
        body = String.format(body, number);
    }

}
