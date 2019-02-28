package com.google.hashcode.utils;

import com.google.hashcode.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.hashcode.utils.FilesPaths.EXAMPLE_INPUT_FILE_PATH;
import static org.junit.Assert.assertEquals;

/**
 * @author Ex Armundia Devs
 */
public class ContainerCellsMethodsTest {

    private ObjectLogic pizza;

    @Before
    public void setup() throws IOException {
        pizza = new ObjectLogic(new File(EXAMPLE_INPUT_FILE_PATH), IoUtils.parseObjectLogic(EXAMPLE_INPUT_FILE_PATH), IoUtils.parseInstructions(EXAMPLE_INPUT_FILE_PATH));
    }

    @Test
    public void getAvailableSteps() throws IOException {
        List<ContainCells> output = new ArrayList<>();
        Map<ContainCells, List<Step>> actualMap = ContainerCellsMethods.getAvailableSteps(pizza, ContainerCellsMethods.cutAllStartPositions(pizza), output);
        assertEquals(3, actualMap.keySet().size());
        assertEquals(3, actualMap.get(new ContainCells(new Cell(1, 1, Ingredient.MUSHROOM))).size());
        assertEquals(2, actualMap.get(new ContainCells(new Cell(1, 2, Ingredient.MUSHROOM))).size());
        assertEquals(3, actualMap.get(new ContainCells(new Cell(1, 3, Ingredient.MUSHROOM))).size());
    }

    @Test
    public void cutAllStartPositions() throws IOException {
        List<ContainCells> expected = Arrays.asList(
                new ContainCells(new Cell(1, 1, Ingredient.MUSHROOM)),
                new ContainCells(new Cell(1, 2, Ingredient.MUSHROOM)),
                new ContainCells(new Cell(1, 3, Ingredient.MUSHROOM))
        );
        assertEquals(expected, ContainerCellsMethods.cutAllStartPositions(pizza));
        assertEquals("We expect pizza size reduced to 15-3=12", 12, pizza.getCells().size());
    }

    @Test
    public void performStep() {
        List<ContainCells> startPositions = ContainerCellsMethods.cutAllStartPositions(pizza);
        List<ContainCells> output = new ArrayList<>();
        Map<ContainCells, List<Step>> availableSteps = ContainerCellsMethods.getAvailableSteps(pizza, startPositions, output);
        ContainerCellsMethods.performStep(pizza, ContainerCellsMethods.selectStep(availableSteps), startPositions, output);
        assertEquals(11, pizza.getCells().size());
    }
}