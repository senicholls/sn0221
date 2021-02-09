import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.math.BigDecimal;

public class Rental {
    private static int rentalDays;
    private Date checkOutDate;
    private static Date dueDate;
    private static int chargeDays;
    private static BigDecimal subTotal;
    private static double discountPercent;
    private static BigDecimal discountAmount;
    private static BigDecimal finalTotal;
    static Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);
    static Tool chainsaw = new Tool("Chainsaw", "Stihl", "CHNS", 1.49, true, false, true);
    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    static Tool jackhammerD = new Tool("JackHammer", "DeWalt", "JAKD", 2.99, true, false, false);

    public static Date calcDueDate(Date checkoutDate, int rentalDays){
        Calendar c = Calendar.getInstance();
        c.setTime(checkoutDate);
        c.add(Calendar.DATE, rentalDays);
        dueDate = c.getTime();
        System.out.println("Due date: " + dueDate);
        return dueDate;
    }

    public static int calcChargeDays(Date checkOutDate, Date dueDate, Tool tool){
        //System.out.println("In calcChargeDays");
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
        } System.out.println("Charge Days: " + chargeDays);
        return chargeDays;
    }

    public static Calendar calcIndependenceDay(Date checkOutDate){
        Calendar c = Calendar.getInstance();
        c.setTime(checkOutDate);
        Calendar InDay = Calendar.getInstance();
        InDay.set(Calendar.YEAR, 0);
        //InDay.set(Calendar.MONTH, Calendar.JULY);
        //InDay.set(Calendar.DAY_OF_MONTH, 4);
        //System.out.println(c.get(Calendar.YEAR));
        //int year = c.get(Calendar.YEAR);
        InDay.set(c.get(Calendar.YEAR),Calendar.JULY, 4);
        if(InDay.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            InDay.add(Calendar.DATE, -1);
        }
        else if(InDay.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            InDay.add(Calendar.DATE, 1);
        }
        //System.out.println(InDay.get(Calendar.MONTH));
        //System.out.println(InDay.get(Calendar.DATE));
        //System.out.println(InDay.get(Calendar.YEAR));
        return InDay;
    }

    public static BigDecimal calcSubTotal(int chargeDays, Tool tool){
        double doubDubTotal = chargeDays * tool.getDayCharge();
        subTotal = BigDecimal.valueOf(doubDubTotal);
        System.out.println("SubTotal: " + subTotal);
        return subTotal;
    }
    public static BigDecimal calcDiscount(double discountPercent, BigDecimal subTotal){
        BigDecimal percent = BigDecimal.valueOf(discountPercent / 100);
        //BigDecimal subtotal = BigDecimal.valueOf(subTotal);
        BigDecimal discountAmount = percent.multiply(subTotal);
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
        System.out.print("Discount Amount: " + discountAmount);
        return discountAmount;
    }

    public static BigDecimal calcFinalTotal(BigDecimal discountAmount, BigDecimal subTotal){
        finalTotal = subTotal.subtract(discountAmount);
        System.out.println("Final Total: " + finalTotal);
        return finalTotal;
    }

    public static void main(String[] args){
        Scanner myScan = new Scanner(System.in);
        Tool tool = null;
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
        System.out.println("Enter the checkout date in format dd-MMM-yyyy: ");
        String stringCheckoutDate = myScan.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(stringCheckoutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Enter the number of days to rent: ");
        rentalDays = myScan.nextInt();
        if(rentalDays < 1){
            System.out.println("Rental Days must be 1 or greater");
            System.exit(1);
        }
        chargeDays = calcChargeDays(startDate, calcDueDate(startDate, rentalDays), tool);
        System.out.println("Enter your discount percent as a whole number (ie 20% is 20): ");
        discountPercent = myScan.nextInt();
        if (discountPercent > 100 || discountPercent < 0){
            System.out.println("Discount Percent must be between 0 and 100");
            System.exit(1);
        }
        subTotal = calcSubTotal(chargeDays, tool);
        discountAmount = calcDiscount(discountPercent, subTotal);
        finalTotal = calcFinalTotal(discountAmount, subTotal);
    }
}
