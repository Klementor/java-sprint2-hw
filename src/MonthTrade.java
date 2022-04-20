class MonthTrade {
    MonthTrade(String itemName, Boolean isExpense, int quantity, int sumOfOne ){
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
    private final String itemName;
    private final Boolean isExpense;
    private final int quantity;
    private final int sumOfOne;

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public Boolean getExpense() {
        return isExpense;
    }

}
