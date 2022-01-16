/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdmCiudadController{


    @FXML
    TableView<Ciudad> tabProv;
    @FXML
    private TableColumn<Ciudad, String> colCod;
    @FXML
    private TableColumn<Ciudad, String> colNom;
    @FXML
    private TableColumn<Ciudad, String> colProv;
    @FXML
    private Button btAgg;
    @FXML
    private Button btEli;
    @FXML
    private Button btEdit;
    @FXML
    private Button btMenu;
    /**
     * Initializes the controller class.
     */    
    
    public void initialize(){
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombreC"));
        colProv.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        
        tabProv.getItems().setAll(Ciudad.cargarCiudades(App.pathCiudades));
    } 
    
    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    private void switchToCiudad() throws IOException {
        App.setRoot("ciudad");
    }
    
    @FXML
    private void eliminarCiudad() throws IOException {
        
        ArrayList<Ciudad> ciudades = Ciudad.cargarCiudades(App.pathCiudades);
        
        Ciudad c = (Ciudad) tabProv.getSelectionModel().getSelectedItem();
        //int posicion =adDuenio.getSelectionModel().getSelectedIndex();
        int posicion=0;
        int posicionCiu=0;
        int posicionMascota=0;
        int posicionDue=0;
        int controladorPosiciones=0;
     
        System.out.println("Eliminando dueno");
        for(Ciudad ciu :ciudades){
            if(c.getCodigo().equals(ciu.getCodigo())){
                posicion=ciudades.indexOf(ciu);
                System.out.println(ciudades.get(posicion));
            }
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Debe de confirmar la ejecucion");
        alert.setContentText("Esta seguro que desea eliminar la ciudad "+c+"?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //duenos.remove(posicion);
            System.out.println(ciudades.get(posicion)+" sera eliminado");           
            
            System.out.println(ciudades.remove(posicion)+" fue eliminado");
            
            try {
                FileWriter writer = new FileWriter("src/main/resources/"+App.pathCiudades);//true significa que escribe al final del archivo
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                
                for(Ciudad c1:ciudades){
                    //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                    bufferedWriter.write(c1.getCodigo()+","+c1.getNombreC()+","+c1.getProvincia());
                    bufferedWriter.newLine(); 
                }
               
                bufferedWriter.close();
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Mensaje de confirmacion");
                conf.setHeaderText("Ciudad eliminado correctamente");
                //conf.setContentText("Dueno eliminado exitosamente");

                conf.showAndWait();            
                App.setRoot("admCiudad");
                
                
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ... user chose CANCEL or closed the
            System.out.println(ciudades.get(posicion)+" no fue eliminado");
        }
    }
    
    private void mostrarVentana() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarCiudad.fxml"));//no tiene el controlador especificado
        EditarCiudadController ct = new EditarCiudadController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        App.changeRoot(root);
    }
    
    @FXML
    private void editarCiudad() throws IOException {
        Ciudad c = (Ciudad) tabProv.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarCiudad.fxml"));//no tiene el controlador especificado
        EditarCiudadController ct = new EditarCiudadController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCampos(c);
        App.changeRoot(root);

    }

    

}
