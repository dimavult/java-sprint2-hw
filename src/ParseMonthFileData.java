public class ParseMonthFileData {
    private String name;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;

    public String getName() {
        return name;
    }

    public boolean getExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public ParseMonthFileData(String[] lineContent) {
        this.name = lineContent[0];
        this.isExpense = Boolean.parseBoolean(lineContent[1]);
        this.quantity = Integer.parseInt(lineContent[2]);
        this.sumOfOne = Integer.parseInt(lineContent[3]);
    }
}
