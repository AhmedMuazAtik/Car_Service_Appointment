/**
 * @file Project
 * @description Herhangi bir sanayide, teknikerin musteriyle olan randevu
 * baglantisini kuruyor.
 * @assignment 2.Proje
 * @date 20/01/2022
 * @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
 */
package project;

public class Randevu {

    private Ariza ariza;
    private Musteri musteri;
    private Tekniker tekniker;
    private String randevuTarihi;

    public Randevu(Musteri musteri, Tekniker tekniker, String randevuTarihi) {
        try {
            this.randevuTarihi = randevuTarihi;
            this.musteri = musteri;
            this.tekniker = tekniker;
            this.ariza = musteri.getArac().getAriza();

            Sanayi s1 = tekniker.getS1();
            String tarih = tekniker.getS1().getTarih();

            if (tekniker.tarihDogruMu(randevuTarihi)) {
                int fark = s1.tarihFarki(randevuTarihi, tarih);
                if (fark >= 0) {
                    if (fark <= 90) {
                        if (tekniker.teknikerMusaitMi(randevuTarihi)) {

                            tekniker.setBakiye(musteri.getArac().getAriza().getUcret());
                            musteri.setBakiye(musteri.getArac().getAriza().getUcret());

                            listeyeEkle(musteri.getArac().getAriza(), s1);
                            listeyeEkle(musteri, s1);
                            listeyeEkle(tekniker, s1);

                            if (fark == 0) {
                                System.out.println(musteri.getAdSoyad() + " musterisi " + tekniker.getAdSoyad() + " teknikerine " + getRandevuTarihi() + " tarihine randevu almistir.");
                                tekniker.aktifRandevuEkle(this);
                                musteri.aktifRandevuEkle(this);
                                s1.aktifRandevuEkle(this);
                                s1.randevuEkle(this);
                            } else {
                                System.out.println(musteri.getAdSoyad() + " musterisi " + tekniker.getAdSoyad() + " teknikerine " + getRandevuTarihi() + " tarihine randevu almistir.");
                                tekniker.gelecekRandevuEkle(this, ariza);
                                musteri.gelecekRandevuEkle(this);
                                s1.gelecekRandevuEkle(this);
                                s1.randevuEkle(this);
                            }
                        }
                    } else {
                        System.out.println(randevuTarihi + " tarihi " + tarih + " tarihinden 90 gun fazla olamaz.");
                    }
                } else { //gecmis tarih
                    System.out.println(musteri.getAdSoyad() + " musterisi " + randevuTarihi + " gecmis tarihine randevu alamaz."
                    );

                }
            }
        } catch (java.lang.NumberFormatException e) { //eger sayiya cevrilecek olan stringe harf girerse hata mesaji veriliyor.
            for (int i = 0; i < randevuTarihi.length(); i++) {
                if (!(((int) randevuTarihi.charAt(i) >= 65 && (int) randevuTarihi.charAt(i) <= 90) || ((int) randevuTarihi.charAt(i) >= 97 && (int) randevuTarihi.charAt(i) <= 122))) {
                    System.out.println("(" + randevuTarihi + ") Tarihe harf giremezsiniz.");
                    break;
                }
            }
        }

    }

    public Ariza getAriza() {
        return ariza;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public String getRandevuTarihi() {
        return randevuTarihi;
    }

    public Tekniker getTekniker() {
        return tekniker;
    }

    public void setRandevuTarihi(String randevuTarihi) {
        this.randevuTarihi = randevuTarihi;
    }

    public String randevuBilgi() {
        return musteri.musteriYazdir() + " " + this.tekniker.getAdSoyad() + " teknikeri ile " + this.randevuTarihi + " tarihinde randevusu vardir.";
    }

    public void listeyeEkle(Ariza ariza, Sanayi s1) { //girilen ariza belirli bir sanayideki tumArizalar listesine ekleniyor.
        int sayac = 0;

        for (int i = 0; i < s1.getTumArizalar().length; i++) {
            if (ariza == s1.getTumArizalar()[i]) {
                sayac++;
            }
        }
        for (int i = 0; i < s1.getTumArizalar().length; i++) {
            if (sayac == 0) {
                if (s1.getTumArizalar()[i] == null) {
                    s1.getTumArizalar()[i] = ariza;
                    break;
                }
            }
        }
    }

    public void listeyeEkle(Musteri musteri, Sanayi s1) { //girilen musteri belirli bir sanayideki tumArizalar listesine ekleniyor.
        int sayac = 0;
        for (int i = 0; i < s1.getTumMusteriler().length; i++) {
            if (s1.getTumMusteriler()[i] == musteri) {
                sayac++;
            }
        }
        if (sayac == 0) {
            for (int i = 0; i < s1.getTumMusteriler().length; i++) {
                if (s1.getTumMusteriler()[i] == null) {
                    s1.getTumMusteriler()[i] = musteri;
                    break;
                }
            }
        }
    }

    public void listeyeEkle(Tekniker tekniker, Sanayi s1) { //girilen tekniker belirli bir sanayideki tumArizalar listesine ekleniyor.
        int sayac = 0;
        for (int i = 0; i < s1.getTumTeknikerler().length; i++) {
            if (s1.getTumTeknikerler()[i] == tekniker) {
                sayac++;
            }
        }
        if (sayac == 0) {
            for (int i = 0; i < s1.getTumTeknikerler().length; i++) {
                if (s1.getTumTeknikerler()[i] == null) {
                    s1.getTumTeknikerler()[i] = tekniker;
                    break;
                }
            }
        }
    }
}
