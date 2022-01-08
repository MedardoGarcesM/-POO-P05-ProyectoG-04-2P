package Main;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import java.time.LocalDate;
import java.time.LocalTime;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Medardo Garces
 */
public class Sistema {
    
    public static ArrayList<Dueno> duenos;
    public static ArrayList<Mascota> mascotas;
    public static ArrayList<Concurso> concurso;
    public static ArrayList<Ciudad> ciudades;
    public static ArrayList<Auspiciante> auspiciantes;
    private static int ultimoIdMascota = 1;
    private static int codigoDueno = 1;
    private static int codigociudad = 1;
    private static int idconcurso = 1;
    private static int idausp = 1;
    Scanner sc = new Scanner(System.in);
    private static Sistema sistema;

    public static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    public Scanner getScanner() {
        return sc;
    }
    
    //---------------------------------------------------------------------------------------------------------------
    //DIALOGOS QUE IRAN EN LOS MENUS
    public void presentarSistemai() {
        System.out.println("\n1.Administrar Concursos");
        System.out.println("2.Administrar Dueños");
        System.out.println("3.Administrar Mascotas");
        System.out.println("");
        System.out.println("Ingrese opcion (1-2-3): ");
    }
    
    public void presentarSistema1() {
        System.out.println("1.Crear concurso");
        System.out.println("2.Inscribir participante");
        System.out.println("3.Regresar al menú principal");
        System.out.println("");
        System.out.println("Ingrese opcion (1-2-3): ");
    }

    public void presentarSistema2() {
        System.out.println("4.Crear dueño");
        System.out.println("5.Editar dueño");
        System.out.println("6.Regresar al menú principal");
        System.out.println("");
        System.out.println("Ingrese opcion (4-5-6): ");
    }

    public void presentarSistema3() {
        System.out.println("7.Crear mascota");
        System.out.println("8.Eliminar mascota");
        System.out.println("9.Regresar al menú principal");
        System.out.println("");
        System.out.println("Ingrese opcion (7-8-9): ");
    }

    //---------------------------------------------------------------------------------------------------------------
    //AUSPICIANTES
    public static void llenadoAuspiciantes() {
        auspiciantes = new ArrayList<Auspiciante>();
        auspiciantes.add(new Auspiciante("Dog Chow", "Via daule", "2390832", "Guayaquil", "info@dogchow.ec", "www.dogchow.ec", generarIdauspiciante()));
        auspiciantes.add(new Auspiciante("Pronaca", "Via duran tambo", "2286984", "Duran", "pronaca@pronaca.com", "www.pronaca.com", generarIdauspiciante()));
        auspiciantes.add(new Auspiciante("Austrovet", "Av Gonzales Suarez", "501236", "Cuenca", "info@austrovet.net", "www.vetAustro.com", generarIdauspiciante()));
    }

    public static String generarIdauspiciante() {
        return "A-" + idausp++;
    }

    //---------------------------------------------------------------------------------------------------------------
    //CIUDADES
    public static void llenadoCiudades() {
        ciudades = new ArrayList<Ciudad>();
        ciudades.add(new Ciudad(generarIdCiudad(), "Guayaquil", "Guayas"));
        ciudades.add(new Ciudad(generarIdCiudad(), "Quito", "Pichincha"));
        ciudades.add(new Ciudad(generarIdCiudad(), "Cuenca", "Azuay"));
    }

    public static String generarIdCiudad(){
        return "C-" + codigociudad++;
    }

