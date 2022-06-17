public class ParseMonthFileData {
    String name;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public ParseMonthFileData(String[] lines) {
        this.name = lines[0];
        this.isExpense = Boolean.parseBoolean(lines[1]);
        this.quantity = Integer.parseInt(lines[2]);
        this.sumOfOne = Integer.parseInt(lines[3]);
    }
}
