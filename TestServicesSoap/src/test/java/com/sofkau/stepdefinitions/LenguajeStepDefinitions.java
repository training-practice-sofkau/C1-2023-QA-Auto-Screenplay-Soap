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

public class LenguajeStepDefinitions extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(LenguajeStepDefinitions.class);
    String body;

    private void loadBody(String codigoL) {
        body = readFile(BODY_PATH_LENGUAJE.getValue());
        body = String.format(body, codigoL);
    }
    @Given("que estoy apuntando con un endpoint a la api SOAP de paises")
    public void queEstoyApuntandoConUnEndpointALaApiSOAPDePaises() {
        try{
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        }catch (Exception e){
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio la peticion post con el {string} del lenguaje de un pais")
    public void envioLaPeticionPostConElDelLenguajeDeUnPais(String codigoLenguaje) {
        try{
            loadBody(codigoLenguaje);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_LENGUAJE.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
        }catch (Exception e){
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} de codigo de respuesta y el {string} del lenguaje")
    public void reciboDeCodigoDeRespuestaYElDelLenguaje(Integer codigo, String respuesta) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(codigo)),
                    seeThat(" la moneda es",
                            responseSoap(), containsString(respuesta))
            );

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            LOGGER.info("Error al realizar la comparacion");
            Assertions.fail();
        }
    }
}
