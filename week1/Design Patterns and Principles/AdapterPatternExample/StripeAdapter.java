class Stripe {
    public void charge(double amount) {
        System.out.println("Processing Stripe payment of $" + amount);
    }
}

public class StripeAdapter implements PaymentProcessor {
    private Stripe stripe;

    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }

    @Override
    public void processPayment(double amount) {
        stripe.charge(amount);
    }
}
