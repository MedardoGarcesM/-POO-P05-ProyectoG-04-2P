package com.mycompany.proyectog4parcial2.modelo;

/**
 *
 * @author majo_
 */
import Main.Sistema;
import static Main.Sistema.concurso;
import static Main.Sistema.mascotas;
import com.mycompany.proyectog4parcial2.App;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import com.mycompany.proyectog4parcial2.modelo.*;
import java.util.Arrays;
import java.util.Collections;

public class Concurso extends Sistema implements Serializable {

    private static int idausp = 1;
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
    public Concurso(String nombre, LocalDate fechaEvento, LocalTime horaEvento, LocalDate fechaInicioInscripción, LocalDate fechaCierreInscripción, Ciudad ciudad, String lugar, String[] premios, Auspiciante auspiciantes, String dirigido, String codigo, boolean concursoAbierto, ArrayList<Mascota> mascotasInscri, ArrayList<String> ganadores) {
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
        this.mascotasInscri = mascotasInscri;
        this.ganadores = ganadores;
    }

    public Concurso() {

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
        for (Concurso c : concurso) {// recorre la lista de concurso y la imprime 
            System.out.println(" " + c.codigo + " " + c.nombre + " - Dirigido a: " + c.dirigido + " - Concurso Abierto: " + c.concursoAbierto);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre + ";" + fechaEvento + ";" + horaEvento + ";" + fechaInicioInscripción + ";" + fechaCierreInscripción + ";" + ciudad + ";" + lugar + ";" + premios[0] + "," + premios[1] + "," + premios[2] + ";" + auspiciantes.getNombreA() + ";" + dirigido + ";" + concursoAbierto + ";" + mascotasInscri + ";" + ganadores + '}';
    }

    public static void mostrarMascotasInscri(Concurso c) {
        System.out.println("\nLista de Mascotas Inscritas: \n");
        for (Mascota m : c.mascotasInscri) {// recorre la lista e imprime la info de las mascotas inscritas en el concurso 
            System.out.println(" " + m.getId() + " Nombre: " + m.getNombre() + " - Dueno: " + m.getDueno().getNombres() + " " + m.getDueno().getApellidos());
        }
        System.out.println();
    }

    public static void mostrarMascotasGanadoras(Concurso c) {
        int posicion = 1;
        System.out.println("\nLista de Mascotas que ganaron fueron:\n");

        for (String mascgan : c.ganadores) {// recorre la lista de ganadores e imprime suposicion 
            if (posicion == 1 || posicion == 3) {
                System.out.println("    " + posicion + "er Ganador: " + mascgan + " " + c.premios[posicion - 1]);
            } else {
                System.out.println("    " + posicion + "do Ganador: " + mascgan + " " + c.premios[posicion - 1]);
            }
            posicion++;
        }
        System.out.println();
        System.out.println("Los premios fueron auspiciados por: " + c.auspiciantes.getNombreA());
    }

    public void inscribirParticipante() {
        mascotasInscri = new ArrayList<Mascota>();
        Scanner sc = new Scanner(System.in);
        int posC = 0;
        int verificadorCC = 0;
        Concurso concurEleg;
        int posM = 0;
        int verificadorMasc = 0;
        int verifTipoMasc = 0;
        Mascota mascInscrita;
        Concurso.mostrarListaConcursoss();
        do {
            System.out.println("Ingrese el codigo del concurso donde desea agregar al participante:");
            String cc = sc.nextLine();
            for (Concurso c : concurso) {
                if (cc.equals(c.getCodigo())) {// compara el codigo del concurso ingresado con el existente 
                    posC = concurso.indexOf(c);// guarda la posicion del concurso que la persona quiere encontrar 
                    verificadorCC++;
                }
            }
            if (verificadorCC == 0) {
                System.out.println("Dicho concurso no se encuntra dentro de la lista de concursos.");
            }
        } while (verificadorCC == 0);
        concurEleg = concurso.get(posC);// guarda la posicion del concurso elegido
        Concurso.mostrarMascotasInscri(concurEleg); // asigna el concurso elegido  
        if (concurEleg.concursoAbierto == true) {// condicion para comprobar si el concurso aun esta vigente 
            Mascota.mostrarListaMascotas();
            do {
                System.out.println("Ingrese el id de la mascota que desee agregar:");
                String mn = sc.nextLine();
                for (Mascota m : mascotas) {
                    if ((mn.equals(m.getId())) && (m.getTipoMascota().equals(concurEleg.getDirigido()) || concurEleg.getDirigido().equals("Todos"))) {
                        posM = mascotas.indexOf(m);
                        verificadorMasc++;
                    }
                }
                if (verificadorMasc == 0) {
                    System.out.println("La mascota no se pudo inscribir.");
                }
            } while (verificadorMasc == 0);
            mascInscrita = mascotas.get(posM);
            concurEleg.getMascotasInscri().add(mascInscrita);
            System.out.println("La mascota " + mascInscrita.getNombre() + " fue correctamente agregada");
        } else {
            Concurso.mostrarMascotasGanadoras(concurEleg);
        }
    }

    // 2P
    public static void crearArchivo() {
        concurso = new ArrayList<Concurso>();
        ArrayList<Auspiciante> auspiciantes = new ArrayList<>(Auspiciante.generarAus());

        String[] premios = new String[3];
        ArrayList<Mascota> mic1 = new ArrayList<>();
        ArrayList<Mascota> mic2 = new ArrayList<>();
        ArrayList gc1 = new ArrayList();
        ArrayList gc2 = new ArrayList<>();
        ArrayList<Mascota> mic3 = new ArrayList<>();
        ArrayList gc3 = new ArrayList<>();
        ArrayList<Mascota> lisMas = Mascota.cargarMascotas(App.pathMascotas);
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        for (int i = 0; i < premios.length; i++) {
            if (i == 0) {
                premios[i] = " primer lugar premio $1500";
            } else if (i == 1) {
                premios[i] = " segundo lugar premio $750";
            } else if (i == 2) {
                premios[i] = " tercer lugar premio $500";
            }
        }
        //consurso anterior
        mic1.add(lisMas.get(0));
        mic1.add(lisMas.get(3));
        mic1.add(lisMas.get(10));
        mic1.add(lisMas.get(12));
        mic1.add(lisMas.get(44));
        mic1.add(lisMas.get(46));
        mic1.add(lisMas.get(9));
        mic1.add(lisMas.get(7));
        Concurso concun = new Concurso("Mas obediente", LocalDate.of(2021, 10, 14), LocalTime.of(8, 30), LocalDate.of(2021, 9, 14), LocalDate.of(2021, 10, 13), lisCiu.get(0), "parque Samanes", premios, auspiciantes.get(0), "Gato", Sistema.generarIdConcurso(), false, mic1, gc1);
        System.out.println(gc1);
        concurso.add(concun);

        //concurso pasados
        mic2.add(lisMas.get(1));
        mic2.add(lisMas.get(2));
        mic2.add(lisMas.get(5));
        mic2.add(lisMas.get(9));
        mic2.add(lisMas.get(15));
        mic2.add(lisMas.get(31));
        mic2.add(lisMas.get(8));
        gc2.add(lisMas.get(1).getNombre());
        gc2.add(lisMas.get(2).getNombre());
        gc2.add(lisMas.get(8).getNombre());
        Concurso convig = new Concurso("Mas rapido", LocalDate.of(2021, 9, 5), LocalTime.of(11, 10), LocalDate.of(2021, 5, 10), LocalDate.of(2021, 9, 1), lisCiu.get(1), "parque Samanes", premios, auspiciantes.get(1), "Perro", Sistema.generarIdConcurso(), true, mic2, gc2);
        concurso.add(convig);
        System.out.println(gc2);

        mic3.add(lisMas.get(30));
        mic3.add(lisMas.get(36));
        mic3.add(lisMas.get(25));
        mic3.add(lisMas.get(24));
        mic3.add(lisMas.get(15));
        mic3.add(lisMas.get(10));
        mic3.add(lisMas.get(3));
        gc3.add(lisMas.get(3).getNombre());
        gc3.add(lisMas.get(10).getNombre());
        gc3.add(lisMas.get(24).getNombre());
        Concurso con1 = new Concurso("Mejor Portado", LocalDate.of(2021, 9, 5), LocalTime.of(11, 10), LocalDate.of(2021, 5, 10), LocalDate.of(2021, 9, 1), lisCiu.get(2), "parque nacional cajas ", premios, auspiciantes.get(2), "Perro", Sistema.generarIdConcurso(), true, mic2, gc2);
        concurso.add(con1);
        System.out.println(gc2);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/main/resources/" + App.pathConcurso, true);
            pw = new PrintWriter(fichero);
            for (Concurso c : concurso) {
                System.out.println(c);
                fichero.write(c.toString() + "\r\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println("problema alcargar archivo");
                e2.printStackTrace();
            }
        }

    }

    public static ArrayList<Concurso> cargarArchivo(String ruta) {
        int posicion = 1;
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<Mascota> lismas = Mascota.cargarMascotas(App.pathMascotas);
        ArrayList<Concurso> liscon = new ArrayList<>();
        ArrayList<Auspiciante> lisau = Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);

        //InputStream input = Concurso.class.getClassLoader().getResourceAsStream(ruta);
        //try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) 
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String line = br.readLine();
            while (line != null) {

                if (line.equals("id,apellidos,nombres,direccion,telefono,ciudad,email")) {//Evitamos leer la primera linea del archivo
                    line = br.readLine();
                } else {
                    String[] datos = line.split(";");

                    for (Ciudad c : lisCiu) {
                        if (datos[6].equals(c.getNombreC())) {
                            posicion = lisCiu.indexOf(c);
                        }
                    }

                    Ciudad ciu = lisCiu.get(posicion);

                    // conversion del string a array y posterior en local date y time 
                    String[] st = datos[2].split("-");
                    LocalDate fecha1 = LocalDate.of(Integer.parseInt(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]));

                    String[] hr = datos[3].split(":");
                    LocalTime hora = LocalTime.of(Integer.parseInt(hr[0]), Integer.parseInt(hr[1]));

                    String[] fa = datos[4].split("-");
                    LocalDate fecha2 = LocalDate.of(Integer.parseInt(fa[0]), Integer.parseInt(fa[1]), Integer.parseInt(fa[2]));

                    String[] fb = datos[5].split("-");
                    LocalDate fecha3 = LocalDate.of(Integer.parseInt(fb[0]), Integer.parseInt(fb[1]), Integer.parseInt(fb[2]));

                    String[] premio = datos[8].split(",");
// para los auspiciantes
                    for (Auspiciante a : lisau) {
                        if (datos[9].equals(a.getNombreA())) {
                            posicion = lisau.indexOf(a);
                        }
                    }
                    Auspiciante nau = lisau.get(posicion);
// para el array de mascotas 
                    String[] nm = datos[12].split(",");
                    ArrayList<Mascota> masl = new ArrayList<>();
                    for (Mascota m : lismas) {
                        for (int i = 0; i < nm.length; i++) {
                            if (nm[i].equals(m.getNombre())) {
                                posicion = lismas.indexOf(m);
                            }
                            Mascota masc = lismas.get(posicion);
                            masl.add(masc);

                        }
                    }

// para el array de ganadores 
                    String[] ag = datos[13].split(",");
                    ArrayList<String> nlg = new ArrayList<>();
                    nlg.addAll(Arrays.asList(ag));
// inicia con elmcdigo el archivo
                    //("Mas rapido", LocalDate.of(2021, 9, 5), LocalTime.of(11, 10), LocalDate.of(2021, 5, 10), LocalDate.of(2021, 9, 1), lisCiu.get(1), "parque Samanes", premios, auspiciantes.get(1), "Perro", Sistema.generarIdConcurso(), true, mic2, gc2);                    
                    Concurso d = new Concurso(datos[1], fecha1, hora, fecha2, fecha3, ciu, datos[7], premio, nau, datos[10], datos[0], Boolean.parseBoolean(datos[11]), masl,nlg);
                    //System.out.println(d);
                    liscon.add(d);
                    line = br.readLine();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return liscon;
    }
        }

    
