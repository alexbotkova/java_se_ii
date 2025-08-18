package cz.alexbotkova;

import java.util.Arrays;
import java.util.Random;

/**
 * Class representing a named integer array with defined operations.
 */
public class PFArray {
    /**Name of the array.*/
    private final String name;
    /**An integer array.*/
    private int[] items;
    /** Bounds used for random array creation. */
    private final RandomArrayCreationBounds randomArrayCreationBounds;
    /** Source of randomness used by {@link #createRandomly()}. */
    private final Random random;

    /**
     * Creates a new object with the given array name.
     * @param name non-null and non-blank array name
     */
    public PFArray(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;
        randomArrayCreationBounds = RandomArrayCreationBounds.BOUNDS;
        random = new Random();
    }

    /**
     * For testing purposes. Creates a new object with the given array name and the given source of randomness.
     * @param name non-null and non-blank array name
     * @param random random generator to use
     */
    public PFArray(String name, Random  random) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;
        randomArrayCreationBounds = RandomArrayCreationBounds.BOUNDS;
        this.random = random;
    }

    /**
     * Returns the name of this array.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the integer array.
     * @return integer array
     */
    public int[] getItems() {
        return items;
    }

    /**
     * Creates a new random array.
     */
    public void createRandomly() {
        int length = random.nextInt(randomArrayCreationBounds.getMinLength(), randomArrayCreationBounds.getMaxLength()) + 1;
        items = new int[length];
        for (int i = 0; i < length; i++) {
            int item = random.nextInt() % randomArrayCreationBounds.getModulo();
            items[i] = item;
        }
    }

    /**
     * Creates an array from an array of string inputs.
     * @param inputs array of strings representing integer values
     */
    public void createManually(String[] inputs) {
        int length = inputs.length;
        items = new int[length];
        int item;
        for (int i = 0; i < length; i++) {
            String input = inputs[i];
            try {
                item = Integer.parseInt(input);
            } catch (NumberFormatException _) {
                System.out.println(input + " is not a number. Array not created.");
                return;
            }
            items[i] = item;
        }
    }

    /**
     * Creates an empty array.
     */
    public void createEmpty() {
        items = new int[0];
    }

    /**
     * Helper function for building a space-separated string representation of the array – for testing purposes.
     * @return a string containing array items separated by spaces
     */
    String printArrayHelper() {
        if (items == null) {
            items = new int[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int item : items) {
            sb.append(item).append(" ");
        }
        return sb.toString();
    }

    /**
     * Builds a space-separated string representation of the given array.
     */
    public void printArray() {
        String s = printArrayHelper();
        System.out.println(s);
    }

    /**
     * Appends a number to the end of the array.
     * @param number number to append
     */
    public void appendNumber(int number) {
        if (items == null) {
            items = new int[0];
        }
        items =  Arrays.copyOf(items, items.length + 1);
        items[items.length - 1] = number;

    }

    /**
     * Finds the maximum value in the array.
     * @return maximum value or null
     */
    public Integer getMax() {
        if (items == null || items.length == 0) {
            return null;
        }
        int max = items[0];
        for (int i = 1; i < items.length; i++) {
            if (items[i] > max) {
                max = items[i];
            }
        }
        return max;
    }

    /**
     * Finds the minimum value in the array.
     * @return minimum value or null
     */
    public Integer getMin() {
        if (items == null || items.length == 0) {
            return null;
        }
        int min = items[0];
        for (int i = 1; i < items.length; i++) {
            if (items[i] < min) {
                min = items[i];
            }
        }
        return min;
    }

    /**
     * Removes one or all occurrences of a given number from the array.
     * @param number number to remove
     * @param removeAll if true removes all occurrences, if false removes only the first occurrence
     */
    public void removeNumber(int number, boolean removeAll) {
        if (items == null || items.length == 0) {
            items = new int[0];
        }
        if (!removeAll) {
            int[] newArray = new int[items.length - 1];
            boolean removed = false;
            int index = 0;
            for (int item : items) {
                if (item != number || removed) {
                    newArray[index] = item;
                    index++;
                } else {
                    removed = true;
                }
            }
            items = newArray;
        } else {
            int count = 0;
            for (int item : items) {
                if (item == number) {
                    count++;
                }
            }
            int[] newArray = new int[items.length - count];
            int index = 0;
            for (int item : items) {
                if (item != number) {
                    newArray[index] = item;
                    index++;
                }
            }
            items = newArray;
        }
    }

    /**
     * Deletes the array by setting it to null
     */
    public void deleteArray() {
        items = null;
    }

    /**
     * Sums the items of the array.
     * @return sum of the items of the array
     */
    public int sumItems() {
        int sum = 0;
        if (items == null) {
            return sum;
        }
        for (int item : items) {
            sum += item;
        }
        return sum;
    }
}