package com.mycompany.proyectog4parcial2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AgregarMascotaController  {

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
    private TextField cantMa;

    @FXML
    private void agregarMC() {
        

    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }

    @FXML
    void switchToadmConcurso() throws IOException {
        App.setRoot("admConcurso");

    }
    public void campos(){
        colmCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoMascota"));
        

        admM.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));
       
    }
}
