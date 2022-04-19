import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadDataOfMonth {
    private final ArrayList<MonthReport> reports;

    ReadDataOfMonth() {
        reports = new ArrayList<>();
    }

    public void read(String path, int monthNum) {
        String fileContents = readFileContentsOrNull(path);
        assert fileContents != null;
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
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в " +
                    "нужной директории.");
            return null;
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
        int [][] sumOfMonth = new int[3][2];
        int monthNum=1;
        while (monthNum<=4) {
            int sum_true = 0;
            int sum_false = 0;
            if (monthNum < 4) {
                for (int a = 0; a < reports.get(monthNum - 1).getTrades().size(); a++) {
                    if (reports.get(monthNum - 1).getTrades().get(a).getExpense()) {
                        sum_true += (reports.get(monthNum - 1).getTrades().get(a).getSumOfOne() *
                                reports.get(monthNum - 1).getTrades().get(a).getQuantity());
                    } else {
                        sum_false += (reports.get(monthNum - 1).getTrades().get(a).getSumOfOne() *
                                reports.get(monthNum - 1).getTrades().get(a).getQuantity());
                    }
                }
                sumOfMonth[monthNum - 1][0] = sum_true;
                sumOfMonth[monthNum - 1][1] = sum_false;
            }
            monthNum++;
        }
        return sumOfMonth;
    }

    public ArrayList<MonthReport> getReports() {
        return reports;
    }
}
