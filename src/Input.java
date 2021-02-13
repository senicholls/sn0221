import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    static Tool ladder = new Tool("Ladder", "Werner", "LADW", 1.99, true, true, false);
    static Tool chainsaw = new Tool("Chainsaw", "Stihl", "CHNS", 1.49, true, false, true);
    static Tool jackhammerR = new Tool("JackHammer", "Ridgid", "JAKR", 2.99, true, false, false);
    static Tool jackhammerD = new Tool("JackHammer", "DeWalt", "JAKD", 2.99, true, false, false);

    private static Tool tool;
    private static String stringCheckOutDate;
    private static int rentalDays;
    private static double discountPercent;

    public static Tool getTool() {
        return tool;
    }

    public static String getStringCheckOutDate() {
        return stringCheckOutDate;
    }

    public static int getRentalDays() {
        return rentalDays;
    }

    public static double getDiscountPercent() {
        return discountPercent;
    }

    public static void setTool(String toolcode) throws InputMismatchException {
        switch (toolcode){
            case "LADW":
                //tool = ladder;
                Input.tool = ladder;
                break;
            case "CHNS":
                tool = chainsaw;
                Input.tool = chainsaw;
                break;
            case "JAKR":
                tool = jackhammerR;
                Input.tool = jackhammerR;
                break;
            case "JAKD":
                tool = jackhammerD;
                Input.tool = jackhammerD;
                break;
            default:
                System.out.println("No matching tool code was found.");
                throw new InputMismatchException();
        }
    }

    public static void setStringCheckOutDate(String date) throws InputMismatchException {
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if(matcher.matches()){
            Input.stringCheckOutDate = date;
        }
        else{
            System.out.println("Invalid date format.");
            throw new InputMismatchException();
        }
    }

    public static void setRentalDays(int num) throws InputMismatchException {
        Input.rentalDays = num;
        if(Input.rentalDays < 1){
            System.out.println("Rental Days must be 1 or greater");
            throw new InputMismatchException();
        }
    }

    public static void setDiscountPercent(int num) throws InputMismatchException {
        Input.discountPercent = num;
        if (Input.discountPercent > 100 || Input.discountPercent < 0){
            System.out.println("Discount Percent must be between 0 and 100");
            throw new InputMismatchException();
        }
    }
}
