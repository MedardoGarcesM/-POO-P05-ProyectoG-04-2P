package com.mycompany.proyectog4parcial2.modelo;

/**
 *
 * @author majo_
 */
import Main.Sistema;
import static Main.Sistema.auspiciantes;
import com.mycompany.proyectog4parcial2.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

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
    public Auspiciante(String codigoA,String nombreA, String direccionA, String telefonoA, String ciudadA, String emailA, String webPage) {
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

    @Override
    public String toString() {
        return nombreA;
    }
    
    public static ArrayList<Auspiciante> cargarAuspiciantes(String ruta){
        
        ArrayList<Auspiciante> listaAuspiciantes = new ArrayList<>();
        InputStream input = Dueno.class.getClassLoader().getResourceAsStream(ruta);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(input)))
         {
            String line = br.readLine();
            while (line != null){
                
                if(line.equals("id,apellidos,nombres,direccion,telefono,ciudad,email")){//Evitamos leer la primera linea del archivo
                    line = br.readLine();
                }else{
                    String[] datos = line.split(",");
                    //String codigoA,String nombreA, String direccionA, String telefonoA, String ciudadA, String emailA, String webPage
                    //1,Dog Chow,Via daule,2390832,Guayaquil,info@dogchow.ec,www.dogchow.ec
                    Auspiciante a = new Auspiciante(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5],datos[6]);
                    //System.out.println(d);
                    listaAuspiciantes.add(a);
                    line = br.readLine();
                }
            }
            //Collections.sort(listaAuspiciantes);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return listaAuspiciantes;
    }
    
     

     
    

}
