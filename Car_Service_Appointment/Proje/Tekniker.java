/**
 * @file Project
 * @description Herhangi bir sanayide, teknikerin musteriyle olan randevu
 * baglantisini kuruyor.
 * @assignment 2.Proje
 * @date 20/01/2022
 * @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
 */
package project;

public class Tekniker extends Insan {

    private Ariza[] arizaListesi;
    private Randevu[] cozumlenmisRandevular;
    private Randevu[] gecmisRandevular;
    private Randevu[] aktifRandevular;
    private Randevu[] gelecekRandevular;
    private Sanayi s1;

    public Tekniker(String adSoyad, Sanayi s1) {
        super(adSoyad);
        s1.teknikerEkle(this);
        this.s1 = s1;
        aktifRandevular = new Randevu[20];
        gelecekRandevular = new Randevu[20];
        arizaListesi = new Ariza[10];
        cozumlenmisRandevular = new Randevu[20];
        gecmisRandevular = new Randevu[20];
    }

    @Override
    public void setAdSoyad(String adSoyad) {
        super.setAdSoyad(adSoyad); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBakiye() {
        return super.getBakiye(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAdSoyad() {
        return super.getAdSoyad(); //To change body of generated methods, choose Tools | Templates.
    }

    public Randevu[] getAktifRandevular() {
        return aktifRandevular;
    }

    public Randevu[] getGelecekRandevular() {
        return gelecekRandevular;
    }

    public Ariza[] getArizaListesi() {
        return arizaListesi;
    }

    public Randevu[] getCozumlenmisRandevular() {
        return cozumlenmisRandevular;
    }

    public Randevu[] getGecmisRandevular() {
        return gecmisRandevular;
    }

    public Sanayi getS1() {
        return s1;
    }

    public void guncelle(Sanayi s1) {
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

    public void randevulariSil(Object array[]) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                array[i] = null;
            }
        }
    }

    public void randevuSil(Randevu randevu) {
        for (int i = 0; i < gelecekRandevular.length; i++) {
            if (gelecekRandevular[i] != null) {
                if (gelecekRandevular[i] == randevu) {
                    gelecekRandevular[i] = null;
                    break;
                }
            }
        }
    }

    public void gelecekRandevulariSil() {
        randevulariSil(gelecekRandevular);
    }

    public void gecmisRandevulariSil() {
        randevulariSil(gecmisRandevular);
    }

    public void aktifRandevulariSil() {
        randevulariSil(aktifRandevular);
    }

    public void aktifRandevuDegistir(Tekniker tekniker) {
        for (int i = 0; i < this.aktifRandevular.length; i++) {
            if (tekniker.aktifRandevular[i] != null) {
                this.aktifRandevular[i] = tekniker.aktifRandevular[i];
            }
        }
    }

    public boolean teknikerMusaitMi(String randevuTarihi) { //teknikerin girilen randevuTarihinde baska randevusunun olup olmadigina bakiliyor.
        for (int i = 0; i < gelecekRandevular.length; i++) {
            if (gelecekRandevular[i] != null) {
                if (gelecekRandevular[i].getRandevuTarihi() == randevuTarihi) {
                    System.out.println(this.getAdSoyad() + " teknikerinin " + randevuTarihi + " tarihinde baska bir randevusu vardir.");
                    return false;
                }
            }
        }

        for (int i = 0; i < aktifRandevular.length; i++) {
            if (aktifRandevular[i] != null) {
                if (aktifRandevular[i].getRandevuTarihi() == randevuTarihi) {
                    System.out.println(this.getAdSoyad() + " teknikerinin " + randevuTarihi + " tarihinde baska bir randevusu vardir.");
                    return false;
                }
            }
        }
        return true;
    }

    public void randevuEkle(Object array[], Randevu randevu) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = randevu;
                break;
            }
        }
    }

    public int hasilatHesapla() { //teknikerin gecmis, aktif ve gelecek randevulardan elde ettigi hasilati hesapliyor.
        int toplam = 0;
        for (int i = 0; i < gelecekRandevular.length; i++) {
            if (gelecekRandevular[i] != null) {
                toplam += gelecekRandevular[i].getAriza().getUcret();
            }
        }
        for (int i = 0; i < aktifRandevular.length; i++) {
            if (aktifRandevular[i] != null) {
                toplam += aktifRandevular[i].getAriza().getUcret();
            }
        }
        for (int i = 0; i < gecmisRandevular.length; i++) {
            if (gecmisRandevular[i] != null) {
                toplam += gecmisRandevular[i].getAriza().getUcret();
            }
        }
        return toplam;
    }

    public void aktifRandevuEkle(Randevu randevu) {
        randevuEkle(aktifRandevular, randevu);
    }

    public void gelecekRandevuEkle(Randevu randevu, Ariza ariza) {
        randevuEkle(gelecekRandevular, randevu);
    }

    public boolean tarihDogruMu(String randevuTarihi) { //girilen tarihin normale aykiri olup olmadigina bakiliyor.
        String gun1 = "";
        String ay1 = "";
        String yil1 = "";

        int counter = 0;
        for (int i = 0; i < randevuTarihi.length(); i++) {
            if (randevuTarihi.charAt(i) != '.' && counter < 2) {
                counter++;
                gun1 += randevuTarihi.charAt(i);
            } else if (randevuTarihi.charAt(i) != '.' && counter < 4) {
                counter++;
                ay1 += randevuTarihi.charAt(i);
            } else if (randevuTarihi.charAt(i) != '.' && counter < 8) {
                counter++;
                yil1 += randevuTarihi.charAt(i);
            }
        }
        if (!(Integer.parseInt(gun1) >= 0 && Integer.parseInt(gun1) <= 30
                && Integer.parseInt(ay1) >= 0 && Integer.parseInt(ay1) <= 12
                && Integer.parseInt(yil1) >= 2000 && Integer.parseInt(yil1) <= 2100)) {
            System.out.println("Lutfen gecerli bir gun veya ay giriniz. (0-30) (0-12) (2000-2100)");
            return false;
        }
        return true;

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
        for (int i = 0; i < randevuTarihi.length(); i++) { //stringi "." lara gore ayirip bos stringlere assign ediyor. 
            if (randevuTarihi.charAt(i) != '.' && sayac < 2) {
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
