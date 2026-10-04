import java.math.BigDecimal;
import java.math.MathContext;

public class ClasseCriptografica {
    private String missatge;
    private String clau;

    public ClasseCriptografica(String clau, String missatge) {
        this.clau = clau;
        this.missatge = missatge;
    }

    private char[] Abecedari = { //Array per poder agafar el número de l'abecedari.
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'À', 'Á', 'Ç', 'È', 'É', 'Í', 'Ì', 'Ï', 'Ò', 'Ó', 'Ú', 'Ù', 'Ü', '!', '?', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'
    };

    public String encripta(String missatge, String clau) {

        int[] MissatgeNum = LletraNumero(missatge); //omple l'array, cada lletra passada a número
        String arrelStr = calculaArrel(clau, missatge.length()); //Fa l'arrel de els números junts de l'array
        if (arrelStr.length() >= missatge.length()) { //comprova que hi hagi una xifra per cada lletra
            return sumaNum(MissatgeNum, arrelStr);
        } else {
            return "Aquesta clau no és possible"; //l'arrel s'ha quedat curta, la clau no serveix
        }

    }

    public String desencripta(String missatge2, String clau2) {

        int[] MissatgeNum = LletraNumero(missatge2); //el mateix que a encripta, pero amb el text xifrat
        String arrelStr = calculaArrel(clau2, missatge2.length());
        if (arrelStr.length() >= missatge2.length()) {
            return restaNum(MissatgeNum, arrelStr); //aqui es resta en comptes de sumar
        } else {
            return "Aquesta clau no és possible";
        }

    }

    public String calculaArrel(String clau, int llargadaMissatge) {
        int[] clauNum = LletraNumero(clau);
        String concat = "";
        for (int i = 0; i < clauNum.length; i++) {
            concat = concat + clauNum[i];   //junta els números de les lletres
        }

        MathContext mc = new MathContext(llargadaMissatge + 10); //xifres que vull, una per lletra i 10 de marge
        BigDecimal arrel = new BigDecimal(concat).sqrt(mc); //big decimal ja que double no guarda mes de 17 decimals

        return arrel.toPlainString().replace(".", ""); //treu la coma i queda la tira de xifres seguides
    }

    public String numLletra(int num[]) { //fa el contrari de LletraNumero, de número a lletra
        String paraula = "";
        for (int i = 0; i < num.length; i++) {

            if (num[i] == 0) {
                paraula = paraula + " "; //el 0 vol dir que no era cap lletra de l'abecedari, hi poso un espai
            } else {
                paraula = paraula + Abecedari[num[i] - 1]; //-1 perque jo compto des de l'1 i els arrays des del 0
            }
        }
        return paraula;
    }

    public String sumaNum(int[] missatgeNum, String arrelStr) { //encriptar: lletra + xifra de l'arrel
        String cadena = "";
        int lletraArrel = 0;
        int suma[] = new int[missatgeNum.length];

        int lletraMissatge = 0;
        for (int i = 0; i < missatgeNum.length; i++) {
            if (missatgeNum[i] == 0) {
                suma[i] = 0; //els espais i el que no coneixo es queden igual, no els toco
            } else {
                lletraMissatge = missatgeNum[i];
                lletraArrel = arrelStr.charAt(i) - '0'; //el -'0' passa el caracter '4' al número 4
                suma[i] = lletraMissatge + lletraArrel;
                if (suma[i] > Abecedari.length) { //si em passo del final de l'abecedari
                    suma[i] = (suma[i] - Abecedari.length); //torno a començar pel principi
                }
            }

        }
        String traduccio = numLletra(suma); //passo tots els números a lletres de cop
        return traduccio;

    }

    public int[] LletraNumero(String text) {
        int[] cadena = new int[text.length()]; //una posició per cada caracter del text
        String textMajuscules = text.toUpperCase(); //tot a majúscules, l'abecedari només les té així
        for (int i = 0; i < textMajuscules.length(); i++) {
            char lletra = textMajuscules.charAt(i);
            for (int j = 0; j < Abecedari.length; j++) { //busco la lletra per tot l'abecedari

                if (Abecedari[j] == lletra) {   //si la lletra coincideix, guarda aquell número (+1)
                    int codi = j + 1;
                    cadena[i] = codi;
                }
            }
        }
        return cadena; //si no la troba, aquella posició es queda amb el 0 que hi posa Java
    }

    public String restaNum(int[] missatgeNum, String arrelStr) { //desencriptar: lletra - xifra de l'arrel

        int resta[] = new int[missatgeNum.length];

        int lletraMissatge = 0;
        for (int i = 0; i < missatgeNum.length; i++) {
            if (missatgeNum[i] == 0) {
                resta[i] = 0; //igual que a sumaNum, els espais no es toquen
            } else {
                lletraMissatge = missatgeNum[i];
                int lletraArrel = arrelStr.charAt(i) - '0';

                resta[i] = lletraMissatge - lletraArrel;
                if (resta[i] < 1) { //si em quedo per sota de l'1, que no és cap lletra
                    resta[i] = resta[i] + Abecedari.length; //dono la volta per l'altre costat
                }
            }

        }
        String traduccio = numLletra(resta);
        return traduccio;

    }
}
