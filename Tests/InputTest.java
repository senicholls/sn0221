import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

class InputTest {

    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    Input input = new Input();

//region Test1
    @Test @Tag("Test1")
    void Test1testSetTool(){
        input.setTool("JAKR");
        assertEquals(jackhammerR.getToolCode(), input.getTool().getToolCode());
    }

    @Test @Tag("Test1")
    void Test1testSetCheckOutDate(){
        input.setStringCheckOutDate("09/03/15");
        assertEquals("09/03/15", input.getStringCheckOutDate());
    }

    @Test @Tag("Test1")
    void Test1testSetRentalDays(){
        input.setRentalDays(5);
        assertEquals(5, input.getRentalDays());
    }

    @Test @Tag("Test1")
    void Test1testSetDiscountPercent(){
        assertThrows(InputMismatchException.class, () -> input.setDiscountPercent(101));
    }

//endregion

    @Test
    void testSetToolfail(){
        assertThrows(InputMismatchException.class, () -> input.setTool("invalid"));
    }

    @Test
    void testSetCheckOutDatefail(){
        assertThrows(InputMismatchException.class, () -> input.setStringCheckOutDate("9/1/2020"));
    }

    @Test
    void testSetRentalDaysfail(){
        assertThrows(InputMismatchException.class, () -> input.setRentalDays(0));
    }

    @Test @Tag("Test1")
    void testSetDiscountPercentfail(){
        assertThrows(InputMismatchException.class, () -> input.setDiscountPercent(-1));
    }
}