package project.state;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;
import project.service.DriverFileService;

public class OnBase implements State{

    public void changeDriver(Truck truck) throws StateException {
        Driver[] drivers = DriverFileService.driverReadFile();
        int count = 0;
        for (Driver d : drivers) {
            if (d.isBusy()) {
                count++;
                System.out.println(count);
                if (count == drivers.length){
                    System.out.println("All drivers are busy now!");
                    throw new StateException();
                };
            }
            else if (truck.getDriver().equals("")){
                truck.setDriver(d.getName());
                d.setBusy(true);
                System.out.println("project.entity.Driver " + d.getName() + " has just got behind the wheel of " + truck.getName());
                break;
            }
            else if(!d.isBusy() && !truck.getDriver().equals("")){
                String gName = d.getName();
                System.out.println(gName);
                d.setBusy(true);
                if (d.getName().equals(truck.getName())){
                    d.setBusy(false);
                }
                truck.setDriver(gName);
                System.out.println("Current truck: " + truck.getName() + ", driven by " + gName);
            }
        }
    }

    public void startDriving(Truck truck) {
        truck.setCurrentState(new OnRoute());
        truck.setState("route");
        System.out.println("The truck went on the route successfully");
    }

    public void startRepair(Truck truck) {
        truck.setState("repair");
        truck.setCurrentState(new OnRepair());
        System.out.println("The truck went under repair");
    }
}