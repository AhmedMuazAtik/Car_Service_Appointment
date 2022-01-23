/**
 * @file Project
 * @description Herhangi bir sanayide, teknikerin musteriyle olan randevu
 * baglantisini kuruyor.
 * @assignment 2.Proje
 * @date 20/01/2022
 * @author Ahmed Muaz Atik - ahmedmuaz.atik@stu.fsm.edu.tr
 */
package project;

public class Arac {

    private Ariza ariza;
    private String renk;
    private String marka;
    private String model;

    public Arac(Ariza ariza, String renk, String marka, String model) {
        this.ariza = ariza;
        this.renk = renk;
        this.marka = marka;
        this.model = model;
    }

    public String aracBilgi() {
        return this.renk + " renkli " + this.marka + " " + this.model + " aracinin "
                + this.ariza.getTur() + " arizasi vardir. Ucreti " + this.ariza.getUcret()
                + " TL'dir.";
    }

    public Ariza getAriza() {
        return ariza;
    }

    public String getMarka() {
        return marka;
    }

    public String getRenk() {
        return renk;
    }

    public String getModel() {
        return model;
    }

}
