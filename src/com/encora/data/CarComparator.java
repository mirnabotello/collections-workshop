package com.encora.data;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {

    @Override
    public int compare(Car car, Car t1) {
        return car.getVin().compareTo(t1.getVin());
    }
}
