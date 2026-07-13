public abstract class DocumentFactory {
    public abstract Document createDocument();

    public void openAndSaveDocument() {
        Document doc = createDocument();

        doc.open();
        doc.save();
        doc.close();
    }
}
