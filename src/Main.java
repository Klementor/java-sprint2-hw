import java.util.Scanner;

public class Main {
    private static final int MONTH_COUNT = 3 ;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadDataOfMonth readDataOfMonth = new ReadDataOfMonth();
        ReadDataOfYear readDataOfYear = new ReadDataOfYear();
        CompareService compareService = new CompareService();
        ReconciliationReport reconciliationReport = new ReconciliationReport();
        int year = 0;
        boolean isEnabled = true;
        while (isEnabled) {
            printMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 1: {
                    for (int i = 1; i <= MONTH_COUNT; i++) {
                        readDataOfMonth.read("resources/m.20210" + i + ".csv", i);
                    }
                    break;
                }

                case 2: {
                    System.out.println("За какой год вы хотите считать отчет?");
                    year = scanner.nextInt();
                    readDataOfYear.read("resources/y." + year + ".csv");
                    break;
                }

                case 3: {
                    int[][] sumOfMonths = readDataOfMonth.sum();
                    int[][] sumOfYear = readDataOfYear.sum();
                    boolean fullness = readDataOfMonth.getReports().isEmpty();
                    boolean isEqual = compareService.isEqual(sumOfMonths, sumOfYear);
                    int errorMonth = compareService.getErrorMonth();
                    reconciliationReport.reconciliation(fullness, isEqual, errorMonth);
                    break;
                }

                case  4: {
                    readDataOfMonth.CheckMonth();
                    break;
                }
                case 5: {
                    readDataOfYear.ChechYear(year);
                    break;
                }

                case 373: {
                    System.out.println("Выход");
                    isEnabled = false;
                    break;
                }

                default: {
                    System.out.println("Такой команды нет");
                    break;
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Чтобы завершить работу, введите число 373");
    }
    }