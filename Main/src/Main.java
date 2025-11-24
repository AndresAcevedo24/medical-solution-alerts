import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalTime;



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

    public String getSolucion() {
        return solucion;
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
    public List<String> generarTiraHoraria() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Generación de formato de tira horaria");

        System.out.println("¿Cuántos mililitros tiene la solución?");
        System.out.println("1) 250 ml");
        System.out.println("2) 500 ml");
        System.out.println("3) 1000 ml");
        System.out.println("4) Otro");
        int opcion = sc.nextInt();
        sc.nextLine();

        int totalMl;
        switch (opcion) {
            case 1 -> totalMl = 250;
            case 2 -> totalMl = 500;
            case 3 -> totalMl = 1000;
            case 4 -> {
                System.out.print("Ingrese el volumen en ml: ");
                totalMl = sc.nextInt();
                sc.nextLine();
            }
            default -> throw new IllegalArgumentException("Opción inválida");
        }

        System.out.print("¿En cuántas horas debe administrarse? ");
        int horasTotales = sc.nextInt();
        sc.nextLine();

        System.out.print("Hora de inicio (formato HH:mm): ");
        String horaTexto = sc.nextLine();
        LocalTime horaInicio = LocalTime.parse(horaTexto);

        System.out.println("¿Cada cuánto marcar?");
        System.out.println("1) Cada 100 ml");
        System.out.println("2) Cada 25% del volumen total");
        System.out.println("3) Otro intervalo (en ml)");
        int marcaOp = sc.nextInt();
        sc.nextLine();

        int intervaloMl;
        switch (marcaOp) {
            case 1 -> intervaloMl = 100;
            case 2 -> intervaloMl = totalMl / 4;
            case 3 -> {
                System.out.print("Indique el intervalo (ml): ");
                intervaloMl = sc.nextInt();
                sc.nextLine();
            }
            default -> throw new IllegalArgumentException("Opción inválida");
        }

        List<String> tira = new ArrayList<>();

        double horasPorMl = (double) horasTotales / totalMl;

        for (int ml = totalMl; ml >= 0; ml -= intervaloMl) {
            double horasPasadas = ml == totalMl ? 0 : (totalMl - ml) * horasPorMl;
            LocalTime horaMarca = horaInicio.plusMinutes((long)(horasPasadas * 60));

            String linea = ml + " ml  →  " + horaMarca;
            tira.add(linea);
        }

        return tira;
    }

    public void descargarTira(
            Paciente paciente,
            Enfermero enfermero,
            Solucion solucion,
            List<String> horas,
            List<String> volumenes
    ) {
        Document documento = new Document(PageSize.A4);

        try {
            String nombreArchivo = "Formato_THR_001_" +
                    paciente.getNombrePaciente().replace(" ", "_") + ".pdf";

            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            float topY = writer.getVerticalPosition(true); // posición inicial superior

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph titulo = new Paragraph("TIRA HORARIA DE INFUSIÓN\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Font infoFont = new Font(Font.FontFamily.HELVETICA, 13);

            Paragraph info = new Paragraph(
                    "Paciente: " + paciente.getNombrePaciente() + "\n" +
                            "Enfermero responsable: " + enfermero.getNombreEnfermero() + "\n" +
                            "Solución administrada: " + solucion.getSolucion() + "\n" +
                            "Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                            "\n\n",
                    infoFont
            );
            info.setAlignment(Element.ALIGN_CENTER);
            documento.add(info);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(50);
            tabla.setSpacingBefore(15);
            tabla.setSpacingAfter(15);
            tabla.setWidths(new float[]{2, 1});
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);

            PdfPCell h1 = new PdfPCell(new Phrase("Hora", headerFont));
            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
            h1.setPadding(10);
            h1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            h1.setBorderWidth(2);

            PdfPCell h2 = new PdfPCell(new Phrase("Volumen (ml)", headerFont));
            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
            h2.setPadding(10);
            h2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            h2.setBorderWidth(2);

            tabla.addCell(h1);
            tabla.addCell(h2);

            Font contenidoFont = new Font(Font.FontFamily.HELVETICA, 13);
            for (int i = 0; i < horas.size(); i++) {
                PdfPCell c1 = new PdfPCell(new Phrase(horas.get(i), contenidoFont));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setPadding(5);
                c1.setBorderWidth(1.5f);

                PdfPCell c2 = new PdfPCell(new Phrase(volumenes.get(i), contenidoFont));
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c2.setPadding(5);
                c2.setBorderWidth(1.5f);

                tabla.addCell(c1);
                tabla.addCell(c2);
            }

            documento.add(tabla);

            float bottomY = writer.getVerticalPosition(true);


            PdfContentByte cb = writer.getDirectContent();
            cb.saveState();
            cb.setLineWidth(1f);
            cb.setLineDash(4f, 4f);

            float left = 90;
            float width = (documento.getPageSize().getWidth() - 80) - 100;

            cb.rectangle(left, bottomY - 10, width, (topY - bottomY) + 20);
            cb.stroke();

            cb.restoreState();

            Font plegableFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);

            Paragraph nota = new Paragraph(
                    "\n\n----- Zona de corte / plegado para colocar en la bolsa de solución -----\n\n",
                    plegableFont
            );
            nota.setAlignment(Element.ALIGN_CENTER);
            documento.add(nota);

            Paragraph miniEtiqueta = new Paragraph(
                    "Paciente: " + paciente.getNombrePaciente() + "\n" +
                            "Solución: " + solucion.getSolucion() + "\n" +
                            "Inicio: " + horas.get(0) + "\n" +
                            "Volumen total: " + volumenes.get(0) + "\n",
                    new Font(Font.FontFamily.HELVETICA, 12)
            );
            miniEtiqueta.setAlignment(Element.ALIGN_CENTER);
            documento.add(miniEtiqueta);

            documento.close();
            System.out.println("PDF generado correctamente: " + nombreArchivo);

        } catch (Exception e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
        }
    }

}


