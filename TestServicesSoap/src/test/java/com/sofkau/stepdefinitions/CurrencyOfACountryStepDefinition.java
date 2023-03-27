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
import static com.sofkau.utils.Path.*;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.models.Headers.headers;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;


public class CurrencyOfACountryStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(CurrencyOfACountryStepDefinition.class);
    String body;

    @Given("the user wants to know the currency of a country")
    public void theUserWantsToKnowTheCurrencyOfACountry() {

        try {
            setUp(SOAP_CURRENCY_OF_A_COUNTRY_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("Fallo en la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("the user sends a request with the name {string} of a country")
    public void theUserSendsARequestWithTheNameOfACountry(String country) {
        try {
            loadBody(country);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CURRENCY_OF_A_COUNTRY.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets the currency of that country {string} and its ISO Code {string}")
    public void theUserGetsTheCurrencyOfThatCountryAndItsISOCode(String currency, String IsoCode) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("Retorna el nombre de la moneda",
                            responseSoap(), containsString(currency)),
                    seeThat("Y retorna el codigo ISO de la moneda",
                            responseSoap(), containsString(IsoCode))
            );
            LOGGER.info("Cumple!");
        } catch (Exception e) {
            LOGGER.info("Error en la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    private void loadBody(String country) {
        body = readFile(BODY_PATH_CURRENCY_OF_A_COUNTRY.getValue());
        body = String.format(body, country);
    }
}
