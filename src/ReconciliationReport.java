public class ReconciliationReport {
    public void reconciliation( boolean fullness, boolean isEqual, int errorMonth) {
        if (!fullness) {
            if (isEqual) {
                System.out.println("Отчеты сошлись!");
            } else {
                System.out.println("В месяце " + errorMonth + " ошибка");
            }
        } else {
            System.out.println("Сначала считайте отчеты");
        }
    }
}
