package project;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;
import project.service.DriverFileService;
import project.service.TruckFileService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            run();
        } catch (StateException e) {
            throw new RuntimeException(e);
        }
    }

    public static void run() throws StateException {
        Truck[] trucks = TruckFileService.truckReadFile();
        System.out.println(String.format(" %s | %-14s | %-7s| %-6s\n", "#", "project.entity.Truck", "project.entity.Driver", "project.state.State") +
                "---+----------------+--------+--------");
        for (Truck t : trucks) {
            System.out.println(t);
        }
        System.out.println(String.format("\n %s | %-14s | %-7s\n", "#", "project.entity.Driver", "project.entity.Truck") +
                "---+----------------+--------");
        Driver[] drivers = DriverFileService.driverReadFile();
        for (Driver d : drivers) {
            System.out.println(d);
        }
        System.out.print("Enter ID of a truck: ");
        int idChoose = new Scanner(System.in).nextInt();
        try {
            System.out.println("\n" + trucks[idChoose - 1]);
            if (idChoose > 3) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
        System.out.println("""
                What would you like to do?
                1. Change driver
                2. Send on route
                3. Send on repair
                """);
        int actChoose = new Scanner(System.in).nextInt();
        System.out.println("");
        switch (actChoose) {
            case 1:
            case 2:
            case 3:
                try {
                    trucks[idChoose - 1].getCurrentState().changeDriver(trucks[idChoose - 1]);
                } catch (StateException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new StateException();
        }
        run();
    }
}