class Reporte{
    private String idReporte;
    private String tituloReporte;
    private LocalDateTime fechaReporte;
    private String contenidoReporte;

    public Reporte(String id, String titulo, LocalDateTime fecha, String contenido) {
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
        TiraHoraria tira = new TiraHoraria(LocalDateTime.now(), new Solucion("S01", "Solución salina 0.7%", paciente));
        List<String> tiraHoraria = tira.generarTiraHoraria();

        List<String> horas = new ArrayList<>();
        List<String> volumenes = new ArrayList<>();

        for (String linea : tiraHoraria) {
            String[] partes = linea.split("→");

            String volumen = partes[0].trim();
            String hora = partes[1].trim();

            volumenes.add(volumen);
            horas.add(hora);
        }


        System.out.println();


        System.out.println("\n- Formato de tira horaria creada con exito -");
        tiraHoraria.forEach(System.out::println);

        paciente.registrarSolucion("S01", "Solución salina 0.7%");
        enfermero.notificacion();
        doctor.administrarPaciente(paciente);
        doctor.generarReporte();

        Alerta alerta = new Alerta("S01", "Solución salina 0.7%", paciente,
                "¡Alerta médica! Termino de solución salina 0.7% a paciente " + paciente.getNombrePaciente());

        alerta.notificacion();
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

        tira.descargarTira(
                paciente,
                enfermero,
                new Solucion("S01", "Solución salina 0.7%", paciente),
                horas,
                volumenes
        );


        System.out.println();

        System.out.println("Personal de salud y paciente:");
        System.out.println("Doctor: " + doctor.getNombreDoctor());
        System.out.println("Enfermero: " + enfermero.getNombreEnfermero());
        System.out.println("Paciente: " + paciente.getNombrePaciente());
        System.out.println("Reporte: " + reporte.getTituloReporte());
    }
}