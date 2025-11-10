import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


class Usuario {
    protected String id;
    protected String usuario;
    protected String clave;

    public Usuario() {

    }

    public Usuario(String id, String usuario, String clave) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
    }

    public void login() {

    }
    public void actualizarPerfil() {

    }
    public void eliminarPerfil() {

    }
    public void registrarUsuarioDB() {

    }
}

class Doctor extends Usuario {
    private String nombreDoctor;
    private String cedulaDoctor;
    private String especialidadDoctor;
    private List<Paciente> pacientes = new ArrayList<>();

    public Doctor(String id, String usuario, String clave, String nombre, String cedula, String especialidad) {
        super(id, usuario, clave);
        this.nombreDoctor = nombre;
        this.cedulaDoctor = cedula;
        this.especialidadDoctor = especialidad;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void administrarPaciente(Paciente p) {
        pacientes.add(p);
    }
    public void generarReporte() {
        System.out.println("Reporte generado por el doctor " + nombreDoctor);
    }
}


class Enfermero extends Usuario {
    private String nombreEnfermero;
    private String cedulaEnfermero;
    private String especialidadEnfermero;

    private String idReporte;
    private String tituloReporte;
    private LocalDateTime fechaReporte;
    private String contenidoReporte;

    private DateTimeFormatter formato;

    public Enfermero(String id, String usuario, String clave, String nombre, String cedula, String especialidad) {
        super(id, usuario, clave);
        this.nombreEnfermero = nombre;
        this.cedulaEnfermero = cedula;
        this.especialidadEnfermero = especialidad;
    }

    public Enfermero() {
    }

    public void establecerDatosReporte(String idReporte, String tituloReporte, LocalDateTime fechaReporte, String contenidoReporte) {
        this.idReporte = idReporte;
        this.tituloReporte = tituloReporte;
        this.fechaReporte = fechaReporte;
        this.contenidoReporte = contenidoReporte;
    }

    public void generarReporte() {
        if (idReporte == null || tituloReporte == null || fechaReporte == null || contenidoReporte == null) {
            System.out.println("Error: Faltan datos del reporte. Use establecerDatosReporte() primero.");
            return;
        }

        Document documento = new Document();
        try {
            String nombreArchivo = "Reporte_" + idReporte + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            documento.add(new Paragraph("----- REPORTE MÉDICO -----"));
            documento.add(new Paragraph("ID Reporte: " + idReporte));
            documento.add(new Paragraph("Título: " + tituloReporte));
            documento.add(new Paragraph("Fecha de generación: " + fechaReporte.format(formato)));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Contenido del reporte:"));
            documento.add(new Paragraph(contenidoReporte));

            documento.close();
            System.out.println("PDF generado correctamente: " + nombreArchivo);
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
        }
    }

    public void generarReporte(String idReporte, String tituloReporte, LocalDateTime fechaReporte, String contenidoReporte) {
        establecerDatosReporte(idReporte, tituloReporte, fechaReporte, contenidoReporte);
        generarReporte();
    }

    public String getNombreEnfermero() {
        return nombreEnfermero;
    }

    public void consultarTurno() {
    }

    public void notificacion() {
        System.out.println("Enfermero " + nombreEnfermero + " en espera de alerta");
    }
}

class Turno extends Enfermero{
    private String idTurno;

    public Turno(String id) {
        this.idTurno = id;
    }

    public void registrarEnfermero() {

    }
    public void administrarPaciente() {

    }
    public void generarHorario() {

    }
}

class Paciente {
    private String idPaciente;
    private String nombrePaciente;
    private String sexoPaciente;
    private String edadPaciente;
    private int nssPaciente;
    private String solucionPaciente;
    private String patologiaPaciente;
    private String condicionPaciente;
    private int camaPaciente;
    private String areaPaciente;
    private String servicioPaciente;

    public Paciente(String id, String nombre, String sexo, String edad, int nss, String patologia, String condicion, int cama, String area, String servicio) {
        this.idPaciente = id;
        this.nombrePaciente = nombre;
        this.sexoPaciente = sexo;
        this.edadPaciente = edad;
        this.nssPaciente = nss;
        this.patologiaPaciente = patologia;
        this.condicionPaciente = condicion;
        this.camaPaciente = cama;
        this.areaPaciente = area;
        this.servicioPaciente = servicio;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void administrarPacienteDB() {

    }

    public void registrarSolucion(String id, String descripcion) {
        Solucion nuevaSolucion = new Solucion(id, descripcion, this);
        Alerta nuevaAlerta = new Alerta(id, descripcion, this, "Alerta programada");
        nuevaSolucion.registrarTira(LocalDateTime.now());
        System.out.println("Solución: '" + descripcion + "' registrada para el paciente " + nombrePaciente);
    }

    public void generarReporte() {

    }

}

class Solucion {
    private String idSolucion;
    private String solucion;
    private Paciente paciente;

