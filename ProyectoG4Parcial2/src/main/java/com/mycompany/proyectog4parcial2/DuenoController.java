/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.App;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class DuenoController{


    @FXML
    private Button btguardar;
    @FXML
    private Button btcancel;
    @FXML
    private ComboBox cmbCiudad;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtApelli;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtDirecc;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button menuP;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToAdmDueno() throws IOException{
        App.setRoot("admDuenos");
    }
    
    @FXML
    private void initialize() {

        cmbCiudad.getItems().setAll(Ciudad.cargarCiudades(App.pathCiudades));


    }
    
    
    @FXML
    private void guardarEmpleado() {
        
        int posicion=0;
        ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
     
        System.out.println("Editando dueno");
        //Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
        Dueno d = new Dueno(txtCedula.getText(), txtNom.getText(), txtApelli.getText(), txtDirecc.getText(), txtTelefono.getText(),(Ciudad) cmbCiudad.getValue(), txtEmail.getText());
        duenos.add(d);//agregar empleado a la lista
        System.out.println("Nuevo dueno:" + d);
        
        
        try {
            //"src/main/resources/"+
            FileWriter writer = new FileWriter(App.pathDuenos);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Dueno m:duenos){
                for(Ciudad c:lisCiu){
                    if(m.getCiudad().getNombreC().equals(c.getNombreC())){
                        posicion=lisCiu.indexOf(c);
                    }
                }
                Ciudad ciu = lisCiu.get(posicion);
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(m.getCedula()+","+m.getApellidos()+","+m.getNombres()+","+m.getDireccion()+","+m.getTelefono()+","+ciu+","+m.getEmail());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de confirmacion");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Nuevo dueno agregado correctamente");

            alert.showAndWait();
            App.setRoot("admDuenos");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
