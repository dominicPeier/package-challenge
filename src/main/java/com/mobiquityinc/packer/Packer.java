package com.mobiquityinc.packer;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.parser.FileParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Packer {

    public static String pack(String absoluteFilePath) {
        Map<Integer, List<Item>> maxPackageWeightToItemsListMap = FileParser.parseFileIntoItems(absoluteFilePath);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, List<Item>> maxPackageWeightToItemList : maxPackageWeightToItemsListMap.entrySet()) {
            int maxPackageWeight = maxPackageWeightToItemList.getKey();
            List<Item> items = maxPackageWeightToItemList.getValue();

            Package optimalPackage = computeOptimalPackage(maxPackageWeight, items);
            stringBuilder.append(optimalPackage);
        }

        return stringBuilder.toString();
    }

    private static Package computeOptimalPackage(int maxPackageWeight, List<Item> items) {
        // Not great -- wanted to use references but they kept on being reset
        List<Package> packages = new ArrayList<>(Collections.singleton(new Package(maxPackageWeight)));
        computeOptimalPackage(maxPackageWeight, new ArrayList<>(), items, packages);
        return packages.get(0);
    }

    // Simple recursive function to get combinations
    private static void computeOptimalPackage(int maxPackageWeight, List<Item> itemsUnderScrutiny, List<Item> remainderItems, List<Package> singlePackageList) {
        Package newPackage = new Package(maxPackageWeight, itemsUnderScrutiny);
        if (newPackage.isOverweight()) {
            // return here so we don't bother continuing checking the following combinations -- if 1 is overweight, no point in checking 1, 2
            return;
        }

        Package bestPackage = singlePackageList.get(0);
        if (newPackageIsMoreExpensive(newPackage, bestPackage)) {
            resetBestCombination(singlePackageList, newPackage);
        } else if (newPackageCostsTheSameButWeighsLess(newPackage, bestPackage)) {
            resetBestCombination(singlePackageList, newPackage);
        }

        for (int i = 0; i < remainderItems.size(); i++) {
            List<Item> itemsUnderScrutinyWithValueAdded = new ArrayList<>(itemsUnderScrutiny);
            itemsUnderScrutinyWithValueAdded.add(remainderItems.get(i));

            List<Item> remainderItemsWithValueRemoved = remainderItems.subList(i + 1, remainderItems.size());
            computeOptimalPackage(maxPackageWeight, itemsUnderScrutinyWithValueAdded, remainderItemsWithValueRemoved, singlePackageList);
        }
    }

    private static boolean newPackageIsMoreExpensive(Package newPackage, Package bestPackage) {
        return bestPackage.getCost() < newPackage.getCost();
    }

    private static boolean newPackageCostsTheSameButWeighsLess(Package newPackage, Package bestPackage) {
        return bestPackage.getCost() == newPackage.getCost() && bestPackage.getWeight() > newPackage.getWeight();
    }

    private static void resetBestCombination(List<Package> singlePackageList, Package newPackage) {
        singlePackageList.clear();
        singlePackageList.add(newPackage);
    }
}