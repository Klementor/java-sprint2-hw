import java.util.ArrayList;

public class ReadDataOfMonth {
    private final ArrayList<MonthReport> reports;
    private static final int MONTH_COUNT = 3;
    private static final int COSTS_AND_PROFITS = 2;
    public ReadDataOfMonth() {
        this.reports = new ArrayList<>();
    }

    public void read(String path, int monthNum) {
        String fileContents = ReadFileContentsOrNull.readFileContentsOrNull(path);
        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] data = lines[i].split(",");
            insertData(data, monthNum);
        }
    }

    private void insertData(String[] data, int monthNum) {
        MonthTrade trade = new MonthTrade(String.valueOf(data[0]), Boolean.valueOf(data[1]), Integer.parseInt(data[2]),
                Integer.parseInt(data[3]));
        if (reports.size() < monthNum) {
            reports.add(new MonthReport());
        }
        reports.get(monthNum - 1).getTrades().add(trade);
    }

    public void monthValueOutput(int monthNum) {
        int maxProfit = 0;
        int maxQuantity = 0;
        int minProfit = Integer.MAX_VALUE;
        String nameOfMaxProfit = "";
        String nameOfMinProfit = "";
        if (monthNum == 1) {
            System.out.println("Январь");
        } else if (monthNum == 2) {
            System.out.println("Февраль");
        } else {
            System.out.println("Март");
        }
        for (int a = 0; a < reports.get(monthNum - 1).getTrades().size(); a++) {
            if (!reports.get(monthNum - 1).getTrades().get(a).getExpense()) {
                maxQuantity = (reports.get(monthNum - 1).getTrades().get(a).getSumOfOne() *
                        reports.get(monthNum - 1).getTrades().get(a).getQuantity());
                if (maxQuantity > maxProfit) {
                    maxProfit = maxQuantity;
                    nameOfMaxProfit = reports.get(monthNum - 1).getTrades().get(a).getItemName();
                }
            } else {
                int minQuantity = (reports.get(monthNum - 1).getTrades().get(a).getSumOfOne() *
                        reports.get(monthNum - 1).getTrades().get(a).getQuantity());
                if (maxQuantity < minProfit) {
                    minProfit = minQuantity;
                    nameOfMinProfit = reports.get(monthNum - 1).getTrades().get(a).getItemName();
                }
            }
        }
        System.out.println("Max - " + nameOfMaxProfit + " : " + maxProfit);
        System.out.println("Min - " + nameOfMinProfit + " : " + minProfit);
    }

    public int[][] sum() {
        int [][] sumOfMonth = new int[MONTH_COUNT][COSTS_AND_PROFITS];
        for (int monthNum=0; monthNum<=MONTH_COUNT; monthNum++) {
            int sumTrue = 0;
            int sumFalse = 0;
            if (monthNum < MONTH_COUNT) {
                for (int a = 0; a < reports.get(monthNum).getTrades().size(); a++) {
                    if (reports.get(monthNum).getTrades().get(a).getExpense()) {
                        sumTrue += (reports.get(monthNum).getTrades().get(a).getSumOfOne() *
                                reports.get(monthNum).getTrades().get(a).getQuantity());
                    } else {
                        sumFalse += (reports.get(monthNum).getTrades().get(a).getSumOfOne() *
                                reports.get(monthNum).getTrades().get(a).getQuantity());
                    }
                }
                sumOfMonth[monthNum][0] = sumTrue;
                sumOfMonth[monthNum][1] = sumFalse;
            }
        }
        return sumOfMonth;
    }

    public void CheckMonth(){
        if (!reports.isEmpty()) {
            for (int i = 1; i <= MONTH_COUNT; i++) {
                monthValueOutput(i);
            }
        } else {
            System.out.println("Сначала считайте месячные отчеты");
        }
    }

    public ArrayList<MonthReport> getReports() {
        return reports;
    }
}