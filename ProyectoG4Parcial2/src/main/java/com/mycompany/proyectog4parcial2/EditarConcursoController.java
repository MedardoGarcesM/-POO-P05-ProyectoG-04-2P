package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

public class EditarConcursoController {

    @FXML
    private Label lbs;

    @FXML
    private Label lbp;

    @FXML
    private ComboBox CCDirigidoa;

    @FXML
    private TextField CCnombre;

    @FXML
    private TextField CClugar;

    @FXML
    private DatePicker CCfechaEvento;

    @FXML
    private DatePicker CCinicioInscripcion;

    @FXML
    private DatePicker CCcierreInscripcion;

    @FXML
    private ComboBox CCciudad;

    @FXML
    private TextField CCp;
  @FXML
    private TextField txtCodigo;
    @FXML
    private ComboBox CCAuspiciantes;

    @FXML
    private TextField CChora;

    @FXML
    private Label lbt;

    @FXML
    private TextField CCs;

    @FXML
    private TextField CCt;

    @FXML
    private Button CCguardar;

    @FXML
    private Button CCCancelar;

    @FXML
    private Button menuP;

    @FXML
    void editarConcurso() {

    }

    @FXML
    void switchToAdmConcurso() throws IOException {
        App.setRoot("AdmConcurso");

    }

    public void llenarCombo(ArrayList<Ciudad> ciudades) {
        CCciudad.getItems().setAll(ciudades);
    }

    public void llenarComboa(ArrayList<Auspiciante> auspiciantes) {
        CCAuspiciantes.getItems().setAll(auspiciantes);

    }

    public void llenarCombod() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Perro", "Gato");
        CCDirigidoa.getItems().setAll(items);
    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }
   public void llenarCampos(Concurso d) {

        txtCodigo.setEditable(false);
        txtCodigo.setText(d.getCodigo());
        CCciudad.setValue(d.getCiudad());
        CCAuspiciantes.setValue(d.getAuspiciantes());
        CCnombre.setText(d.getNombre());
        CClugar.setText(d.getLugar());
        CCDirigidoa.setValue(d.getDirigido());
        CCinicioInscripcion.setValue(d.getFechaInicioInscripción());
        CCcierreInscripcion.setValue(d.getFechaCierreInscripción());
        CCfechaEvento.setValue(d.getFechaEvento());
        CChora.setText(d.getHoraEvento().toString());
       

    }

}