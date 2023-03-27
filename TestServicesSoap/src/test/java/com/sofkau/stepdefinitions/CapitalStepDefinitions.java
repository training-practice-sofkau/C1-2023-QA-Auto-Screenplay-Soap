package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.models.Headers.headers;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CapitalStepDefinitions extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(CapitalStepDefinitions.class);
    String body;

    @Given("a user that wants to know the actual capital")
    public void aUserThatWantsToKnowTheActualCapital() {
        try {
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            loadBody();
        } catch (Exception e) {
            log.error("Wrong Setup provided");
            log.error(e.getMessage());
            Assertions.fail();
        }

    }

    @When("the user sends the request to the api")
    public void theUserSendsTheRequestToTheApi() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andResource(RESOURCE_CAPITAL.getValue())
                            .withHeaders(headers().getHeadersCol())
                            .andBody(body)
            );
        } catch (Exception e) {
            log.error("Wrong step provided");
            log.error(e.getMessage());
            Assertions.fail();
        }

    }

    @Then("the user gets the capital")
    public void theUserGetsTheCapital() {
        try {
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" la capital es",
                            responseSoap(), containsString("Bogota"))
            );
        } catch (Exception e) {
            log.error("Test failed");
            log.error(e.getMessage());
            Assertions.fail();
        } finally {
            log.info("Test completed");
        }

    }

    private void loadBody() {
        body = readFile(BODY_PATH.getValue());
        body = String.format(body, "CO");
    }
}