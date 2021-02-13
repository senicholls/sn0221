import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

public class Rental {

    static Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);
    static Tool chainsaw = new Tool("Chainsaw", "Stihl", "CHNS", 1.49, true, false, true);
    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    static Tool jackhammerD = new Tool("JackHammer", "DeWalt", "JAKD", 2.99, true, false, false);

    private static Tool tool;
    private static String stringCheckoutDate;
    private static int rentalDays;
    private static double discountPercent;
    private String stringDueDate;
    private int chargeDays;
    private BigDecimal subTotal;
    private BigDecimal discountAmount;
    static Scanner myScan = new Scanner(System.in);

    public Tool getTool() {
        return tool;
    }

    public String getStringCheckoutDate() {
        return stringCheckoutDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public Rental(Tool tool, String stringCheckOutDate, int rentalDays, double discountPercent){
        this.tool = tool;
        this.stringCheckoutDate = stringCheckOutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
    }

    public String calcDueDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date checkOutDate = null;
        try {
            checkOutDate = dateFormat.parse(stringCheckoutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(checkOutDate);
        c.add(Calendar.DATE, rentalDays);
        Date dueDate = c.getTime();
        stringDueDate = dateFormat.format(dueDate);
        return stringDueDate;
    }

    public int calcChargeDays(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date checkOutDate = null;
        Date dueDate = null;
        try {
            checkOutDate = dateFormat.parse(stringCheckoutDate);
            dueDate = dateFormat.parse(stringDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        chargeDays = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(checkOutDate);
        c.add(Calendar.DATE, 1);
        Calendar d = Calendar.getInstance();
        d.setTime(dueDate);
        d.add(Calendar.DATE, 1);
        Calendar inDay = calcIndependenceDay(checkOutDate);
        while (c.before(d)){
            //Check if weekend
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                //check if tool has weekend charge
                if (tool.isWeekEndCharge()){
                    chargeDays++;
                }
            }
            //Check if Labor Day
            else if(c.get(Calendar.DAY_OF_MONTH) >= 7 && c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && c.get(Calendar.MONTH) == Calendar.SEPTEMBER){
                //check if tool has holiday charge
                if (tool.isHolidayCharge()){
                    chargeDays++;
                }
            }
            //check if July 4th
            else if (c.get(Calendar.MONTH) == inDay.get(Calendar.MONTH) && c.get(Calendar.DATE) == inDay.get(Calendar.DATE)){
                //check if tool has holiday charge
                if (tool.isHolidayCharge()){
                    chargeDays++;
                }
            }
            else{
                chargeDays++;
            }
            c.add(Calendar.DATE, 1);
        }
        return chargeDays;
    }

    public static Calendar calcIndependenceDay(Date checkOutDate){
        Calendar c = Calendar.getInstance();
        c.setTime(checkOutDate);
        Calendar InDay = Calendar.getInstance();
        InDay.set(Calendar.YEAR, 0);
        InDay.set(c.get(Calendar.YEAR),Calendar.JULY, 4);
        if(InDay.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            InDay.add(Calendar.DATE, -1);
        }
        else if(InDay.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            InDay.add(Calendar.DATE, 1);
        }
        return InDay;
    }

    public BigDecimal calcSubTotal(){
        double doubSubTotal = chargeDays * tool.getDayCharge();
        subTotal = BigDecimal.valueOf(doubSubTotal).setScale(2, RoundingMode.HALF_UP);
        return subTotal;
    }
    public BigDecimal calcDiscount(){
        BigDecimal percent = BigDecimal.valueOf(discountPercent / 100);
        discountAmount = percent.multiply(subTotal);
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
        return discountAmount;
    }

    public BigDecimal calcFinalTotal(){
        BigDecimal finalTotal = subTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
        return finalTotal;
    }

    public void printRentalAgreement(){
        String stringDueDate = calcDueDate();
        int chargeDays = calcChargeDays();
        BigDecimal subTotal = calcSubTotal();
        BigDecimal discountAmount = calcDiscount();
        BigDecimal finalTotal = calcFinalTotal();
        System.out.println("RENTAL AGREEMENT");
        System.out.println("Tool Code: " + tool.getToolCode());
        System.out.println("Tool Type: " + tool.getToolType());
        System.out.println("Tool Brand: " + tool.getToolBrand());
        System.out.println("Check Out Date: " + stringCheckoutDate);
        System.out.println("Due Date: " + stringDueDate);
        System.out.println("Daily Rental Charge: $" + tool.getDayCharge());
        System.out.println("Charge Days: " + chargeDays);
        System.out.println("SubTotal: $" + subTotal);
        System.out.println("Discount Percent: " + discountPercent +"%");
        System.out.println("Discount Amount: $" + discountAmount);
        System.out.println("Final Total: $" + finalTotal);
    }

    public static void main(String[] args){
        Input input = new Input();
        System.out.println("Enter the tool code for the tool you would like to rent: LADW, CHNS, JAKR, JAKD");
        input.setTool(myScan.next());
        System.out.println("Enter the checkout date in format MM/dd/yy: ");
        input.setStringCheckOutDate(myScan.next());
        System.out.println("Enter the number of days to rent: ");
        input.setRentalDays(myScan.nextInt());
        System.out.println("Enter your discount percent as a whole number (ie 20% is 20): ");
        input.setDiscountPercent(myScan.nextInt());
        Rental rental = new Rental(input.getTool(), input.getStringCheckOutDate(), input.getRentalDays(), input.getDiscountPercent());
        rental.printRentalAgreement();
    }
}
