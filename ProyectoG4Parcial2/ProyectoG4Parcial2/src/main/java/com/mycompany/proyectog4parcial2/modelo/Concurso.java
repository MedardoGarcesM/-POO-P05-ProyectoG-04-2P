package com.mycompany.proyectog4parcial2.modelo;

/**
 *
 * @author majo_
 */
import Main.Sistema;
import static Main.Sistema.concurso;
import static Main.Sistema.mascotas;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Concurso extends Sistema{

    private String nombre;
    private LocalDate fechaEvento;
    private LocalTime horaEvento;
    private LocalDate fechaInicioInscripción;
    private LocalDate fechaCierreInscripción;
    private Ciudad ciudad;//de la clase ciudades
    private String lugar;
    private String[] premios;
    private Auspiciante auspiciantes;//de la clase auspiciantes
    private String dirigido;
    private String codigo;//
    private boolean concursoAbierto;
    private ArrayList<Mascota> mascotasInscri;
    private ArrayList<String> ganadores;

    //constructores
    public Concurso(String nombre, LocalDate fechaEvento, LocalTime horaEvento, LocalDate fechaInicioInscripción, LocalDate fechaCierreInscripción, Ciudad ciudad, String lugar, String[] premios, Auspiciante auspiciantes, String dirigido, String codigo, boolean concursoAbierto,ArrayList<Mascota> mascotasInscri, ArrayList<String> ganadores) {
        this.nombre = nombre;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.fechaInicioInscripción = fechaInicioInscripción;
        this.fechaCierreInscripción = fechaCierreInscripción;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.premios = premios;
        this.auspiciantes = auspiciantes;
        this.dirigido = dirigido;
        this.codigo = codigo;
        this.concursoAbierto = concursoAbierto;
        this.mascotasInscri=mascotasInscri;
        this.ganadores=ganadores;
    }

    public Concurso(){
        
    }
    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public LocalTime getHoraEvento() {
        return horaEvento;
    }

    public LocalDate getFechaInicioInscripción() {
        return fechaInicioInscripción;
    }

    public LocalDate getFechaCierreInscripción() {
        return fechaCierreInscripción;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public String getLugar() {
        return lugar;
    }

    public String[] getPremios() {
        return premios;
    }

    public Auspiciante getAuspiciantes() {
        return auspiciantes;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDirigido() {
        return dirigido;
    }

    public boolean isConcursoAbierto() {
        return concursoAbierto;
    }

    public ArrayList<Mascota> getMascotasInscri() {
        return mascotasInscri;
    }

    public ArrayList<String> getGanadores() {
        return ganadores;
    }

    public void setMascotasInscri(ArrayList<Mascota> mascotasInscri) {
        this.mascotasInscri = mascotasInscri;
    }

    public void setGanadores(ArrayList<String> ganadores) {
        this.ganadores = ganadores;
    }
    
    

    public static void mostrarListaConcursoss() {
        System.out.println("\nLista de Concursos agregados: \n");
        for (Concurso c:concurso) {// recorre la lista de concurso y la imprime 
            System.out.println(" " + c.codigo + " " + c.nombre+" - Dirigido a: "+c.dirigido+" - Concurso Abierto: "+c.concursoAbierto);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Concurso:" +" codigo=" + codigo+ " nombre=" + nombre + ", fechaEvento=" + fechaEvento + ", horaEvento=" + horaEvento + ", fechaInicioInscripci\u00f3n=" + fechaInicioInscripción + ", fechaCierreInscripci\u00f3n=" + fechaCierreInscripción + ", ciudad=" + ciudad.getNombreC() + ", lugar=" + lugar + ", auspiciantes=" + auspiciantes.getNombreA() + ", dirigido a=" + dirigido;
    }
    
    public static void mostrarMascotasInscri(Concurso c){
        System.out.println("\nLista de Mascotas Inscritas: \n");
        for(Mascota m:c.mascotasInscri){// recorre la lista e imprime la info de las mascotas inscritas en el concurso 
            System.out.println(" "+m.getId() +" Nombre: " +m.getNombre()+" - Dueno: "+m.getDueno().getNombres()+" "+m.getDueno().getApellidos());
        }
        System.out.println();
    }
    
    public static void mostrarMascotasGanadoras(Concurso c){
        int posicion=1;
        System.out.println("\nLista de Mascotas que ganaron fueron:\n");
        
        for(String mascgan:c.ganadores){// recorre la lista de ganadores e imprime suposicion 
            if(posicion==1||posicion==3){
                System.out.println("    "+posicion+"er Ganador: "+mascgan+" "+c.premios[posicion-1]);
            }else{
                System.out.println("    "+posicion+"do Ganador: "+mascgan+" "+c.premios[posicion-1]);
            }
            posicion++;
        }
        System.out.println();
        System.out.println("Los premios fueron auspiciados por: "+c.auspiciantes.getNombreA());
    }
    
    public void inscribirParticipante(){
        mascotasInscri = new ArrayList<Mascota>();
        Scanner sc = new Scanner(System.in);
        int posC=0;
        int verificadorCC=0;
        Concurso concurEleg;
        int posM=0;
        int verificadorMasc=0;
        int verifTipoMasc=0;
        Mascota mascInscrita;
        Concurso.mostrarListaConcursoss();
        do{
            System.out.println("Ingrese el codigo del concurso donde desea agregar al participante:");
            String cc= sc.nextLine();
            for(Concurso c: concurso){
                if(cc.equals(c.getCodigo())){// compara el codigo del concurso ingresado con el existente 
                    posC=concurso.indexOf(c);// guarda la posicion del concurso que la persona quiere encontrar 
                    verificadorCC++;
                }
            }
            if(verificadorCC==0){
                System.out.println("Dicho concurso no se encuntra dentro de la lista de concursos.");
            }
        }while(verificadorCC==0);
        concurEleg=concurso.get(posC);// guarda la posicion del concurso elegido
        Concurso.mostrarMascotasInscri(concurEleg); // asigna el concurso elegido  
        if(concurEleg.concursoAbierto==true){// condicion para comprobar si el concurso aun esta vigente 
            Mascota.mostrarListaMascotas();
            do{
                System.out.println("Ingrese el id de la mascota que desee agregar:");
                String mn= sc.nextLine();
                for(Mascota m: mascotas){
                    if((mn.equals(m.getId()))&&(m.getTipoMascota().equals(concurEleg.getDirigido())||concurEleg.getDirigido().equals("Todos"))){
                        posM=mascotas.indexOf(m);
                        verificadorMasc++;
                    }
                }
                if(verificadorMasc==0){
                    System.out.println("La mascota no se pudo inscribir.");
                }
            }while(verificadorMasc==0);
            mascInscrita=mascotas.get(posM);
            concurEleg.getMascotasInscri().add(mascInscrita);
            System.out.println("La mascota "+mascInscrita.getNombre()+" fue correctamente agregada");
        }else{
            Concurso.mostrarMascotasGanadoras(concurEleg);
        }
    }

}