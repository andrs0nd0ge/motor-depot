package project.state;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;

public interface State {
    void changeDriver(Truck truck, Driver driver) throws StateException;
    void startDriving(Truck truck) throws StateException;
    void startRepair(Truck truck) throws StateException;
}
