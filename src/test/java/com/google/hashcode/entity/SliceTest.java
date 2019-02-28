package com.google.hashcode.entity;

import com.google.hashcode.utils.IoUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.google.hashcode.utils.FilesPaths.EXAMPLE_INPUT_FILE_PATH;
import static org.junit.Assert.*;

/**
 * @author Ex Armundia Devs
 */
public class SliceTest {
    private ObjectLogic pizza;

    @Before
    public void setup() throws IOException {
        pizza = new ObjectLogic(new File(EXAMPLE_INPUT_FILE_PATH), IoUtils.parseObjectLogic(EXAMPLE_INPUT_FILE_PATH), IoUtils.parseInstructions(EXAMPLE_INPUT_FILE_PATH));
    }

    @Test
    public void isValid() throws Exception {
        ContainCells invalidSlice = new ContainCells(pizza.getCells());
        assertFalse(invalidSlice.isValid(pizza));
        //create a valid slice
        ContainCells validSlice = new ContainCells(Arrays.asList(
                new Cell(0, 0, Ingredient.TOMATO),
                new Cell(0, 1, Ingredient.MUSHROOM)));
        assertTrue(validSlice.isValid(pizza));
    }

    @Test
    public void generateStepDeltaBelow() {
        ContainCells slice = new ContainCells(Arrays.asList(
                new Cell(0, 0, Ingredient.MUSHROOM),
                new Cell(0, 1, Ingredient.TOMATO)));
        assertEquals(2, slice.generateStepBelow(pizza).delta.cells.size());
    }

    @Test
    public void centGenerateStepDeltaAbove() {
        ContainCells slice = new ContainCells(Arrays.asList(
                new Cell(0, 0, Ingredient.MUSHROOM),
                new Cell(0, 1, Ingredient.TOMATO)));
        assertEquals(null, slice.generateStepAbove(pizza));
    }

    @Test
    public void generateStepDeltaAbove() {
        ContainCells slice = new ContainCells(Arrays.asList(
                new Cell(1, 0, Ingredient.MUSHROOM),
                new Cell(1, 1, Ingredient.TOMATO)));
        assertEquals(2, slice.generateStepAbove(pizza).delta.cells.size());
    }

    @Test
    public void generateStepLeft() {
        ContainCells slice = new ContainCells(new ArrayList<>(Collections.singletonList(
                new Cell(1, 1, Ingredient.MUSHROOM))));
        assertEquals(2, slice.generateStepLeft(pizza).size());
    }

    @Test
    public void cantGenerateStepLeft() {
        ContainCells slice = new ContainCells(Arrays.asList(
                new Cell(0, 0, Ingredient.MUSHROOM),
                new Cell(0, 1, Ingredient.TOMATO)));
        assertEquals(null, slice.generateStepLeft(pizza));
    }

    @Test
    public void generateStepRight() {
        ContainCells slice = new ContainCells(Arrays.asList(
                new Cell(0, 0, Ingredient.MUSHROOM),
                new Cell(0, 1, Ingredient.TOMATO)));
        assertEquals(1, slice.generateStepRight(pizza).delta.cells.size());
        assertEquals(3, slice.generateStepRight(pizza).size());
    }

    @Test
    public void testToString() {
        ContainCells slice = new ContainCells(Arrays.asList(
                new Cell(0, 0, Ingredient.MUSHROOM)));
        assertEquals("slice : \n" +
                "  0\n" +
                "0 M", slice.toString());
        ContainCells slice1 = new ContainCells(Arrays.asList(
                new Cell(2, 3, Ingredient.TOMATO),
                new Cell(0, 1, Ingredient.MUSHROOM))
        );
        assertEquals("slice : \n" +
                "  0 1 2 3\n" +
                "0   M     \n" +
                "1         \n" +
                "2       T", slice1.toString());
    }
}