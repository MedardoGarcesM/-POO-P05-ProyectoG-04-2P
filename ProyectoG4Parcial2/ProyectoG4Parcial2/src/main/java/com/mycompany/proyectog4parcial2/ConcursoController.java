/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import Main.Sistema;
import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class ConcursoController {

    @FXML
    private ComboBox< String> CCDirigidoa;

    @FXML
    private TextField CCnombre;

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
        for (Auspiciante a : Auspiciante.generarAus()) {
            CCAuspiciantes.getItems().addAll(a.getNombreA());
             MenuItem A1 = new MenuItem(a.getNombreA());
             CCPremiosAuspiciantes.getItems().add(A1);}
             

//Setea los premios 
        MenuItem m1 = new MenuItem("Primer lugar");
        MenuItem m2 = new MenuItem("Segundo lugar");
        MenuItem m3 = new MenuItem("Tercer lugar");
        CCPremiosLugar.getItems().add(m1);
        CCPremiosLugar.getItems().add(m2);
        CCPremiosLugar.getItems().add(m3);

        // setea la descricion 
        MenuItem n1 = new MenuItem("$1500");
        MenuItem n2 = new MenuItem("$750");
        MenuItem n3 = new MenuItem("$500");
        CCPremioDescripcion.getItems().add(n1);
        CCPremioDescripcion.getItems().add(n2);
        CCPremioDescripcion.getItems().add(n3);
        
        // setea los auspiciantes 
       

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

}
