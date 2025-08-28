package cz.alexbotkova;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;


public class WelcomeScreenTest {

    void run(PFArrayManager pfArrayManager, String... args) {
        String argsJoined = String.join("\n",  args) + "\n";
        WelcomeScreen welcomeScreen = new WelcomeScreen(new StringReader(argsJoined), new PrintStream(new ByteArrayOutputStream()), pfArrayManager);
        welcomeScreen.run();
    }

    @Test
    public void manualCreate() {
        PFArrayManager pfArrayManager = new PFArrayManager();
        run(
            pfArrayManager,
            "2", "A", "1,2,3",
            "1", "A",
            "1",
            "14"
        );
        assertEquals(6, pfArrayManager.arrays.get("A").sumItems());
    }

    @Test
    public void manualCreate_delete() {
        PFArrayManager pfArrayManager = new PFArrayManager();
        run(
                pfArrayManager,
                "2", "A", "1,2,3",
                "1", "A",
                "8",
                "14"
        );
        assertEquals(0, pfArrayManager.arrays.get("A").sumItems());
    }

    @Test
    public void manualCreate_append() {
        PFArrayManager pfArrayManager = new PFArrayManager();
        run(
                pfArrayManager,
                "2", "A", "1,2,3",
                "1", "A",
                "2",
                "4",
                "14"
        );
        assertEquals(10, pfArrayManager.arrays.get("A").sumItems());
    }

    @Test
    public void manualCreate_remove() {
        PFArrayManager pfArrayManager = new PFArrayManager();
        run(
                pfArrayManager,
                "2", "A", "1,2,3",
                "1", "A",
                "5",
                "3",
                "14"
        );
        assertEquals(3, pfArrayManager.arrays.get("A").sumItems());
    }

    @Test
    public void manualCreate_regenerate() {
        PFArrayManager pfArrayManager = new PFArrayManager();
        run(
                pfArrayManager,
                "2", "A", "1,2,3",
                "1", "A",
                "10",
                "2",
                "0",
                "14"
        );
        assertEquals(0, pfArrayManager.arrays.get("A").sumItems());
    }
}