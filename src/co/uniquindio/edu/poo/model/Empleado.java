package co.uniquindio.edu.poo.model;

import java.time.LocalTime;

public class Empleado {
    private String nombre;
    private String cedula;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    // Constructor
    public Empleado(String nombre, String cedula, LocalTime horaEntrada, LocalTime horaSalida) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    // getters and setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    //toString
    @Override
    public String toString() {
        return ("co.uniquindio.poo.model.Empleado: " +'\n' +
                " Nombre = " + nombre + '\n' +
                " Cedula = " + cedula + '\n' +
                " Hora de Entrada registrada = " + horaEntrada +'\n'+
                " Hora de Salida registrada = " + horaSalida + '\n'+"\n");
    }

    //Método para verificar si el empleado llegó tarde
    public boolean llegoTarde(LocalTime horaEntradaEmpresa){
        boolean resultado = false;
        if(horaEntrada.isAfter(horaEntradaEmpresa)){
            resultado = true;
        }
        return resultado;
    }
}
