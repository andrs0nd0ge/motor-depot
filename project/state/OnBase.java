package project.state;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;

public class OnBase implements State{

    public void changeDriver(Truck truck, Driver driver) throws StateException {
        System.out.printf("%s is now being driven by %s\n", truck.getName(), truck.getDriver().getName());
    }

    @Override
    public void startDriving(Truck truck) {
        try {
            if (truck.getDriver().getName() != null) {
                truck.setCurrentState(new OnRoute());
                truck.setState("route");
                System.out.println("The truck went on the route successfully");
            } else {
                throw new StateException();
            }
        } catch (StateException e) {
            System.out.println("No one is behind the wheel of a given truck!");
        }
    }

    @Override
    public void startRepair(Truck truck) {
        truck.setCurrentState(new OnRepair());
        truck.setState("repair");
        System.out.println("The truck went under repair");
    }
}