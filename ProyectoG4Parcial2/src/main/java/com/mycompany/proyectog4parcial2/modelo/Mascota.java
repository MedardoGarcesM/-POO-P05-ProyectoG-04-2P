/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2.modelo;

/**
 *
 * @author majo_
 */
import java.util.ArrayList;
import java.util.Scanner;
import Main.Sistema;
import static Main.Sistema.mascotas;
import com.mycompany.proyectog4parcial2.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

public class Mascota extends Sistema implements Comparable<Mascota>{
    private String id;
    private String nombre;
    private String tipoMascota;
    private String raza;
    private String fechaNacimiento;
    private String foto; //esto segundo parcial
    private Dueno dueno;
    
    Scanner sc = new Scanner(System.in);
    
// constructor 
    public Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno){
        this.id=id;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.dueno = dueno;
    }

// getters
    public String getNombre() {
        return nombre;
    }
    public String getId() {
        return id;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public String getRaza() {
        return raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public Dueno getDueno() {
        return dueno;
    }
    
    /*public void agregarMascota(Mascota m){
        if(m instanceof Mascota){
            mascotas.add(m);
        }else{
            System.out.println("La mascota no pudo ser agregado.");
        }      
    }*/
    
    //METODO PARA ELIMINAR MASCOTA DE LA LISTA DE MASCOTAS
    public static void eliminarMascota(){
        Scanner sc = new Scanner(System.in);
        int verificador=0;
        System.out.println("\nIngrese el nombre de la mascota que desee eliminar: ");
        String mascotaEliminar=sc.nextLine();
        
        for(int i=0;i<mascotas.size();i++){// recorre la lista de las mascotas
            if(mascotas.get(i).getNombre().equals(mascotaEliminar)){//.equals(mascotaEliminar); busca la posicion de la mascotas de acuerdoa lo ingresado y la emilina 
                mascotas.remove(i);
                System.out.println("La mascota "+mascotaEliminar+" fue eliminada.");
                verificador++;// aumenta la variable de verificador
            }
        }
        if(verificador==0){System.out.println("La mascota no se encuentra dentro de la lista.");}// si el verificador no se mantiene en 0 agrega el mensaje 
    }
    
    @Override
    public int compareTo(Mascota o){
        return id.compareToIgnoreCase(o.id);
    }
    
    
    //LECTURA DEL ARCHIVO DUEÑOS DE NUESTRO PARALELO 
    public static ArrayList<Mascota> cargarMascotas(String ruta){
        
        int pos=0;
        ArrayList<Mascota> listaMascotas = new ArrayList<>();
        //InputStream input = Dueno.class.getClassLoader().getResourceAsStream(ruta);
        ArrayList<Dueno> lisDue = Dueno.cargarDuenos(App.pathDuenos);
        //try(BufferedReader br = new BufferedReader(new InputStreamReader(input)))
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String line = br.readLine();
            while (line != null){
                //System.out.println(line);
                String[] datos = line.split(";");
                String duenoMascota = datos[6];
                
                for(Dueno d:lisDue){
                    if(datos[6].equals(d.getCedula())){
                        pos=lisDue.indexOf(d);
                    }
                }
                Dueno dm = lisDue.get(pos);
                Mascota m = new Mascota(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], dm);
                listaMascotas.add(m);
                
                line = br.readLine();
            }
            //Collections.sort(listaMascotas);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return listaMascotas;
    }
    
    //METODO PARA IMPRIMIR TODA LA LISTA DE MASCOTAS
    public static void mostrarListaMascotas(){
        System.out.println("\nLista de mascotas agregadas: \n");
        for(Mascota m:mascotas){// recorre la lista de mascotas e imprime la info necesaria 
            System.out.println("    "+m.id+" Nombre: "+m.nombre+" - Tipo de mascota: "+m.getTipoMascota());
        }
        System.out.println();
    }
    
    //METODO PARA IMPRIMIR UNA MASCOTA
    @Override
    public String toString() {
        //return "Mascota{" + "nombre=" + nombre + ", tipoMascota=" + tipoMascota + ", raza=" + raza + ", fechaNacimiento=" + fechaNacimiento + ", foto=" + foto + ", dueno=" + dueno.getNombres() +" "+ dueno.getApellidos() +'}';
        return nombre;
    }
    
    
}

