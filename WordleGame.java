
public class WordleGame {
    private String palabraSecreta;
    private int intentosRestantes;
    private boolean juegoTerminado;
    private Tablero tablero;

    public WordleGame(String palabraSecreta, int intentos, int tamañoTablero) {
        this.palabraSecreta = palabraSecreta;
        this.intentosRestantes = intentos;
        this.juegoTerminado = false;
        this.tablero = new Tablero(tamañoTablero);
    }

    public boolean intento(String palabra) {
        if (juegoTerminado) {
            return false;
        }

        intentosRestantes--;

        if (palabra.equals(palabraSecreta)) {
            juegoTerminado = true;
            return true;
        }

        return false;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public void mostrarTablero() {
        tablero.mostrarTablero();
    }

    public void colocarLetraEnTablero(int fila, int columna, char letra) {
        tablero.colocarLetra(fila, columna, letra);
    }
}
