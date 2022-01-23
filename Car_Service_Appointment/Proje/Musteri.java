/**
 * @file Project
 * @description Herhangi bir sanayide, teknikerin musteriyle olan randevu
 * baglantisini kuruyor.
 * @assignment 2.Proje
 * @date 20/01/2022
 * @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
 */
package project;

public class Musteri extends Insan {

    private Arac arac;
    private Randevu[] gecmisRandevular;
    private Randevu[] aktifRandevular;
    private Randevu[] gelecekRandevular;

    public Musteri(String adSoyad, Arac arac) {
        super(adSoyad);
        this.arac = arac;
        gecmisRandevular = new Randevu[20];
        aktifRandevular = new Randevu[10];
        gelecekRandevular = new Randevu[10];
    }

    @Override
    public double getBakiye() {
        return super.getBakiye(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAdSoyad() {
        return super.getAdSoyad(); //To change body of generated methods, choose Tools | Templates.
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public Randevu[] getAktifRandevular() {
        return aktifRandevular;
    }

    public Randevu[] getGecmisRandevular() {
        return gecmisRandevular;
    }

    public Randevu[] getGelecekRandevular() {
        return gelecekRandevular;
    }

    public void setGecmisRandevular(Randevu[] gecmisRandevular) {
        this.gecmisRandevular = gecmisRandevular;
    }

    public String musteriYazdir() {
        return getAdSoyad() + " musterisinin, " + getArac().getRenk() + " " + getArac().getMarka() + " " + getArac().getModel() + " aracinin " + getArac().getAriza().getTur() + " arizasiyla alakali";
    }

    public void guncelle(Sanayi s1) { //girilen sanayideki tarih degisikligine gore musterinin randevulari guncelleniyor.
        for (int i = 0; i < this.gelecekRandevular.length; i++) {
            if (this.gelecekRandevular[i] != null) {
                if (tarihFarki(this.gelecekRandevular[i].getRandevuTarihi(), s1.getTarih()) < 0) {
                    for (int j = 0; j < this.gecmisRandevular.length; j++) {
                        if (this.gecmisRandevular[j] == null) {
                            this.gecmisRandevular[j] = this.gelecekRandevular[i];
                            this.gelecekRandevular[i] = null;
                            break;
                        }
                    }
                } else if (tarihFarki(this.gelecekRandevular[i].getRandevuTarihi(), s1.getTarih()) == 0) {
                    for (int j = 0; j < this.aktifRandevular.length; j++) {
                        if (this.aktifRandevular[j] == null) {
                            this.aktifRandevular[j] = this.gelecekRandevular[i];
                            this.gelecekRandevular[i] = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    public int tumRandevularToplam() { //musterinin toplam randevu sayisi hesaplaniyor.
        int toplam = 0;
        for (int i = 0; i < gelecekRandevular.length; i++) {
            if (gelecekRandevular[i] != null) {
                toplam++;
            }
        }
        for (int i = 0; i < gecmisRandevular.length; i++) {
            if (gecmisRandevular[i] != null) {
                toplam++;
            }
        }
        for (int i = 0; i < aktifRandevular.length; i++) {
            if (aktifRandevular[i] != null) {
                toplam++;
            }
        }
        return toplam;
    }

    public void randevulariSil(Object array[]) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                array[i] = null;
            }
        }
    }

    public void randevuSil(Randevu randevu) { //parametre olarak girilen randevuyu gelecek randevular listesinden siliyor.
        for (int i = 0; i < gelecekRandevular.length; i++) {
            if (gelecekRandevular[i] != null) {
                if (gelecekRandevular[i] == randevu) {
                    gelecekRandevular[i] = null;
                    break;
                }
            }
        }
    }

    public void tumGecmisRandevulariSil() { //musterinin tum gecmis randevularini siliyor.
        randevulariSil(gecmisRandevular);
    }

    public void randevuEkle(Object array[], Randevu randevu) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = randevu;
                break;
            }
        }
    }

    public void aktifRandevuEkle(Randevu randevu) {
        randevuEkle(aktifRandevular, randevu);
    }

    public void gelecekRandevuEkle(Randevu randevu) {
        randevuEkle(gelecekRandevular, randevu);
    }

    public void gecmisRandevulariListele() {
        for (int i = 0; i < gecmisRandevular.length; i++) {
            if (gecmisRandevular[i] != null) {
                System.out.println(gecmisRandevular[i].randevuBilgi());
            }
        }
    }

    public void aktifRandevulariListele() {
        for (int i = 0; i < aktifRandevular.length; i++) {
            if (aktifRandevular[i] != null) {
                System.out.println(aktifRandevular[i].randevuBilgi());
            }
        }
    }

    public void gelecekRandevulariListele() {
        for (int i = 0; i < gelecekRandevular.length; i++) {
            if (gelecekRandevular[i] != null) {
                System.out.println(gelecekRandevular[i].randevuBilgi());
            }
        }
    }
 
    public int tarihFarki(String randevuTarihi, String tarih) { //tarih ile randevuTarihi arasindaki gun farkini hesapliyor.
        String tarihGun = "";
        String tarihAy = "";
        String tarihYil = "";
        String randevuTarihiGun = "";
        String randevuTarihiAy = "";
        String randevuTarihiYil = "";

        int sayac = 0;
        for (int i = 0; i < tarih.length(); i++) { //stringi "." lara gore ayirip bos stringlere assign ediyor. 
            if (tarih.charAt(i) != '.' && sayac < 2) {
                sayac++;
                tarihGun += tarih.charAt(i);
            } else if (tarih.charAt(i) != '.' && sayac < 4) {
                sayac++;
                tarihAy += tarih.charAt(i);
            } else if (tarih.charAt(i) != '.' && sayac < 8) {
                sayac++;
                tarihYil += tarih.charAt(i);
            }
        }
        sayac = 0;
        for (int i = 0; i < randevuTarihi.length(); i++) {
            if (randevuTarihi.charAt(i) != '.' && sayac < 2) { //stringi "." lara gore ayirip bos stringlere assign ediyor.
                sayac++;
                randevuTarihiGun += randevuTarihi.charAt(i);
            } else if (randevuTarihi.charAt(i) != '.' && sayac < 4) {
                sayac++;
                randevuTarihiAy += randevuTarihi.charAt(i);
            } else if (randevuTarihi.charAt(i) != '.' && sayac < 8) {
                sayac++;
                randevuTarihiYil += randevuTarihi.charAt(i);
            }
        }
        int fark = ((Integer.parseInt(randevuTarihiYil) - Integer.parseInt(tarihYil)) * 365)
                + ((Integer.parseInt(randevuTarihiAy) - Integer.parseInt(tarihAy)) * 30)
                + ((Integer.parseInt(randevuTarihiGun) - Integer.parseInt(tarihGun)));
        return fark;
    }

}
