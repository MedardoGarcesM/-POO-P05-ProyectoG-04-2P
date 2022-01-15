package com.mycompany.proyectog4parcial2.modelo;

/**
 *
 * @author majo_
 */
import Main.Sistema;
import static Main.Sistema.auspiciantes;
import java.util.ArrayList;

public class Auspiciante extends Sistema {

    private String nombreA;
    private String direccionA;
    private String telefonoA;
    private String ciudadA;
    private String emailA;
    private String webPage;
    private String codigoA;//
public static int idausp=1;

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
    public static ArrayList<Auspiciante> generarAus(){
        
    
    ArrayList<Auspiciante> auspiciantes = new ArrayList<Auspiciante>();
        auspiciantes.add(new Auspiciante("Dog Chow", "Via daule", "2390832", "Guayaquil", "info@dogchow.ec", "www.dogchow.ec", "A-" + idausp++));
        auspiciantes.add(new Auspiciante("Pronaca", "Via duran tambo", "2286984", "Duran", "pronaca@pronaca.com", "www.pronaca.com", "A-" + idausp++));
        auspiciantes.add(new Auspiciante("Austrovet", "Av Gonzales Suarez", "501236", "Cuenca", "info@austrovet.net", "www.vetAustro.com", "A-" + idausp++));
        return auspiciantes;
}
  

}
