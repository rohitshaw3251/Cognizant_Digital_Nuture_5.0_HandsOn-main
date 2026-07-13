public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("John Doe", "1234-5678-9012-3456"));
        context.executePayment(50.0);

        context.setPaymentStrategy(new PayPalPayment("john@example.com"));
        context.executePayment(80.5);
    }
}
