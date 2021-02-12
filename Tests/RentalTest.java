import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    static Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);
    static Tool chainsaw = new Tool("Chainsaw", "Stihl", "CHNS", 1.49, true, false, true);
    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    static Tool jackhammerD = new Tool("JackHammer", "DeWalt", "JAKD", 2.99, true, false, false);

    Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
    Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
    Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
    Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
    Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);

//region Test2
    @Test @Tag("Test2")
    void Test2testToolCode(){
        assertEquals("LADW", rental2.getTool().getToolCode());
    }

    @Test @Tag("Test2")
    void Test2testToolType() {
        assertEquals("Ladder", rental2.getTool().getToolType());
    }

    @Test @Tag("Test2")
    void Test2testToolBrand() {
        assertEquals("Werner", rental2.getTool().getToolBrand());
    }

    @Test @Tag("Test2")
    void Test2testRentalDays() {
        assertEquals(3, rental2.getRentalDays());
    }

    @Test @Tag("Test2")
    void Test2testCheckOutDate() {
        assertEquals("07/02/20", rental2.getStringCheckoutDate());
    }

    @Test @Tag("Test2")
    void Test2testDueDate() {
        assertEquals("07/05/20", rental2.calcDueDate());
    }

    @Test @Tag("Test2")
    void Test2testDayCharge() {
        assertEquals(1.99, rental2.getTool().getDayCharge());
    }

    @Test @Tag("Test2")
    void Test2testChargeDays() {
        rental2.calcDueDate();
        assertEquals(2, rental2.calcChargeDays());
    }

    @Test @Tag("Test2")
    void Test2testSubTotal() {
        rental2.calcDueDate();
        rental2.calcChargeDays();
        assertTrue(BigDecimal.valueOf(3.98).compareTo(rental2.calcSubTotal()) == 0);
    }

    @Test @Tag("Test2")
    void Test2testDiscountPercent() {
        assertEquals(10, rental2.getDiscountPercent());
    }

    @Test @Tag("Test2")
    void Test2testDiscountAmount() {
        rental2.calcDueDate();
        rental2.calcChargeDays();
        rental2.calcSubTotal();
        assertTrue(BigDecimal.valueOf(0.40).compareTo(rental2.calcDiscount()) == 0);
    }

    @Test @Tag("Test2")
    void Test2testFinalTotal() {
        rental2.calcDueDate();
        rental2.calcChargeDays();
        rental2.calcSubTotal();
        rental2.calcDiscount();
        assertTrue(BigDecimal.valueOf(3.58).compareTo(rental2.calcFinalTotal()) == 0);
    }
//endregion
//region Test3
    @Test @Tag("Test3")
    void Test3testToolCode(){
        assertEquals("CHNS", rental3.getTool().getToolCode());
    }

    @Test @Tag("Test3")
    void Test3testToolType() {
        assertEquals("Chainsaw", rental3.getTool().getToolType());
    }

    @Test @Tag("Test3")
    void Test3testToolBrand() {
        assertEquals("Stihl", rental3.getTool().getToolBrand());
    }

    @Test @Tag("Test3")
    void Test3testRentalDays() {
        assertEquals(5, rental3.getRentalDays());
    }

    @Test @Tag("Test3")
    void Test3testCheckOutDate() {
        assertEquals("07/02/15", rental3.getStringCheckoutDate());
    }

    @Test @Tag("Test3")
    void Test3testDueDate() {
        assertEquals("07/07/15", rental3.calcDueDate());
    }

    @Test @Tag("Test3")
    void Test3testDayCharge() {
        assertEquals(1.49, rental3.getTool().getDayCharge());
    }

    @Test @Tag("Test3")
    void Test3testChargeDays() {
        rental3.calcDueDate();
        assertEquals(3, rental3.calcChargeDays());
    }

    @Test @Tag("Test3")
    void Test3testSubTotal() {
        rental3.calcDueDate();
        rental3.calcChargeDays();
        assertTrue(BigDecimal.valueOf(4.47).compareTo(rental3.calcSubTotal()) == 0);
    }

    @Test @Tag("Test3")
    void Test3testDiscountPercent() {
        assertEquals(25, rental3.getDiscountPercent());
    }

    @Test @Tag("Test3")
    void Test3testDiscountAmount() {
        rental3.calcDueDate();
        rental3.calcChargeDays();
        rental3.calcSubTotal();
        assertTrue(BigDecimal.valueOf(1.12).compareTo(rental3.calcDiscount()) == 0);
    }

    @Test @Tag("Test3")
    void Test3testFinalTotal() {
        rental3.calcDueDate();
        rental3.calcChargeDays();
        rental3.calcSubTotal();
        rental3.calcDiscount();
        assertTrue(BigDecimal.valueOf(3.35).compareTo(rental3.calcFinalTotal()) == 0);
    }
