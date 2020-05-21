package com.example.solucionesgpm;

public class Clase {
    private String claseId;
    private String Classroom;
    private String Level;
    private String Name;
    private  String Professor;
    private String Time;

    public Clase() {
    }

    public Clase(String claseId, String classroom, String level, String name, String professor, String time) {
        claseId = claseId;
        Classroom = classroom;
        Level = level;
        Name = name;
        Professor = professor;
        Time = time;
    }

    public String getclaseId() {
        return claseId;
    }

    public void setclaseId(String id) {
        claseId = claseId;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String professor) {
        Professor = professor;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}



