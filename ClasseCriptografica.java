import java.math.BigDecimal;
import java.math.MathContext;

public class ClasseCriptografica {
    private String missatge;
    private String clau;

    public ClasseCriptografica(String clau, String missatge) {
        this.clau = clau;
        this.missatge = missatge;
    }

    private char[] Abecedari = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'À', 'Á', 'Ç', 'È', 'É', 'Í', 'Ì', 'Ï', 'Ò', 'Ó', 'Ú', 'Ù', 'Ü', '!', '?', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'
    };

    public String encripta(String missatge, String clau) {

        int[] MissatgeNum = LletraNumero(missatge);
        String arrelStr = calculaArrel(clau, missatge.length());
        if (arrelStr.length() >= missatge.length()) {
            return sumaNum(MissatgeNum, arrelStr);
        } else {
            return "Aquesta clau no és possible";
        }

    }

    public String desencripta(String missatge2, String clau2) {

        int[] MissatgeNum = LletraNumero(missatge2);
        String arrelStr = calculaArrel(clau2, missatge2.length());
        if (arrelStr.length() >= missatge2.length()) {
            return restaNum(MissatgeNum, arrelStr);
        } else {
            return "Aquesta clau no és possible";
        }

    }

    public String calculaArrel(String clau, int llargadaMissatge) {
        int[] clauNum = LletraNumero(clau);
        String concat = "";
        for (int i = 0; i < clauNum.length; i++) {
            concat = concat + clauNum[i];
        }

        MathContext mc = new MathContext(llargadaMissatge + 10);
        BigDecimal arrel = new BigDecimal(concat).sqrt(mc);

        return arrel.toPlainString().replace(".", "");
    }

    public String numLletra(int num[]) {
        String paraula = "";
        for (int i = 0; i < num.length; i++) {

            if (num[i] == 0) {
                paraula = paraula + " ";
            } else {
                paraula = paraula + Abecedari[num[i] - 1];
            }
        }
        return paraula;
    }

    public String sumaNum(int[] missatgeNum, String arrelStr) {
        String cadena = "";
        int lletraArrel = 0;
        int suma[] = new int[missatgeNum.length];

        int lletraMissatge = 0;
        for (int i = 0; i < missatgeNum.length; i++) {
            if (missatgeNum[i] == 0) {
                suma[i] = 0;
            } else {
                lletraMissatge = missatgeNum[i];
                lletraArrel = arrelStr.charAt(i) - '0';
                suma[i] = lletraMissatge + lletraArrel;
                if (suma[i] > Abecedari.length) {
                    suma[i] = (suma[i] - Abecedari.length);
                }
            }

        }
        String traduccio = numLletra(suma);
        return traduccio;

    }

    public int[] LletraNumero(String text) {
        int[] cadena = new int[text.length()];
        String textMajuscules = text.toUpperCase();
        for (int i = 0; i < textMajuscules.length(); i++) {
            char lletra = textMajuscules.charAt(i);
            for (int j = 0; j < Abecedari.length; j++) {

                if (Abecedari[j] == lletra) {
                    int codi = j + 1;
                    cadena[i] = codi;
                }
            }
        }
        return cadena;
    }

    public String restaNum(int[] missatgeNum, String arrelStr) {

        int resta[] = new int[missatgeNum.length];

        int lletraMissatge = 0;
        for (int i = 0; i < missatgeNum.length; i++) {
            if (missatgeNum[i] == 0) {
                resta[i] = 0;
            } else {
                lletraMissatge = missatgeNum[i];
                int lletraArrel = arrelStr.charAt(i) - '0';

                resta[i] = lletraMissatge - lletraArrel;
                if (resta[i] < 1) {
                    resta[i] = resta[i] + Abecedari.length;
                }
            }

        }
        String traduccio = numLletra(resta);
        return traduccio;

    }
}
