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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Medardo Garces
 */
public class MascotaInscritaController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<String> lisM;
    
    @FXML
    private Button btMenu;
    
    @FXML
    private Button btRegresar;
    
    @FXML
    void switchToAdmConcurso() throws IOException {
        App.setRoot("admConcurso");
    }
    
    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }
     public void llenarCampo(Concurso c) { 
         
        ObservableList items = FXCollections.observableArrayList(
                c.getMascotasInscri());
       
        lisM.setItems(items);
    }
}
