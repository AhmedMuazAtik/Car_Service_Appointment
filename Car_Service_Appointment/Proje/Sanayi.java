/**
 * @file Project
 * @description Herhangi bir sanayide, teknikerin musteriyle olan randevu
 * baglantisini kuruyor.
 * @assignment 2.Proje
 * @date 20/01/2022
 * @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
 */
package project;

public class Sanayi {

    private String tarih;
    private Ariza[] tumArizalar;
    private Musteri[] tumMusteriler;
    private Tekniker[] tumTeknikerler;
    private Randevu[] tumRandevular;
    private Randevu[] tumAktifRandevular;
    private Randevu[] tumGelecekRandevular;
    private Ariza[] eskiArizalar;
    private Randevu[] eskiRandevular;

    public Sanayi(String tarih) {
        this.tarih = tarih;
        tumTeknikerler = new Tekniker[20];
        tumArizalar = new Ariza[20];
        tumRandevular = new Randevu[20];
        tumAktifRandevular = new Randevu[20];
        tumGelecekRandevular = new Randevu[20];
        tumMusteriler = new Musteri[20];
        eskiArizalar = new Ariza[20];
        eskiRandevular = new Randevu[20];
    }

    public String getTarih() {
        return tarih;
    }

    public Tekniker[] getTumTeknikerler() {
        return tumTeknikerler;
    }

    public Randevu[] getTumRandevular() {
        return tumRandevular;
    }

    public Musteri[] getTumMusteriler() {
        return tumMusteriler;
    }

    public Ariza[] getTumArizalar() {
        return tumArizalar;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
        guncelle();
    }

    public void bahsisVer(Musteri musteri, Tekniker tekniker, double bahsis) { //musterinin teknikere bahsis vermesi saglaniyor.
        System.out.println(musteri.getAdSoyad() + " musterisi " + tekniker.getAdSoyad() + " teknikerine " + bahsis + " TL bahsis vermistir.");
        tekniker.setBakiye(bahsis);
        musteri.setBakiye(bahsis);
    }

    public void cozulmusRandevulariListele(Tekniker tekniker, String ilkTarih, String sonTarih) { //tarihler arasinda olup cozumlenmis olan randevular listeleniyor.
        int numara = 1;

        System.out.println("Cozumlenmis randevular: ");

        for (int i = 0; i < tekniker.getGecmisRandevular().length; i++) {
            if (tekniker.getGecmisRandevular()[i] != null) {
                if (tarihFarki(sonTarih, ilkTarih) >= 0) {
                    if (tarihFarki(tekniker.getGecmisRandevular()[i].getRandevuTarihi(), ilkTarih) >= 0 && tarihFarki(sonTarih, tekniker.getGecmisRandevular()[i].getRandevuTarihi()) >= 0) {
                        System.out.println(numara + ") " + tekniker.getGecmisRandevular()[i].randevuBilgi());
                        numara++;
                    }
                } else {
                    System.out.println("Ilk tarih ikinci tarihten buyuk olamaz.");
                }
            }
        }
    }

    public void cozulmemisRandevulariListele(Tekniker tekniker, String ilkTarih, String sonTarih) { //tarihler arasinda olup cozumlenmemis olan randevular listeleniyor.
        int numara = 1;

        System.out.println("Cozumlenmemis randevular: ");

        for (int i = 0; i < tekniker.getGelecekRandevular().length; i++) {
            if (tekniker.getGelecekRandevular()[i] != null) {
                if (tarihFarki(sonTarih, ilkTarih) >= 0) {
                    if (tarihFarki(tekniker.getGelecekRandevular()[i].getRandevuTarihi(), ilkTarih) > 0 && tarihFarki(sonTarih, tekniker.getGelecekRandevular()[i].getRandevuTarihi()) > 0) {
                        System.out.println(numara + ") " + tekniker.getGelecekRandevular()[i].randevuBilgi());
                        numara++;
                    }
                } else {
                    System.out.println("Ilk tarih ikinci tarihten buyuk olamaz.");
                }
            }
        }

        for (int i = 0; i < tekniker.getAktifRandevular().length; i++) {
            if (tekniker.getAktifRandevular()[i] != null) {
                if (tarihFarki(sonTarih, ilkTarih) >= 0) {
                    if (tarihFarki(tekniker.getAktifRandevular()[i].getRandevuTarihi(), ilkTarih) == 0 && tarihFarki(sonTarih, tekniker.getAktifRandevular()[i].getRandevuTarihi()) == 0) {
                        System.out.println(numara + ") " + tekniker.getAktifRandevular()[i].randevuBilgi());
                        numara++;
                    }
                } else {
                    System.out.println("Ilk tarih ikinci tarihten buyuk olamaz.");
                }
            }
        }
    }

