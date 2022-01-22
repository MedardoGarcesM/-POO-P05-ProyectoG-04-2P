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
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    TableView<Concurso> adConcurso;

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
    @FXML
    private TableColumn<Concurso, String> colDirigido;
    @FXML
    private TextField txtFechaSistema;
    @FXML
    private TextField txtNomConcur;
    @FXML
    private TextField txtCierreIns;
    @FXML
    private TextField txtInicioIns;

    public void initialize() {

        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEvento"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("concursoAbierto"));
        colDirigido.setCellValueFactory(new PropertyValueFactory<>("dirigido"));

        adConcurso.getItems().setAll(Concurso.cargarArchivo(App.pathConcurso));
    }
    
    @FXML
    private void mostrarDetalles() throws IOException{
        Concurso p = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        txtNomConcur.setEditable(false); 
        txtNomConcur.setText(p.getNombre());
        
        txtCierreIns.setEditable(false);
        String diaC = String.valueOf(p.getFechaCierreInscripción().getDayOfMonth());
        String mesC = String.valueOf(p.getFechaCierreInscripción().getMonthValue());
        String anioC = String.valueOf(p.getFechaCierreInscripción().getYear());
        txtCierreIns.setText(diaC+"/"+mesC+"/"+anioC);
        
        txtInicioIns.setEditable(false);
        String diaI = String.valueOf(p.getFechaInicioInscripción().getDayOfMonth());
        String mesI = String.valueOf(p.getFechaInicioInscripción().getMonthValue());
        String anioI = String.valueOf(p.getFechaInicioInscripción().getYear());
        txtInicioIns.setText(diaI+"/"+mesI+"/"+anioI);
        
        String dia = String.valueOf(Concurso.fechaSistema.getDayOfMonth());
        String mes = String.valueOf(Concurso.fechaSistema.getMonthValue());
        String anio = String.valueOf(Concurso.fechaSistema.getYear());
        txtFechaSistema.setEditable(false); 
        txtFechaSistema.setText(dia+"/"+mes+"/"+anio);
    }
    
    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToCrearConcurso() throws IOException {
        App.setRoot("concurso");
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
        LocalDate fechac = Concurso.fechaSistema;
        if (d.getFechaEvento().compareTo(fechac) > 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editarConcurso.fxml"));//no tiene el controlador especificado
            EditarConcursoController ct = new EditarConcursoController();

            fxmlLoader.setController(ct);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            ct.llenarCombod();
            ct.llenarCombo(Ciudad.cargarCiudades(App.pathCiudades));
            ct.llenarComboa(Auspiciante.cargarAuspiciantes(App.pathAuspiciantes));
            ct.llenarCampos(d);
            App.changeRoot(root);
        } else{
            App.mostrarAlerta(Alert.AlertType.ERROR, "CONCURSO TERMINADO, NO SE PUEDEN EDITAR LOS DATOS");
            
        }

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
                    if(b.getMascotasInscri().size()!=0&&b.getGanadores().size()!=0){
                        bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +mascotasGanadoras);
                    }else if (b.getMascotasInscri().size()!=0&&b.getGanadores().size()==0){
                        bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +"0,0");
                    }else if (b.getMascotasInscri().size()==0&&b.getGanadores().size()!=0){
                        bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +mascotasGanadoras);
                    }else if((b.getMascotasInscri().size()==0)&&(b.getGanadores().size()==0)){
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
        //Si el concurso ya ha finalizado se deberá poder consultar la lista de ganadores.
        // menor a 0 primera fecha es anterior a segunda fecha
        LocalDate fechac = Concurso.fechaSistema;
        Concurso d = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        if (d.getFechaEvento().compareTo(fechac) < 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("consultarGanadores.fxml"));//no tiene el controlador especificado
            ConsultarGanadoresController ct = new ConsultarGanadoresController();

            fxmlLoader.setController(ct);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            ct.llenarCampo(d);
            App.changeRoot(root);
        }else {
            App.mostrarAlerta(Alert.AlertType.ERROR, "EL CONCURSO AUN NO ACABA, NO SE PUEDEN MOSTRAR GANADORES");
        }
    }
       
    private void mostrarVentana2() throws IOException{
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
        App.changeRoot(root);
   }
       

    // ventana para agregar concursantes
    private void mostrarVentana3() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("agregarMascota.fxml"));//no tiene el controlador especificado
        AgregarMascotaController ct = new AgregarMascotaController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios

        App.changeRoot(root);
    }

    @FXML
    private void anadirParticipantes() throws IOException {
        Concurso d = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        LocalDate fechac = Concurso.fechaSistema;
        boolean verfCierreIns = d.getFechaCierreInscripción().compareTo(fechac)>0;
        boolean verfInicioIns = d.getFechaInicioInscripción().compareTo(fechac)<0;
        //d.getFechaCierreInscripción();
        if((d.isConcursoAbierto())){
            if(verfInicioIns&&verfCierreIns){
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("agregarMascota.fxml"));//no tiene el controlador especificado
                AgregarMascotaController ct = new AgregarMascotaController();
                fxmlLoader.setController(ct);//se asigna el controlador

                VBox root = (VBox) fxmlLoader.load();
                ct.campos(d);
                ct.llenartxt(d);
                App.changeRoot(root);
            }else{
                App.mostrarAlerta(Alert.AlertType.ERROR, "El tiempo de inscripcion de mascotas culmino.");
            }
            
        }else {
            App.mostrarAlerta(Alert.AlertType.ERROR, "EL CONCURSO YA ACABO, NO SE PUEDEN INSCRIBIR MASCOTAS");
        }
    }
    
    @FXML
    public void probarCorreo() {
        Concurso concursoCorreo = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
        ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);
        if(concursoCorreo.isConcursoAbierto()){
            System.out.println("Enviar correo");
            Thread th = new Thread(new CorreoDue());
            th.start();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//mostrar informacion sobre el correo a enviar
            alert.setTitle("Informacion del correo");
            alert.setHeaderText("Enviar correo");
            alert.setContentText("Enviando correo....");
            alert.setContentText("Correo enviado!");
            alert.showAndWait();///

            System.out.println("Correo enviado!");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);//mostrar informacion sobre el correo a enviar
            alert.setTitle("Informacion del correo");
            alert.setHeaderText("Enviar no enviado");
            alert.setContentText("No se pueden enviar correos porque el concurso finalizo.");
            alert.showAndWait();
        }
        
    }
    
    class CorreoDue implements Runnable{        
        
        @Override
        public void run() {
            Concurso concursoCorreo = (Concurso) adConcurso.getSelectionModel().getSelectedItem();
            ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);
            for(Dueno d:duenos){
                System.out.println("ENVIADO MUCHACHO");
                String destinatario = d.getEmail();//agregar a todos los usuarios tipo dueño
                String asunto = "Invitacion al concurso: "+concursoCorreo.getNombre();
                String cuerpo = "Estimad@ "+d.getNombres()+" "+d.getApellidos()+" se lo invita a participar en el concurso "
                                +concursoCorreo.getNombre()+" que se realizara el dia "+concursoCorreo.getFechaEvento()+" en la ciudad de "+concursoCorreo.getCiudad().getNombreC()+"."
                                +"\nLe indicamos que tenga en consideracion los siguientes datos importantes: \n"
                                +" \n   Fecha de inicio de inscripcion: "+concursoCorreo.getFechaInicioInscripción()
                                +" \n   Fecha de cierre de inscripcion: "+concursoCorreo.getFechaCierreInscripción()
                                +" \n   Lugar del concurso: "+concursoCorreo.getLugar().toUpperCase()
                                +" \n   Concurso dirigido a: "+concursoCorreo.getDirigido().toLowerCase()+"s"
                                +" \n   Premio al primer lugar: $"+concursoCorreo.getPremios()[0]
                                +" \n   Premio al segundo lugar: $"+concursoCorreo.getPremios()[1]
                                +" \n   Premio al tercer lugar: $"+concursoCorreo.getPremios()[2]
                                +"\n\n"+
                                "Agradecemos al auspiciante del concurso "+concursoCorreo.getAuspiciantes().getNombreA().toUpperCase()+"."
                                +"\n\n"+
                                concursoCorreo.getAuspiciantes().getNombreA().toUpperCase()+" telefono: "+concursoCorreo.getAuspiciantes().getTelefonoA()+", direccion: "+concursoCorreo.getAuspiciantes().getDireccionA()+"-"+concursoCorreo.getAuspiciantes().getCiudadA()
                                +"\ncorreo electronico: "+concursoCorreo.getAuspiciantes().getEmailA()+", pagina web: "+concursoCorreo.getAuspiciantes().getWebPage();
                enviarCorreo(destinatario, asunto, cuerpo);
            }
        }
        
        public static final String remitente = "pooespol@gmail.com";
        public static final String clave = "FIEC_2022";

        public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave",clave);    //La clave de la cuenta
            props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(remitente));
                message.addRecipients(Message.RecipientType.TO, destinatario);


                message.setSubject(asunto);
                message.setText(cuerpo);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", remitente, clave);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
            catch (MessagingException me) {
                me.printStackTrace();   //Si se produce un error
            }
        }
        
    }
}
