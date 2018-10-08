package com.mobiquityinc;

import com.mobiquityinc.parser.FileParserTest;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class AbsoluteFilePathUtil {
    public static String getAbsoluteFilePathForResource(String resourceName) throws URISyntaxException {
        URL resource = FileParserTest.class.getResource(String.format("/%s", resourceName));
        return Paths.get(resource.toURI()).toString();
    }
}
