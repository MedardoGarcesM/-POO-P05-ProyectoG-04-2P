package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

public class EditarConcursoController {

    @FXML
    private Label lbs;

    @FXML
    private Label lbp;

    @FXML
    private ComboBox CCDirigidoa;

    @FXML
    private TextField CCnombre;

    @FXML
    private TextField CClugar;

    @FXML
    private DatePicker CCfechaEvento;

    @FXML
    private DatePicker CCinicioInscripcion;

    @FXML
    private DatePicker CCcierreInscripcion;

    @FXML
    private ComboBox CCciudad;

    @FXML
    private TextField CCp;
    @FXML
    private TextField txtCodigo;
    @FXML
    private ComboBox CCAuspiciantes;

    @FXML
    private TextField CChora;

    @FXML
    private Label lbt;

    @FXML
    private TextField CCs;

    @FXML
    private TextField CCt;

    @FXML
    private Button CCguardar;

    @FXML
    private Button CCCancelar;

    @FXML
    private Button menuP;

    @FXML
    void editarConcurso() {
        int posicion = 0;
        int posBuscad = 0;
        ArrayList<Concurso> concursos = Concurso.cargarArchivo(App.pathConcurso);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<String> gana = new ArrayList<>();
        ArrayList<Mascota> mas = new ArrayList<>();
        ArrayList<Auspiciante> auspiciante = Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);
        String[] premio = new String[]{CCp.getText() + "," + CCs.getText() + "," + CCt.getText()};
        String[] st = CChora.getText().split(":");
        LocalTime hora = LocalTime.of(Integer.parseInt(st[0]), Integer.parseInt(st[1]));

        for (Auspiciante a : auspiciante) {
            if (CCAuspiciantes.getValue().equals(a.getNombreA())) {
                posicion = auspiciante.indexOf(a);
            }
        }
        Auspiciante aaa = auspiciante.get(posicion);

        ArrayList<Integer> idC = new ArrayList<>();
        for (Concurso a : concursos) {
            idC.add((Integer) Integer.parseInt(a.getCodigo()));
        }
        String pos = String.valueOf(Collections.max(idC) + 1);

        System.out.println("Creando concurso");
        /*String nombre, datos[1]
        LocalDate fechaEvento, datos[2]
        LocalTime horaEvento, datos[3]
        LocalDate fechaInicioInscripción, datos[4]
        LocalDate fechaCierreInscripción, datos[5]
        Ciudad ciudad, datos[6]
        String lugar, datos[7]
        String[] premios, datos[8]
        Auspiciante auspiciantes, datos[9] 
        String dirigido, datos[10]
        String codigo, datos[0]
        boolean concursoAbierto, datos[11]
        ArrayList<Mascota> mascotasInscri, datos[12] 
        ArrayList<String> ganadores) datos[13]*/
        Concurso ceditado = new Concurso(CCnombre.getText(), CCfechaEvento.getValue(), hora, CCinicioInscripcion.getValue(), CCcierreInscripcion.getValue(), (Ciudad) CCciudad.getValue(), CClugar.getText(), premio, aaa, (String) CCDirigidoa.getValue(), txtCodigo.getText(), true, mas, gana);
        for (Concurso d : concursos) {
            if (ceditado.getCodigo().equals(d.getCodigo())) {
                posBuscad = concursos.indexOf(d);
            }
        }

