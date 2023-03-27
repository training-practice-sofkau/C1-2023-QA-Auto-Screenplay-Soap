package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.models.Headers.headers;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.Constants.*;
import static com.sofkau.utils.Constants.TOKEN;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;

public class ConvertValueStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(ConvertValueStepDef.class);

    @Given("I have access to the Xignite API server")
    public void iHaveAccessToTheXigniteAPIServer() {
        try {
            log.info("Init scenario");
            setUp(XIGNITE_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Wrong Setup provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @When("I try to convert from {string} currency, to {string} currency the amount {string}")
    public void iTryToConvertFromCurrencyToCurrencyTheAmount(String from, String to, String amount) {
        try {
            log.info("Running step");
            actor.attemptsTo(
                    doPostSoap()
                            .withHeaders(headers()
                                    .getHeaders(String.format("%s;%s;%s",
                                            TYPE.getValue(),
                                            CODE.getValue(),
                                            ACTION_CONVERT_VALUE.getValue())))
                            .andResource(GLOBAL_CURRENCIES_RESOURCE.getValue())
                            .andBody(String.format(readFile(BODY_CONVERT_VALUE.getValue()),
                                    TOKEN.getValue(),
                                    from,
                                    to,
                                    amount))
            );
            log.info("Step completed");
        } catch (Exception e) {
            log.error("Wrong step provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("I will see the response code {int}")
    public void iWillSeeTheResponseCode(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Status code",
                            response -> response.statusCode(code))
            );
            log.info("First assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @And("I will see the result {string}")
    public void iWillSeeTheResult(String result) {
        try {
            actor.should(
                    seeThat("Convert result",
                            responseSoap(), containsString(result))
            );
            log.info("Second assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            log.error(e.getMessage());
            Assertions.fail();
        } finally {
            log.info("Test completed");
        }
    }
}