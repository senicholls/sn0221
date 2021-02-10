import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.math.BigDecimal;

public class Rental {
    static Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);
    static Tool chainsaw = new Tool("Chainsaw", "Stihl", "CHNS", 1.49, true, false, true);
    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    static Tool jackhammerD = new Tool("JackHammer", "DeWalt", "JAKD", 2.99, true, false, false);

    public static String calcDueDate(String stringCheckoutDate, int rentalDays){
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
        //System.out.println("Due date: " + dueDate);
        String stringDueDate = dateFormat.format(dueDate);
        return stringDueDate;
    }

    public static int calcChargeDays(String stringCheckoutDate, String stringDueDate, Tool tool){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date checkOutDate = null;
        Date dueDate = null;
        try {
            checkOutDate = dateFormat.parse(stringCheckoutDate);
            dueDate = dateFormat.parse(stringDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int chargeDays = 0;
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
            else if (c.equals(inDay)){
                //check if tool has holiday charge
                if (tool.isHolidayCharge()){
                    chargeDays++;
                }
            }
            else{
                chargeDays++;
            }
            c.add(Calendar.DATE, 1);
        } //System.out.println("Charge Days: " + chargeDays);
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

    public static BigDecimal calcSubTotal(int chargeDays, Tool tool){
        double doubDubTotal = chargeDays * tool.getDayCharge();
        BigDecimal subTotal = BigDecimal.valueOf(doubDubTotal);
        //System.out.println("SubTotal: " + subTotal);
        return subTotal;
    }
    public static BigDecimal calcDiscount(double discountPercent, BigDecimal subTotal){
        BigDecimal percent = BigDecimal.valueOf(discountPercent / 100);
        BigDecimal discountAmount = percent.multiply(subTotal);
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
        //System.out.println("Discount Amount: " + discountAmount);
        return discountAmount;
    }

    public static BigDecimal calcFinalTotal(BigDecimal discountAmount, BigDecimal subTotal){
        BigDecimal finalTotal = subTotal.subtract(discountAmount);
        //System.out.println("Final Total: " + finalTotal);
        return finalTotal;
    }

    public static void printRentalAgreement(Tool tool, int rentalDays, String stringCheckoutDate, int discountPercent){
        String stringDueDate = calcDueDate(stringCheckoutDate, rentalDays);
        int chargeDays = calcChargeDays(stringCheckoutDate, stringDueDate, tool);
        BigDecimal subTotal = calcSubTotal(chargeDays, tool);
        BigDecimal discountAmount = calcDiscount(discountPercent, subTotal);
        BigDecimal finalTotal = calcFinalTotal(discountAmount, subTotal);
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

    public static void checkOut(Tool tool, int rentalDays, String stringCheckoutDate,  int discountPercent){
        Scanner myScan = new Scanner(System.in);
        System.out.println("Enter the tool code for the tool you would like to rent: LADW, CHNS, JAKR, JAKD");
        switch (myScan.next()){
            case "LADW":
                tool = ladder;
                break;
            case "CHNS":
                tool = chainsaw;
                break;
            case "JAKR":
                tool = jackhammerR;
                break;
            case "JAKD":
                tool = jackhammerD;
                break;
            default:
                System.out.println("No matching tool code was found.");
                System.exit(1);
        }
        System.out.println("Enter the checkout date in format MM/dd/yy: ");
        stringCheckoutDate = myScan.next();
        System.out.println("Enter the number of days to rent: ");
        rentalDays = myScan.nextInt();
        if(rentalDays < 1){
            System.out.println("Rental Days must be 1 or greater");
            System.exit(1);
        }
        System.out.println("Enter your discount percent as a whole number (ie 20% is 20): ");
        discountPercent = myScan.nextInt();
        if (discountPercent > 100 || discountPercent < 0){
            System.out.println("Discount Percent must be between 0 and 100");
            System.exit(1);
        }
        printRentalAgreement(tool, rentalDays, stringCheckoutDate, discountPercent);
    }

    public static void main(String[] args){
        Tool tool = null;
        String stringCheckoutDate = null;
        int rentalDays = 0;
        int discountPercent = 0;
        checkOut(tool, rentalDays, stringCheckoutDate, discountPercent);
    }
}
