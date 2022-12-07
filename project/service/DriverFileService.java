package project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project.entity.Driver;
import project.entity.Truck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DriverFileService {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path path = Paths.get("data/trucks.project.json");
    public static Driver[] driverReadFile(){
        Gson gson = new Gson();
        Path path = Paths.get("data/drivers.project.json");
        String json;
        try {
            json = Files.readString(path);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(json, Driver[].class);
    }

    public static void driverWriteFile(Truck[] trucks) {
        String json = gson.toJson(trucks);
        byte[] arr = json.getBytes();
        try {
            Files.write(path, arr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
