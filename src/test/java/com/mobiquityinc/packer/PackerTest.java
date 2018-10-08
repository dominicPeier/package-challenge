package com.mobiquityinc.packer;

import com.mobiquityinc.AbsoluteFilePathUtil;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PackerTest {

    @Test
    public void pack_scenario1_shouldReturnItemWithId4() throws URISyntaxException {
        String optimalItemCombination = Packer.pack(AbsoluteFilePathUtil.getAbsoluteFilePathForResource("scenario-1.txt"));
        assertThat(optimalItemCombination, is(equalTo("4")));
    }

    @Test
    public void pack_scenario2_shouldReturnItemWithDash() throws URISyntaxException {
        String optimalItemCombination = Packer.pack(AbsoluteFilePathUtil.getAbsoluteFilePathForResource("scenario-2.txt"));
        assertThat(optimalItemCombination, is(equalTo("-")));
    }

    @Test
    public void pack_scenario3_shouldReturnItemWithIdsTwoAndSeven() throws URISyntaxException {
        String optimalItemCombination = Packer.pack(AbsoluteFilePathUtil.getAbsoluteFilePathForResource("scenario-3.txt"));
        assertThat(optimalItemCombination, is(equalTo("2,7")));
    }

    @Test
    public void pack_scenario4_shouldReturnItemWithIdsEightAndNine() throws URISyntaxException {
        String optimalItemCombination = Packer.pack(AbsoluteFilePathUtil.getAbsoluteFilePathForResource("scenario-4.txt"));
        assertThat(optimalItemCombination, is(equalTo("8,9")));
    }

    @Test
    public void pack_scenarioAll_shouldReturnItemWithCorrectlyFormattedResult() throws URISyntaxException {
        String optimalItemCombination = Packer.pack(AbsoluteFilePathUtil.getAbsoluteFilePathForResource("scenario-all.txt"));
        String expectedOptimalCombination = "4-8,92,7";
        assertThat(optimalItemCombination, is(equalTo(expectedOptimalCombination)));
    }
}