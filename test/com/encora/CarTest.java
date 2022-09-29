package com.encora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.encora.data.Car;
import com.opencsv.bean.CsvToBeanBuilder;

public class CarTest {

    private static List<Car> cars;

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

    @Test
    void validateCarsCount()
    {
        assertEquals(10000, cars.size());
    }
}
