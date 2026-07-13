public class FactoryMethodTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Factory Method Pattern ===");

        System.out.println("\n--- Word Document Scenario ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        wordFactory.openAndSaveDocument();

        System.out.println("\n--- PDF Document Scenario ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        pdfFactory.openAndSaveDocument();

        System.out.println("\n--- Excel Document Scenario ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        excelFactory.openAndSaveDocument();

        System.out.println("\n======================================");
    }
}
