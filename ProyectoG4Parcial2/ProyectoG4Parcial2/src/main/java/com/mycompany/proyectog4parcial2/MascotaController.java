/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class MascotaController{

    @FXML
    private Button guardarM;
    @FXML
    private Button cancelarM;
    @FXML
    private Button menuP;
    @FXML
    private TextField NombreM;
    @FXML
    private TextField razaM;
    @FXML
    private RadioButton perroM;
    @FXML
    private ToggleGroup tipoMascota;
    @FXML
    private RadioButton gatoM;
    @FXML
    private DatePicker nacimientoM;
    @FXML
    private Button fotoM;
    @FXML
    private ComboBox duenoM;

    /**
     * Initializes the controller class.
     */ 

    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menu");
    }
    
    private void switchToDetalleMascota() throws IOException{
        App.setRoot("detalleMascota");
    }
    
    @FXML
    private void switchToAdmMascota() throws IOException{
        App.setRoot("admMascotas");
    }

    @FXML
    private void buscarArchivo(ActionEvent event) {
    }
    
    @FXML
    private void initialize() {
        duenoM.getItems().setAll(Dueno.cargarDuenos(App.pathDuenos));
    }
    
    
    @FXML
    private void guardarMascota() {
        
        int posicion=0;
        ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);//cargar la lista del archivo
        ArrayList<Mascota> mascotas = Mascota.cargarMascotas(App.pathMascotas);
        ArrayList<Integer> idMascotas = new ArrayList<>();
        
        System.out.println("Creando mascota");
        
        for(Mascota m:mascotas){
            idMascotas.add((Integer) Integer.parseInt(m.getId()));
        }
        
        RadioButton selectedRadioButton = (RadioButton) tipoMascota.getSelectedToggle();
        String tipMascota = selectedRadioButton.getText();
        
        String perro = tipMascota.toUpperCase();
        Mascota masNueva = new Mascota(String.valueOf(Collections.max(idMascotas)+1), NombreM.getText(), tipMascota.toLowerCase(), razaM.getText(),String.valueOf(nacimientoM.getValue()),App.pathMascotas+"nofoto.png", (Dueno) duenoM.getValue());
        mascotas.add(masNueva);
        System.out.println("Nueva mascota:" + masNueva+" "+masNueva.getFechaNacimiento()+" "+masNueva.getTipoMascota()+" "+masNueva.getDueno());
        
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/"+App.pathMascotas);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Mascota m:mascotas){
                for(Dueno d:duenos){
                    if(m.getDueno().getCedula().equals(d.getCedula())){
                        posicion=duenos.indexOf(d);
                    }
                }
                Dueno dueEscrito = duenos.get(posicion);
                //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                bufferedWriter.write(m.getId()+";"+m.getNombre()+";"+m.getTipoMascota()+";"+m.getRaza()+";"+m.getFechaNacimiento()+";"+m.getFoto()+";"+dueEscrito.getCedula());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de confirmacion");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Nueva mascota agregado correctamente");

            alert.showAndWait();
            App.setRoot("admMascotas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
