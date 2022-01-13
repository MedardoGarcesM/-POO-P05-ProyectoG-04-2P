/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.App;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdmMascotasController{

    @FXML
    private TableColumn<Mascota, String> colCodigo;
    @FXML
    private TableColumn<Mascota, String> colNombre;
    @FXML
    private TableColumn<Mascota, String> colTipo;
    @FXML
    private TableColumn<Mascota, String> colDueno;
    @FXML
    private TableColumn<Mascota, String> colFoto;
    @FXML
    private Button menuP;
    @FXML
    TableView adMascota;
    @FXML
    private Button agregarMascota;
    @FXML
    private Label labelNomMasco;
    @FXML
    private Button editMascota;
    @FXML
    private Button elimMascota;
    @FXML
    private ImageView imagMasco;

    @FXML
    private void switchToMascota() throws IOException {
        App.setRoot("mascota");
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menu");
    }
    
    public void initialize(){
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoMascota"));
        colDueno.setCellValueFactory(new PropertyValueFactory<>("dueno"));

        adMascota.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));
    }
    
    @FXML
    private void mostrarDetalle() throws IOException{
        Mascota m = (Mascota) adMascota.getSelectionModel().getSelectedItem();
        //se puede recuperar el indice del elemento recuperado con getSelectedIndex
        System.out.println(adMascota.getSelectionModel().getSelectedIndex());
        System.out.println(m);
        
        
        //mostrar la foto y nombre del empleado en la seccion derecha
        InputStream input = null;
        try {
            String fileName = App.pathImg + m.getNombre() + ".png";//armar la ruta de la foto
            labelNomMasco.setText(m.getNombre());
            //abrir el stream de la imagen de la persona
            input = App.class.getResource(fileName).openStream();
            
            //crear la imagen 
            Image image = new Image(input, 100, 100, false, false);
            imagMasco.setImage(image);

        } catch (Exception ex) {
            System.out.println("no se encuentra archivo de imagen");
            cargarFotoDefecto();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println("no se pudo cerrar");
                }
            }
        }
    }
    
    private void cargarFotoDefecto() throws IOException{
        InputStream  input = null;
        try {           
            input =  App.class.getResource(App.pathImg+"nofoto.png").openStream();
            Image image = new Image(input, 100, 100, false, false);
            imagMasco.setImage(image);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar foto por defecto");
        } finally {
            if (input!=null){
                //buscar archivo
            try {
                input.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el recurso");
            }
            }
        }
    }
    
    @FXML
    private void eliminarMascota() throws IOException {
        
        ArrayList<Mascota> mascotas = Mascota.cargarMascotas(App.pathMascotas);//cargar la lista del archivo
        ArrayList<Dueno> listDuenos = Dueno.cargarDuenos(App.pathDuenos);
        
        Mascota m = (Mascota) adMascota.getSelectionModel().getSelectedItem();
        //int posicion =adDuenio.getSelectionModel().getSelectedIndex();
        int posicion=0;
        int posicionDue=0;
     
        System.out.println("Eliminando dueno");
        
        for(Mascota mas:mascotas){
            if(mas.getId().equals(m.getId())){
                posicion=mascotas.indexOf(mas);
                System.out.println(mascotas.get(posicion));
            }
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Debe de confirmar la ejecucion");
        alert.setContentText("Esta seguro que desea eliminar a la mascota "+m+" con numero de identificacion "+m.getId()+"?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //duenos.remove(posicion);
            System.out.println(mascotas.remove(posicion)+" fue eliminado");
            try {
                FileWriter writer = new FileWriter("src/main/resources/"+App.pathMascotas);//true significa que escribe al final del archivo
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                
                for(Mascota m1:mascotas){
                    for(Dueno selecDu:listDuenos){
                        if(selecDu.getCedula().equals(m1.getDueno().getCedula())){
                            posicionDue=listDuenos.indexOf(selecDu);
                        }
                    }
                    Dueno d = listDuenos.get(posicionDue);
                    //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                    bufferedWriter.write(m1.getId()+";"+m1.getNombre()+";"+m1.getTipoMascota()+";"+m1.getRaza()+";"+m1.getFechaNacimiento()+";"+m1.getFoto()+";"+d.getCedula());
                    bufferedWriter.newLine(); 
                }
                
               
                bufferedWriter.close();
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Mensaje de confirmacion");
                conf.setHeaderText("Mascota eliminada correctamente");
                conf.showAndWait();            
                
                App.setRoot("admMascotas");
                
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ... user chose CANCEL or closed the
            System.out.println(mascotas.get(posicion)+" no fue eliminado");
        }
    }
    
    @FXML
    private void editarMascota() throws IOException {
        Mascota m = (Mascota) adMascota.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarMascota.fxml"));//no tiene el controlador especificado
        EditarMascotaController dt = new EditarMascotaController();

        fxmlLoader.setController(dt);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load(); 

        dt.llenarComboM(Dueno.cargarDuenos (App.pathDuenos));
        dt.llenarCamposM(m);
        App.changeRoot(root);

    }
    
}
