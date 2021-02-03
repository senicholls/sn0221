public class Tool {
    private String toolType;
    private String toolBrand;
    private String toolCode;
    private double dayCharge;
    private boolean weekDayCharge;
    private boolean weekEndCharge;
    private boolean holidayCharge;


    public Tool(String toolType, String toolBrand, String toolCode, double dayCharge, boolean weekDayCharge, boolean weekEndCharge, boolean holidayCharge) {
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.toolCode = toolCode;
        this.dayCharge = dayCharge;
        this.weekDayCharge = weekDayCharge;
        this.weekEndCharge = weekEndCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public double getDayCharge() {
        return dayCharge;
    }

    public boolean isWeekDayCharge() {
        return weekDayCharge;
    }

    public boolean isWeekEndCharge() {
        return weekEndCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
