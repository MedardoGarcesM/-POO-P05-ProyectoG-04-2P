/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.IOException;
import com.mycompany.proyectog4parcial2.modelo.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import utils.Correo;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdConcursoController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Concurso> adConcurso;

    @FXML
    private TableColumn<Concurso, String> colCod;

    @FXML
    private TableColumn<Concurso, String> colNombres;

    @FXML
    private TableColumn<Concurso, String> colFecha;
    @FXML
    private TableColumn<Concurso, String> colCiudad;
    @FXML
    private TableColumn<Concurso, String> colCorreo;
    @FXML
    private Button crearconcursoadC;
    @FXML
    private Button editarConcurso;
    @FXML
    private Button btaaconsultarM;
    @FXML
    private Button elimConcurso;
    @FXML
    private Button enviarCorreo;
    @FXML
    private Button menuP;
    @FXML
    private Button botonAnaPartici;
    @FXML
    private Button botonEditarGana;

    public void initialize() {

        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEvento"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("concursoAbierto"));

        adConcurso.getItems().setAll(Concurso.cargarArchivo(App.pathConcurso));
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToCrearConcurso() throws IOException {
        App.setRoot("concurso");
    }

    private void probarCorreo() {
        System.out.println("Enviar correo");
        String destinatario = "mjmoyano@espol.edu.ec";//agregar a todos los usuarios tipo dueño
        String asunto = "Invitacion a un nuevo concurso";
        String cuerpo = "Cuerpo de email.  Saludos";
        Correo.enviarCorreo(destinatario, asunto, cuerpo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);//mostrar informacion sobre el correo a enviar
        alert.setTitle("Informacion del correo");
        alert.setHeaderText("Enviar correo");
        alert.setContentText("Enviando correo....");
        alert.setContentText("Correo enviado!");
        alert.showAndWait();///

        System.out.println("Correo enviado!");
    }

    private void mostrarVentana() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarConcurso.fxml"));//no tiene el controlador especificado
        EditarConcursoController ct = new EditarConcursoController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        ct.llenarCombo(Ciudad.cargarCiudades(App.pathCiudades));
        ct.llenarComboa(Auspiciante.cargarAuspiciantes(App.pathAuspiciantes));
        ct.llenarCombod();
        App.changeRoot(root);
    }

    @FXML
    private void editarConcurso() throws IOException {
        Concurso d = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarConcurso.fxml"));//no tiene el controlador especificado
        EditarConcursoController ct = new EditarConcursoController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        ct.llenarCombod();
        ct.llenarCombo(Ciudad.cargarCiudades(App.pathCiudades));
        ct.llenarComboa(Auspiciante.cargarAuspiciantes(App.pathAuspiciantes));
        ct.llenarCampos(d);
        App.changeRoot(root);

    }

    @FXML
    private void eliminarConcurso() throws IOException {

        ArrayList<Concurso> concursos = Concurso.cargarArchivo(App.pathConcurso);//cargar la lista del archivo
        ArrayList<Ciudad> listCity = Ciudad.cargarCiudades(App.pathDuenos);
        ArrayList<Mascota> mascotas = Mascota.cargarMascotas(App.pathMascotas);

        Concurso concursoSelec = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        //int posicion =adDuenio.getSelectionModel().getSelectedIndex();
        int posicion=0;
        int posicionDue=0;

        System.out.println("Eliminando Concurso");

        for(Concurso con:concursos){
            if(con.getCodigo().equals(concursoSelec.getCodigo())){
                posicion=concursos.indexOf(con);
                System.out.println(concursos.get(posicion));
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Debe de confirmar la ejecucion");
        alert.setContentText("Esta seguro que desea eliminar el concurso "+concursoSelec.getNombre()+" con codigo "+concursoSelec.getCodigo()+"?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //duenos.remove(posicion);
            System.out.println(concursos.remove(posicion)+" fue eliminado");
            try {
                //"src/main/resources/"+
                FileWriter writer = new FileWriter(App.pathConcurso);//true significa que escribe al final del archivo
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                for(Concurso b:concursos){
                    String premios="";
                    int contPremios=0;
                    for(String prem:b.getPremios()){
                        if(contPremios!=(b.getPremios().length-1)){
                            premios=premios+prem+",";
                        }else{
                            premios=premios+prem;
                        }
                        contPremios++;
                    }
                    
                    //Generacion de ids de mascotas para poder sobreescribir al archivo concurso
                    ArrayList<String> listIdMascotasIns = new ArrayList<>();
                    for(Mascota mi:b.getMascotasInscri()){
                        listIdMascotasIns.add(mi.getId());
                    }
                    //Para que aparezca sin los corchetes generamos un String con todos los ids
                    String mascotasInscrip="";
                    int contMasIns=0;
                    for(String id:listIdMascotasIns){
                        if(contMasIns!=(listIdMascotasIns.size()-1)){
                            mascotasInscrip=mascotasInscrip+id+",";
                        }else{
                            mascotasInscrip=mascotasInscrip+id;
                        }
                        contMasIns++;
                    }
                    
                    
                    //Generacion de ids de mascotas ganadoras para poder sobreescribir al archivo concurso
                    ArrayList<String> listIdMascotasGan = new ArrayList<>();
                    for(Mascota mascIns:b.getMascotasInscri()){
                        for(String mascGan:b.getGanadores()){
                            if(mascGan.equals(mascIns.getNombre())){
                                listIdMascotasGan.add(mascIns.getId());                                
                            }
                        }
                    }
                    //Para que aparezca sin los corchetes generamos un String con todos los ids
                    String mascotasGanadoras="";
                    int contgan=0;
                    for(String idg:listIdMascotasGan){
                        if(contgan!=(listIdMascotasGan.size()-1)){
                            mascotasGanadoras=mascotasGanadoras+idg+",";
                        }else{
                            mascotasGanadoras=mascotasGanadoras+idg;
                        }
                        contgan++;
                    }
                    //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                    if(b.getMascotasInscri().size()!=0){
                        bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +mascotasGanadoras);
                    }else{
                        bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +"0,0");
                    }
                    bufferedWriter.newLine(); 
                }


                bufferedWriter.close();
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Mensaje de confirmacion");
                conf.setHeaderText("Concurso eliminada correctamente");
                conf.showAndWait();            

                App.setRoot("admConcurso");

            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ... user chose CANCEL or closed the
            System.out.println(concursos.get(posicion)+" no fue eliminado");
        }
    }

    @FXML
    private void anadirParticipantes(ActionEvent event) {
    }

   

     private void mostrarVentana1() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("consultarGanadores.fxml"));//no tiene el controlador especificado
        ConsultarGanadoresController ct = new ConsultarGanadoresController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        
        App.changeRoot(root);
    }
     @FXML
       private void consultarGanadores() throws IOException {
        Concurso d = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("consultarGanadores.fxml"));//no tiene el controlador especificado
        ConsultarGanadoresController ct = new ConsultarGanadoresController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        ct.llenarCampo(d);
        App.changeRoot(root);

    }
       private void mostrarVentana2() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mascotaInscrita.fxml"));//no tiene el controlador especificado
        MascotaInscritaController ct = new MascotaInscritaController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        
        App.changeRoot(root);
    }
        @FXML
       private void consultarM() throws IOException {
        Concurso d = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mascotaInscrita.fxml"));//no tiene el controlador especificado
        MascotaInscritaController ct = new MascotaInscritaController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        ct.llenarCampo(d);
        App.changeRoot(root);}
       

}
