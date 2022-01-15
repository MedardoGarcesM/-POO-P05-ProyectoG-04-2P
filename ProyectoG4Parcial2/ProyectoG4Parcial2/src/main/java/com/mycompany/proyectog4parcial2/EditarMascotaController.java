/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import Main.Extras;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author majo_
 */
public class EditarMascotaController{

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
    @FXML
    private ImageView ivFMascotaa;
    /**
     * Initializes the controller class.
     */  

    @FXML
    private void switchToAdmMascota() throws IOException{
        App.setRoot("admMascotas");
    }

    @FXML
    private void switchToMenuPrincipal()  throws IOException{
        App.setRoot("menu");
    }

    @FXML
    private void buscarArchivo(ActionEvent event)throws IOException  {
        System.out.println("MAJOOO WIIIII");
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");//para abrir el explorador de archivo

        // Agregar filtros para facilitar la busqueda, solo busca archivos jpg o png
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File imgFile = fileChooser.showOpenDialog(null); //obtengo la foto que escogio el usuario

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            System.out.println(imgFile.getAbsolutePath());//recupero ruta de la imagen
            ivFMascotaa.setImage(image);//muestro la foto en el image view
            Path from = Paths.get(imgFile.toURI()); //copiar la imagen
            Path to = Paths.get("archivos/" + imgFile.getName());
            Files.copy(from, to);
        
 } 
        
    }
    
    public void llenarComboM(ArrayList<Dueno> duenos) {
        dueñoM.getItems().setAll(duenos);
        
    }
    
    public void llenarCamposM(Mascota m){
        lblTituloM.setText("Editar mascota");
        razaM.setText(m.getRaza());
        NombreM.setText(m.getNombre());
        String[] fecha = m.getFechaNacimiento().replace("-", "/").split("/");
        int anio = Integer.parseInt(fecha[0]);
        int mes = Integer.parseInt(fecha[1]);
        int dia = Integer.parseInt(fecha[2]);
        LocalDate.of(anio, mes, dia);
        nacimientoM.setValue(LocalDate.of(anio, mes, dia));
        dueñoM.setValue(m.getDueno());
        fotoM.setText(m.getFoto());
        //perroM gatoM
        if(m.getTipoMascota().equals("perro")){
            tipoMascota.selectToggle(perroM);
        }else{
            tipoMascota.selectToggle(gatoM);
        }
    }
    
    @FXML
    private void guardarEdicion() {
        
        int posicion = 0;
        int posBuscad = 0;
        int id = 1;
        int contador = 1;
        ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);//cargar la lista del archivo
        ArrayList<Mascota> mascotas = Mascota.cargarMascotas(App.pathMascotas);
        ArrayList<Integer> idMascotas = new ArrayList<>();
        
        System.out.println("Editando mascota");
        
        for(Mascota m:mascotas){
            idMascotas.add((Integer) Integer.parseInt(m.getId()));
            if(NombreM.getText().equals(m.getNombre())){
                id=contador;
            }
            contador++;
        }
        
        RadioButton selectedRadioButton = (RadioButton) tipoMascota.getSelectedToggle();
        String tipMascota = selectedRadioButton.getText();
        String perro = tipMascota.toUpperCase();
        
        Mascota masEditada = new Mascota(String.valueOf(id), NombreM.getText(), tipMascota.toLowerCase(), razaM.getText(),String.valueOf(nacimientoM.getValue()),App.pathMascotas+"nofoto.png", (Dueno) dueñoM.getValue());
        
        for(Mascota m:mascotas){
            if(masEditada.getId().equals(m.getId())){
                posBuscad=mascotas.indexOf(m);
            }
        }
        
        mascotas.set(posBuscad, masEditada);
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
            alert.setContentText("Mascota editada correctamente");

            alert.showAndWait();
            App.setRoot("admMascotas");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    
    
}
