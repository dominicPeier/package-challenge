package com.mobiquityinc.model;

import com.mobiquityinc.exception.APIException;

import java.util.ArrayList;
import java.util.List;

public class Package {

    private static final double WEIGHT_LIMIT = 100.0;

    private double maxWeight;
    private double weight;
    private double cost;
    private List<Item> items;

    public Package(int maxWeight) {
        this(maxWeight, new ArrayList<>());
    }

    public Package(double maxWeight, List<Item> items) {
        if (maxWeight > WEIGHT_LIMIT) {
            throw new APIException("Package max weight exceeds weight limit");
        }

        this.maxWeight = maxWeight;
        this.items = items;

        calculateWeight();
        calculateCost();
    }

    private void calculateWeight() {
        if (this.items == null || this.items.isEmpty()) {
            this.weight = 0.0;
            return;
        }

        this.weight = this.items.stream().mapToDouble(Item::getWeight).sum();
    }

    private void calculateCost() {
        if (this.items == null || this.items.isEmpty()) {
            this.cost = 0.0;
            return;
        }

        this.cost = this.items.stream().mapToDouble(Item::getCost).sum();
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    public boolean isOverweight() {
        return this.weight > this.maxWeight;
    }

    @Override
    public String toString() {
        if (this.items.isEmpty()) {
            return "-";
        }

        if (this.items.size() == 1) {
            return this.items.get(0).getId();
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            builder.append(items.get(i).getId());
            if (i != items.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
}