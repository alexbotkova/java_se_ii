package cz.alexbotkova;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Manages multiple {@link PFArray} instances and tracks the currently selected one.
 */
public class PFArrayManager {
    /** Storage of arrays by name. */
    Map<String, PFArray> arrays;
    /** The currently selected array. */
    PFArray selected;

    /**
     * Creates an empty manager with no selected array.
     */
    public PFArrayManager() {
        arrays = new LinkedHashMap<>();
        selected = null;
    }

    /**
     * Validates a proposed array name.
     * @param name the candidate name
     * @return true if the candidate name is valid, false otherwise
     */
    public boolean isNameValid(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println(("Name cannot be null or empty."));
            return false;
        } else if (arrays.containsKey(name)) {
            System.out.println(("Name already exists."));
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks whether an array with the given name exists.
     * @param name array name to look up
     * @return true if present, false otherwise
     */
    public boolean arrayExists(String name) {
        return arrays.containsKey(name);
    }

    /**
     *  Prints all stored array names.
     */
    public void listPFArrayNames() {
        for (Map.Entry<String, PFArray> entry : arrays.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    /**
     * Adds the given {@link PFArray} instance to the manager
     * @param array {@link PFArray} instance
     */
    public void add(PFArray array) {
        arrays.put(array.getName(), array);
    }

    /**
     * Finds the name of the array whose sum of items is extreme with respect to the provided comparator.
     * @param comparator greater-than or less-than
     * @return the name of the array whose sum of items is extreme with respect to the provided comparator
     */
    private String findExtremeSumArray(BiPredicate<Integer, Integer> comparator) {
        String arrayName = "";
        Integer extremeSum = null;

        for (Map.Entry<String, PFArray> entry : arrays.entrySet()) {
            int sum = entry.getValue().sumItems();
            if (extremeSum == null || comparator.test(sum, extremeSum)) {
                extremeSum = sum;
                arrayName = entry.getKey();
            }
        }
        return arrayName;
    }

    /**
     * Returns the name of the array with the biggest sum of items.
     * @return name of the array with the biggest sum
     */
    public String findBiggestSumArray() {
        return findExtremeSumArray((a, b) -> a > b);
    }

    /**
     * Returns the name of the array with the smallest sum of items.
     * @return name of the array with the smallest sum
     */
    public String findSmallestSumArray() {
        return findExtremeSumArray((a, b) -> a < b);
    }
}