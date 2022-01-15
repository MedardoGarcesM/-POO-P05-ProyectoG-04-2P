package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdmCiudadController {

    @FXML
    private TableView <Ciudad>tabProv;

    @FXML
    private TableColumn <Ciudad,String> colCod;

    @FXML
    private TableColumn  <Ciudad , String>colNom;

    @FXML
    private TableColumn  <Ciudad, String> colProv;

    @FXML
    private Button btAgg;

    @FXML
    private Button btEli;

    @FXML
    private Button btEdit;

    @FXML
    private Button btMenu;
    
        @FXML
    private void initialize() {
          colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombreC"));
        colProv.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        
        
        tabProv.getItems().setAll(Ciudad.cargarCiudades(App.pathCiudades));  
    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }
}
    
