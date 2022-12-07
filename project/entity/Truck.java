package project.entity;

import project.state.OnBase;
import project.state.OnRepair;
import project.state.OnRoute;
import project.state.State;

public class Truck {
    protected int id;
    protected String name;
    protected String driver;
    protected String state;
    protected transient State currentState;

    public Truck (int id, String name, String driver, String state){
        this.id = id;
        this.name = name;
        this.driver = driver;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getState() {
        return state;
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
        return String.format(" %s | %-14s | %-7s| %-6s", id, name, driver, "On base");
    }
}
