package project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project.entity.Driver;
import project.entity.Truck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileService {

    private static final Gson GSON = new GsonBuilder().create();
    private static final Path PATH_TRUCK = Paths.get("project/data/trucks.json");
    private static final Path PATH_DRIVER = Paths.get("project/data/drivers.json");

    public static Truck[] truckReadFile() {
        String json;
        try {
            json = Files.readString(PATH_TRUCK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return GSON.fromJson(json, Truck[].class);
    }

    public static Driver[] driverReadFile() {
        String json;
        try {
            json = Files.readString(PATH_DRIVER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return GSON.fromJson(json, Driver[].class);
    }
}
