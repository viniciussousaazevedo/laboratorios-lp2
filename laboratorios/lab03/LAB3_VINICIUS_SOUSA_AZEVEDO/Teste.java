public class Teste {
    
    public static void main(String[] args) {
        String[][] matriz = new String[3][3];
        
        for (int i1 = 0; i1 < matriz.length; i1++) {
            for (int i2 = 0; i2 < matriz[0].length; i2++) {
                if (i1 == 1) {
                    matriz[i1][i2] = "-";
                } else if (i1 == 2) {
                    matriz[i1][i2] = "|";
                } else if (i1 == 3) {
                    matriz[i1][i2] = "/";
                }
            }
        }
    }
}
