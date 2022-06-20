public class ParseYearFileData {

    private int amount;
    private boolean isExpense;

    public int getAmount() {
        return amount;
    }

    public boolean isExpense() {
        return isExpense;
    }


    public ParseYearFileData(String[] linesContent) {
        this.amount = Integer.parseInt(linesContent[1]);
        this.isExpense = Boolean.parseBoolean(linesContent[2]);
    }
}
