package cz.alexbotkova;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PFArrayTest {
    @Test
    public void createRandomly() {
        Random random = new Random(12);
        PFArray pfArray = new PFArray("PFArray", random);
        pfArray.createRandomly();
        assertArrayEquals(new int[]{24, 13, 66, 49, 22, -95, -65, 93, -53, 37}, pfArray.getItems());
    }

    static Stream<Arguments> createManuallyCases() {
        return Stream.of(
                Arguments.of(new String[]{"1", "2", "3"}, new int[]{1,2,3}),
                Arguments.of(new String[]{}, new int[]{})
        );
    }

    @ParameterizedTest()
    @MethodSource("createManuallyCases")
    void createManually(String[] inputs, int[] expected) {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(inputs);
        assertArrayEquals(expected, pfArray.getItems());
    }

    static Stream<Arguments> printArrayHelperCases() {
        return Stream.of(
                Arguments.of(new String[]{"1", "2", "3"}, "1 2 3 "),
                Arguments.of(new String[]{}, "")
        );
    }

    @ParameterizedTest()
    @MethodSource("printArrayHelperCases")
    public void printArrayHelper(String[] inputs, String expectedString) {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(inputs);
        assertEquals(expectedString, pfArray.printArrayHelper());
    }

    static Stream<Arguments> appendNumberCases() {
        return Stream.of(
                Arguments.of(4, new String[]{"1", "2", "3"}, new int[]{1, 2, 3, 4}),
                Arguments.of(2, new String[]{}, new int[]{2})
        );
    }

    @ParameterizedTest()
    @MethodSource("appendNumberCases")
    void appendNumber(int numberToAppend, String[] inputs, int[] expected) {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(inputs);
        pfArray.appendNumber(numberToAppend);
        assertArrayEquals(expected, pfArray.getItems());
    }

    @Test
    public void getMax() {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(new String[]{"1", "2", "3"});
        Integer max = pfArray.getMax();
        assertNotNull(max);
        assertEquals(Integer.valueOf(3), max);
    }

    @Test
    public void getMaxNullCase() {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(new String[]{});
        assertNull(pfArray.getMax());
    }

    @Test
    public void getMin() {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(new String[]{"1", "2", "3"});
        Integer max = pfArray.getMin();
        assertEquals(Integer.valueOf(1), max);
    }

    @Test
    public void getMinNullCase() {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(new String[]{});
        assertNull(pfArray.getMin());
    }

    static Stream<Arguments> removeNumberCases() {
        return Stream.of(
                Arguments.of(2, new String[]{"1", "2", "3"}, false, new int[]{1,3}),
                Arguments.of(2, new String[]{"2", "2", "2"}, true,  new int[]{}),
                Arguments.of(9, new String[]{"1", "2"}, true,  new int[]{1,2})
        );
    }

    @ParameterizedTest()
    @MethodSource("removeNumberCases")
    void removeNumber(int numberToRemove, String[] inputs, boolean removeAll, int[] expected) {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(inputs);
        pfArray.removeNumber(numberToRemove, removeAll);
        assertArrayEquals(expected, pfArray.getItems());
    }

    @Test
    public void deleteArray() {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(new String[]{"1", "2", "3"});
        pfArray.deleteArray();
        assertNull(pfArray.getItems());
    }

    static Stream<Arguments> sumItemsCases() {
        return Stream.of(
                Arguments.of(new String[]{"1", "2", "3"}, 6),
                Arguments.of(new String[]{}, 0)
        );
    }

    @ParameterizedTest()
    @MethodSource("sumItemsCases")
    void sumItems(String[] inputs, int expected) {
        PFArray pfArray = new PFArray("PFArray");
        pfArray.createManually(inputs);
        pfArray.sumItems();
        assertEquals(expected, pfArray.sumItems());
    }
}