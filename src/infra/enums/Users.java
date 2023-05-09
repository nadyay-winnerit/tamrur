package infra.enums;

public enum Users {

    menahel(true),
    manager(true),
    melave(false),
    attendant(false),
    //
    ;

    private boolean isManager;

    Users(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }

    public String getEmail() {
        return name() + "@automation.com";
    }

    public String getPassword() {
        return name();
    }

}
