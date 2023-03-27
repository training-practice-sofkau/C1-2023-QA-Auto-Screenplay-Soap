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

public class CodigoTelefonicoStepDefinitions extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(CodigoTelefonicoStepDefinitions.class);
    @Given("the user knows what the telephone code of a country")
    public void theUserKnowsWhatTheTelephoneCodeOfACountry() {
        try {
            setUp(SOAP_CODIGOTEL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();

        } catch (Exception e) {

            LOGGER.info(" ha fallado la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();

        }

    }

    @When("you send the request to the API")
    public void youSendTheRequestToTheAPI() {
        try {

            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CODIGOTELEFONICO.getValue())
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

    @Then("you get the correct code")
    public void youGetTheCorrectCode() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" la capital es",
                            responseSoap(), containsString("64"))
            );
            LOGGER.info("SI CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }
    private void loadBody() {
        body = readFile(BODYCODIGOTEL_PATH.getValue());
        body = String.format(body, "NZ");
    }


}
