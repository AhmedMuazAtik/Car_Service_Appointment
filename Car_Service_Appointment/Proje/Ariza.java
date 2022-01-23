/**
 * @file Project
 * @description Herhangi bir sanayide, teknikerin musteriyle olan randevu
 * baglantisini kuruyor.
 * @assignment 2.Proje
 * @date 20/01/2022
 * @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
 */
package project;

public class Ariza {

    private double ucret;
    private String tur;
    private double tamirSuresi;

    public Ariza(String tur, double ucret, double tamirSuresi) {
        this.tur = tur;
        this.ucret = ucret;
        this.tamirSuresi = tamirSuresi;
    }

    public double getUcret() {
        return ucret;
    }

    public String getTur() {
        return tur;
    }

    public double getTamirSuresi() {
        return tamirSuresi;
    }

}
