package com.mobiquityinc.parser;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class FileParser {

    private static final int MAX_ITEMS = 15;

    public static Map<Integer, List<Item>> parseFileIntoItems(String absoluteFilepath) {

        try {
            List<String> lineItems = Files.readAllLines(Paths.get(absoluteFilepath));
            if (lineItems.size() > MAX_ITEMS) {
                throw new APIException(String.format("File contains more items than max of %s", MAX_ITEMS));
            }

            Map<Integer, List<Item>> itemMap = new HashMap<>();

            lineItems.forEach(lineItem -> {
                String[] splitLineItems = lineItem.trim().split(" ");
                int maxPackageWeight = mapPackageWeight(splitLineItems);
                List<Item> itemList = mapItems(splitLineItems);
                itemMap.put(maxPackageWeight, itemList);
            });

            return itemMap;
        } catch (Exception e) {
            throw new APIException("Failed to parse input", e);
        }
    }

    private static int mapPackageWeight(String[] lineItemArray) {
        return parseInt(lineItemArray[0].trim());
    }

    private static List<Item> mapItems(String[] lineItemArray) {
        List<Item> items = new ArrayList<>();

        for (int i = 2; i < lineItemArray.length; i++) {
            String rawItem = lineItemArray[i].trim();
            String[] itemComponents = rawItem.substring(1, rawItem.length() - 1).split(",");
            Item item = new Item(itemComponents[0], parseDouble(itemComponents[1]), mapMoney(itemComponents[2]));
            items.add(item);
        }

        return items;
    }

    // TODO: 10/7/18 Don't assume that currency symbol is only 1 character length
    private static double mapMoney(String moneyItemComponent) {
        return Double.parseDouble(moneyItemComponent.substring(1));
    }
}