    //------------------------------------------------------------------------------------------------------------------
    //DUENOS
    /*
    public static void llenadoDuenos() {
        duenos = new ArrayList<Dueno>();
        duenos.add(new Dueno("0931884547", "Pedro Segundo", "Elizalde Macias", "Mucho Lote 1 mz 48-98", "0987885632", "Guayaquil", "pedroel@gmail.com"));
        duenos.add(new Dueno("0875241456", "Maria Jose", "Calero Villon", "Orquideas mz 1-3", "0985478632", "Quito", "mariadal@gmail.com"));
        duenos.add(new Dueno("1577856452", "Luis Jose", "Lozada Mazzini", "Mapasingue mz 25-65", "0988786345", "Ambato", "luisl@gmail.com"));
        duenos.add(new Dueno("5842448562", "Oscar Carlos", "Villagran Villa", "Alborada mz 23-22", "0912364485", "Guayaquil", "oscarvill@gmail.com"));
        duenos.add(new Dueno("5845698745", "Felipe Gustavo", "Moreno Alcivar", "Orquideas mz 9-8", "0935254587", "Guayaquil", "felipmo@gmail.com"));
        duenos.add(new Dueno("0987545624", "Andres Diego", "Preciado Alce", "Villa España mz 11-2", "0963487569", "Quito", "andrespree@gmail.com"));
        duenos.add(new Dueno("0154569787", "Rosa Martha", "Perlaza Ochoa", "Mucho Lote 2 mz 11-3", "0912548989", "Guayaquil", "rosaperla@gmail.com"));
        duenos.add(new Dueno("8786631457", "Florencia Camila", "Ochoa Quishpe", "Sauces 3 mz 8-15", "0912345896", "Manta", "florocho@gmail.com"));
        duenos.add(new Dueno("0931994786", "Nicole Elizabeth", "Elizalde Macias", "Mucho Lote mz 9-7", "0958785634", "Ambato", "nicole88@gmail.com"));
        duenos.add(new Dueno("0978656896", "Alison Valeria", "Mera Mero", "Sauces 8 mz 10-14", "098796863", "Guayaquil", "almera@gmail.com"));
    }*/
    
    /*
    public void crearDueno() {
        sc = new Scanner(System.in);
        System.out.println("\nIngrese el nombre: ");
        String name = sc.nextLine();
        System.out.println("Ingrese el apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Ingrese el cedula: ");
        String cedula = sc.nextLine();
        System.out.println("Ingrese la direccion: ");
        String direc = sc.nextLine();
        System.out.println("Ingrese el telefono: ");
        String phone = sc.nextLine();
        System.out.println("Ingrese la ciudad:  ");
        String city = sc.nextLine();
        System.out.println("Ingrese el email: ");
        String mail = sc.nextLine();
        Dueno dn = new Dueno(cedula, name, apellido, direc, phone, city, mail);
        System.out.println("El dueno se ha creado correctamente.");
        System.out.println(dn.toString());
        duenos.add(dn);
    }*/
    
    public static String generarIdDueno(){
        return "D-" + codigoDueno++;
    }

