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
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Constants.*;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class OfficialRateStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(OfficialRateStepDef.class);

    @Given("I have access to Xignite API server")
    public void iHaveAccessToXigniteAPIServer() {
        try {
            log.info("Init scenario");
            setUp(XIGNITE_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Wrong Setup provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @When("I try to get the {string} currency to {string} as of the date {string}")
    public void iTryToGetTheCountryCurrencyToSymbolAsOfTheDate(String country, String symbol, String date) {
        try {
            log.info("Running step");
            actor.attemptsTo(
                    doPostSoap()
                            .withHeaders(headers()
                                    .getHeaders(String.format("%s;%s;%s",
                                            TYPE.getValue(),
                                            CODE.getValue(),
                                            ACTION_OFFICIAL_RATE.getValue())))
                            .andResource(GLOBAL_CURRENCIES_RESOURCE.getValue())
                            .andBody(String.format(readFile(BODY_OFFICIAL_RATE.getValue()),
                                    TOKEN.getValue(),
                                    country,
                                    symbol,
                                    date))
            );
            log.info("Step completed");
        } catch (Exception e) {
            log.error("Wrong step provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("I will see a response code {int}")
    public void iWillSeeAResponseCode(Integer code) {
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

    @And("I will see the Open rate {string}")
    public void iWillSeeTheOpenRate(String rate) {
        try {
            actor.should(
                    seeThat("Open Rate",
                            responseSoap(), containsString(rate))
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