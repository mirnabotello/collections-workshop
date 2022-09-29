package com.encora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.encora.data.Car;
import com.opencsv.bean.CsvToBeanBuilder;

public class CarTest {

    private static List<Car> cars;
    private long startTime;
    private long endTime;

    @BeforeAll
    static void setup() throws IOException {

        cars = new ArrayList<Car>();

        Files.list(Paths.get("resources/car/")).forEach(pathToFile -> {
            try {
                cars.addAll(new CsvToBeanBuilder<Car>(new FileReader(pathToFile.toString()))
                        .withType(Car.class).build().parse());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @BeforeEach
    void startTime(){
        startTime = System.nanoTime();
    }

    @AfterEach
    void endTime() {
        endTime = System.nanoTime();
        System.out.println("Time to process: " + (endTime - startTime)/1000000);
    }

    @Test
    void validateCarsCount()
    {
        assertEquals(10000, cars.size());
    }

}
