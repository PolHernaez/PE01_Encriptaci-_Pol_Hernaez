
import java.util.InputMismatchException;
import java.util.Scanner;

public class EncriptacioPolH {
    public static void main(String[] args) {
        EncriptacioPolH p = new EncriptacioPolH();
        p.principal();
    }

    Scanner sc = new Scanner(System.in);

    public void principal() {
        System.out.println("BENVINGUT");
        int menu = 0;
        do {
            menu = llegirInt("\n 1- ENCRIPTAR\n 2- DESENCRIPTAR\n 3- Sortir\n");
            switch (menu) {
                case 1:
                    encriptar();
                    break;
                case 2:
                    desencriptar();
                    break;
                case 3:
                    System.out.println("Adeu");
                    break;
                default:
                    System.out.println("No pots");
            }
        } while (menu != 3);
    }

    public int llegirInt(String m) {
        int text = 0;
        boolean error = false;
        do {
            try {
                System.out.println(m);
                text = sc.nextInt();
                sc.nextLine();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Error, escriu numeros");
                error = true;
                sc.next();
            } catch (Exception e) {
                System.out.println("Error desconegut, torna");
                error = true;
                sc.next();
            }
        } while (error);
        return text;
    }

    public String llegirString(String m) {
        String text = "";
        boolean error = false;
        do {
            try {
                System.out.println(m);
                text = sc.nextLine();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Error, escriu lletres o numeros");
                error = true;
                sc.next();
            } catch (Exception e) {
                System.out.println("Error desconegut, torna");
                error = true;
                sc.next();
            }
        } while (error);
        return text;
    }

    public void encriptar() {

        String missatge = llegirString("Quin missatge vols enviar?:");
             String sortida="" ;
        do {String clau = llegirString("CLAU:");
         ClasseCriptografica c = new ClasseCriptografica(clau, missatge);
            sortida = c.encripta(missatge, clau);
            System.out.println(sortida);
        } while (sortida.equals("Aquesta clau no és possible"));
    }

    public void desencriptar() {
        String missatge = llegirString("Quin missatge vols desencriptar?:");
        
        String sortida="" ;
        do {String clau = llegirString("CLAU:");
         ClasseCriptografica c = new ClasseCriptografica(clau, missatge);
            sortida = c.desencripta(missatge, clau);
            System.out.println(sortida);
        } while (sortida.equals("Aquesta clau no és possible"));
    }

}