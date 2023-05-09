package infra.ui.components;

public class TableCell {
    String column;
    String value;
    boolean isSelect;

    public TableCell(String column, String value, boolean select) {
        this.column = column;
        this.value = value;
        this.isSelect = select;
    }
}
