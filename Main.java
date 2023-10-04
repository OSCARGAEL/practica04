import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String palabraSecreta = generarPalabraSecreta();
        WordleGame juego = new WordleGame(palabraSecreta, 5, 5);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wordle");
        juego.mostrarTablero();

        while (!juego.isJuegoTerminado() && juego.getIntentosRestantes() > 0) {
            System.out.print("Introduce la palabra: ");
            String intento = scanner.nextLine().toUpperCase();

            if (intento.length() != palabraSecreta.length()) {
                System.out.println("La palabra debe tener " + palabraSecreta.length() + " letras.");
            } else {
                for (int i = 0; i < intento.length(); i++) {
                    char letra = intento.charAt(i);
                    if (juego.getPalabraSecreta().contains(String.valueOf(letra))) {
                        int fila = juego.getPalabraSecreta().indexOf(letra);
                        juego.colocarLetraEnTablero(fila, i, letra);
                    }
                }

                juego.mostrarTablero();

                boolean esCorrecto = juego.intento(intento);
                if (esCorrecto) {
                    System.out.println("Has adivinado parte de la palabra secreta:");
                } else {
                    System.out.println("Intento incorrecto. Intentos restantes: " + juego.getIntentosRestantes());
                }
            }
        }

        if (!juego.isJuegoTerminado()) {
            System.out.println("Lo siento, has agotado tus intentos. La palabra secreta era: " + palabraSecreta);
        } else {
            System.out.println("¡Felicidades! Has adivinado la palabra secreta.");
        }
        
    }

    private static String generarPalabraSecreta() {
        String[] palabras = {"BLUEJ", "JUEGO", "CARRO", "MOUSE", "NARIZ", "TILIN", "CLASE", "OSCAR", "LECHE", "PARIS"};
        Random random = new Random();
        int indice = random.nextInt(palabras.length);
        return palabras[indice].toUpperCase();
    }
}
