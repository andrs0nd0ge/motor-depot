package project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project.entity.Truck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class TruckFileService {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path path = Paths.get("json/trucks.json");

    public static Truck[] truckReadFile(){
        Path path = Paths.get("json/trucks.json");
        String json;
        try {
            json = Files.readString(path);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(json, Truck[].class);
    }

    public static void truckWriteFile(Truck[] trucks) {
            String json = gson.toJson(trucks);
            byte[] arr = json.getBytes();
            try {
                Files.write(path, arr);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
