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

public class CalculadoraStepDefinition extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(CalculadoraStepDefinition.class);

    @Given("un usuario quiere obtener el resultado de una multiplicacion")
    public void unUsuarioQuiereObtenerElResultadoDeUnaMultiplicacion() {
        try {
            setUp(SOAP_CALCULADORA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("envio una solicitud GET para obtener el resultado de una multiplicacion especificada")
    public void envioUnaSolicitudGETParaObtenerElResultadoDeUnaMultiplicacionEspecificada() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CALCULADORA.getValue())
                            .withTheHeaders(headers().getHeadersZipCode())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info("fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("el servicio me responde con un estado HTTP {int} OK")
    public void elServicioMeRespondeConUnEstadoHTTPOK(Integer int1) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
            LOGGER.info("CUMPLE EL CODIGO DE ESTADO");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("debe devolver el resultado de la multiplicacion en el cuerpo de la respuesta")
    public void debeDevolverElResultadoDeLaMultiplicacionEnElCuerpoDeLaRespuesta() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThat(" El resultado de la multiplicacion: ",
                            responseSoap(), containsString("15"))
            );
            LOGGER.info("CUMPLE!!");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }
    private void loadBody() {
        body = readFile(BODY_CALCULADORA_PATH.getValue());
        body = String.format(body, "5", "3");

    }
}
