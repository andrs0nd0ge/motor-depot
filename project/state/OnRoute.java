package project.state;

import project.entity.Truck;
import project.exception.StateException;

public class OnRoute implements State{

    @Override
    public void changeDriver(Truck truck) throws StateException {
        System.out.println("The truck is already on route, cannot change driver!");
        throw new StateException();
    }

    @Override
    public void startDriving(Truck truck) throws StateException {
        System.out.println("The truck is already on route!");
        throw new StateException();
    }

    @Override
    public void startRepair(Truck truck) {
        truck.setCurrentState(new OnRepair());
        System.out.println("The truck has been sent to repair");
    }
}
