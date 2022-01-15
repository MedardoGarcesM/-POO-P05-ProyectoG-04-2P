/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
/**
 * FXML Controller class
 *
 * @author majo_
 */
public class EditarMascotaController implements Initializable {

    @FXML
    private Label lblTituloM;
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
    private ComboBox dueñoM;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void switchToDetalleMascota(ActionEvent event) {
    }

    @FXML
    private void switchToAdmMascota(ActionEvent event) {
    }

    @FXML
    private void switchToMenuPrincipal(ActionEvent event) {
    }

    @FXML
    private void buscarArchivo(ActionEvent event) {
    }
    
    @FXML
    private void switchToAdmDueno() throws IOException {
        App.setRoot("admDuenos");
    }
    
    public void llenarComboM(ArrayList<Dueno> duenos) {
        dueñoM.getItems().setAll(duenos);
        
    }
    
   //public void llenarCamposM(Mascota m){
     //   lblTituloM.setText("Editar mascota");
       // razaM.setText(m.getRaza());
        //NombreM.setText(m.getNombre());
        //nacimientoM.setValue(m.getFechaNacimiento());
        //dueñoM.setValue(m.getDueno());
        //fotoM.setText(m.getFoto());
        //tipoMascota.setSelectedToggle(m.selectedRadioButton.getText());
       
    //}
    
}
