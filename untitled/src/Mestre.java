public class Mestre extends Bolseiro{
    private Pessoa orientador;
    public Mestre(String user, String email, Data diai, Data diaf, Pessoa orientador) {
        super(user, email, diai, diaf);
        this.orientador=orientador;
    }

    public Pessoa getOrientador() {return orientador;}

    public String getTipo() {return "Mestre";}
}