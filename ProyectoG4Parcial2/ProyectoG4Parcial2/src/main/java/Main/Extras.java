/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Sistema;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Medardo Garces
 */
public class Extras {
    public static LocalDate obtenerFecha(){
        System.out.print("Fecha (dd-mm-yyyy): ");

        String[] fechaEvento = Sistema.getInstance().getScanner().nextLine().split("-");
        int anio = Integer.parseInt(fechaEvento[2]);
        int mes = Integer.parseInt(fechaEvento[1]);
        int dia = Integer.parseInt(fechaEvento[0]);
        return LocalDate.of(anio, mes, dia);
    }
    
    public static LocalDate fechaStringLocal(String a){
        String[] fechaEvento=a.split("-");
        int anio = Integer.parseInt(fechaEvento[2]);
        int mes = Integer.parseInt(fechaEvento[1]);
        int dia = Integer.parseInt(fechaEvento[0]);
        return LocalDate.of(anio, mes, dia);
    }

    public static LocalTime obtenerHora(){
        System.out.print("Hora(hh:mm): ");
        String[] horaEvento = Sistema.getInstance().getScanner().nextLine().split(":");
        int hora = Integer.parseInt(horaEvento[0]);
        int min = Integer.parseInt(horaEvento[1]);
        return LocalTime.of(hora, min);
    }
    
    public static String[] registrarPremios(){
        String[] premios = new String[3];
        String premioTmp;
        for (int i = 0; i < premios.length; i++) {
            System.out.print("Premio lugar #" + (i + 1) + ": ");
            premioTmp = Sistema.getInstance().getScanner().nextLine();
            premios[i] = premioTmp;
        }
        return premios;
    }
}