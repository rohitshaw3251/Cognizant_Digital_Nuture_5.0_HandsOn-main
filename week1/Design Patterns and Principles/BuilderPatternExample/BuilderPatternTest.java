public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer basic = new Computer.Builder("Intel i3", "8GB").build();
        Computer gaming = new Computer.Builder("AMD Ryzen 9", "32GB")
            .setStorage("2TB SSD")
            .setGraphicsCardEnabled(true)
            .setBluetoothEnabled(true)
            .build();

        System.out.println("Basic Computer: CPU=" + basic.getCPU() + ", RAM=" + basic.getRAM());
        System.out.println("Gaming Computer: CPU=" + gaming.getCPU() + ", Graphics=" + gaming.isGraphicsCardEnabled());
    }
}
