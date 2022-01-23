/**
* @file Project
* @description Herhangi bir sanayide, teknikerin musteriyle olan randevu baglantisini kuruyor.
* @assignment 2.Proje
* @date 20/01/2022
* @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
*/
package project;

public class Insan {

    private String adSoyad;
    private double bakiye;

    public Insan(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye += bakiye;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

}
