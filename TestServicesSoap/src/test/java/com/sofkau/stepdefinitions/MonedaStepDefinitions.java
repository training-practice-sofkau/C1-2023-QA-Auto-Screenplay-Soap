package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

public class MonedaStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(MonedaStepDefinitions.class);
    String body;
    private void loadBody(String codigoP) {
        body = readFile(BODY_MONEDA.getValue());
        body = String.format(body, codigoP);
    }
    @Given("que estoy apuntando con un endpoint a la api")
    public void queEstoyApuntandoConUnEndpointALaApi() {
        try{
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        }catch (Exception e){
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio la peticion post con el {string} de un pais")
    public void envioLaPeticionPostConElDeUnPais(String codigoPais) {
        try{
            loadBody(codigoPais);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_MONEDA.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
        }catch (Exception e){
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} de codigo de respuesta y el {string} de la moneda del pais")
    public void reciboDeCodigoDeRespuestaYElDeLaMonedaDelPais(Integer code, String moneda) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat(" la moneda es",
                            responseSoap(), containsString(moneda))
            );

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            LOGGER.info("Error al realizar la comparacion");
            Assertions.fail();
        }
    }
}
