package project.state;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;

public class OnRoute implements State{

    @Override
    public void changeDriver(Truck truck, Driver driver) throws StateException {
        try {
            throw new StateException();
        } catch (StateException e) {
            System.out.println("The truck is already on route, cannot change driver!");
        }
    }

    @Override
    public void startDriving(Truck truck) throws StateException {
        try {
            throw new StateException();
        } catch (StateException e) {
            System.out.println("The truck is already on route!");
        }
    }

    @Override
    public void startRepair(Truck truck) {
        truck.setCurrentState(new OnRepair());
        truck.setState("repair");
        System.out.println("The truck has been sent to repair");
    }
}
