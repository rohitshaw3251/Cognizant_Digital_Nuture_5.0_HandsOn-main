class PayPal {
    public void makePayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

public class PayPalAdapter implements PaymentProcessor {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    @Override
    public void processPayment(double amount) {
        payPal.makePayment(amount);
    }
}
