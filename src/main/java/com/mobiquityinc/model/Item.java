package com.mobiquityinc.model;

import com.mobiquityinc.exception.APIException;

public class Item {

    private static final double MAX_WEIGHT = 100.0;
    private static final double MAX_COST = 100.0;

    private final String id;
    private final double weight;
    private final double cost;

    public Item(String id, double weight, double cost) {
        this.id = id;
        this.weight = weight;
        this.cost = cost;

        if (weight > MAX_WEIGHT || cost > MAX_COST) {
            throw new APIException(String.format("Item with id %s is either too heavy or costs too much", id));
        }
    }

    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return id;
    }
}