    public Solucion(String id, String solucion, Paciente paciente) {
        this.idSolucion = id;
        this.solucion = solucion;
        this.paciente = paciente;
    }

    public void registrarTira(LocalDateTime horaTira) {
        TiraHoraria nuevaTiraHoraria = new TiraHoraria(horaTira,  this);
    }

    public void velocidadInfusion() {

    }
    public void conteoGotas() {

    }
    public void gotasPorMinuto() {

    }
}

class Alerta extends Solucion {
    private String idAlerta;
    private String mensajeAlerta;
    private LocalDateTime fechaAlerta;
    private boolean leidoAlerta;

    public Alerta(String id, String solucion, Paciente paciente, String mensaje) {
        super(id, solucion, paciente);
        this.mensajeAlerta = mensaje;
        this.fechaAlerta = LocalDateTime.now();
        this.leidoAlerta = false;
        System.out.println("Alerta generada: " + mensajeAlerta);
    }

    public void notificacion() {
        try {
            HttpClient cliente = HttpClient.newHttpClient();

            String jsoncuerpo = "{"
                    + "\"idAlerta\":\"" + idAlerta + "\","
                    + "\"mensaje\":\"" + mensajeAlerta + "\","
                    + "\"fecha\":\"" + fechaAlerta + "\""
                    + "}";

            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsoncuerpo))
                    .build();

            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            System.out.println("Alerta enviada a la API. Código de respuesta: " + respuesta.statusCode());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al enviar la alerta a la API: " + e.getMessage());
        }
    }

    public void recibido() {
        this.leidoAlerta = true;
        System.out.println("Alerta marcada como leída.");
    }
}


class TiraHoraria {
    private LocalDateTime horarioTira;

    private Solucion solucion;

    public TiraHoraria(LocalDateTime horario, Solucion solucion) {
        this.horarioTira = horario;
        this.solucion = solucion;
        System.out.println("Formato de tira horaria creada para solución del paciente");
    }

    public void gotasMinuto() {

    }
    public void generarTira() {

    }
    public void descargarTira() {

    }
}


class Reporte{
    private String idReporte;
    private String tituloReporte;
    private LocalDateTime fechaReporte;
    private String contenidoReporte;
    //private TiraHoraria tiraHoraria;

    public Reporte(String id, String titulo, LocalDateTime fecha, String contenido) {
        //this.tiraHoraria = new TiraHoraria(fecha, solucion);
        this.idReporte = id;
        this.tituloReporte = titulo;
        this.fechaReporte = fecha;
        this.contenidoReporte = contenido;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }


    public void gotasMinuto() {

    }
    public void generarReporte() {

    }
    public void descargarReporte() {

    }
}

public class Main {
    public static void main(String[] args) {

        Doctor doctor = new Doctor("D01", "Dr. Acevedo", "Pass548!", "Andres de Jesus Acevedo", "DC00156", "Medicina Interna");
        Enfermero enfermero = new Enfermero("D01", "SergioD", "Pass545)", "Sergio Flores Martagon", "DC02350", "Enfermería de cuidados intensivos");
        Paciente paciente = new Paciente("P01", "Miguel Romero Torres", "Masculino", "34", 1324558453, "Hipertensión arterial", "Neurológicas", 17, "Cardiologo", "Hospitalización");
        Reporte reporte = new Reporte("R01", "Historial Médico", LocalDateTime.now(), "Paciente grave bajo tratamiento");

        System.out.println();

        paciente.registrarSolucion("S01", "Solución salina 0.7%");
        enfermero.notificacion();
        doctor.administrarPaciente(paciente);
        doctor.generarReporte();

        Alerta alerta = new Alerta("S01", "Solución salina 0.7%", paciente,
                "¡Alerta médica! Termino de solución salina 0.7% a paciente " + paciente.getNombrePaciente());

        alerta.notificacion();  // Envia la alerta mediante la API
        alerta.recibido();

        enfermero.generarReporte(
                "REP_002",
                "Reporte de Alerta Médica",
                LocalDateTime.now(),
                "ALERTA GENERADA:\n" +
                        "Paciente: " + paciente.getNombrePaciente() + "\n" +
                        "Tipo de alerta: Finalización de solución\n" +
                        "Solución: Solución salina 0.7%\n" +
                        "Hora de la alerta: " +  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                        "Acción tomada: Notificación enviada al personal médico\n" +
                        "Estado: Atención requerida"
        );

        System.out.println();

        System.out.println("Personal de salud y paciente:");
        System.out.println("Doctor: " + doctor.getNombreDoctor());
        System.out.println("Enfermero: " + enfermero.getNombreEnfermero());
        System.out.println("Paciente: " + paciente.getNombrePaciente());
        System.out.println("Reporte: " + reporte.getTituloReporte());
    }
}