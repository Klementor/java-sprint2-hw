class MonthTrade {
    MonthTrade(String itemName, Boolean isExpense, int quantity, int sumOfOne ){
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
    private String itemName;
    private Boolean isExpense;
    private int quantity;
    private int sumOfOne;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public void setSumOfOne(int sumOfOne) {
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean getExpense() {
        return isExpense;
    }

    public void setExpense(Boolean expense) {
        isExpense = expense;
    }
}
