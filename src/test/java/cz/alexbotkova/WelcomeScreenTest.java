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


}
