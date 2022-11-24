import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Islemler implements Methods {
    public static final String G = "\u001B[32m";
    public static final String Y = "\u001B[33m";
    Scanner scan = new Scanner(System.in);
    HashMap<Integer, Urunler> urunMap = new HashMap<>();
    //HashMap -> urunİd , Urunler
    String urunIsmiArr[] = {"Un", "Şeker", "Yumurta", "Helva", "Yağ", "Margarin"};
    String ureticiArr[] = {"Hekimoğlu", "Ekici", "Ova un", "Ülker", "Bili Bili"};
    String urunBirimiArr[] = {"Koli", "Çuval", "Paket", "Litre", "Kilogram"};

    @Override
    public void urunTanimlama() {
        String urunIsmı = urunIsmiSec();
        String uretici = ureticiFirmaSec();
        String birim = urunBirimSec(urunIsmı);
        if (!ayniUrunKontrol(uretici, urunIsmı)) {
            Urunler urun = new Urunler(urunIsmı, uretici, birim);
            urunMap.put(urun.id, urun);
        }
    }

    @Override
    public void urunListele() {
        System.out.println("|ID   |Urun Ismi|Uretici Adi|Urun Miktari|Urun Birimi|Raf Bilgisi|");
        for (Map.Entry<Integer, Urunler> value : urunMap.entrySet()) {
            System.out.printf("|%-5d|%-9S|%-11S|%-12d|%-11S|%-11S|\n",
                    value.getKey(), value.getValue().urunIsmı, value.getValue().uretici, value.getValue().miktar, value.getValue().birim, value.getValue().raf);
        }
    }

    @Override
    public void urunGirisi() {
        urunListele();
        System.out.print("Lütfen aşağıdaki listeden girişi yapılacak ürün id sini yazınız : ");
        int girilecekId = intTryCatch(Integer.MAX_VALUE, 1000);
        if (urunMap.containsKey(girilecekId)) {
            System.out.print("Şu anda mevcut miktar = " + urunMap.get(girilecekId).miktar + " Ekleme yapılacak miktarı giriniz? => ");
            int girilecekMiktar = intTryCatch(Integer.MAX_VALUE, 0);
            urunMap.get(girilecekId).miktar += girilecekMiktar;
        } else {
            System.out.println(girilecekId + " ID numaralı Kayıt Bulunamamıştır!..");
        }
    }

    @Override
    public void urunuRafaKoy() {
        System.out.print("Rafa koymak istediğiniz ürünün ID'sini giriniz : ");
        int id = intTryCatch(Integer.MAX_VALUE, 0);
        if (urunMap.containsKey(id)) {
            System.out.println("Girdiğiniz ürün : " + urunMap.get(id).urunIsmı);
            System.out.print("Raf numarasını giriniz : ");
            String rafNumarası = "RAF" + intTryCatch(Integer.MAX_VALUE, 0);
            urunMap.get(id).raf = rafNumarası;
        } else {
            System.out.println("girdiğiniz " + id + " numaralı ürün kayıtlarda bulunamadı.");
        }
    }

    @Override
    public void urunCikisi() {
        urunListele();
        System.out.print("Lütfen yukardaki listeden çıkış yapmak istediğiniz ürün id sini yazınız : ");
        int silinecekId = intTryCatch(Integer.MAX_VALUE, 0);
        if (urunMap.containsKey(silinecekId)) {
            System.out.print("Şu anda mevcut miktar :" + urunMap.get(silinecekId).miktar + " Çıkış yapılacak miktarı giriniz : ");
            int silinecekMiktar = intTryCatch(urunMap.get(silinecekId).miktar, 0);
            urunMap.get(silinecekId).miktar -= silinecekMiktar;
            System.out.println("Kalan miktar = " + urunMap.get(silinecekId).miktar);
        } else {
            System.out.println("İstediğiniz ürün depoda mevcut değildir. Sizi ana menüye yönlendiriyorum");
        }
    }

    @Override
    public boolean ayniUrunKontrol(String uretici, String urunIsmi) {
        boolean flag = false;
        for (Map.Entry<Integer, Urunler> e : urunMap.entrySet()) {
            if (e.getValue().uretici.equals(uretici) && e.getValue().urunIsmı.equals(urunIsmi)) {
                flag = true;
                System.out.println(e.getKey() + " numaralı kayıt mevcut. Tekrar oluşturamazsınız");
            }
        }
//        for (Integer w:urunMap.keySet()) {
//            if(urunMap.get(w).uretici.equals(uretici)&&urunMap.get(w).urunIsmi.equals(urunIsmi)){
//                flag=true;
//                System.out.println(w+ " numaralı kayıt mevcut. Tekrar oluşturamazsınız");
//            }
//        }
        return flag;
    }

    @Override
    public int intTryCatch(int ustlimit, int altlimit) {
        //burda int limit olarak belirledigimiz parametre her blokta farkli degerler verilerek kullanilabilir bir parametre
        //belirledigimiz parametre kullandigimiz method icine gore deger alacak 1 den fazla yerde istedigimiz sekilde kullanilabilecek hale gelecek
        //Verilerimizide String variable atadigimiz icin String variable olaraktan tarafimiza dondurecek
        int userinput;//kullanici her turlu sayisal giris islemini alip atayacagimiz variable
        while (true) {//true oldugu surece calisacak olan dongumuz yani kullanicidan tam bilgi alana kadar calisacak
            try {
                userinput = scan.nextInt();//variable deger atadigimiz kisim
                if (userinput > ustlimit || userinput < altlimit) {//eger kullanici belirledigimiz limit uzerinde veya 0 altinda giris yaparsa
                    //handle etmek icin kullandigimiz if blogu
                    //kullanici burda istedigimiz sinirlar disinda veri girisi yaptiginda Exception firlatacak ve kullanici dogru bilgi
                    //girene kadar while dongusu kullanicidan bilgi almaya devam edecek
                    throw new Exception();
                }
                break;//kullanici sinirlar icerisinde bilgi girisi yaptigi zaman break komutut ile while dongusunden cikilacak
            } catch (
                    Exception e) {//if blogu calisinca exception firlatacagi icin burda kullanicinin yaptigi hatali girisi handle ediyoruz
                //yaptigimiz handle sonrasinda kullaniciya assagidaki bilgilendirme mesajini gonderiyoruz ve dongumuzun basina donuyoruz.
                System.out.print("Girilen deger gecerli limit sinirlari icerisinde olmalidir\n" +
                        "Tekrar giris yapiniz : ");
                scan.nextLine();//dongu basina girince scande sorun olmasin diye kullandigimiz dummy.
            }
        }
        scan.nextLine();//Donguden cikinca bu methodu kullandigimiz methodda dummy atmamiza gerek kalmasin diye kullandigimiz dummy
        return userinput;
    }

    @Override
    public void menuYazdir() {
        System.out.println(Y + "============================ CodeForLife DEPO SİSTEMİ ============================\r\n"
                + "   __________________            __________________            __________________    \n"
                + "   | 1-URUN TANIMLA |            | 2-URUN LISTELE |            | 3-URUN GIRISI  |  \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                \n"
                + "   __________________            __________________            __________________  \n"
                + "   | 4-URUN RAFA KOY|            | 5-URUN CIKISI  |            | 6-CIKIS        |  \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯   " + G);

        System.out.print("İSLEM SECİNİZ : ");
        int secm = intTryCatch(6, 1);
        switch (secm) {
            case 1:
                urunTanimlama();
                menuYazdir();
                break;
            case 2:
                urunListele();
                menuYazdir();
                break;
            case 3:
                urunGirisi();
                menuYazdir();
                break;
            case 4:
                urunuRafaKoy();
                menuYazdir();
                break;
            case 5:
                urunCikisi();
                menuYazdir();
                break;
            case 6:
                System.out.println("Depo yönetiminden çıkıldı");
                break;
            default:
                System.out.println("Hatali deger girdiniz");
                menuYazdir();
        }
    }

    @Override
    public String urunIsmiSec() {
        System.out.println("***** ÜRÜN LİSTESİ *****");
        for (int i = 0; i < urunIsmiArr.length; i++) {
            System.out.print((i + 1) + " -> " + urunIsmiArr[i] + "\n");
        }
        System.out.print("Seçmek istediğiniz ürünün numarasını giriniz = ");
        int secim = intTryCatch(urunIsmiArr.length, 1);
        return urunIsmiArr[secim - 1];
    }

    @Override
    public String ureticiFirmaSec() {
        System.out.println("---ÜRETICI FIRMA LİSTESİ----");
        for (int i = 0; i < ureticiArr.length; i++) {
            System.out.print((i + 1) + " -> " + ureticiArr[i] + "\n");
        }
        System.out.print("Seçmek istediğiniz firmayı giriniz = ");
        int secim = intTryCatch(ureticiArr.length, 1);
        return ureticiArr[secim - 1];
    }

    @Override
    public String urunBirimSec(String urunIsmi) {
        switch (urunIsmi) {
            case "Un":
            case "Şeker":
                return "Çuval";
            case "Yumurta":
                return "Koli";
            case "Yağ":
                return "Litre";
            case "Margarin":
                return "Paket";
            case "Helva":
                return "Kilogram";
        }
        return null;
    }
}


