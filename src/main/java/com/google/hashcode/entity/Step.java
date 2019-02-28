package com.google.hashcode.entity;

import java.util.ArrayList;

/**
 * Step as an entity is a slice tha can be added to a particular slice inside a particular pizza, considering
 * pizza's slice instructions
 *
 * @author Ex Armundia Devs
 */
public class Step {

    public ContainCells startPosition;
    public ContainCells delta;

    public Step(ContainCells startPosition, ContainCells delta) {
        super();
        this.startPosition = startPosition;
        this.delta = delta;
    }

    public boolean isValid(ObjectLogic pizza) {
        ContainCells slice = new ContainCells(new ArrayList<>(startPosition.cells));
        slice.cells.addAll(delta.cells);
        return slice.isValid(pizza) ||
                slice.cells.size() < pizza.getInstruction().getMaxNumberOfCellsPerSlice();
    }

    public int size() {
        return startPosition.cells.size() + delta.cells.size();
    }

    @Override
    public String toString() {
        return "\nStep{" +
                "\nstartPosition=" + startPosition.toString() +
                "\ndelta=" + delta.toString() +
                "\n}";
    }


}
