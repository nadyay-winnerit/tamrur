package objects.pages.newTherapist;

import objects.BaseData;

public class DaysAndHoursOfOperationPageData extends BaseData {
    private String dayMenu;
    private String fromHour;
    private String toAnHour;
    private String Remarks;

    //setter

    public void setDayMenu(String dayMenu) {
        this.dayMenu = dayMenu;
    }

    public void setFromHour(String fromHour) {
        this.fromHour = fromHour;
    }

    public void setToAnHour(String toAnHour) {
        this.toAnHour = toAnHour;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
    //getter

    public String getDayMenu() {
        return dayMenu;
    }

    public String getFromHour() {
        return fromHour;
    }

    public String getToAnHour() {
        return toAnHour;
    }

    public String getRemarks() {
        return Remarks;
    }
}
