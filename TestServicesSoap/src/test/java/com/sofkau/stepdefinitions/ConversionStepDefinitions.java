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
import static com.sofkau.models.HeadersCurrency.headersCurrency;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class ConversionStepDefinitions extends ApiSetUp{
    private static final Logger LOGGER = Logger.getLogger(ConversionStepDefinitions.class);
    private String body;
    @Given("a user that wants to know the exchange of their money in the {string} to {string} the amount of {int}")
    public void aUserThatWantsToKnowTheExchangeOfTheirMoneyInTheToTheAmountOf(String string, String string2, Integer int1) {
        try {
            setUp(SOAP_DIVISAS_BASE_URL.getValue());
            LOGGER.info("Se inicia la automatizacion");
            loadBody(string,string2,int1);
        }catch (Exception e){
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("the user sends the request to the currenci api")
    public void theUserSendsTheRequestToTheCurrenciApi() {
        try{
            actor.attemptsTo(
                    doPostSoap().withTheHeaders(headersCurrency().getHeadersCollection())
                            .andTheBody(body)
                            .andTheResource(RESOURCE_CURRENCY.getValue())
            );
            LOGGER.info("Se realiza la peticion");
        }catch (Exception e){
            LOGGER.info("Fallo la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @Then("the user gets the money exchange at {string}")
    public void theUserGetsTheMoneyExchangeAt(String string) {
        try{
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("el monto es",
                            responseSoap(), containsString(string))
            );
        }catch (Exception e){
            LOGGER.info("Error al comparar");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void loadBody(String string, String string2,Integer int1) {
        body = readFile(CURRENCY_BODY_PATH.getValue());
        body = String.format(body, string,string2,int1);
    }
}
