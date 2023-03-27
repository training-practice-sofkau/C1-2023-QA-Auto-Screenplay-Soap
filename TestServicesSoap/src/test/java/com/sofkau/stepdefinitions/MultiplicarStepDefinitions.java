package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
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

public class MultiplicarStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(MultiplicarStepDefinitions.class);
    String body;

    private void loadBody(String num1,String num2) {
        body = readFile(BODY_MULTIPLICAR.getValue());
        body = String.format(body, Integer.parseInt(num1), Integer.parseInt(num2));
    }
    @Given("que estoy apuntando con un endpoint a la api de la calculadora")
    public void queEstoyApuntandoConUnEndpointALaApiDeLaCalculadora() {
        try{
            setUp(SOAP_CALCULADORA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        }catch (Exception e){
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio la peticion post con el {string} y el {string}")
    public void envioLaPeticionPostConElYEl(String num1, String num2) {
        loadBody(num1,num2);
        try{

            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CALCULADORA_MULTIPLICAR.getValue())
                            .withTheHeaders(headers().getHeadersCollectionCalculadora())
                            .andTheBody(body)
            );
        }catch (Exception e){
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} de codigo de respuesta y el {string} de la multiplicacion")
    public void reciboDeCodigoDeRespuestaYElDeLaMultiplicacion(Integer code, String resultado) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat(" El resultado es",
                            responseSoap(), containsString(resultado))
            );

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            LOGGER.info("Error al realizar la comparacion");
            Assertions.fail();
        }
    }
}
