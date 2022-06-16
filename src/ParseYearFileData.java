public class ParseYearFileData {
    int month;
    boolean isExpense;
    int amount;

    public ParseYearFileData(String[] lineContents) {
        this.month = Integer.parseInt(lineContents[0]);
        this.isExpense = Boolean.parseBoolean(lineContents[2]);
        this.amount = Integer.parseInt(lineContents[1]);
    }
}
