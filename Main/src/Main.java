import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Enfermero(String id, String usuario, String clave, String nombre, String cedula, String especialidad) {
        super(id, usuario, clave);
        this.nombreEnfermero = nombre;
        this.cedulaEnfermero = cedula;
        this.especialidadEnfermero = especialidad;
    }

    public String getNombreEnfermero() {
        return nombreEnfermero;
    }

    public Enfermero() {

    }

    public void consultarTurno() {

    }
    public void generarReporte() {
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

class Alerta extends Solucion{
    private String idAlerta;
    private String mensajeAlerta;
    private LocalDateTime fechaAlerta;
    private boolean leidoAlerta;

    public Alerta(String id, String solucion, Paciente paciente, String mensaje) {
        super(id, solucion, paciente);
        this.mensajeAlerta = mensaje;
        System.out.println(mensajeAlerta);
    }

    public void notificacion() {

    }
    public void recibido() {

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

        System.out.println();

        System.out.println("Personal de salud y paciente:");
        System.out.println("Doctor: " + doctor.getNombreDoctor());
        System.out.println("Enfermero: " + enfermero.getNombreEnfermero());
        System.out.println("Paciente: " + paciente.getNombrePaciente());
        System.out.println("Reporte: " + reporte.getTituloReporte());
    }
}