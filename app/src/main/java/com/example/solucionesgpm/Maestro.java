package com.example.solucionesgpm;

class Maestro {
    private String MaestroID;
    private String Edad;
    private String clase;
    private String Nombre;
    private String sexo;

    public Maestro() {
    }

    public Maestro(String MaestroID, String edad, String clase, String nombre, String sexo) {
        this.MaestroID = MaestroID;
        Edad = edad;
        this.clase = clase;
        Nombre = nombre;
        this.sexo = sexo;
    }

    public String getID() {
        return MaestroID;
    }

    public void setID(String ID) {
        this.MaestroID = MaestroID;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
