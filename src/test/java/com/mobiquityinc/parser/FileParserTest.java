package com.mobiquityinc.parser;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static com.mobiquityinc.AbsoluteFilePathUtil.getAbsoluteFilePathForResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileParserTest {

    @Test
    public void parseFileIntoItems_testScenario1_shouldContainOneItem() throws URISyntaxException {
        String file = getAbsoluteFilePathForResource("scenario-1.txt");
        System.out.println(file);

        Map<Integer, List<Item>> integerListMap = FileParser.parseFileIntoItems(file);
        assertEquals(1, integerListMap.size());
    }

    @Test
    public void parseFileIntoItems_testScenarioAll_shouldContainFourItems() throws URISyntaxException {
        String file = getAbsoluteFilePathForResource("scenario-all.txt");
        System.out.println(file);

        Map<Integer, List<Item>> integerListMap = FileParser.parseFileIntoItems(file);
        assertEquals(4, integerListMap.size());
    }

    @Test(expected = APIException.class)
    public void parseFileIntoItems_testScenarioTooManyItems_shouldThrowAPIException() throws URISyntaxException {
        String file = getAbsoluteFilePathForResource("scenario-too-many-items.txt");
        System.out.println(file);

        FileParser.parseFileIntoItems(file);
    }

    @Test(expected = APIException.class)
    public void parseFileIntoItems_testScenarioItemTooHeavy_shouldThrowAPIException() throws URISyntaxException {
        String file = getAbsoluteFilePathForResource("scenario-item-too-heavy.txt");
        System.out.println(file);

        FileParser.parseFileIntoItems(file);
    }

    @Test(expected = APIException.class)
    public void parseFileIntoItems_testScenarioItemTooExpensive_shouldThrowAPIException() throws URISyntaxException {
        String file = getAbsoluteFilePathForResource("scenario-item-too-expensive.txt");
        System.out.println(file);

        FileParser.parseFileIntoItems(file);
    }
}