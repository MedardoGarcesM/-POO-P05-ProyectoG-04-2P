/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdmAuspicianteController{


    @FXML
    TableView<Auspiciante> admAusp;
    @FXML
    private TableColumn<Auspiciante, String> colmCod;
    @FXML
    private TableColumn<Auspiciante, String> colmNom;
    @FXML
    private TableColumn<Auspiciante, String> colmTel;
    @FXML
    private TableColumn<Auspiciante, String> colmCiu;
    @FXML
    private TableColumn<Auspiciante, String> colmPagweb;
    @FXML
    private Button botonAggAus;
    @FXML
    private Button botonEditAus;
    @FXML
    private Button botonElimAus;
    @FXML
    private Button botonMenuP;
    @FXML
    private Label labelTitulo;
    /**
     * Initializes the controller class.
     */  
    
    public void initialize(){
        colmCod.setCellValueFactory(new PropertyValueFactory<>("codigoA"));
        colmNom.setCellValueFactory(new PropertyValueFactory<>("nombreA"));
        colmTel.setCellValueFactory(new PropertyValueFactory<>("telefonoA"));
        colmCiu.setCellValueFactory(new PropertyValueFactory<>("ciudadA"));
        colmPagweb.setCellValueFactory(new PropertyValueFactory<>("webPage"));
        
        admAusp.getItems().setAll(Auspiciante.cargarAuspiciantes(App.pathAuspiciantes));
    }
    
    @FXML
    private void switchToAuspiciante() throws IOException {
        App.setRoot("auspiciante");
    }
    
    private void mostrarVentana() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarAuspciante.fxml"));//no tiene el controlador especificado
        EditarAuspcianteController ct = new EditarAuspcianteController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        App.changeRoot(root);
    }
    
    @FXML
    private void editarAusp() throws IOException {
        Auspiciante a = (Auspiciante) admAusp.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarAuspciante.fxml"));//no tiene el controlador especificado
        EditarAuspcianteController ct = new EditarAuspcianteController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCampos(a);
        App.changeRoot(root);

    }
    
    @FXML
    private void eliminarAusp() throws IOException {
        
        ArrayList<Auspiciante> auspiciantes = Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);
        
        Auspiciante ausp = (Auspiciante) admAusp.getSelectionModel().getSelectedItem();
        //int posicion =adDuenio.getSelectionModel().getSelectedIndex();
        int posicion=0;
        int posicionCiu=0;
        int posicionMascota=0;
        int posicionDue=0;
        int controladorPosiciones=0;
     
        System.out.println("Eliminando auspiciante");
        for(Auspiciante a :auspiciantes){
            if(ausp.getCodigoA().equals(a.getCodigoA())){
                posicion=auspiciantes.indexOf(a);
            }
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Debe de confirmar la ejecucion");
        alert.setContentText("Esta seguro que desea eliminar el auspiciante "+ausp+"?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //duenos.remove(posicion);
            System.out.println(auspiciantes.get(posicion)+" sera eliminado");           
            
            System.out.println(auspiciantes.remove(posicion)+" fue eliminado");
            
            try {
                FileWriter writer = new FileWriter("src/main/resources/"+App.pathAuspiciantes);//true significa que escribe al final del archivo
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                
                for(Auspiciante a1:auspiciantes){
                    //String codigoA,String nombreA, String direccionA, String telefonoA, String ciudadA, String emailA, String webPage
                    bufferedWriter.write(a1.getCodigoA()+","+a1.getNombreA()+","+a1.getDireccionA()+","+a1.getTelefonoA()+","+a1.getCiudadA()+","+a1.getEmailA()+","+a1.getWebPage());
                    bufferedWriter.newLine(); 
                }
               
                bufferedWriter.close();
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Mensaje de confirmacion");
                conf.setHeaderText("Auspiciante eliminado correctamente");
                //conf.setContentText("Dueno eliminado exitosamente");

                conf.showAndWait();            
                App.setRoot("admAuspiciante");
                
                
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ... user chose CANCEL or closed the
            System.out.println(auspiciantes.get(posicion)+" no fue eliminado");
        }
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

}