//endregion
//region Test4
    @Test @Tag("Test4")
    void Test4testToolCode(){
        assertEquals("JAKD", rental4.getTool().getToolCode());
    }

    @Test @Tag("Test4")
    void Test4testToolType() {
        assertEquals("JackHammer", rental4.getTool().getToolType());
    }

    @Test @Tag("Test4")
    void Test4testToolBrand() {
        assertEquals("DeWalt", rental4.getTool().getToolBrand());
    }

    @Test @Tag("Test4")
    void Test4testRentalDays() {
        assertEquals(6, rental4.getRentalDays());
    }

    @Test @Tag("Test4")
    void Test4testCheckOutDate() {
        assertEquals("09/03/15", rental4.getStringCheckoutDate());
    }

    @Test @Tag("Test4")
    void Test4testDueDate() {
        assertEquals("09/09/15", rental4.calcDueDate());
    }

    @Test @Tag("Test4")
    void Test4testDayCharge() {
        assertEquals(2.99, rental4.getTool().getDayCharge());
    }

    @Test @Tag("Test4")
    void Test4testChargeDays() {
        rental4.calcDueDate();
        assertEquals(3, rental4.calcChargeDays());
    }

    @Test @Tag("Test4")
    void Test4testSubTotal() {
        rental4.calcDueDate();
        rental4.calcChargeDays();
        assertTrue(BigDecimal.valueOf(8.97).compareTo(rental4.calcSubTotal()) == 0);
    }

    @Test @Tag("Test4")
    void Test4testDiscountPercent() {
        assertEquals(0, rental4.getDiscountPercent());
    }

    @Test @Tag("Test4")
    void Test4testDiscountAmount() {
        rental4.calcDueDate();
        rental4.calcChargeDays();
        rental4.calcSubTotal();
        assertTrue(BigDecimal.valueOf(0).compareTo(rental4.calcDiscount()) == 0);
    }

    @Test @Tag("Test4")
    void Test4testFinalTotal() {
        rental4.calcDueDate();
        rental4.calcChargeDays();
        rental4.calcSubTotal();
        rental4.calcDiscount();
        assertTrue(BigDecimal.valueOf(8.97).compareTo(rental4.calcFinalTotal()) == 0);
    }
//endregion
//region Test5
    @Test @Tag("Test5")
    void Test5testToolCode(){
        assertEquals("JAKR", rental5.getTool().getToolCode());
}

    @Test @Tag("Test5")
    void Test5testToolType() {
        assertEquals("JackHammer", rental5.getTool().getToolType());
    }

    @Test @Tag("Test5")
    void Test5testToolBrand() {
        assertEquals("Ridgid", rental5.getTool().getToolBrand());
    }

    @Test @Tag("Test5")
    void Test5testRentalDays() {
        assertEquals(9, rental5.getRentalDays());
    }

    @Test @Tag("Test5")
    void Test5testCheckOutDate() {
        assertEquals("07/02/15", rental5.getStringCheckoutDate());
    }

    @Test @Tag("Test5")
    void Test5testDueDate() {
        assertEquals("07/11/15", rental5.calcDueDate());
    }

    @Test @Tag("Test5")
    void Test5testDayCharge() {
        assertEquals(2.99, rental5.getTool().getDayCharge());
    }

    @Test @Tag("Test5")
    void Test5testChargeDays() {
        rental5.calcDueDate();
        assertEquals(5, rental5.calcChargeDays());
    }

    @Test @Tag("Test5")
    void Test5testSubTotal() {
        rental5.calcDueDate();
        rental5.calcChargeDays();
        assertTrue(BigDecimal.valueOf(14.95).compareTo(rental5.calcSubTotal()) == 0);
    }

    @Test @Tag("Test5")
    void Test5testDiscountPercent() {
        assertEquals(0, rental5.getDiscountPercent());
    }

    @Test @Tag("Test5")
    void Test5testDiscountAmount() {
        rental5.calcDueDate();
        rental5.calcChargeDays();
        rental5.calcSubTotal();
        assertTrue(BigDecimal.valueOf(0).compareTo(rental5.calcDiscount()) == 0);
    }

    @Test @Tag("Test5")
    void Test5testFinalTotal() {
        rental5.calcDueDate();
        rental5.calcChargeDays();
        rental5.calcSubTotal();
        rental5.calcDiscount();
        assertTrue(BigDecimal.valueOf(14.95).compareTo(rental5.calcFinalTotal()) == 0);
    }
//endregion
//region Test6
    @Test @Tag("Test6")
    void Test6testToolCode(){
    assertEquals("JAKR", rental6.getTool().getToolCode());
}

    @Test @Tag("Test6")
    void Test6testToolType() {
        assertEquals("JackHammer", rental6.getTool().getToolType());
    }

    @Test @Tag("Test6")
    void Test6testToolBrand() {
        assertEquals("Ridgid", rental6.getTool().getToolBrand());
    }

    @Test @Tag("Test6")
    void Test6testRentalDays() {
        assertEquals(4, rental6.getRentalDays());
    }

    @Test @Tag("Test6")
    void Test6testCheckOutDate() {
        assertEquals("07/02/20", rental6.getStringCheckoutDate());
    }

    @Test @Tag("Test6")
    void Test6testDueDate() {
        assertEquals("07/06/20", rental6.calcDueDate());
    }

    @Test @Tag("Test6")
    void Test6testDayCharge() {
        assertEquals(2.99, rental6.getTool().getDayCharge());
    }

    @Test @Tag("Test6")
    void Test6testChargeDays() {
        rental6.calcDueDate();
        assertEquals(1, rental6.calcChargeDays());
    }

    @Test @Tag("Test6")
    void Test6testSubTotal() {
        rental6.calcDueDate();
        rental6.calcChargeDays();
        assertTrue(BigDecimal.valueOf(2.99).compareTo(rental6.calcSubTotal()) == 0);
    }

    @Test @Tag("Test6")
    void Test6testDiscountPercent() {
        assertEquals(50, rental6.getDiscountPercent());
    }

    @Test @Tag("Test6")
    void Test6testDiscountAmount() {
        rental6.calcDueDate();
        rental6.calcChargeDays();
        rental6.calcSubTotal();
        assertTrue(BigDecimal.valueOf(1.50).compareTo(rental6.calcDiscount()) == 0);
    }

    @Test @Tag("Test6")
    void Test6testFinalTotal() {
        rental6.calcDueDate();
        rental6.calcChargeDays();
        rental6.calcSubTotal();
        rental6.calcDiscount();
        assertTrue(BigDecimal.valueOf(1.49).compareTo(rental6.calcFinalTotal()) == 0);
    }
//endregion
}