        concursos.set(posBuscad, ceditado);
        try {
            //"src/main/resources/"+
            FileWriter writer = new FileWriter(App.pathConcurso);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Concurso b : concursos) {
                String premios = "";
                int contPremios = 0;
                for (String prem : b.getPremios()) {
                    if (contPremios != (b.getPremios().length - 1)) {
                        premios = premios + prem + ",";
                    } else {
                        premios = premios + prem;
                    }
                    contPremios++;
                }

                //Generacion de ids de mascotas para poder sobreescribir al archivo concurso
                ArrayList<String> listIdMascotasIns = new ArrayList<>();
                for (Mascota mi : b.getMascotasInscri()) {
                    listIdMascotasIns.add(mi.getId());
                }
                //Para que aparezca sin los corchetes generamos un String con todos los ids
                String mascotasInscrip = "";
                int contMasIns = 0;
                for (String id : listIdMascotasIns) {
                    if (contMasIns != (listIdMascotasIns.size() - 1)) {
                        mascotasInscrip = mascotasInscrip + id + ",";
                    } else {
                        mascotasInscrip = mascotasInscrip + id;
                    }
                    contMasIns++;
                }

                //Generacion de ids de mascotas ganadoras para poder sobreescribir al archivo concurso
                ArrayList<String> listIdMascotasGan = new ArrayList<>();
                for (Mascota mascIns : b.getMascotasInscri()) {
                    for (String mascGan : b.getGanadores()) {
                        if (mascGan.equals(mascIns.getNombre())) {
                            listIdMascotasGan.add(mascIns.getId());
                        }
                    }
                }
                //Para que aparezca sin los corchetes generamos un String con todos los ids
                String mascotasGanadoras = "";
                int contgan = 0;
                for (String idg : listIdMascotasGan) {
                    if (contgan != (listIdMascotasGan.size() - 1)) {
                        mascotasGanadoras = mascotasGanadoras + idg + ",";
                    } else {
                        mascotasGanadoras = mascotasGanadoras + idg;
                    }
                    contgan++;
                }
                //Mascota(String id,String nombre, String tipoMascota, String raza, String fechaNacimiento, String foto, Dueno dueno)
                if((b.getMascotasInscri().size()!=0)&&(b.getGanadores().size()!=0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +mascotasGanadoras);
                }else if((b.getMascotasInscri().size()!=0)&&(b.getGanadores().size()==0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + mascotasInscrip + ";" +"0,0");
                }else if((b.getMascotasInscri().size()==0)&&(b.getGanadores().size()!=0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +mascotasGanadoras);
                }else if((b.getMascotasInscri().size()==0)&&(b.getGanadores().size()==0)){
                    bufferedWriter.write(b.getCodigo() + ";" + b.getNombre() + ";" + b.getFechaEvento() + ";" + b.getHoraEvento() + ";" + b.getFechaInicioInscripción() + ";" + b.getFechaCierreInscripción() + ";" + b.getCiudad() + ";" + b.getLugar() + ";" + premios + ";" + b.getAuspiciantes() + ";" + b.getDirigido() + ";" + b.isConcursoAbierto() + ";" + "0,0" + ";" +"0,0");
                }
                bufferedWriter.newLine();

            }

            bufferedWriter.close();
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            conf.setTitle("Mensaje de confirmacion");
            conf.setHeaderText("Concurso agregado correctamente");
            conf.showAndWait();

            App.setRoot("admConcurso");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void switchToAdmConcurso() throws IOException {
        App.setRoot("AdmConcurso");

    }

    public void llenarCombo(ArrayList<Ciudad> ciudades) {
        CCciudad.getItems().setAll(ciudades);
    }

    public void llenarComboa(ArrayList<Auspiciante> auspiciantes) {
        CCAuspiciantes.getItems().setAll(auspiciantes);

    }

    public void llenarCombod() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Perro", "Gato");
        CCDirigidoa.getItems().setAll(items);
    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }

    public void llenarCampos(Concurso d) {

        txtCodigo.setEditable(false);
        txtCodigo.setText(d.getCodigo());
        CCciudad.setValue(d.getCiudad());
        CCAuspiciantes.setValue(d.getAuspiciantes());
        CCnombre.setText(d.getNombre());
        CClugar.setText(d.getLugar());
        CCDirigidoa.setValue(d.getDirigido());
        CCinicioInscripcion.setValue(d.getFechaInicioInscripción());
        CCcierreInscripcion.setValue(d.getFechaCierreInscripción());
        CCfechaEvento.setValue(d.getFechaEvento());
        CChora.setText(d.getHoraEvento().toString());
        CCp.setText(d.getPremios()[0]);
        CCs.setText(d.getPremios()[1]);
        CCt.setText(d.getPremios()[2]);

    }

}
