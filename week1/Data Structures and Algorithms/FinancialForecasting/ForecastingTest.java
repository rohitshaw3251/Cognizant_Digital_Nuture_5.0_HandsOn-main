public class ForecastingTest {
    public static void main(String[] args) {
        double presentValue = 1000.0;
        double growthRate = 0.05;
        int years = 10;

        System.out.println("=== Financial Forecasting (Recursive Approach) ===");
        System.out.println("Present Value: $" + presentValue);
        System.out.println("Annual Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Forecast Period: " + years + " years");

        double futureValue = FinancialForecasting.calculateFutureValue(presentValue, growthRate, years);

        System.out.printf("Predicted Future Value: $%.2f%n", futureValue);
        System.out.println("==================================================");
    }
}