    public void eskiRandevulariListele() { //sistemdeki tum eski randevulari listeliyor.
        for (int i = 0; i < this.eskiRandevular.length; i++) {
            if (this.eskiRandevular[i] != null) {
                System.out.println(this.eskiRandevular[i].randevuBilgi());
            }
        }
    }

    public void eskiArizalariListele() { //sistemdeki tum eski arizalari listeliyor.
        for (int i = 0; i < this.eskiArizalar.length; i++) {
            if (this.eskiArizalar[i] != null) {
                System.out.println(this.eskiArizalar[i].getTur());
            }
        }
    }

    public void teknikerSil(Tekniker tekniker) { //sistemden teknikeri eger aktif randevusu yoksa siliyor.
        int aktifRandevuSayac = 0;
        for (int i = 0; i < tekniker.getAktifRandevular().length; i++) {
            if (tekniker.getAktifRandevular()[i] != null) {
                aktifRandevuSayac++;
            }
        }
        if (aktifRandevuSayac == 0) { //eger aktif randevusu yoksa gecmis, aktif, gelecek randevularini ve teknikeri sistemden siliyor.
            for (int i = 0; i < tekniker.getGelecekRandevular().length; i++) {
                if (tekniker.getGelecekRandevular()[i] != null) {
                    for (int j = 0; j < this.eskiArizalar.length; j++) {
                        if (this.eskiArizalar[j] == null) {
                            this.eskiArizalar[j] = tekniker.getGelecekRandevular()[i].getAriza();
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < tekniker.getGecmisRandevular().length; i++) {
                if (tekniker.getGecmisRandevular()[i] != null) {
                    for (int j = 0; j < this.eskiArizalar.length; j++) {
                        if (this.eskiArizalar[j] == null) {
                            this.eskiArizalar[j] = tekniker.getGecmisRandevular()[i].getAriza();
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < this.tumTeknikerler.length; i++) {
                if (this.tumTeknikerler[i] == tekniker) {
                    this.tumTeknikerler[i] = null;
                    break;
                }
            }

            System.out.println(tekniker.getAdSoyad() + " teknikeri sistemden tamamen silinmistir.");
            tekniker.aktifRandevulariSil();
            tekniker.gelecekRandevulariSil();
            tekniker.gecmisRandevulariSil();
            eskiArizalariListele();
            eskiRandevulariListele();

        } else { //eger aktif randevusu varsa uzerindeki randevulari random bir teknikere atayip sistemden siliniyor.
            int teknikerSayac = 0;
            for (int i = 0; i < this.tumTeknikerler.length; i++) {
                if (this.tumTeknikerler[i] != null) {
                    teknikerSayac++;
                }
            }
            if (teknikerSayac > 1) {
                int random = (int) (Math.random() * this.tumTeknikerler.length);
                while (true) {
                    if (this.tumTeknikerler[random] == null) {
                        random = (int) (Math.random() * this.tumTeknikerler.length);
                    } else if (this.tumTeknikerler[random] != tekniker) {
                        System.out.println(tekniker.getAdSoyad() + " teknikerinin uzerinde "
                                + aktifRandevuSayac + " tane aktif randevusu vardir.");
                        System.out.println(this.tumTeknikerler[random].getAdSoyad()
                                + " teknikerine " + tekniker.getAdSoyad() + " teknikerinin aktif arizalari aktarilmistir.");

                        this.tumTeknikerler[random].aktifRandevuDegistir(tekniker);

                        for (int i = 0; i < this.eskiRandevular.length; i++) {
                            if (this.eskiRandevular[i] == null) {
                                for (int j = 0; j < tekniker.getGecmisRandevular().length; j++) {
                                    if (tekniker.getGecmisRandevular()[j] != null) {
                                        this.eskiRandevular[i] = tekniker.getGecmisRandevular()[j];
                                        break;
                                    }
                                }
                                break;
                            }
                        }

                        for (int i = 0; i < this.eskiRandevular.length; i++) {
                            if (this.eskiRandevular[i] == null) {
                                for (int j = 0; j < tekniker.getGelecekRandevular().length; j++) {
                                    if (tekniker.getGelecekRandevular()[j] != null) {
                                        this.eskiRandevular[i] = tekniker.getGelecekRandevular()[j];
                                        break;
                                    }
                                }
                                break;
                            }
                        }

                        for (int i = 0; i < this.tumTeknikerler.length; i++) {
                            if (this.tumTeknikerler[i] != null) {
                                if (this.tumTeknikerler[i] == tekniker) {
                                    this.tumTeknikerler[i] = null;
                                    break;
                                }
                            }
                        }

                        System.out.println(tekniker.getAdSoyad() + " teknikeri sistemden tamamen silinmistir.");
                        tekniker.aktifRandevulariSil();
                        tekniker.gelecekRandevulariSil();
                        tekniker.gecmisRandevulariSil();
                        eskiArizalariListele();
                        eskiRandevulariListele();
                        break;
                    }
                }

            } else { //aktif randevusu olmasina ragmen aktarilacak baska tekniker yoksa silme islemi yapilamiyor.
                System.out.println("Atanabilecek baska tekniker olmadigindan silme islemi yapilamiyor.");
            }
        }
    }

    public void musteriSil(Musteri musteri) { //sistemden musteriyi siliyor.
        int aktifRandevuSayac = 0;
        int gelecekRandevuSayac = 0;
        boolean musteriMi = false;

        for (int i = 0; i < musteri.getAktifRandevular().length; i++) {
            if (musteri.getAktifRandevular()[i] != null) {
                aktifRandevuSayac++;
            }
        }
        for (int i = 0; i < musteri.getGelecekRandevular().length; i++) {
            if (musteri.getGelecekRandevular()[i] != null) {
                gelecekRandevuSayac++;
            }
        }
        if (aktifRandevuSayac == 0 && gelecekRandevuSayac == 0) { //eger musterinin gelecek ve aktif randevusu yoksa silme islemi yapiliyor.
            for (int i = 0; i < this.tumMusteriler.length; i++) {
                if (this.tumMusteriler[i] != null) {
                    if (this.tumMusteriler[i] == musteri) {
                        musteriMi = true;
                        System.out.println(musteri.getAdSoyad() + " musterisi sistemden tamamen silinmistir.");
                        this.tumMusteriler[i] = null;
                        musteri.tumGecmisRandevulariSil();
                        break;
                    }
                }
            }
            if (musteriMi == false) { //musteri sistemde yoksa sistemde bulunmadigina dair bir output gonderiliyor.
                System.out.println(musteri.getAdSoyad() + " musterisi sistemde bulunmamaktadir.");
            }

        } else { //musterinin gelecek veya aktif randevusu varsa silinemiyor.
            System.out.println(musteri.getAdSoyad() + " musterisinin " + aktifRandevuSayac + " tane aktif randevusu, "
                    + gelecekRandevuSayac + " tane gelecek randevusu bulunmaktadir."
                    + " Bu yuzden silinemez.");
        }
    }

    public void randevulariEkle(Object array[], Randevu randevu) { //arrayin null degerlerine randevu assign ediliyor.
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = randevu;
                break;
            }
        }
    }

    public void aktifRandevuEkle(Randevu randevu) { //arrayin null degerlerine aktif randevu assign ediliyor.
        randevulariEkle(tumAktifRandevular, randevu);
    }

    public void gelecekRandevuEkle(Randevu randevu) { //arrayin null degerlerine gelecek randevu assign ediliyor.
        randevulariEkle(tumGelecekRandevular, randevu);
    }

    public void randevuIptal(Randevu randevu) { //parametre olarak girilen randevu sistemden siliniyor.
        for (int i = 0; i < this.tumRandevular.length; i++) {
            if (this.tumRandevular[i] != null) {
                if (this.tumRandevular[i] == randevu) {
                    if (tarihFarki(randevu.getRandevuTarihi(), this.tarih) > 1) { //randevu gelecek tarihteyse musteri ve teknikerden ariza ucreti siliniyor.
                        System.out.println(this.tumRandevular[i].getMusteri().getAdSoyad() + " musterisinin guncel harcamasi: "
                                + this.tumRandevular[i].getMusteri().getBakiye());
                        System.out.println(this.tumRandevular[i].getTekniker().getAdSoyad() + " teknikerinin guncel bakiyesi : "
                                + this.tumRandevular[i].getTekniker().getBakiye());

                        this.tumRandevular[i].getMusteri().setBakiye(-this.tumRandevular[i].getAriza().getUcret());
                        this.tumRandevular[i].getTekniker().setBakiye(-this.tumRandevular[i].getAriza().getUcret());

                        System.out.println(this.tumRandevular[i].getRandevuTarihi() + " tarihli randevu iptal edilmistir.");
                        System.out.println(this.tumRandevular[i].getMusteri().getAdSoyad() + " musterisinin guncel harcamasi: "
                                + this.tumRandevular[i].getMusteri().getBakiye());
                        System.out.println(this.tumRandevular[i].getTekniker().getAdSoyad() + " teknikerinin guncel bakiyesi : "
                                + this.tumRandevular[i].getTekniker().getBakiye());

                        this.tumRandevular[i] = null; //randevu sistemden siliniyor.
                        break;
                    } else { //eger randevu aktifse silinemiyor ancak guncellenebiliyor.
                        System.out.println("1 gunden daha az bir sure kaldigi icin randevuyu iptal edemezsiniz ancak guncelleyebilirsiniz.");
                    }
                }
            }
        }
        for (int i = 0; i < randevu.getMusteri().getGelecekRandevular().length; i++) {
            if (randevu.getMusteri().getGelecekRandevular()[i] != null) {
                if (randevu.getMusteri().getGelecekRandevular()[i] == randevu) {
                    if (tarihFarki(randevu.getRandevuTarihi(), this.tarih) > 1) {
                        randevu.getMusteri().randevuSil(randevu); //randevu musteriden siliniyor.
                        break;
                    } else { //eger randevu aktifse silinemiyor ancak guncellenebiliyor.
                        System.out.println("1 gunden daha az bir sure kaldigi icin randevuyu iptal edeemezsiniz ancak guncelleyebilirsiniz.");
                    }
                }
            }
        }
        for (int i = 0; i < randevu.getTekniker().getGelecekRandevular().length; i++) {
            if (randevu.getTekniker().getGelecekRandevular()[i] != null) {
                if (randevu.getTekniker().getGelecekRandevular()[i] == randevu) {
                    if (tarihFarki(randevu.getRandevuTarihi(), this.tarih) > 1) {
                        randevu.getTekniker().randevuSil(randevu); //randevu teknikerden siliniyor.
                        break;
                    } else { //eger randevu aktifse silinemiyor ancak guncellenebiliyor.
                        System.out.println("1 gunden daha az bir sure kaldigi icin randevuyu iptal edeemezsiniz ancak guncelleyebilirsiniz.");
                    }
                }
            }
        }
    }

    public void randevuGuncelle(Randevu randevu, String randevuTarihi) { //randevunun tarihi parametre olarak girilen randevuTarihiyle degistiriliyor.
        int fark = tarihFarki(randevuTarihi, this.tarih);
        if (fark >= 0 && fark <= 90) { //eger randevu 3 ay icerisindeyse degisim islemi yapilabiliyor.
            System.out.print(randevu.getRandevuTarihi() + " tarihli randevu ");
            randevu.setRandevuTarihi(randevuTarihi);
            System.out.println(randevuTarihi + " tarihine alinmistir.");
            System.out.println(randevu.randevuBilgi());
        } else {
            System.out.println("Lutfen gecerli bir tarih giriniz. (0-90)");
        }
    }

    public void randevuEkle(Randevu randevu) {
        randevulariEkle(tumRandevular, randevu);
    }

    public void randevuYazdir() {
        for (int i = 0; i < this.tumRandevular.length; i++) {
            if (this.tumRandevular[i] != null) {
                System.out.println(this.tumRandevular[i].randevuBilgi());
            }
        }
    }

    public void teknikerEkle(Tekniker tekniker) {
        for (int i = 0; i < this.tumTeknikerler.length; i++) {
            if (this.tumTeknikerler[i] == null) {
                this.tumTeknikerler[i] = tekniker;
                break;
            }
        }
    }

    public void arizaListele(Tekniker tekniker) {
        int numara = 1;
        System.out.println(tekniker.getAdSoyad() + " teknikerinin aktif arizalari: ");
        for (int i = 0; i < tekniker.getAktifRandevular().length; i++) {
            if (tekniker.getAktifRandevular()[i] != null) {
                System.out.println(numara + ") " + tekniker.getAktifRandevular()[i].getMusteri().getArac().aracBilgi());
                numara++;
            }
        }
    }

    public char tumHarfleriKucult(char harf) { //girilen harf degeri ASCII table'ye gore kucultuluyor.
        char yeniKelime = 0;
        if (harf >= 65 && harf <= 90 || harf >= 97 && harf <= 122) {
            if (harf >= 65 && harf <= 90) {
                yeniKelime = (char) (harf + 32);
            } else {
                yeniKelime = (char) harf;
            }
        }
        return yeniKelime;
    }

    public void musterininTumRandevulariniListele(Musteri musteri) { //girilen musterinin gecmis,aktif ve gelecek randevulari listeleniyor.
        guncelle();
        int numara1 = 1;
        int numara2 = 1;
        int numara3 = 1;

        System.out.println(musteri.getAdSoyad() + " musterisinin tum randevulari: \n");
        System.out.println(musteri.getAdSoyad() + " gecmis randevulari: ");
        for (int i = 0; i < musteri.getGecmisRandevular().length; i++) {
            if (musteri.getGecmisRandevular()[i] != null) {
                System.out.println(numara1 + ") " + musteri.getGecmisRandevular()[i].randevuBilgi());
                numara1++;
            }
        }
        System.out.println(musteri.getAdSoyad() + " aktif randevulari: ");
        for (int i = 0; i < musteri.getAktifRandevular().length; i++) {
            if (musteri.getAktifRandevular()[i] != null) {
                System.out.println(numara2 + ") " + musteri.getAktifRandevular()[i].randevuBilgi());
                numara2++;
            }
        }
        System.out.println(musteri.getAdSoyad() + " gelecek randevulari: ");
        for (int i = 0; i < musteri.getGelecekRandevular().length; i++) {
            if (musteri.getGelecekRandevular()[i] != null) {
                System.out.println(numara3 + ") " + musteri.getGelecekRandevular()[i].randevuBilgi());
                numara3++;
            }
        }
    }

    public void guncelle() { //sistemde degistirilen tarih tum musterilerin ve tum teknikerlerin randevularina etki ediyor.
        for (int i = 0; i < this.tumMusteriler.length; i++) {
            if (this.tumMusteriler[i] != null) {
                this.tumMusteriler[i].guncelle(this);
            }
        }
        for (int i = 0; i < this.tumTeknikerler.length; i++) {
            if (this.tumTeknikerler[i] != null) {
                this.tumTeknikerler[i].guncelle(this);
            }
        }
    }

    public void ortKazancVeTamirSuresi() { //teknikerlerin ortalama kazanci ve ortalama tamir suresi hesaplaniyor.
        int toplam = 0;
        int teknikerSayisi = 0;
        for (int i = 0; i < this.tumTeknikerler.length; i++) {
            if (this.tumTeknikerler[i] != null) {
                teknikerSayisi++;
                toplam += this.tumTeknikerler[i].hasilatHesapla();
            }
        }
        if (teknikerSayisi > 0) {
            System.out.println("Ortalama kazanc: " + toplam / teknikerSayisi + " TL'dir");
        } else { //teknikerSayisi 0 ise 1/0 erroru verilmesin diye output yaziliyor.
            System.out.println("Ortalama kazanc: 0");
        }

        double gun = 0;
        int randevuSayisi = 0;

        for (int i = 0; i < this.tumRandevular.length; i++) {
            if (this.tumRandevular[i] != null) {
                gun += this.tumRandevular[i].getMusteri().getArac().getAriza().getTamirSuresi();
                randevuSayisi++;
            }
        }

        if (randevuSayisi > 0) {
            System.out.println("Ortalama tamir suresi: " + gun / randevuSayisi + " gundur");
        } else { //randevuSayisi 0 ise 1/0 erroru verilmesin diye output yaziliyor.
            System.out.println("Ortlama tamir suresi: 0");
        }
    }

    public void farkliTumArizalar() { //alinmis tum arizalari listeliyor.
        int numara = 1;
        System.out.println("Simdiye kadar alinan tum farkli arizalar: ");
        for (int i = 0; i < this.tumArizalar.length; i++) {
            if (this.tumArizalar[i] != null) {
                System.out.println(numara + ") " + this.tumArizalar[i].getTur());
                numara++;
            }
        }
    }

    public void enCokOdemeYapanMusteri() { //tum musterileri dolasip en cok harcama yapani hesapliyor.
        double max = 0;
        int maxIndex = 0;
        int numara = 1;

        for (int i = 0; i < this.tumMusteriler.length; i++) {
            if (this.tumMusteriler[i] != null) {
                if (this.tumMusteriler[i].getBakiye() > max) {
                    max = this.tumMusteriler[i].getBakiye();
                    maxIndex = i;
                }
            }
        }
        if (this.tumMusteriler[maxIndex] != null) {
            System.out.println("En cok odeme yapan musteri olan " + this.tumMusteriler[maxIndex].getAdSoyad() + "'in harcamasi: " + tumMusteriler[maxIndex].getBakiye() + " TL'dir ");
        }
        for (int i = 0; i < tumRandevular.length; i++) {
            if (tumRandevular[i] != null && tumRandevular[i].getMusteri().equals(this.tumMusteriler[maxIndex])) {
                System.out.println(numara + ") " + tumRandevular[i].randevuBilgi());
                numara++;
            }
        }
    }

    public void enCokHasilatiYapanTekniker() { //tum teknikerleri dolasip en cok hasilat elde edeni hesapliyor.
        double max = 0;
        int maxIndex = 0;

        for (int i = 0; i < this.tumTeknikerler.length; i++) {
            if (this.tumTeknikerler[i] != null) {
                if (this.tumTeknikerler[i].hasilatHesapla() > max) {
                    max = this.tumTeknikerler[i].hasilatHesapla();
                    maxIndex = i;
                }
            }
        }
        if (this.tumTeknikerler[maxIndex] != null) {
            System.out.println("En cok hasilati yapan tekniker: \n" + this.tumTeknikerler[maxIndex].getAdSoyad()
                    + " teknikeri " + this.tumTeknikerler[maxIndex].hasilatHesapla() + " TL hasilat yapmistir.");
        }
    }

    public void enCokRandevusuOlanMusteri() { //tum musterileri dolasip en cok randevusu olani hesapliyor.
        int max = 0;
        int maxIndex = 0;
        int numara = 1;

        for (int i = 0; i < this.tumMusteriler.length; i++) {
            if (this.tumMusteriler[i] != null) {
                if (this.tumMusteriler[i].tumRandevularToplam() >= max) {
                    max = this.tumMusteriler[i].tumRandevularToplam();
                    maxIndex = i;
                }
            }
        }
        System.out.println("En cok randevusu olan musterinin randevu bilgileri: (" + this.tumMusteriler[maxIndex].getAdSoyad() + ")");

        for (int i = 0; i < this.tumRandevular.length; i++) {
            if (this.tumRandevular[i] != null && tumRandevular[i].getMusteri().equals(this.tumMusteriler[maxIndex])) {
                System.out.println(numara + ") " + this.tumRandevular[i].randevuBilgi());
                numara++;
            }
        }
    }

    public void musteriAramasi(String text) { //girilen harfle tum musteriler listesindeki bir kisinin ismindeki harf eslesirse kisiyi yazdiriyor.
        int numara = 1;
        int index = 0;

        System.out.println("Icerisinde \"" + text + "\" harfi bulunan musterilerin gecmis ve gelecek randevulari: \n");
        if (text.length() == 1) {
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < this.tumMusteriler.length; j++) {
                    if (this.tumMusteriler[j] != null) {
                        for (int k = 0; k < this.tumMusteriler[j].getAdSoyad().length(); k++) {
                            if (tumHarfleriKucult(text.charAt(i)) == tumHarfleriKucult(this.tumMusteriler[j].getAdSoyad().charAt(k))) {
                                for (int l = 0; l < tumMusteriler.length; l++) {
                                    if (tumMusteriler[i] != null) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (j > index) {
                                    System.out.println();
                                }
                                System.out.println(numara + ") " + this.tumMusteriler[j].getAdSoyad());
                                System.out.println(this.tumMusteriler[j].getAdSoyad() + "'in gecmis randevulari: ");
                                for (int l = 0; l < this.tumMusteriler[j].getGecmisRandevular().length; l++) {
                                    if (this.tumMusteriler[j].getGecmisRandevular()[l] != null) {
                                        System.out.println(this.tumMusteriler[j].getGecmisRandevular()[l].randevuBilgi());
                                    }
                                }
                                System.out.println();
                                System.out.println(this.tumMusteriler[j].getAdSoyad() + "'in gelecek randevulari: ");
                                for (int l = 0; l < this.tumMusteriler[j].getGelecekRandevular().length; l++) {
                                    if (this.tumMusteriler[j].getGelecekRandevular()[l] != null) {
                                        System.out.println(this.tumMusteriler[j].getGelecekRandevular()[l].randevuBilgi());
                                    }
                                }
                                numara++;
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Cumle yerine kelime giriniz.");
        }
    }

    public int tarihFarki(String randevuTarihi, String tarih) { //tarih ile randevuTarihi arasindaki gun farkini hesapliyor.
        String gun1 = "";
        String ay1 = "";
        String yil1 = "";
        String gun2 = "";
        String ay2 = "";
        String yil2 = "";

        int sayac = 0;
        for (int i = 0; i < tarih.length(); i++) { //stringi "." lara gore ayirip bos stringlere assign ediyor.
            if (tarih.charAt(i) != '.' && sayac < 2) {
                sayac++;
                gun1 += tarih.charAt(i);
            } else if (tarih.charAt(i) != '.' && sayac < 4) {
                sayac++;
                ay1 += tarih.charAt(i);
            } else if (tarih.charAt(i) != '.' && sayac < 8) {
                sayac++;
                yil1 += tarih.charAt(i);
            }
        }
        sayac = 0;
        for (int i = 0; i < randevuTarihi.length(); i++) { //stringi "." lara gore ayirip bos stringlere assign ediyor.
            if (randevuTarihi.charAt(i) != '.' && sayac < 2) {
                sayac++;
                gun2 += randevuTarihi.charAt(i);
            } else if (randevuTarihi.charAt(i) != '.' && sayac < 4) {
                sayac++;
                ay2 += randevuTarihi.charAt(i);
            } else if (randevuTarihi.charAt(i) != '.' && sayac < 8) {
                sayac++;
                yil2 += randevuTarihi.charAt(i);
            }
        }

        int fark = ((Integer.parseInt(yil2) - Integer.parseInt(yil1)) * 365)
                + ((Integer.parseInt(ay2) - Integer.parseInt(ay1)) * 30)
                + ((Integer.parseInt(gun2) - Integer.parseInt(gun1)));
        return fark;
    }
}
