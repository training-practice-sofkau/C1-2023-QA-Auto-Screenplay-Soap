package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static com.sofkau.models.Headers.headers;
import static com.sofkau.questions.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.PathFindPerson.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class FindPersonStepDefinition extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(FindPersonStepDefinition.class);

    @Given("a user that wants to know the Person Information by ID")
    public void aUserThatWantsToKnowThePersonInformationByID() {
        try {
            setUp(SOAP_FIND_PERSON_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("the user sends a request to get the person's information with the provided {string}")
    public void theUserSendsARequestToGetThePersonSInformationWithTheProvided(String id) {
        try {
            loadBody(id);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_FIND_PERSON.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion..." + "\n" + body);
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @Then("the user should receive the person's information and the status {int}")
    public void theUserShouldReceiveThePersonSInformationAndTheStatus(Integer code) {

        String responseBody = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);

        try {
            LOGGER.info(responseBody);

            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + code,
                            response -> response.statusCode(code))
            );
            /**
             * Método parse() de la instancia de DocumentBuilder analiza el cuerpo de respuesta del mensaje SOAP,
             * que se proporciona como un ByteArrayInputStream
             */
            if(code == 200) {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(responseBody.getBytes()));

                //la consulta XPath es "//", que significa "seleccionar todos los elementos con el nombre especificado en el documento"
                String name = XPathFactory.newInstance().newXPath().compile("//Name").evaluate(document);
                String age = XPathFactory.newInstance().newXPath().compile("//Age").evaluate(document);

                LOGGER.info("La respuesta obtenia: ");
                LOGGER.info("Name: " + name);
                LOGGER.info("Age: " + age);

                actor.should(
                        seeThat(" El nombre de la persona es:",
                                responseSoap(), containsString(name)
                ));

                LOGGER.info("CUMPLE");
            }

        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody(String idPerson) {
        body = readFile(BODY_FIND_PERSON_PATH.getValue());
        body = String.format(body, idPerson);
    }
}
