/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2.modelo;
 

import java.util.ArrayList;
import Main.Sistema;
import static Main.Sistema.duenos;
import com.mycompany.proyectog4parcial2.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author Medardo Garces
 */



public class Dueno extends Sistema implements Comparable<Dueno>{
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private Ciudad ciudad;
    private String email;
    
    Scanner sc = new Scanner(System.in);
    
    //public static ArrayList<Dueno> duenos = new ArrayList<Dueno>();
    // constructor 

    public Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
    }
    // getters
    
    public String getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    /*
    // Menu editar dueno 
    public static void editarDueno(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cedula de la persona que desea cambiar sus datos");
        String cedula = sc.nextLine();
        int verificadorHallado=0;
        for(Dueno d: duenos){// recorre la lista de dueños
            if(d.getCedula().equals(cedula)){// verifica la igualdad en la cedula 
                
                System.out.println("Puede cambiar cualquier dato excepto la cedula.");
                System.out.println("Escriba 'n' si no desea editar algun campo.");
                
                for(int i=1; i<=6;i++){// recorre los atributos del objeto hasta que seleccionemos el que deseamos cambiar 
                    if(i==1){
                        System.out.println("Nombres: ");
                        String nombreNuevo = sc.nextLine();
                        if(nombreNuevo.equals("n")){nombreNuevo="";}// si el scanner detecta que esta vacio no cambia nada, caso contrario cambia de acorde  a lo ingresado
                        else{d.nombres=nombreNuevo;}
                    }else if(i==2){
                        System.out.println("Apellidos: ");
                        String apellidoNuevo = sc.nextLine();
                        if(apellidoNuevo.equals("n")){apellidoNuevo="";}
                        else{d.apellidos=apellidoNuevo;}
                    }else if(i==3){
                        System.out.println("Direccion: ");
                        String direcNuevo = sc.nextLine();
                        if(direcNuevo.equals("n")){direcNuevo="";}
                        else{d.direccion=direcNuevo;}
                    }else if(i==4){
                        System.out.println("Telefono: ");
                        String telNuevo = sc.nextLine();
                        if(telNuevo.equals("n")){telNuevo="";}
                        else{d.telefono=telNuevo;}
                    }else if(i==5){
                        System.out.println("Ciudad: ");
                        String ciudadNuevo = sc.nextLine();
                        if(ciudadNuevo.equals("n")){ciudadNuevo="";}
                        else{d.ciudad=ciudadNuevo;}
                    }else if(i==6){
                        System.out.println("Email: ");
                        String emailNuevo = sc.nextLine();
                        if(emailNuevo.equals("n")){emailNuevo="";}
                        else{d.email=emailNuevo;}
                    }
                }
                System.out.println("Los datos han sido cambiados con exito.");
                System.out.println(d.toString());
                verificadorHallado++;
            }
        }
        if(verificadorHallado==0){System.out.println("Dicho dueno no existe dentro del sistema.");}
    }*/
    
    //Ordenar mediante el nombre del dueno
    @Override
    public int compareTo(Dueno o){
        return apellidos.compareToIgnoreCase(o.apellidos);
    }
    
    
    //LECTURA DEL ARCHIVO DUEÑOS DE NUESTRO PARALELO 
    public static ArrayList<Dueno> cargarDuenos(String ruta){
        
        int posicion=1;
        ArrayList<Ciudad> lisCiu =Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<Dueno> listaDuenos = new ArrayList<>();
        InputStream input = Dueno.class.getClassLoader().getResourceAsStream(ruta);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(input)))
         {
            String line = br.readLine();
            while (line != null){
                
                if(line.equals("id,apellidos,nombres,direccion,telefono,ciudad,email")){//Evitamos leer la primera linea del archivo
                    line = br.readLine();
                }else{
                    String[] datos = line.split(",");
                    for(Ciudad c:lisCiu){
                        if(datos[5].equals(c.getNombreC())){
                            posicion=lisCiu.indexOf(c);
                        }
                    }
                    Ciudad ciu = lisCiu.get(posicion);
                    Dueno d = new Dueno(datos[0], datos[2], datos[1], datos[3], datos[4], ciu,datos[6]);
                    //System.out.println(d);
                    listaDuenos.add(d);
                    line = br.readLine();
                }
            }
            Collections.sort(listaDuenos);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return listaDuenos;
    }
    
    //Metodo para la lectura de la lista de dueños
    public static void mostrarListaDuenos(){// muestra la lista de los duenos con su cedula nombre y apellido 
        System.out.println("\nLista de duenos agregados: \n");
        for(Dueno dueno:duenos){// refcorre la lista para imprimir cada objeto con sus atributos 
            System.out.println(" "+dueno.cedula +" " + dueno.nombres +" "+ dueno.apellidos);
        }
        System.out.println();
    }

    @Override
    public String toString(){
        //return "Dueño: Cedula=" + cedula + ", Nombres=" + nombres + ", Apellidos=" + apellidos + ", Direccion=" + direccion + ", Telefono=" + telefono + ", Ciudad=" + ciudad + ", Email=" + email;
        return nombres+" "+apellidos;
    }
    
}
