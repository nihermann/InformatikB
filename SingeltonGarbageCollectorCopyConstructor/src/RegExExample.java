public class RegExExample {
    public static void main(String[] args){
        String s = "Hallllllo";
        boolean ergebnis = s.matches("Hal*o");
        System.out.println(ergebnis);

        String bw = "Y 123456";
        String dipl = "0 12-3456";

        String bwREEs = "Y \\d{6}";
        boolean bwErgebnis = bw.matches(bwREEs);
        boolean diplErgebnis = dipl.matches();
    }
}
