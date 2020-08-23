package pl.training.trains.jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.time.LocalTime;

public class EstimatedArrivalTimeSteps {

    @Given("I want to go from <departure> to <destination>")
    public void givenIWantToGoFrom(String departure, String destination) {
    }

    @Given("the next train is scheduled to leave at <departure-time> on the <line> line")
    public void givenTheNextTrainIsScheduledToLeave(@Named("departure-time") LocalTime departureTime, String line) {
    }

    @When("I ask for my arrival time")
    public void whenIAskForMyArrivalTime() {
    }

    @Then("the estimated arrival time should be <arrival-time>")
    public void thenTheEstimatedArrivalTimeShouldBe(@Named("arrival-time") LocalTime expectedArrivalTime) {
    }

}

