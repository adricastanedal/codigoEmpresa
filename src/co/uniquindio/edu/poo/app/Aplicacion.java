package co.uniquindio.edu.poo.app;

import co.uniquindio.edu.poo.model.Empleado;
import co.uniquindio.edu.poo.model.Empresa;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Aplicacion {
    public static void main(String[] args) {
        Empresa e = inicializarEmpresa();
        initMenu(e);
    }

    private static void initMenu(Empresa e){
        String mensaje = "Ingrese el índice de la opción a ejecutar: \n \n"+
                "1. Registrar nuevo empleado \n"+
                "2. Registrar hora de entrada de un empleado \n"+
                "3. Registrar hora de salida de un empleado \n"+
                "4. Buscar empleado \n"+
                "5. Consultar empleados que llegan tarde \n"+
                "6. Salir de la aplicación \n";

        int input;
        do {
            input = ingresarEntero(mensaje);
            switch (input){
                case 1:
                    registrarNuevoEmpleado (e);
                    break;
                case 2:
                    ingresarHoraSalida(e);
                    break;
                case 3:
                    ingresarHoraEntrada(e);
                    break;
                case 4:
                    buscarEmpleado(e);
                    break;
                case 5:
                    consultarEmpleadosQueLleganTarde(e);
                    break;
            }
        } while (input < 6);

        mostrarMensaje("Cerrando sesión...");
    }

    // -------------------------MÉTODOS DE REGISTRO -------------------------------------------------------------------//

    /* Registra un nuevo empleado con el nombre y la cédula ingresada, e inicializa las horas de entrada y salida en null.
     * @param e co.uniquindio.poo.model.Empresa a la que se añadirá el nuevo empleado.
     */
    private static void registrarNuevoEmpleado (Empresa e){
        String nombre = ingresarCadena("Ingrese el nombre del empleado: ");
        String cedula = ingresarCadena("Ingrese el número de cédula del empleado sin puntos ni separaciones: ");
        Empleado nuevoEmpleado = new Empleado(nombre, cedula, null, null);
        e.registrarEmpleado(nuevoEmpleado);
    }

    private static void ingresarHoraSalida(Empresa e){
        String cedula = ingresarCadena("Ingrese la cedula del empleado: ");
        String mensaje = "Ingrese la hora de salida del empleado. Use el formato HH:mm (Ej. 13:15) ";
        String razon = "HORA_SALIDA";
        mostrarMensaje(e.registrarHora(cedula, mensaje, razon));
    }

    private static void ingresarHoraEntrada(Empresa e){
        String cedula = ingresarCadena("Ingrese la cedula del empleado: ");
        String mensaje = "Ingrese la hora de entrada del empleado. Use el formato HH:mm (Ej. 13:15) ";
        String razon = "HORA_ENTRADA";
        mostrarMensaje(e.registrarHora(cedula, mensaje, razon));
    }

    // -----------------------MÉTODOS DE CONSULTA ---------------------------------------------------------------------//

    /* Solicita una cédula al usuario y busca el empleado correspondiente en la empresa.
     * Si lo encuentra, muestra su información; si no, informa del error.
     * @param e co.uniquindio.poo.model.Empresa en la que se realiza la búsqueda.
     */
    private static void buscarEmpleado(Empresa e){
        String cedula = ingresarCadena("Ingrese el número de cédula del empleado sin puntos ni separaciones: ");
        Empleado aux = e.buscarEmpleado(cedula);
        if (aux != null){
            mostrarMensaje(aux.toString());
        } else{
            mostrarMensaje("Error. Cédula no registrada. ");
        }
    }

    /* Solicita al usuario la hora de entrada estipulada por la empresa y muestra la lista de empleados que llegaron tarde.
     * @param e co.uniquindio.poo.model.Empresa cuya lista de empleados se va a consultar.
     */
    private static void consultarEmpleadosQueLleganTarde(Empresa e){
        LocalTime horaEntradaEmpresa = e.ingresarHora("Ingrese la hora de entrada " +
                "estipulada por la empresa en formato (HH:mm): (Ej. 13:15)");
        if(horaEntradaEmpresa != null){
            String mensaje = "Los empleados que han llegado tarde son: \n"+
                    e.listarEmpleadosQueLleganTarde(horaEntradaEmpresa);
            mostrarMensaje(mensaje);
        }else {
            mostrarMensaje("Error en el ingreso de la hora. ");
        }
    }

    //------------------------ MÉTODOS DE UTILIDAD ------------------------------------------------------------------//

    // Recibe un mensaje instructivo y retorna el texto ingresado por el usuario
    private static String ingresarCadena (String msj){
        String dato = JOptionPane.showInputDialog(null,msj);
        return dato;
    }

    // Recibe un mensaje instructivo y retorna un entero ingresado por el usuario
    private static int ingresarEntero (String msj){
        int dato = Integer.parseInt(JOptionPane.showInputDialog(null,msj));
        return dato;
    }

    // Recibe un mensaje y lo muestra en pantalla
    private static void mostrarMensaje (String msj){
        JOptionPane.showMessageDialog(null,msj);
    }

    // ----------------------MÉTODOS DE INICIALIZACIÓN ---------------------------------------------------------------//

    /* Crea una instancia de co.uniquindio.poo.model.Empresa con una lista de empleados predefinidos.
     * @return co.uniquindio.poo.model.Empresa inicializada con nombre "Angus" y su lista de empleados.
     */
    private static Empresa inicializarEmpresa() {
        Empresa e = new Empresa("Angus", getEmpleados());
        return e;
    }

    /* Crea y retorna una lista con empleados predefinidos para inicializar la empresa.
     * @return List<co.uniquindio.poo.model.Empleado> con los empleados de ejemplo
     */
    private static List<Empleado> getEmpleados(){
        List<Empleado> l = new ArrayList<>();
        Empleado e1 = new Empleado ("Ana","1006", LocalTime.of(14,30),
                LocalTime.of(20,00));
        Empleado e2 = new Empleado ("William","1193", LocalTime.of(14,00),
                LocalTime.of(20,00));
        l.add(e1);
        l.add(e2);
        return l;
    }
}