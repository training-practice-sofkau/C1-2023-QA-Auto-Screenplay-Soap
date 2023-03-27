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
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
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

    final static String str="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
            "    <soapenv:Header/>\n" +
            "    <soapenv:Body>\n" +
            "        <tem:Multiply>\n" +
            "            <tem:intA>%s</tem:intA>\n" +
            "            <tem:intB>%s</tem:intB>\n" +
            "        </tem:Multiply>\n" +
            "    </soapenv:Body>\n" +
            "</soapenv:Envelope>";




    private static String convertXmlToString(Document doc) {
        DOMSource domSource = new DOMSource((Node) doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    private static Document convertStringToXml(String xmlString) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document doc = (Document) builder.parse(new InputSource(new StringReader(xmlString)));

            return doc;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadBody(String num1,String num2) {
        body = readFile(BODY_MULTIPLICAR.getValue());
        body = String.format(body, num1, num2);
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
        Document document=convertStringToXml(String.format(str,num1,num2));
        String xml = convertXmlToString(document);
        try{

            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CALCULADORA_MULTIPLICAR.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
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
                    seeThat(" la moneda es",
                            responseSoap(), containsString(resultado))
            );

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            LOGGER.info("Error al realizar la comparacion");
            Assertions.fail();
        }
    }
}
