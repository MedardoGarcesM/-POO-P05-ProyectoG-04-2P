package com.mycompany.proyectog4parcial2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultarGanadoresController {

    @FXML
    private ListView<String> lisM;

    @FXML
    private Button btMenu;

    @FXML
    private Button btRegresar;

    @FXML
    void switchToAdmConcurso() throws IOException {
        App.setRoot("AdmConcurso");

    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

    public void llenarCampo(Concurso c) {
        ObservableList<String> items = FXCollections.observableArrayList(
                c.getGanadores());
        lisM.setItems(items);
    }
}
