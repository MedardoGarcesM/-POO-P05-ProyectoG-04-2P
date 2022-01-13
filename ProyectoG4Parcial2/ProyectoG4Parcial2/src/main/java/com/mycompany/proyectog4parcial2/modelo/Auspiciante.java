package com.mycompany.proyectog4parcial2.modelo;

/**
 *
 * @author majo_
 */
import Main.Sistema;
import static Main.Sistema.auspiciantes;

public class Auspiciante extends Sistema {

    private String nombreA;
    private String direccionA;
    private String telefonoA;
    private String ciudadA;
    private String emailA;
    private String webPage;
    private String codigoA;//

    //constructores
    public Auspiciante(String nombreA, String direccionA, String telefonoA, String ciudadA, String emailA, String webPage, String codigoA) {
        this.nombreA = nombreA;
        this.direccionA = direccionA;
        this.telefonoA = telefonoA;
        this.ciudadA = ciudadA;
        this.emailA = emailA;
        this.webPage = webPage;
        this.codigoA = codigoA;
    }

    //getters
    public String getNombreA() {
        return nombreA;
    }

    public String getDireccionA() {
        return direccionA;
    }

    public String getTelefonoA() {
        return telefonoA;
    }

    public String getCiudadA() {
        return ciudadA;
    }

    public String getEmailA() {
        return emailA;
    }

    public String getCodigoA() {
        return codigoA;
    }

    public String getWebPage() {
        return webPage;
    }

    public void mostrarListaAuspiciante() {
        System.out.println("\nLista de auspiciantes agregados: \n");
        for (Auspiciante a : auspiciantes) {
            System.out.println(" " + a.nombreA );
        }
        System.out.println();
    }

  

}
