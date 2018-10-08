package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

public class Application {
    public static void main(String[] args) {
        String absoluteFilePath = args[0];

        try {
            String optimalItemCombination = Packer.pack(absoluteFilePath);
            System.out.println(optimalItemCombination);
        } catch (APIException apie) {
            System.out.println("Failed to parse file at");
        }
    }
}
