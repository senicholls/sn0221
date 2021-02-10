import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ToolTest {

    Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);

    @Test
    void getToolType() {
        assertEquals("Ladder", ladder.getToolType());
    }

    @Test
    void getToolBrand() {
        assertEquals("Werner", ladder.getToolBrand());
    }

    @Test
    void getToolCode() {
        assertEquals("LADW", ladder.getToolCode());
    }

    @Test
    void getDayCharge() {
        assertEquals(1.99, ladder.getDayCharge());
    }

    @Test
    void isWeekDayCharge() {
        assertTrue(ladder.isWeekDayCharge());
    }

    @Test
    void isWeekEndCharge() {
        assertTrue(ladder.isWeekEndCharge());
    }

    @Test
    void isHolidayCharge() {
        assertFalse(ladder.isHolidayCharge());
    }
}
