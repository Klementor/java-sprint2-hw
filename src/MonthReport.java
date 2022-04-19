import java.util.ArrayList;

public class MonthReport {
    ArrayList<MonthTrade> trades = new ArrayList<>();
}

class MonthTrade {
    String itemName = null;
    Boolean isExpense = false;
    int quantity = 0;
    int sumOfOne = 0;
}
