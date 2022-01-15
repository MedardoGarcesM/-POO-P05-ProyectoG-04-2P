package com.mycompany.proyectog4parcial2.modelo;

import Main.Sistema;
import static Main.Sistema.ciudades;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Ciudad extends Sistema{// implements Comparable<Ciudad>

    public String nombreC;
    private String provincia;
    private String codigo;//
    //String[] ciudadesL = new String [];

    //constructores
    public Ciudad(String codigo, String nombreC, String provincia) {
        this.codigo = codigo;
        this.nombreC = nombreC;
        this.provincia = provincia;

    }
    
    /*
    @Override
    public int compareTo(Ciudad o){
        return codigoC.compareToIgnoreCase(o.codigoC);
    }*/
    
    //LECTURA DEL ARCHIVO DUEÑOS DE NUESTRO PARALELO 
    public static ArrayList<Ciudad> cargarCiudades(String ruta){
        
        ArrayList<Ciudad> listaCiudades = new ArrayList<>();
        InputStream input = Ciudad.class.getClassLoader().getResourceAsStream(ruta);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(input)))
         {
            String line = br.readLine();
            while (line != null){
                
                if(line.equals("id,apellidos,nombres,direccion,telefono,ciudad,email")){//Evitamos leer la primera linea del archivo
                    line = br.readLine();
                }else{
                    String[] datos = line.split(",");
                    Ciudad c = new Ciudad(datos[0],datos[1],datos[2]);
                    //System.out.println(d);
                    listaCiudades.add(c);
                    line = br.readLine();
                }
            }
            //Collections.sort(listaCiudades);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return listaCiudades;
    }

    public String getNombreC() {
        return nombreC;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getProvincia() {
        return provincia;
    }
    
    /*
    public void mostrarListaCiudades(){
        System.out.println("\nLista de ciudades: \n");
        for(Ciudad ciudad: ciudades){
            System.out.println(" " + ciudad.nombreC );
        }
        System.out.println();
    }*/
    
    @Override
    public String toString() {
        return nombreC;
    }

}
