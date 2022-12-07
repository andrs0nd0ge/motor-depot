package project.entity;

import project.state.State;

public class Driver {
    protected int id;
    protected String name;
    protected boolean isBusy;
    protected String state;
    protected transient State currentState;

    public Driver(int id, String name, boolean isBusy, String state){
        this.id = id;
        this.name = name;
        this.isBusy = isBusy;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public String toString() {
        return String.format(" %s | %-14s |", id, name);
    }
}
