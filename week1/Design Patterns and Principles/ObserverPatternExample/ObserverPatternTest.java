public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket market = new StockMarket("GOOGLE", 175.50);

        Observer app1 = new MobileApp("Android Client");
        Observer app2 = new WebApp("Chrome Dashboard");

        market.register(app1);
        market.register(app2);

        System.out.println("Market updating stock price to $180.00:");
        market.setPrice(180.00);

        System.out.println("\nMarket updating stock price to $178.50 (after Android deregistration):");
        market.deregister(app1);
        market.setPrice(178.50);
    }
}
