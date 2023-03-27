package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.models.HeadersCalculadora.headersCalculadora;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CalculadoraStepDefinitions extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(CalculadoraStepDefinitions.class);
    private String body;
    @Given("a user that wants to add two numbers {int} to {int}")
    public void aUserThatWantsToAddTwoNumbersTo(Integer int1, Integer int2) {
        try {
            setUp(SOAP_CALCULATOR_URL.getValue());
            LOGGER.info("Se inicia la automatizacion");
            loadBody(int1,int2);
        }catch (Exception e){
            LOGGER.info("Fallo la configuracion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("the user sends the request to the sum api")
    public void theUserSendsTheRequestToTheSumApi() {
        try{
            actor.attemptsTo(
                    doPostSoap().withTheHeaders(headersCalculadora().getHeadersCollection())
                            .andTheResource(RESOURCE_SUM.getValue())
                            .andTheBody(body)
            );
            LOGGER.info("Se realiza la peticion");
        }catch (Exception e){
            LOGGER.info("Fallo al hacer la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @Then("the user gets the result {string}")
    public void theUserGetsTheResult(String string) {
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
    private void loadBody(Integer int1, Integer int2) {
        body = readFile(CALCULATOR_BODY_PATH.getValue());
        body = String.format(body,int1,int2);
    }
}