package com.google.hashcode.utils;

import com.google.hashcode.entity.Cell;
import com.google.hashcode.entity.ContainCells;
import com.google.hashcode.entity.Ingredient;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ex Armundia Devs
 */
public class IoUtilsTest {
    private static final String TEST_OUTPUT_FILE = "testOutput.txt";
    private static final String PARAGON_OUTPUT_EXAMPLE_FILE = "src/test/resources/paragonOutputExample.txt";
    private static final String EXAMPLE_PIZZA_FILE = FilesPaths.EXAMPLE_INPUT_FILE_PATH;

    private static List<ContainCells> createSlicesForParagonOutputExample() {
        ContainCells slice0 = new ContainCells();
        ContainCells slice1 = new ContainCells();
        ContainCells slice2 = new ContainCells();

        slice0.cells.add(new Cell(0, 0, Ingredient.TOMATO));
        slice0.cells.add(new Cell(1, 0, Ingredient.TOMATO));
        slice0.cells.add(new Cell(2, 0, Ingredient.TOMATO));
        slice0.cells.add(new Cell(0, 1, Ingredient.TOMATO));
        slice0.cells.add(new Cell(1, 1, Ingredient.MUSHROOM));
        slice0.cells.add(new Cell(2, 1, Ingredient.TOMATO));

        slice1.cells.add(new Cell(0, 2, Ingredient.TOMATO));
        slice1.cells.add(new Cell(1, 2, Ingredient.MUSHROOM));
        slice1.cells.add(new Cell(2, 2, Ingredient.TOMATO));

        slice2.cells.add(new Cell(0, 3, Ingredient.TOMATO));
        slice2.cells.add(new Cell(1, 3, Ingredient.MUSHROOM));
        slice2.cells.add(new Cell(2, 3, Ingredient.TOMATO));
        slice2.cells.add(new Cell(0, 4, Ingredient.TOMATO));
        slice2.cells.add(new Cell(1, 4, Ingredient.TOMATO));
        slice2.cells.add(new Cell(2, 4, Ingredient.TOMATO));

        return Arrays.asList(slice0, slice1, slice2);
    }

    @Test
    public void parseExampleInput() throws IOException {
        List<Cell> input = IoUtils.parseObjectLogic(EXAMPLE_PIZZA_FILE);
    }

    @Test
    public void parseExampleSliceInstructions() throws IOException {
        assertEquals("We expect min 1 ingredient per slice", 1,
                IoUtils.parseInstructions(EXAMPLE_PIZZA_FILE).getMinNumberOfIngredientPerSlice().intValue());
        assertEquals("We expect max 6 cells per slice", 6,
                IoUtils.parseInstructions(EXAMPLE_PIZZA_FILE).getMaxNumberOfCellsPerSlice().intValue());
    }

    @Test
    public void parseSlicesToOutputFormat() throws IOException, URISyntaxException {
        //Given a list of slices
        List<ContainCells> slicesForParagonOutputExample = createSlicesForParagonOutputExample();
        //Then parse slices according to the output format
        String outputDate = IoUtils.parseContainCells(slicesForParagonOutputExample);
        IoUtils.writeToFile(TEST_OUTPUT_FILE, outputDate);
        assertEquals(IoUtils.readFromFile(PARAGON_OUTPUT_EXAMPLE_FILE), IoUtils.readFromFile(TEST_OUTPUT_FILE));
        //clean the file under the test
        Files.deleteIfExists(Paths.get(TEST_OUTPUT_FILE));
    }
}