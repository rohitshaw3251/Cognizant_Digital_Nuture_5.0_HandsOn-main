public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.png");

        System.out.println("Image 1 first display:");
        image1.display();
        
        System.out.println("\nImage 1 second display (should be cached):");
        image1.display();

        System.out.println("\nImage 2 first display:");
        image2.display();
    }
}
