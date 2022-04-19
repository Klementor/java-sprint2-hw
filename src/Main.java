import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadDataOfMonth readDataOfMonth = new ReadDataOfMonth();
        ReadDataOfYear readDataOfYear = new ReadDataOfYear();
        int year = 0;
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                int i =1;
                while(i<=3){
                    readDataOfMonth.read("resources/m.20210" + i + ".csv", i);
                    i++;
                }
            } else if (command == 2) {
                System.out.println("За какой год вы хотите считать отчет?");
                year = scanner.nextInt();
                readDataOfYear.read("resources/y."+ year +".csv");
            } else if (command == 3) {
                if(readDataOfMonth.reports.size() != 0) {
                    int[][] sumOfMonths = readDataOfMonth.sum();
                    int[][] sumOfYear = readDataOfYear.sum();
                    boolean isOk = true;
                    int numberOfMonth = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 2; j++) {

                            if (sumOfMonths[i][j] == sumOfYear[i][j]) {

                            } else {
                                isOk = false;
                                numberOfMonth = i + 1;
                            }
                        }
                    }
                    if (isOk) {
                        System.out.println("Отчеты сошлись!");
                    } else {
                        System.out.println("В отчете по месяцу №" + numberOfMonth + " допущена ошибка");
                    }
                } else {
                    System.out.println("Сначала считайте отчеты");
                }

            } else if (command == 4) {
                if (readDataOfMonth.reports.size()!=0) {
                    int i = 1;
                    while (i <= 3) {
                        readDataOfMonth.monthValueOutput(i);
                        i++;
                    }
                } else {
                    System.out.println("Сначала считайте месячные отчеты");
                }
            } else if (command == 5) {
                if (readDataOfYear.month.size()!= 0) {
                    readDataOfYear.yearValueOutput(year);
                } else {
                    System.out.println("Сначала считайте годовой отчет");
                }
            } else if (command == 373) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Такой команды нет");
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