    //------------------------------------------------------------------------------------------------------------------
    //MASCOTAS
    public static void llenadoMascotas() {
        //Mascota(String nombre, String tipoMascota, String raza, String fechaNacimiento, Dueno dueno)
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(generarIdMascota(), "Michi", "Gato", "Gato Persa", "12/1/2010", "",duenos.get(0)));
        mascotas.add(new Mascota(generarIdMascota(), "Firulais", "Perro", "Pastor Aleman", "5/6/2011", "",duenos.get(1)));
        mascotas.add(new Mascota(generarIdMascota(), "Rabioso", "Perro", "Labrador retriever", "8/8/2008", "",duenos.get(2)));
        mascotas.add(new Mascota(generarIdMascota(), "Beethoven", "Gato", "Bulldog", "25/7/20015", "",duenos.get(3)));
        mascotas.add(new Mascota(generarIdMascota(), "Pulgoso", "Perro", "Labrador retriever", "16/4/2011","" ,duenos.get(4)));
        mascotas.add(new Mascota(generarIdMascota(), "Bambi", "Perro", "Bulldog", "18/3/2009", "",duenos.get(5)));
        mascotas.add(new Mascota(generarIdMascota(), "Orejas", "Perro", "Lobo Siberiano", "25/12/2012", "",duenos.get(6)));
        mascotas.add(new Mascota(generarIdMascota(), "Messi", "Gato", "Ragdoll", "12/2/2014", "",duenos.get(7)));
        mascotas.add(new Mascota(generarIdMascota(), "Max", "Perro", "Chow Chow", "12/2/2012", "",duenos.get(8)));
        mascotas.add(new Mascota(generarIdMascota(), "Garfield", "Gato", "Fold Escoses", "26/10/2013","" ,duenos.get(9)));
    }
    

    public void crearMascota() { 
        sc = new Scanner(System.in);
        int verfDu = 0;
        System.out.println("\nIngrese el nombre: ");
        String name = sc.nextLine();
        System.out.println("Ingrese el tipo de mascota (Perro o Gato): ");
        String tipM = sc.nextLine();
        System.out.println("Ingrese la raza: ");
        String raz = sc.nextLine();
        System.out.println("Ingrese la fecha de nacimiento aproximada: ");
        String fecN = sc.nextLine();
        do {
            System.out.println(" Duenos disponibles");
            for (Dueno d : duenos) {
                System.out.println(" " + d.getCedula() + " " + d.getNombres() + " " + d.getApellidos());
            }
            System.out.println("Ingrese la cedula de la persona que sera su dueno: ");
            String cedDue = sc.nextLine();
            for (Dueno d : duenos) {
                if (cedDue.equals(d.getCedula())){
                    Dueno dM = d;
                    verfDu++;
                    Mascota mn = new Mascota(generarIdMascota(), name, tipM, raz, fecN, " ",dM);
                    System.out.println(mn.toString());
                    mascotas.add(mn);
                }
            }
            if (verfDu == 0) {
                System.out.println("Dicho dueno no se encuentra dentro del sistema, vuelva a ingresar la cedula.");
            }
        } while (verfDu == 0);
        System.out.println("La mascota se ha creado correctamente.");
    }

    public static String generarIdMascota() {
        return "M-" + ultimoIdMascota++;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CONCURSO 
    
    public static void generadorGanaMas(Concurso c){
        int posGanador = 0;
        ArrayList<Integer> premios = new ArrayList<>();
        ArrayList<String> mascoGana = new ArrayList<>();
                
        for (int i=0;i<c.getMascotasInscri().size();i++) {
            premios.add(i);
        }
        
        for(int i=1;i<=3;i++){
            posGanador=(int)Math.floor(Math.random()*premios.size());// genera una posicion random del 1 al 3 para asignar el ganador 
            mascoGana.add(c.getMascotasInscri().get(posGanador).getNombre());
            premios.remove(posGanador);
        }
        c.setGanadores(mascoGana);
    }
    
    public static void llenandoConcurso() {
        concurso = new ArrayList<Concurso>();
        
        String[] premios = new String[3];
        ArrayList<Mascota> mic1=new ArrayList<>();
        ArrayList<Mascota> mic2=new ArrayList<>();
        ArrayList<String> gc1=new ArrayList<>();
        ArrayList<String> gc2=new ArrayList<>();
        
        for (int i = 0; i < premios.length; i++) {
            if(i==0){
                premios[i] = " su premio fue de $1500";
            }else if(i==1){
                premios[i] = " su premio fue de $750";
            }else if(i==2){
                premios[i] = " su premio fue de $500";
            }
        }
        
        //consurso anterior
        mic1.add(mascotas.get(0));
        mic1.add(mascotas.get(3));
        mic1.add(mascotas.get(9));
        mic1.add(mascotas.get(7));
        Concurso concuAn = new Concurso("Mas obediente", LocalDate.of(2021, 10, 14), LocalTime.of(8, 30), LocalDate.of(2021, 9, 14), LocalDate.of(2021, 10, 13), ciudades.get(0), "parque Samanes", premios, auspiciantes.get(0), "Gato", generarIdConcurso(), false, mic1, gc1);
        Sistema.generadorGanaMas(concuAn);
        concurso.add(concuAn);
        //concurso vigente 
        mic2.add(mascotas.get(1));
        mic2.add(mascotas.get(2));
        mic2.add(mascotas.get(5));
        mic2.add(mascotas.get(8));
        gc2.add(mascotas.get(1).getNombre());
        gc2.add(mascotas.get(2).getNombre());
        gc2.add(mascotas.get(8).getNombre());
        concurso.add(new Concurso("Mas rapido", LocalDate.of(2021, 9, 5), LocalTime.of(11, 10), LocalDate.of(2021, 5, 10), LocalDate.of(2021, 9, 1), ciudades.get(1), "parque Samanes", premios, auspiciantes.get(1), "Perro", generarIdConcurso(), true, mic2, gc2));
    }

    public void crearConcurso(){
        sc = new Scanner(System.in);
        
        ArrayList<Mascota> mascIns=new ArrayList<>();
        ArrayList<String> ganaMasc=new ArrayList<>();
        int verificadorCiudad=0;
        Ciudad ciudadElegida;
        int posCiu=0;
        int verificadorAus=0;
        Auspiciante ausElegido;
        int posAus=0;
        int inicioConcurso=0;
        int posM=0;
        Mascota mascInscrita;
        String dir;
        
        System.out.println("\nIngrese el nombre: ");
        String name = sc.nextLine();
        System.out.println("Ingrese la fecha del evento:");
        LocalDate fechac = Extras.obtenerFecha();
        System.out.println("Ingrese la hora del evento:");
        LocalTime horac = Extras.obtenerHora();
        System.out.println("Ingrese la fecha de inicio de inscripciones:");
        LocalDate fechai = Extras.obtenerFecha();
        System.out.println("Ingrese la fecha de fin del inscripciones:");
        LocalDate fechaf = Extras.obtenerFecha();
        System.out.println("Elija una ciudad...");
        System.out.println("\nCiudades que se pueden elegir: ");
        for (Ciudad c : ciudades ){// muestra las ciudades a elegir 
            System.out.println("    "+c.getNombreC());
        }
        do{
            System.out.println("Escriba la ciudad de su eleccion:");
            String city = sc.nextLine();
            for (Ciudad c:ciudades) {// recorre la lista ciudades
                if (city.equals(c.getNombreC())){// condicion que verifica si la ciudad escrita se encuentra en la lista
                    posCiu=ciudades.indexOf(c);// selecciona la posicion de la ciudad 
                    verificadorCiudad++;//aumenta contador 
                }
            }
            if(verificadorCiudad==0){// si el contador no se aumento entonces la ciudad no se encontro en la lista 
                System.out.println("Dicha ciudad no se encuntra dentro de la lista de ciudades disponibles.");
            }
        }while(verificadorCiudad==0);
        ciudadElegida=ciudades.get(posCiu);// asigna la ciudad de acuerdo a la posicion 
        System.out.println("Escriba el lugar:");
        String lugar = sc.nextLine();
        System.out.println("Escriba los premios...");
        String[] premios=Extras.registrarPremios();
        System.out.println("Elija una auspiciante...");
        System.out.println("\nAuspiciantes que se pueden elegir: ");
        for (Auspiciante a : auspiciantes){// recorre la lista asupicisntes para mostrarla 
            System.out.println("    "+a.getNombreA());
        }
        do{
            System.out.println("Escriba el auspiciante que selecciono:");
            String aus= sc.nextLine();
            for(Auspiciante a:auspiciantes) {
                if(aus.equals(a.getNombreA())){
                    posAus=auspiciantes.indexOf(a);// guarda la posicion del auspiciante 
                    verificadorAus++;
                }
            }
            if(verificadorAus==0){
                System.out.println("Dicha ciudad no se encuntra dentro de la lista de ciudades disponibles.");
            }
        }while(verificadorAus==0);
        ausElegido=auspiciantes.get(posAus);// asigna al auspiciante de acuerdo a su posicion 
        System.out.println("A quien va dirigido (Todos, Perro, Gato):");
        dir = sc.nextLine();
        System.out.println("Tiene que inscribir al menos 3 participantes para poder crear un concurso: ");
        Mascota.mostrarListaMascotas();
        do{
            System.out.println("Ingrese el id de la mascota que desee agregar:");
            String mn= sc.nextLine();
            for(Mascota m: mascotas){
                if((mn.equals(m.getId()))&&(m.getTipoMascota().equals(dir)||dir.equals("Todos"))){
                    posM=mascotas.indexOf(m);
                    mascIns.add(mascotas.get(posM));
                    System.out.println("La mascota "+mascotas.get(posM).getNombre()+" fue correctamente agregada");
                    System.out.println((inicioConcurso+1)+"/3 mascotas");
                    inicioConcurso++;
                }
            }
            if(inicioConcurso==0){
                System.out.println("La mascota no se pudo inscribir.");
            }
        }while(inicioConcurso!=3);
        Concurso cn= new Concurso(name,fechac,horac,fechai,fechaf,ciudadElegida,lugar,premios,ausElegido,dir,generarIdConcurso(),true,mascIns,null);
        Sistema.generadorGanaMas(cn);
        concurso.add(cn);
        System.out.println("El concurso se ha creado correctamente.");
        System.out.println(cn.toString());  
    }

    public static String generarIdConcurso() {
        return "M-" + idconcurso++;
    }
}
