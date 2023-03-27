package com.sofkau.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class CalculadoraStepDefinitions {

    String body;
    private static final Logger LOGGER = Logger.getLogger(CambioDivisaStepDefinitions.class);
    @Given("que tengo una calculadora")
    public void queTengoUnaCalculadora() {

    }

    @When("ingreso {string} y {string}")
    public void ingresoY(String string, String string2) {

    }

    @Then("el resultado debe ser {string}")
    public void elResultadoDebeSer(String string) {

    }

}
