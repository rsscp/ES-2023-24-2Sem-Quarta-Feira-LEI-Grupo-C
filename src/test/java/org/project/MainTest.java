package org.project;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testMain() {
        // As the main method doesn't have any logic to test,
        // we can only test if it executes without throwing any exception.
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}