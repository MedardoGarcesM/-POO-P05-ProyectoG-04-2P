package com.mycompany.proyectog4parcial2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Medardo Garces
 */
public class AgregarMascotaController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label labelTitulo;

    @FXML
    private TableView admM;

    @FXML
    private TableColumn<Mascota, String> colmCod;

    @FXML
    private TableColumn<Mascota, String> colmNom;

    @FXML
    private TableColumn<Mascota, String> colTipo;

    @FXML
    private Button botonAggAus;

    @FXML
    private Button botonElimAus;

    @FXML
    private Button botonMenuP;

    @FXML
    private TextField txtCodigo;
    int pos = 0;

    @FXML
    private void agregarMC() {
        Mascota d = (Mascota) admM.getSelectionModel().getSelectedItem();
        String posi = txtCodigo.getText();
        ArrayList<Concurso> concursos = Concurso.cargarArchivo(App.pathConcurso);//cargar la lista del archivo
        ArrayList<String> gana = new ArrayList<>();
        ArrayList<Mascota> mas = new ArrayList<>();
        ArrayList<Mascota> mai = new ArrayList<>();

        for (Concurso c : concursos) {
            if (posi.equals(c.getCodigo())) {
                pos = concursos.indexOf(c);

            }
        }
        Concurso conc = concursos.get(pos);
        ArrayList<Mascota> max = conc.getMascotasInscri();
        if (conc.getMascotasInscri().size() == 0) {
            mai.add(d);
            conc.setMascotasInscri(mai);
            conc.setGanadores(gana);

        } else {
            max.add(d);
            conc.setMascotasInscri(max);
            conc.setGanadores(gana);

        }
        //System.out.println(conc);

        try {
            //"src/main/resources/"+
            FileWriter writer = new FileWriter(App.pathConcurso);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Concurso b : concursos) {
                String premios = "";
                int contPremios = 0;
                for (String prem : b.getPremios()) {
                    if (contPremios != (b.getPremios().length - 1)) {
                        premios = premios + prem + ",";
                    } else {
                        premios = premios + prem;
                    }
                    contPremios++;
                }

                //Generacion de ids de mascotas para poder sobreescribir al archivo concurso
                ArrayList<String> listIdMascotasIns = new ArrayList<>();
                for (Mascota mi : b.getMascotasInscri()) {
                    listIdMascotasIns.add(mi.getId());
                }
                //Para que aparezca sin los corchetes generamos un String con todos los ids
                String mascotasInscrip = "";
                int contMasIns = 0;
                for (String id : listIdMascotasIns) {
                    if (contMasIns != (listIdMascotasIns.size() - 1)) {
                        mascotasInscrip = mascotasInscrip + id + ",";
                    } else {
                        mascotasInscrip = mascotasInscrip + id;
                    }
                    contMasIns++;
                }

                //Generacion de ids de mascotas ganadoras para poder sobreescribir al archivo concurso
                ArrayList<String> listIdMascotasGan = new ArrayList<>();
                for (Mascota mascIns : b.getMascotasInscri()) {
                    for (String mascGan : b.getGanadores()) {
                        if (mascGan.equals(mascIns.getNombre())) {
                            listIdMascotasGan.add(mascIns.getId());
                        }
                    }
                }
                //Para que aparezca sin los corchetes generamos un String con todos los ids
                String mascotasGanadoras = "";
                int contgan = 0;
                for (String idg : listIdMascotasGan) {
                    if (contgan != (listIdMascotasGan.size() - 1)) {
                        mascotasGanadoras = mascotasGanadoras + idg + ",";
                    } else {
                        mascotasGanadoras = mascotasGanadoras + idg;
                    }
                    contgan++;
                }
                //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                if((b.getMascotasInscri().size()!=0)&&(b.getGanadores().size()!=0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +mascotasGanadoras);
                }else if((b.getMascotasInscri().size()!=0)&&(b.getGanadores().size()==0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +"0,0");
                }else if((b.getMascotasInscri().size()==0)&&(b.getGanadores().size()!=0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +mascotasGanadoras);
                }else if((b.getMascotasInscri().size()==0)&&(b.getGanadores().size()==0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +"0,0");
                }
                bufferedWriter.newLine();

            }

            bufferedWriter.close();
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            conf.setTitle("Mensaje de confirmacion");
            conf.setHeaderText("La mascota fue agregada al Concurso");
            conf.showAndWait();

            App.setRoot("admConcurso");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }

    @FXML
    void switchToadmConcurso() throws IOException {
        App.setRoot("admConcurso");

    }

    public void campos() {
        colmCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoMascota"));

        admM.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));

    }

    public void llenartxt(Concurso c) {
        txtCodigo.setEditable(false);
        txtCodigo.setText(c.getCodigo());
    }
}
