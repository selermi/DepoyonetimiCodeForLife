public class Urunler {
        static int idVer = 1000;
        String urunIsmı;
        String uretici;
        int miktar;//miktar methodda tanımlanacak
        String birim;
        String raf;//methodda tanımlanacak
        int id;//otomatik olarak idVer 1 artılıp cons. da kendisi atayacak
        public Urunler(String urunIsmı, String uretici, String birim) {
            this.urunIsmı = urunIsmı;
            this.uretici = uretici;
            this.birim = birim;
            this.id=idVer++;
        }
}
