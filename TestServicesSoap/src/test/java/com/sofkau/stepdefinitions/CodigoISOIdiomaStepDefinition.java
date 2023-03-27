package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CodigoISOIdiomaStepDefinition  extends ApiSetUp {

    @Given("El servicio de Conuntri Info Service SOAP esta disponible")
    public void elServicioDeConuntriInfoServiceSOAPEstaDisponible() {



    }

    @When("Consulto el codigo ISO del {string}")
    public void consultoElCodigoISODel(String string) {

    }

    @Then("deberia obtener el {string}  y el {int} de la respuesta")
    public void deberiaObtenerElYElDeLaRespuesta(String string, Integer int1) {

    }

}
