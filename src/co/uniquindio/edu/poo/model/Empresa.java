package co.uniquindio.edu.poo.model;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;

     // relación
    private List<Empleado> listaEmpleados;

    // constructor
    public Empresa(String nombre, List<Empleado> listaEmpleados) {
        this.nombre = nombre;
        this.listaEmpleados = listaEmpleados;
    }

    //getters and setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }
    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    // Método para bucar un empleado en la lista
    public Empleado buscarEmpleado (String cedula){
        for(Empleado e: listaEmpleados){
            if(e.getCedula().equals(cedula)){
                return e;
            }
        }
        return null;
    }

    // Método para registrar la hora de entrada de un empleado
    public String registrarHora (String cedula, String mensaje, String razon){
        String resultado = "";
        Empleado aux = buscarEmpleado(cedula);
        if (aux != null){
            LocalTime h = ingresarHora(mensaje);
            if (h != null && razon.equals("HORA_ENTRADA")) {
                aux.setHoraEntrada(h);
                resultado = "Hora de entrada registrada con éxito. ";
            } else if (h != null && razon.equals("HORA_SALIDA")) {
                aux.setHoraSalida(h);
                resultado = "Hora de salida registrada con éxito. ";
            } else{
                resultado = "Error en registro de hora. Numero de intentos alcanzado. ";
            }
        }else{
            resultado = "Error. Cédula no registrada. ";
        }
        return resultado;
    }

    // Método que valida el ingreso de una hora
    public LocalTime ingresarHora(String mensaje){
        LocalTime hora = null;
        int contador = 0;
        do{
            try{
                hora = LocalTime.parse(JOptionPane.showInputDialog(null, mensaje));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error. Ingrese la hora en el formato indicado HH:mm (Ej. 13:15)");
            }
            contador++;
        } while(hora == null && contador < 3);
        return hora;
    }

    // Método para añadir un empleado a la lista de empleados
    public String registrarEmpleado (Empleado e){
        String mensaje = "";
        if(buscarEmpleado(e.getCedula()) == null){
            listaEmpleados.add(e);
            mensaje = "co.uniquindio.poo.model.Empleado añadido con éxito a la lista de empleados. ";
        }else{
            mensaje = "Error. El empleado ha sido registrado en la lista previamente. ";
        }
        return mensaje;
    }

    //Método que busca los empleados que llegaron tarde y los retorna en una lista
    public List<Empleado> listarEmpleadosQueLleganTarde(LocalTime horaEntradaEmpresa){
        List<Empleado> resultado = new ArrayList<>();
        for(Empleado e:listaEmpleados){
            if(e.llegoTarde(horaEntradaEmpresa)){
                resultado.add(e);
            }
        }
        return resultado;
    }
}
