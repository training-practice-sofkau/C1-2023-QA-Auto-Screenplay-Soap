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

public class ZipCode_CoordenadasUsStepDefinition extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(ZipCode_CoordenadasUsStepDefinition.class);
    @Given("un usuario quiere saber las coordenadas de un codigo postal")
    public void unUsuarioQuiereSaberLasCoordenadasDeUnCodigoPostal() {
        try {
            setUp(SOAP_ZIPCODE_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("realizo una peticion POST con el codigo postal {string}")
    public void realizoUnaPeticionPOSTConElCodigoPostal(String string) {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_ZIPCODE.getValue())
                            .withTheHeaders(headers().getHeadersZipCode())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @Then("el servicio responde con un estado HTTP {int} OK")
    public void elServicioRespondeConUnEstadoHTTPOK(Integer int1) {
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

    @Then("en el cuerpo de la respuesta obtengo las coordenadas correspondientes al codigo postal {string}")
    public void enElCuerpoDeLaRespuestaObtengoLasCoordenadasCorrespondientesAlCodigoPostal(String string) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThat(" las coordenadas son: ",
                            responseSoap(), containsString("33.5969,-86.4946"))
            );
            LOGGER.info("COINCIDEN LAS COORDENADAS!!");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    private void loadBody() {
        body = readFile(BODY_ZIP_PATH.getValue());
        body = String.format(body, "35004");
    }
}
