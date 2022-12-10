package project.entity;

import java.util.Objects;

public class Driver {
    protected int id;

    protected String name;

    protected Truck truck;

    public Driver(int id, String name, Truck truck){
        this.id = id;
        this.name = name;
        this.truck = truck;
    }

    public String getName() {
        return name;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return String.format(" %s | %-14s | %-6s", id, name, Objects.requireNonNullElse(truck.name, ""));
    }
}
