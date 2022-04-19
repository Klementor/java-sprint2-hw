import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class ReadDataOfMonth {
    ArrayList<MonthReport> reports;

    ReadDataOfMonth() {
        reports = new ArrayList<>();


    }
    public void read(String path, int monthNum) {
        String fileContents = readFileContentsOrNull(path);
        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] data = lines[i].split(",");
            insertData(data, monthNum);
        }
    }

    private static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private void insertData(String[] data, int monthNum) {
        MonthTrade trade = new MonthTrade();
        trade.itemName = String.valueOf(data[0]);
        trade.isExpense = Boolean.valueOf(data[1]);
        trade.quantity = Integer.parseInt(data[2]);
        trade.sumOfOne = Integer.parseInt(data[3]);

        if (reports.size() < monthNum) {
            reports.add(new MonthReport());
        }

        reports.get(monthNum - 1).trades.add(trade);
    }
         void monthValueOutput(int monthNum) {
                 int maxProfit = 0;
                 int maxQuantity = 0;
                 Integer minProfit = Integer.MAX_VALUE;
                 int minQuantity = 0;
                 String nameOfMaxProfit = "";
                 String nameOfMinProfit = "";
                 if (monthNum == 1) {
                     System.out.println("Январь");
                 } else if (monthNum == 2) {
                     System.out.println("Февраль");
                 } else {
                     System.out.println("Март");
                 }
                 for (int a = 0; a < reports.get(monthNum - 1).trades.size(); a++) {
                     if (reports.get(monthNum - 1).trades.get(a).isExpense == false) {
                         maxQuantity = (reports.get(monthNum - 1).trades.get(a).sumOfOne * reports.get(monthNum - 1).trades.get(a).quantity);
                         if (maxQuantity > maxProfit) {
                             maxProfit = maxQuantity;
                             nameOfMaxProfit = reports.get(monthNum - 1).trades.get(a).itemName;
                         }
                     } else {
                         minQuantity = (reports.get(monthNum - 1).trades.get(a).sumOfOne * reports.get(monthNum - 1).trades.get(a).quantity);
                         if (maxQuantity < minProfit) {
                             minProfit = minQuantity;
                             nameOfMinProfit = reports.get(monthNum - 1).trades.get(a).itemName;
                         }
                     }
                 }
                 System.out.println("Max - " + nameOfMaxProfit + " : " + maxProfit);
                 System.out.println("Min - " + nameOfMinProfit + " : " + minProfit);
             }

        int[][] sum() {
            int [][] sumOfMonth = new int[3][2];
            int monthNum=1;
            while (monthNum<=4) {
                int sum_true = 0;
                int sum_false = 0;
                if (monthNum < 4) {
                    for (int a = 0; a < reports.get(monthNum - 1).trades.size(); a++) {
                        if (reports.get(monthNum - 1).trades.get(a).isExpense) {
                            sum_true += (reports.get(monthNum - 1).trades.get(a).sumOfOne * reports.get(monthNum - 1).trades.get(a).quantity);
                        } else {
                            sum_false += (reports.get(monthNum - 1).trades.get(a).sumOfOne * reports.get(monthNum - 1).trades.get(a).quantity);
                        }
                    }
                    sumOfMonth[monthNum - 1][0] = sum_true;
                    sumOfMonth[monthNum - 1][1] = sum_false;
                }
                monthNum++;
            }
            return sumOfMonth;
        }
}
