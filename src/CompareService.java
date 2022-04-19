public class CompareService {
    private static final int MONTH_COUNT = 3;
    private static final int CASH_TRANSACTIONS = 2;

    public Boolean isEqual (int [][] sumOfMonths, int[][] sumOfYear){
        for ( int i = 0; i < MONTH_COUNT; i++) {
            for (int j = 0; j < CASH_TRANSACTIONS; j++) {
                if (sumOfMonths[i][j] != sumOfYear[i][j]) {
                    ErrorMonth = i+1;
                    return false;
                }
            }
        }
        return true;
    }
    public Integer ErrorMonth = 0;
}
