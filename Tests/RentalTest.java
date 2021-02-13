import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    static Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);
    static Tool chainsaw = new Tool("Chainsaw", "Stihl", "CHNS", 1.49, true, false, true);
    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    static Tool jackhammerD = new Tool("JackHammer", "DeWalt", "JAKD", 2.99, true, false, false);

//region Test2
    @Test @Tag("Test2")
    void Test2testToolCode(){
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals("LADW", rental2.getTool().getToolCode());
    }

    @Test @Tag("Test2")
    void Test2testToolType() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals("Ladder", rental2.getTool().getToolType());
    }

    @Test @Tag("Test2")
    void Test2testToolBrand() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals("Werner", rental2.getTool().getToolBrand());
    }

    @Test @Tag("Test2")
    void Test2testRentalDays() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals(3, rental2.getRentalDays());
    }

    @Test @Tag("Test2")
    void Test2testCheckOutDate() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals("07/02/20", rental2.getStringCheckoutDate());
    }

    @Test @Tag("Test2")
    void Test2testDueDate() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals("07/05/20", rental2.calcDueDate());
    }

    @Test @Tag("Test2")
    void Test2testDayCharge() {

        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);assertEquals(1.99, rental2.getTool().getDayCharge());
    }

    @Test @Tag("Test2")
    void Test2testChargeDays() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        rental2.calcDueDate();
        assertEquals(2, rental2.calcChargeDays());
    }

    @Test @Tag("Test2")
    void Test2testSubTotal() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        rental2.calcDueDate();
        rental2.calcChargeDays();
        assertTrue(BigDecimal.valueOf(3.98).compareTo(rental2.calcSubTotal()) == 0);
    }

    @Test @Tag("Test2")
    void Test2testDiscountPercent() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        assertEquals(10, rental2.getDiscountPercent());
    }

    @Test @Tag("Test2")
    void Test2testDiscountAmount() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
        rental2.calcDueDate();
        rental2.calcChargeDays();
        rental2.calcSubTotal();
        assertTrue(BigDecimal.valueOf(0.40).compareTo(rental2.calcDiscount()) == 0);
    }

    @Test @Tag("Test2")
    void Test2testFinalTotal() {
        Rental rental2 = new Rental(ladder, "07/02/20", 3, 10);
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
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals("CHNS", rental3.getTool().getToolCode());
    }

    @Test @Tag("Test3")
    void Test3testToolType() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals("Chainsaw", rental3.getTool().getToolType());
    }

    @Test @Tag("Test3")
    void Test3testToolBrand() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals("Stihl", rental3.getTool().getToolBrand());
    }

    @Test @Tag("Test3")
    void Test3testRentalDays() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals(5, rental3.getRentalDays());
    }

    @Test @Tag("Test3")
    void Test3testCheckOutDate() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals("07/02/15", rental3.getStringCheckoutDate());
    }

    @Test @Tag("Test3")
    void Test3testDueDate() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals("07/07/15", rental3.calcDueDate());
    }

    @Test @Tag("Test3")
    void Test3testDayCharge() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals(1.49, rental3.getTool().getDayCharge());
    }

    @Test @Tag("Test3")
    void Test3testChargeDays() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        rental3.calcDueDate();
        assertEquals(3, rental3.calcChargeDays());
    }

    @Test @Tag("Test3")
    void Test3testSubTotal() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        rental3.calcDueDate();
        rental3.calcChargeDays();
        assertTrue(BigDecimal.valueOf(4.47).compareTo(rental3.calcSubTotal()) == 0);
    }

    @Test @Tag("Test3")
    void Test3testDiscountPercent() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        assertEquals(25, rental3.getDiscountPercent());
    }

    @Test @Tag("Test3")
    void Test3testDiscountAmount() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
        rental3.calcDueDate();
        rental3.calcChargeDays();
        rental3.calcSubTotal();
        assertTrue(BigDecimal.valueOf(1.12).compareTo(rental3.calcDiscount()) == 0);
    }

    @Test @Tag("Test3")
    void Test3testFinalTotal() {
        Rental rental3 = new Rental(chainsaw, "07/02/15", 5, 25);
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
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals("JAKD", rental4.getTool().getToolCode());
    }

    @Test @Tag("Test4")
    void Test4testToolType() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals("JackHammer", rental4.getTool().getToolType());
    }

    @Test @Tag("Test4")
    void Test4testToolBrand() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals("DeWalt", rental4.getTool().getToolBrand());
    }

    @Test @Tag("Test4")
    void Test4testRentalDays() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals(6, rental4.getRentalDays());
    }

    @Test @Tag("Test4")
    void Test4testCheckOutDate() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals("09/03/15", rental4.getStringCheckoutDate());
    }

    @Test @Tag("Test4")
    void Test4testDueDate() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals("09/09/15", rental4.calcDueDate());
    }

    @Test @Tag("Test4")
    void Test4testDayCharge() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals(2.99, rental4.getTool().getDayCharge());
    }

    @Test @Tag("Test4")
    void Test4testChargeDays() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        rental4.calcDueDate();
        assertEquals(3, rental4.calcChargeDays());
    }

    @Test @Tag("Test4")
    void Test4testSubTotal() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        rental4.calcDueDate();
        rental4.calcChargeDays();
        assertTrue(BigDecimal.valueOf(8.97).compareTo(rental4.calcSubTotal()) == 0);
    }

    @Test @Tag("Test4")
    void Test4testDiscountPercent() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        assertEquals(0, rental4.getDiscountPercent());
    }

    @Test @Tag("Test4")
    void Test4testDiscountAmount() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
        rental4.calcDueDate();
        rental4.calcChargeDays();
        rental4.calcSubTotal();
        assertTrue(BigDecimal.valueOf(0).compareTo(rental4.calcDiscount()) == 0);
    }

    @Test @Tag("Test4")
    void Test4testFinalTotal() {
        Rental rental4 = new Rental(jackhammerD, "09/03/15", 6, 0);
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
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals("JAKR", rental5.getTool().getToolCode());
}

    @Test @Tag("Test5")
    void Test5testToolType() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals("JackHammer", rental5.getTool().getToolType());
    }

    @Test @Tag("Test5")
    void Test5testToolBrand() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals("Ridgid", rental5.getTool().getToolBrand());
    }

    @Test @Tag("Test5")
    void Test5testRentalDays() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals(9, rental5.getRentalDays());
    }

    @Test @Tag("Test5")
    void Test5testCheckOutDate() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals("07/02/15", rental5.getStringCheckoutDate());
    }

    @Test @Tag("Test5")
    void Test5testDueDate() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals("07/11/15", rental5.calcDueDate());
    }

    @Test @Tag("Test5")
    void Test5testDayCharge() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals(2.99, rental5.getTool().getDayCharge());
    }

    @Test @Tag("Test5")
    void Test5testChargeDays() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        rental5.calcDueDate();
        assertEquals(5, rental5.calcChargeDays());
    }

    @Test @Tag("Test5")
    void Test5testSubTotal() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        rental5.calcDueDate();
        rental5.calcChargeDays();
        assertTrue(BigDecimal.valueOf(14.95).compareTo(rental5.calcSubTotal()) == 0);
    }

    @Test @Tag("Test5")
    void Test5testDiscountPercent() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        assertEquals(0, rental5.getDiscountPercent());
    }

    @Test @Tag("Test5")
    void Test5testDiscountAmount() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
        rental5.calcDueDate();
        rental5.calcChargeDays();
        rental5.calcSubTotal();
        assertTrue(BigDecimal.valueOf(0).compareTo(rental5.calcDiscount()) == 0);
    }

    @Test @Tag("Test5")
    void Test5testFinalTotal() {
        Rental rental5 = new Rental(jackhammerR, "07/02/15", 9, 0);
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
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals("JAKR", rental6.getTool().getToolCode());
}

    @Test @Tag("Test6")
    void Test6testToolType() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals("JackHammer", rental6.getTool().getToolType());
    }

    @Test @Tag("Test6")
    void Test6testToolBrand() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals("Ridgid", rental6.getTool().getToolBrand());
    }

    @Test @Tag("Test6")
    void Test6testRentalDays() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals(4, rental6.getRentalDays());
    }

    @Test @Tag("Test6")
    void Test6testCheckOutDate() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals("07/02/20", rental6.getStringCheckoutDate());
    }

    @Test @Tag("Test6")
    void Test6testDueDate() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals("07/06/20", rental6.calcDueDate());
    }

    @Test @Tag("Test6")
    void Test6testDayCharge() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals(2.99, rental6.getTool().getDayCharge());
    }

    @Test @Tag("Test6")
    void Test6testChargeDays() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        rental6.calcDueDate();
        assertEquals(1, rental6.calcChargeDays());
    }

    @Test @Tag("Test6")
    void Test6testSubTotal() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        rental6.calcDueDate();
        rental6.calcChargeDays();
        assertTrue(BigDecimal.valueOf(2.99).compareTo(rental6.calcSubTotal()) == 0);
    }

    @Test @Tag("Test6")
    void Test6testDiscountPercent() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        assertEquals(50, rental6.getDiscountPercent());
    }

    @Test @Tag("Test6")
    void Test6testDiscountAmount() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        rental6.calcDueDate();
        rental6.calcChargeDays();
        rental6.calcSubTotal();
        assertTrue(BigDecimal.valueOf(1.50).compareTo(rental6.calcDiscount()) == 0);
    }

    @Test @Tag("Test6")
    void Test6testFinalTotal() {
        Rental rental6 = new Rental(jackhammerR, "07/02/20", 4, 50);
        rental6.calcDueDate();
        rental6.calcChargeDays();
        rental6.calcSubTotal();
        rental6.calcDiscount();
        assertTrue(BigDecimal.valueOf(1.49).compareTo(rental6.calcFinalTotal()) == 0);
    }
//endregion
}