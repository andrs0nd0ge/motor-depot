package project.state;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;

import java.util.Random;

public class OnRepair implements State {

    public void changeDriver(Truck trucks, Driver driver) throws StateException {
        try {
            throw new StateException();
        } catch (StateException e) {
            System.out.println("Cannot change driver, since the truck is on repair!");
        }
    }

    public void startDriving(Truck truck) {
        int rand = new Random().nextInt(2) + 1;
        switch (rand) {
            case 1 -> {
                truck.setCurrentState(new OnBase());
                truck.setState("base");
                System.out.println("The truck went onto the base");
            }
            case 2 -> {
                if (truck.getDriver().getName() != null) {
                    truck.setCurrentState(new OnRoute());
                    truck.setState("route");
                    System.out.println("The truck went on the route");
                } else {
                    System.out.println("No one is behind the wheel of a given truck, the truck has been sent onto the base");
                    truck.setCurrentState(new OnBase());
                    truck.setState("base");
                }
            }
        }
    }

    public void startRepair(Truck truck) throws StateException {
        try {
            throw new StateException();
        } catch (StateException e) {
            System.out.println("The truck is already under repair!");
        }
    }
}
