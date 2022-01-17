/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;


import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.App;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdmDuenosController{


    @FXML
    TableView<Dueno> adDuenio;
    @FXML
    private TableColumn<Dueno, String> colNombres;
    @FXML
    private TableColumn<Dueno, String> colApellidos;
    @FXML
    private TableColumn<Dueno, String> colCiudad;
    @FXML
    private Button agregardueñoAdD;
    @FXML
    private Button menuP;
    @FXML
    private TableColumn<Dueno, String> colCedula;
    @FXML
    private TableColumn<Dueno, String> colCorreo;
    @FXML
    private Button editarDueno;
    @FXML
    private Button elimDueno;
    
    @FXML
    public void initialize(){
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        adDuenio.getItems().setAll(Dueno.cargarDuenos(App.pathDuenos));
    }    
    
    @FXML
    private void switchToCrearDueno() throws IOException {
        App.setRoot("dueno");
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menu");
    }
    
    /*@FXML
    private void switchToEditarDueno() throws IOException{
        App.setRoot("editarDueno");
    }*/
    
        
    @FXML
    private void mostrarVentana() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarDueno.fxml"));//no tiene el controlador especificado
        EditarDuenoController ct = new EditarDuenoController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        ct.llenarCombo(Ciudad.cargarCiudades(App.pathCiudades));
        App.changeRoot(root);
    }
    
    @FXML
    private void editarDueno() throws IOException {
        Dueno d = (Dueno) adDuenio.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarDueno.fxml"));//no tiene el controlador especificado
        EditarDuenoController ct = new EditarDuenoController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCombo(Ciudad.cargarCiudades(App.pathCiudades));
        ct.llenarCampos(d);
        App.changeRoot(root);

    }
    
   

}