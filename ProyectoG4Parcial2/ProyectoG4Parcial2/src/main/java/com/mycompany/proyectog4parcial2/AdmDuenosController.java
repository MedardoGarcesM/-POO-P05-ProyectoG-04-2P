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
    
    @FXML
    private void eliminarDueno() throws IOException {
        
        ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu =Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<Mascota> mascotas = Mascota.cargarMascotas(App.pathMascotas);
        ArrayList<Integer> listapos = new ArrayList<>();
        
        Dueno d = (Dueno) adDuenio.getSelectionModel().getSelectedItem();
        //int posicion =adDuenio.getSelectionModel().getSelectedIndex();
        int posicion=0;
        int posicionCiu=0;
        int posicionMascota=0;
        int posicionDue=0;
        int controladorPosiciones=0;
     
        System.out.println("Eliminando dueno");
        for(Dueno due :duenos){
            if(d.getCedula().equals(due.getCedula())){
                posicion=duenos.indexOf(due);
                System.out.println(duenos.get(posicion));
            }
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Debe de confirmar la ejecucion");
        alert.setContentText("Esta seguro que desea eliminar al dueno "+d+" con numero de cedula "+d.getCedula()+"?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //duenos.remove(posicion);
            System.out.println(duenos.get(posicion)+" sera eliminado");
            
            
            for(Mascota mascotaeliminada:mascotas){
                if(mascotaeliminada.getDueno().getCedula().equals(duenos.get(posicion).getCedula())){
                    listapos.add(mascotas.indexOf(mascotaeliminada));
                }
            }
            
            if(listapos.size()>0){
                for(Integer i:listapos){
                    mascotas.remove(i.intValue()+controladorPosiciones);
                    controladorPosiciones--;
                }
            }
            
            
            try {
                FileWriter writer = new FileWriter(App.pathMascotas);//true significa que escribe al final del archivo
                BufferedWriter bf = new BufferedWriter(writer);

                for(Mascota m1:mascotas){
                    for(Dueno selecDu:duenos){
                        if(selecDu.getCedula().equals(m1.getDueno().getCedula())){
                            posicionDue=duenos.indexOf(selecDu);
                        }
                    }
                    Dueno dsel = duenos.get(posicionDue);
                    //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                    bf.write(m1.getId()+";"+m1.getNombre()+";"+m1.getTipoMascota()+";"+m1.getRaza()+";"+m1.getFechaNacimiento()+";"+m1.getFoto()+";"+dsel.getCedula());
                    bf.newLine(); 
                }


                bf.close();
                /*Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Mensaje de confirmacion");
                conf.setHeaderText("Mascota eliminada correctamente");
                conf.showAndWait();*/
            
            }catch (IOException e) {
                e.printStackTrace();
            }
            
            System.out.println(duenos.remove(posicion)+" fue eliminado");
            
            try {
                FileWriter writer = new FileWriter(App.pathDuenos);//true significa que escribe al final del archivo
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                
                for(Dueno m:duenos){
                    for(Ciudad c:lisCiu){
                        if(m.getCedula().equals(c.getNombreC())){
                            posicionCiu=lisCiu.indexOf(c);
                        }
                    }
                    Ciudad ciu = lisCiu.get(posicionCiu);
                    //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                    bufferedWriter.write(m.getCedula()+","+m.getApellidos()+","+m.getNombres()+","+m.getDireccion()+","+m.getTelefono()+","+ciu+","+m.getEmail());
                    bufferedWriter.newLine(); 
                }
               
                bufferedWriter.close();
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Mensaje de confirmacion");
                conf.setHeaderText("Dueno eliminado correctamente");
                //conf.setContentText("Dueno eliminado exitosamente");

                conf.showAndWait();            
                App.setRoot("admDuenos");
                
                
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ... user chose CANCEL or closed the
            System.out.println(duenos.get(posicion)+" no fue eliminado");
        }
    }
    
   

}