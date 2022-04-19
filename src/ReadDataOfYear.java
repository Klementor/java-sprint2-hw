import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadDataOfYear {
    ArrayList<Integer> month;
    ArrayList<Integer> amount;
    ArrayList<Boolean> isExpense;
    ReadDataOfYear() {
        month = new ArrayList<>();
        amount = new ArrayList<>();
        isExpense = new ArrayList<>();
    }
    public void read(String path) {
        String fileContents = readFileContentsOrNull(path);
        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] data = lines[i].split(",");
            insertData(data);
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

    private void insertData(String[] data) {
        month.add(Integer.valueOf(data[0]));
        amount.add(Integer.valueOf(data[1]));
        isExpense.add(Boolean.valueOf(data[2]));
    }
    void yearValueOutput(int year) {
            System.out.println("Год: " + year);
            int[][] sumOfYear = sum();
            int averageProfit = 0;
            int averageExpenses = 0;
            for (int i = 0; i < (isExpense.size() / 2); i++) {
                int profitOfYear = 0;
                profitOfYear += sumOfYear[i][1] - sumOfYear[i][0];
                System.out.println("Чистая прибыль за " + (i + 1) + " месяц: " + profitOfYear);
                averageProfit += sumOfYear[i][1] / (i + 1);
                averageExpenses += sumOfYear[i][0] / (i + 1);
            }
            System.out.println("Средняя прибыль за год: " + averageProfit);
            System.out.println("Средние затраты за год: " + averageExpenses);
    }
    int[][] sum () {
        int [][] sumOfYear = new int[3][2];
        for (int i = 0; i < isExpense.size(); i++)
            if(isExpense.get(i) == true){
                int numMonth = month.get(i);
                sumOfYear[numMonth-1][0] = amount.get(i);
            } else {
                int numMonth = month.get(i);
                sumOfYear[numMonth-1][1] = amount.get(i);
            }
        return sumOfYear;
    }
}
