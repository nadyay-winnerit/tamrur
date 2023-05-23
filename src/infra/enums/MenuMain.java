package infra.enums;

public enum MenuMain {
    dashboard("home", "תיקים רפואיים"),
    treatments("home", "טיפולים"),
    tasks("home", "משימות"),
    correspondences("home", "תכתובות"),
    persons("home", "אנשי קשר"),
    users("manager", "משתמשים"),
    settings("manager", "הגדרות"),
    terapists("manager", "מטפלים"),
    routes("manager", "מסלולים");
    private final String rootMenu;
    private final String nameInHebrew;

    MenuMain(String rootMenu, String nameInHebrew) {
        this.rootMenu = rootMenu;
        this.nameInHebrew = nameInHebrew;
    }

    public String rootMenu() {
        return rootMenu;
    }

    public String nameInHebrew() {
        return nameInHebrew;
    }
}
