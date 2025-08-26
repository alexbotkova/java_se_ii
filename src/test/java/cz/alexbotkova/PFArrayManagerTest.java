package cz.alexbotkova;

import org.junit.Test;

import static org.junit.Assert.*;

public class PFArrayManagerTest {

    private static PFArray makeArray(String name, String... items) {
        PFArray array = new PFArray(name);
        array.createManually(items);
        return array;
    }

    @Test
    public void isNameValid() {
        PFArrayManager manager = new PFArrayManager();
        assertFalse(manager.isNameValid(null));
        assertFalse(manager.isNameValid(""));
        assertTrue(manager.isNameValid("A"));
        assertTrue(manager.isNameValid("B"));
        // reject duplicate
        manager.add(makeArray("A", "1", "2", "3"));
        assertFalse(manager.isNameValid("A"));
    }

    @Test
    public void arrayExists() {
        PFArrayManager manager = new PFArrayManager();
        assertFalse(manager.arrayExists("A"));
        manager.add(makeArray("A", "1"));
        assertTrue(manager.arrayExists("A"));
        assertFalse(manager.arrayExists("B"));
    }

    @Test
    public void add() {
        PFArrayManager manager = new PFArrayManager();
        PFArray a1 = makeArray("A", "1");
        PFArray a2 = makeArray("A", "9", "9");

        manager.add(a1);
        assertEquals(1, manager.arrays.size());
        assertSame(a1, manager.arrays.get("A"));

        manager.add(a2);
        assertEquals(1, manager.arrays.size());
        assertSame(a2, manager.arrays.get("A"));
    }

    @Test
    public void findBiggestSumArray() {
        PFArrayManager manager = new PFArrayManager();
        manager.add(makeArray("A", "1", "2", "3"));
        manager.add(makeArray("B", "10"));
        manager.add(makeArray("C", "-1", "0", "1"));
        assertEquals("B", manager.findBiggestSumArray());
    }

    @Test
    public void findSmallestSumArray() {
        PFArrayManager manager = new PFArrayManager();
        manager.add(makeArray("A", "1", "2", "3"));
        manager.add(makeArray("B", "10"));
        manager.add(makeArray("C", "-1", "0", "1"));
        assertEquals("C", manager.findSmallestSumArray());
    }

    @Test
    public void findExtremeSumArray() {
        PFArrayManager manager = new PFArrayManager();
        assertEquals("", manager.findBiggestSumArray());
        assertEquals("", manager.findSmallestSumArray());
    }

    @Test
    public void changeSelectedField() {
        PFArrayManager manager = new PFArrayManager();
        PFArray a = makeArray("A", "1", "2");
        PFArray b = makeArray("B", "3");
        manager.add(a);
        manager.add(b);

        manager.selected = a;
        assertSame(a, manager.selected);
        manager.selected = b;
        assertSame(b, manager.selected);
    }
}
