public class ProgramaPrincipalAES {
    public static void main(String[] args) {
        String missatge = "Aquest és un missatge secret.";
        String clau = "1234567890123456";
        System.out.println("Missatge original : " + missatge);

String xifrat = ClasseAES.encripta(missatge, clau);
System.out.println("Missatge xifrat   : " + xifrat);
String recuperat = ClasseAES.desencripta(xifrat, clau);
System.out.println("Missatge desxifrat: " + recuperat);

System.out.println("Està bé?       : " + missatge.equals(recuperat));

System.out.println();
System.out.println("P1: Encriptant dues vegades el mateix missatge amb la mateixa clau:");
System.out.println("  " + ClasseAES.encripta(missatge, clau));
System.out.println("  " + ClasseAES.encripta(missatge, clau));
System.out.println();
System.out.println("P2: Clau diferent");
System.out.println(ClasseAES.desencripta(xifrat, "6543210987654321"));
System.out.println("P3: Missatge diferent");
String altre = "Missatge diferent";
String altreXifrat = ClasseAES.encripta(altre, clau);
System.out.println("Xifrat    : " + altreXifrat);
System.out.println("desxifrat : " + ClasseAES.desencripta(altreXifrat, clau));
System.out.println();
System.out.println("P4: Clau longitud incorrecta");
System.out.println(ClasseAES.encripta(missatge, "12345"));
    }
}