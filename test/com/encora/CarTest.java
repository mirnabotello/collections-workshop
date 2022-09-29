package com.encora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.encora.data.Car;
import com.encora.data.Person;
import com.encora.data.Relation;
import com.opencsv.bean.CsvToBeanBuilder;

public class CarTest {

    private static List<Car> cars;
    private static List<Person> people;
    private static List<Relation> relations;

    private Map<Car, Person> ownersByCar;

    private long creationStartTime;
    private long creationEndTime;
    private long startTime;
    private long endTime;

    @BeforeAll
    static void setup() throws IOException {

        cars = new ArrayList<>();
        people = new ArrayList<>();

        Files.list(Paths.get("resources/car/")).forEach(pathToFile -> {
            try {
                cars.addAll(new CsvToBeanBuilder<Car>(new FileReader(pathToFile.toString()))
                        .withType(Car.class).build().parse());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Files.list(Paths.get("resources/people/")).forEach(pathToFile -> {
            try {
                people.addAll(new CsvToBeanBuilder<Person>(new FileReader(pathToFile.toString()))
                        .withType(Person.class).build().parse());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        relations = new CsvToBeanBuilder<Relation>(new FileReader("resources/relations.csv"))
                .withType(Relation.class).build().parse();
    }

    @BeforeEach
    void startTime(){
        startTime = System.nanoTime();
    }

    @AfterEach
    void endTime() {
        endTime = System.nanoTime();
        long creationTime = (creationEndTime - creationStartTime)/1000000;
        long processTime = (endTime - startTime)/1000000;

        System.out.println("Time to create structure: " + creationTime);
        System.out.println("Complete time: " + processTime);
        System.out.println("Time using the structure: " + (processTime - creationTime));
    }

    @Test
    void validateCarsCount()
    {
        assertEquals(10000, cars.size());
        assertEquals(10000, people.size());
        assertEquals(7000, relations.size());
    }

}
