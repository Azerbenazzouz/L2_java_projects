package Ex4;

public class Test {
    public static void main(String[] args) {
        Jeux j1=new Jeux(1); 
        Jeux j2=new Jeux(0); 
        Jeux j= j2;
        System.out.println(j.nombre + " et " + j.nb);

    }
}
