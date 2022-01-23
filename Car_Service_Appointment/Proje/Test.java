package project;

public class Test {

    public static void main(String[] args) {

        Sanayi s1 = new Sanayi("02.01.2010"); //Gun/Ay/Yil 
        Sanayi s2 = new Sanayi("02.01.2013"); //Gun/Ay/Yil

        Ariza arz1 = new Ariza("Kaza", 1500, 3);
        Ariza arz2 = new Ariza("Rot Balans", 2000, 9);
        Ariza arz3 = new Ariza("Kaporta", 3000, 7);
        Ariza arz4 = new Ariza("Elektrik Aksami", 4500, 8);
        Ariza arz5 = new Ariza("Motor", 8000, 5);

        Arac a1 = new Arac(arz1, "Kirmizi", "Mercedes", "Benz");
        Arac a2 = new Arac(arz2, "Siyah", "BMW", "320d");
        Arac a3 = new Arac(arz3, "Beyaz", "Ford", "Focus");
        Arac a4 = new Arac(arz4, "Yesil", "BMW", "M3");
        Arac a5 = new Arac(arz5, "Sari", "Audi", "A8");

        Tekniker t1 = new Tekniker("Ali", s1);
        Tekniker t2 = new Tekniker("Murat", s1);
        Tekniker t3 = new Tekniker("Can", s2);
        Tekniker t4 = new Tekniker("Cem", s2);
        Tekniker t5 = new Tekniker("Cenk", s1);

        Musteri m1 = new Musteri("Ahmet", a1);
        Musteri m2 = new Musteri("Mehmet", a2);
        Musteri m3 = new Musteri("Baki", a3);
        Musteri m4 = new Musteri("Mert", a4);
        Musteri m5 = new Musteri("Berk", a5);

        Randevu r1 = new Randevu(m1, t5, "30.01.200s");
        Randevu r2 = new Randevu(m2, t4, "02.01.2010");
        Randevu r3 = new Randevu(m3, t3, "01.03.2010");
        Randevu r4 = new Randevu(m4, t2, "02.01.2010");
        Randevu r5 = new Randevu(m5, t1, "02.02.2010");

        System.out.println("-------------------- 1  -------------------------");
        s1.arizaListele(t2);

        System.out.println("-------------------- 2  -------------------------");
        s1.farkliTumArizalar();

        System.out.println("-------------------- 3  -------------------------");
        s1.enCokRandevusuOlanMusteri();

        System.out.println("-------------------- 4  -------------------------");
        s1.musterininTumRandevulariniListele(m5);

        System.out.println("-------------------- 5  -------------------------");
        s1.randevuIptal(r2);
        s1.setTarih("01.05.2016");
        s1.randevuGuncelle(r3, "01.06.2016");

        System.out.println("-------------------- 6  -------------------------");
        s1.musteriAramasi("e");

        System.out.println("-------------------- 7  -------------------------");
        s1.musteriSil(m5);

        System.out.println("-------------------- 8  -------------------------");
        s1.teknikerSil(t3);

        System.out.println("-------------------- 9  -------------------------");
        s1.enCokHasilatiYapanTekniker();

        System.out.println("-------------------- 10 -------------------------");
        s1.enCokOdemeYapanMusteri();

        System.out.println("-------------------- 11 -------------------------");
        s1.ortKazancVeTamirSuresi();

        System.out.println("-------------------- 12 -------------------------");
        s1.cozulmusRandevulariListele(t1, "03.03.2008", "04.05.2018");

        System.out.println("-------------------- 13 -------------------------");
        s1.cozulmemisRandevulariListele(t2, "02.04.2007", "02.10.2018");

        System.out.println("-------------------- 14  -------------------------");
        System.out.println("Teknikerin bahsisten onceki bakiyesi: " + t2.getBakiye());
        System.out.println("Musterinin bahsisten onceki harcamasi: " + m2.getBakiye());
        s1.bahsisVer(m2, t2, 200);
        System.out.println("Teknikerin bahsisten sonraki bakiyesi: " + t2.getBakiye());
        System.out.println("Musterinin bahsisten sonraki harcamasi: " + m2.getBakiye());
    }
}
