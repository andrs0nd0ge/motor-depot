package project.entity;

import project.state.OnBase;
import project.state.OnRepair;
import project.state.OnRoute;
import project.state.State;

import java.util.Objects;

public class Truck {
    protected int id;
    protected String name;
    protected Driver driver;
    protected String state;
    protected transient State currentState;

    public Truck (int id, String name, Driver driver, String state){
        this.id = id;
        this.name = name;
        this.driver = driver;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setState(String state) {
        this.state = state;
    }

    public State getCurrentState() {
        return switch (state) {
            case "base" -> new OnBase();
            case "route" -> new OnRoute();
            case "repair" -> new OnRepair();
            default -> currentState;
        };
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public String toString() {
        return String.format(" %s | %-14s | %-6s | %-6s", id, name, Objects.requireNonNullElse(driver.name, ""), state);
    }
}
