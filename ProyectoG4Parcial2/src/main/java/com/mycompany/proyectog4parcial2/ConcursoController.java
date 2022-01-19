package com.mycompany.proyectog4parcial2;

import Main.Sistema;
import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class ConcursoController {

    @FXML
    private ComboBox< String> CCDirigidoa;
    @FXML
    private Label lbs;

    @FXML
    private Label lbp;
    @FXML
    private Label lbt;

    @FXML
    private TextField CCs;

    @FXML
    private TextField CCt;
    @FXML
    private TextField CCp;
    @FXML
    private TextField CCnombre;
    @FXML
    private DatePicker CCfechaA;
    @FXML
    private TextField CChora;

    @FXML
    private TextField CClugar;

    @FXML
    private DatePicker CCinicioInscripcion;

    @FXML
    private DatePicker CCcierreInscripcion;

    @FXML
    private ComboBox CCciudad;

    @FXML
    private ComboBox CCAuspiciantes;

    @FXML
    private SplitMenuButton CCPremiosLugar;

    @FXML
    private MenuButton CCPremioDescripcion;

    @FXML
    private MenuButton CCPremiosAuspiciantes;

    @FXML
    private Button CCAñadirPremio;

    @FXML
    private Button CCguardar;

    @FXML
    private Button CCCancelar;

    @FXML
    private Button menuP;

    @FXML
    private void initialize() {

// setea las ciudades en el cmb
        CCciudad.getItems().setAll(Ciudad.cargarCiudades(App.pathCiudades));

// setea a quien va dirigido en el cmb
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Perro", "Gato");
        CCDirigidoa.getItems().setAll(items);

// Setea a los auspiciantes recorriendo la lista de auspiciantes
        for (Auspiciante a : Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)) {
            CCAuspiciantes.getItems().addAll(a.getNombreA());

        }

    }

    @FXML
    void switchToAdmConcurso() throws IOException {
        App.setRoot("admConcurso");

    }

    @FXML
    void switchToAdmPremios() throws IOException {
        App.setRoot("premio");

    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }

    @FXML
    private void guardarConcurso() {
        int posicion = 0;
        ArrayList<Concurso> concursos = Concurso.cargarArchivo(App.pathConcurso);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<String> gana = new ArrayList<>();
        ArrayList<Mascota> mas = new ArrayList<>();
        ArrayList<Auspiciante> auspiciante = Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);
        String[] premio = new String[]{CCp.getText()+","+CCs.getText()+","+CCt.getText()};
        String[] st = CChora.getText().split(":");
        LocalTime hora = LocalTime.of(Integer.parseInt(st[0]), Integer.parseInt(st[1]));
        
        for (Auspiciante a : auspiciante) {
            if (CCAuspiciantes.getValue().equals(a.getNombreA())) {
                posicion = auspiciante.indexOf(a);
            }
        }
        Auspiciante aaa = auspiciante.get(posicion);
        
        ArrayList<Integer> idC = new ArrayList<>();
        for (Concurso a : concursos) {
            idC.add((Integer) Integer.parseInt(a.getCodigo()));
        }
        String pos =String.valueOf(Collections.max(idC) + 1);
        
        System.out.println("Creando concurso");
        /*String nombre, datos[1]
        LocalDate fechaEvento, datos[2]
        LocalTime horaEvento, datos[3]
        LocalDate fechaInicioInscripción, datos[4]
        LocalDate fechaCierreInscripción, datos[5]
        Ciudad ciudad, datos[6]
        String lugar, datos[7]
        String[] premios, datos[8]
        Auspiciante auspiciantes, datos[9] 
        String dirigido, datos[10]
        String codigo, datos[0]
        boolean concursoAbierto, datos[11]
        ArrayList<Mascota> mascotasInscri, datos[12] 
        ArrayList<String> ganadores) datos[13]*/
        Concurso c = new Concurso(CCnombre.getText(), CCfechaA.getValue(), hora, CCinicioInscripcion.getValue(), CCcierreInscripcion.getValue(), (Ciudad) CCciudad.getValue(), CClugar.getText(), premio, aaa, CCDirigidoa.getValue(), pos, true, mas, gana);
        concursos.add(c);
        //System.out.println("nuevo concurso " + c);
        try {
            //"src/main/resources/"+
            FileWriter writer = new FileWriter(App.pathConcurso);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for(Concurso b:concursos){
                String premios="";
                int contPremios=0;
                for(String prem:b.getPremios()){
                    if(contPremios!=(b.getPremios().length-1)){
                        premios=premios+prem+",";
                    }else{
                        premios=premios+prem;
                    }
                    contPremios++;
                }

                //Generacion de ids de mascotas para poder sobreescribir al archivo concurso
                ArrayList<String> listIdMascotasIns = new ArrayList<>();
                for(Mascota mi:b.getMascotasInscri()){
                    listIdMascotasIns.add(mi.getId());
                }
                //Para que aparezca sin los corchetes generamos un String con todos los ids
                String mascotasInscrip="";
                int contMasIns=0;
                for(String id:listIdMascotasIns){
                    if(contMasIns!=(listIdMascotasIns.size()-1)){
                        mascotasInscrip=mascotasInscrip+id+",";
                    }else{
                        mascotasInscrip=mascotasInscrip+id;
                    }
                    contMasIns++;
                }


                //Generacion de ids de mascotas ganadoras para poder sobreescribir al archivo concurso
                ArrayList<String> listIdMascotasGan = new ArrayList<>();
                for(Mascota mascIns:b.getMascotasInscri()){
                    for(String mascGan:b.getGanadores()){
                        if(mascGan.equals(mascIns.getNombre())){
                            listIdMascotasGan.add(mascIns.getId());                                
                        }
                    }
                }
                //Para que aparezca sin los corchetes generamos un String con todos los ids
                String mascotasGanadoras="";
                int contgan=0;
                for(String idg:listIdMascotasGan){
                    if(contgan!=(listIdMascotasGan.size()-1)){
                        mascotasGanadoras=mascotasGanadoras+idg+",";
                    }else{
                        mascotasGanadoras=mascotasGanadoras+idg;
                    }
                    contgan++;
                }
                //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                if(b.getMascotasInscri().size()!=0){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +mascotasGanadoras);
                }else{
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +"0,0");
                }
                bufferedWriter.newLine();
                 
            }


            bufferedWriter.close();
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            conf.setTitle("Mensaje de confirmacion");
            conf.setHeaderText("Concurso agregado correctamente");
            conf.showAndWait();            

            App.setRoot("admConcurso");

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
