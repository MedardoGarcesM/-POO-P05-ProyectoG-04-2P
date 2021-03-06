/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Sistema;
import com.mycompany.proyectog4parcial2.App;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import com.mycompany.proyectog4parcial2.modelo.Concurso;

/**
 *
 * @author Medardo Garces
 */
public class Main {

    public static void main(String[] args) {
        Mascota.cargarMascotas(App.pathMascotas);
        ArrayList<Mascota> listM=Mascota.cargarMascotas(App.pathMascotas);//com.mycompany.proyectog4parcial2.files
        for(Mascota d:listM){
            System.out.println(d.toString());
        }
        
        Sistema sistema = new Sistema();
        Concurso conc = new Concurso();
        
        Scanner sc = new Scanner(System.in);
        
        //sistema.llenadoDuenos();
        //sistema.llenadoMascotas();
        //sistema.llenadoCiudades();
        //sistema.llenadoAuspiciantes();
        //sistema.llenandoConcurso();
        int opcion;
        
        do {
            sistema.presentarSistemai();
            Concurso.crearArchivo();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    
                    //mostrar listas de concursos existentes
                    Concurso.mostrarListaConcursoss();
                    sistema.presentarSistema1();
                    int opcion1 = sc.nextInt(); //OJO
                    sc.nextLine();
                    switch (opcion1) {
                        case 1:
                            //crear concurso
                            //sistema.crearConcurso();
                            break;
                        case 2:
                            //inscribir participante
                            conc.inscribirParticipante();
                            break;
                        case 3:
                            //regresar al menu principal
                            System.out.println("Regresando al menu principal... \n");
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta");
                    }
                    break;

                case 2:
                    //lista de due??os existentes
                    Dueno.mostrarListaDuenos();
                    sistema.presentarSistema2();// presenta las opciones 3, 4 y 5
Concurso.crearArchivo();
                    int opcion2 = sc.nextInt();
                    sc.nextLine();
                    switch (opcion2) {

                        case 4:
                            //ingresar?? todos los datos relacionados al due??o
                            //sistema.crearDueno();
                            break;

                        case 5:
                            //solicitar?? el id del due??o y permitir?? actualizar los datos que se deseen(no es necesario actualizar todos los datos
                            //Dueno.editarDueno();
                            break;

                        case 6:
                            //regresar al menu principal
                            System.out.println("Regresando al menu principal... \n");
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta \n");
                    }
                    break;

                case 3:
                    //lista de mascotas existentes
                    Mascota.mostrarListaMascotas();
                    sistema.presentarSistema3();
                    int opcion3 = sc.nextInt(); //OJO
                    sc.nextLine();
                    switch (opcion3) {
                        case 7:
                            //ingresar?? todos los datos relacionados a la mascota
                            sistema.crearMascota();
                            break;
                        case 8:
                            //solicitar?? el id de la mascota y eliminar?? a la mascota,la mascota ya no est?? disponible para futuras inscripciones
                            Mascota.eliminarMascota();
                            break;
                        case 9:
                            //regresar al menu principal
                            System.out.println("Regresando al menu principal... \n");
                            break;
                        default:
                            System.out.print("Ingrese una opcion correcta");
                    }
                    break;

                default:
                    System.out.print("La opcion escogida no se encuentra dentro de la opciones");
            }

        } while (opcion == 1 || opcion == 2 || opcion == 3);
        sc.close();
    }
   
}
