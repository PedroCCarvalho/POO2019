import java.io.Serializable;
import java.util.ArrayList;

public class Pessoa implements Serializable {
    private String user=null, email=null;
    private ArrayList<Tarefa> tarefas;

    public Pessoa(String user, String email){
        this.user=user;
        this.email = email;
    }

    public String getUser(){return user;}

    public String getEmail() {return email;}

    public ArrayList<Tarefa> getTarefas() {return tarefas;}

}