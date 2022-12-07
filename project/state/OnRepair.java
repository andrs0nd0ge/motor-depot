package project.state;

import project.entity.Truck;
import project.exception.StateException;

import java.util.Random;

public class OnRepair implements State {

    public void changeDriver(Truck trucks) throws StateException {
        System.out.println("Cannot change driver!");
        throw new StateException();
    }

    public void startDriving(Truck trucks) {
        int rand = new Random().nextInt(2) + 1;
        switch (rand) {
            case 1 -> {
                trucks.setCurrentState(new OnBase());
                System.out.println("The truck went onto the base");
            }
            case 2 -> {
                trucks.setCurrentState(new OnRepair());
                System.out.println("The truck went under repair");
            }
        }
    }

    public void startRepair(Truck trucks) throws StateException {
        System.out.println("The truck is already under repair!");
        throw new StateException();
    }
}
