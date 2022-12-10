package project;

import project.entity.Driver;
import project.entity.Truck;
import project.exception.StateException;
import project.service.FileService;
import project.state.OnBase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int driversBehindTheWheel = 0;
    static Truck[] trucks = FileService.truckReadFile();
    static Driver[] drivers = FileService.driverReadFile();
    public static void main(String[] args) {
        run();
    }

    private static void run() throws StateException {
        setInitialStateOfTrucks(trucks);
        executeInLoop(trucks, drivers);
    }

    private static void executeInLoop(Truck[] trucks, Driver[] drivers) {
        while (true) {
            print(trucks, drivers);
            askForId(trucks, drivers);
        }
    }

    private static void setInitialStateOfTrucks(Truck[] trucks) {
        for (Truck truck : trucks) {
            truck.setCurrentState(new OnBase());
        }
    }

    private static void print(Truck[] trucks, Driver[] drivers) {
        System.out.println(String.format(" %s | %-14s | %-7s| %-6s\n", "#", "Truck", "Driver", "State") +
                "---+----------------+--------+--------");
        for (Truck truck : trucks) {
            System.out.println(truck);
        }
        System.out.print("---+----------------+--------+--------");
        System.out.println(String.format("\n %s | %-14s | %-7s\n", "#", "Driver", "Truck") +
                "---+----------------+--------");
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    private static void askForId(Truck[] trucks, Driver[] drivers) {
        int idChoose;
        try {
            System.out.print("Enter ID of a truck: ");
            idChoose = new Scanner(System.in).nextInt();
            System.out.println(trucks[idChoose - 1]);
            if (idChoose > 3) {
                throw new InputMismatchException();
            }
            askAction(idChoose, trucks, drivers);
        } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("Incorrect input!");
            print(trucks, drivers);
            askForId(trucks, drivers);
        }
    }

    private static void askAction(int idChoose, Truck[] trucks, Driver[] drivers) {
        System.out.print("""
                    What would you like to do?
                    1. Change driver
                    2. Send on route
                    3. Send on repair
                    """);
        try {
            int actChoose = new Scanner(System.in).nextInt();
            if (actChoose > 3) {
                throw new InputMismatchException();
            }
            doAction(actChoose, idChoose, trucks, drivers);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input!");
            System.out.println(trucks[idChoose - 1]);
            askAction(idChoose, trucks, drivers);
        }
    }

    private static void doAction(int actChoose, int idChoose, Truck[] trucks, Driver[] drivers) {
        switch (actChoose) {
            case 1 -> {
                if (checkForDriversBusyness(drivers)) {
                    System.out.println("All drivers are busy!");
                } else {
                    trucks[idChoose - 1].getCurrentState().changeDriver(trucks[idChoose - 1], getDriverBehindTheWheel(idChoose, trucks, drivers));
                }
            }
            case 2 -> trucks[idChoose - 1].getCurrentState().startDriving(trucks[idChoose - 1]);
            case 3 -> trucks[idChoose - 1].getCurrentState().startRepair(trucks[idChoose - 1]);
            default -> throw new StateException();
        }
    }

    private static boolean checkForDriversBusyness(Driver[] drivers) {
        return driversBehindTheWheel >= drivers.length;
    }

    private static Driver getDriverBehindTheWheel(int idChoose, Truck[] trucks, Driver[] drivers) {
        for (int i = 0; i < trucks.length; i++) {
            for (int j = 0; j < trucks.length; j++) {
                if (drivers[i].getTruck().getName() != null) {
                    continue;
                } if (trucks[idChoose - 1].getDriver().getName() == null || trucks[idChoose - 1].getDriver().getName().equals("")) {
                    trucks[idChoose - 1].setDriver(drivers[i]);
                    drivers[i].setTruck(trucks[idChoose - 1]);
                    driversBehindTheWheel++;
                    return drivers[i];
                }
            }
        }
        return null;
    }
}
