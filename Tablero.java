import java.util.Random;
import java.util.Scanner;

public class Tablero {
    private char[][] letras;

    public Tablero(int tamaño) {
        letras = new char[tamaño][tamaño];
        
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                letras[j][i] = ' ';
            }
        }
    }

    public void mostrarTablero() {
        
        for (char[] fila : letras) {
            for (char letra : fila) {
                System.out.print("["+ letra + "]");
            }
           System.out.println();
        }
    }

    public void colocarLetra(int fila, int columna, char letra) {
        letras[fila][columna] = letra;
    }
}
