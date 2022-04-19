import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadDataOfMonth readDataOfMonth = new ReadDataOfMonth();
        ReadDataOfYear readDataOfYear = new ReadDataOfYear();
        CompareService compareService = new CompareService();
        int year = 0;
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            switch (command) {

                case 1: {
                    int i = 1;
                    while (i <= 3) {
                        readDataOfMonth.read("resources/m.20210" + i + ".csv", i);
                        i++;
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
                    if (!readDataOfMonth.getReports().isEmpty()) {
                        int[][] sumOfMonths = readDataOfMonth.sum();
                        int[][] sumOfYear = readDataOfYear.sum();
                        if (compareService.isEqual(sumOfMonths, sumOfYear)) {
                            System.out.println("Отчеты сошлись!");
                        } else {
                            System.out.println("В месяце " + compareService.ErrorMonth + " ошибка");
                        }
                    } else {
                        System.out.println("Сначала считайте отчеты");
                    }
                    break;
                }

                case  4: {
                    if (!readDataOfMonth.getReports().isEmpty()) {
                        int i = 1;
                        while (i <= 3) {
                            readDataOfMonth.monthValueOutput(i);
                            i++;
                        }
                    } else {
                        System.out.println("Сначала считайте месячные отчеты");
                    }
                    break;
                }
                case 5: {
                    if (!readDataOfYear.getMonth().isEmpty()) {
                        readDataOfYear.yearValueOutput(year);
                    } else {
                        System.out.println("Сначала считайте годовой отчет");
                    }
                    break;
                }

                case 373: {
                    System.out.println("Выход");
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


