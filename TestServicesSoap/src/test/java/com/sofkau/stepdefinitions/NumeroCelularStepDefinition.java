package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
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

public class NumeroCelularStepDefinition  extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(NumeroCelularStepDefinition.class);
    String body;
    private void loadBody(String pais) {
        body = readFile(BODY_PATH_ISO_NUMERO_CELULAR.getValue());
        body = String.format(body, pais );
    }


    @Given("El servicio de Country Info Service SOAP esta disponible y estable")
    public void el_servicio_de_Country_Info_Service_SOAP_esta_disponible_y_estable() {

        try {
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }



    @When("Envio el codigo ISO del pais {string} para consultar el numero de celular")
    public void envio_el_codigo_ISO_del_pais_para_consultar_el_numero_de_celular(String pais) {
        try {
            loadBody(pais);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_ISO_LENGUAJE_CODE.getValue())
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


    @Then("deberia obtener el  {string}  y el {int} de la respuesta")
    public void deberia_obtener_el_y_el_de_la_respuesta(String numeroCelular, Integer Statushttp) {

        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(

                    seeThatResponse("el codigo de respuesta es: " + Statushttp,
                            response -> response.statusCode(Statushttp))

            );


            if (Statushttp == 200) {
                actor.should(
                        seeThat("El indicativo del celular es:",
                                responseSoap(), containsString(numeroCelular))
                );
            }

            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }



}
