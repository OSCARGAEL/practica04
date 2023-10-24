import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import java.util.Scanner;

public class Square
{
   private int size;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private String letra;
    private Square[] cuadrados;
  
    private Scanner scanner;
            
    /**
     * Create a new square at default position with default color.
     */
    public Square()
    {
        size = 60;
        xPosition = 310;
        yPosition = 120;
        color = "gris";
        isVisible = false;
       
        letra="a";
        scanner = new Scanner(System.in);
        
        
    }
    
    /**
     * Make this square visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }

    /**
     * Make this square invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }

    /**
     * Move the square a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move the square a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move the square a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move the square a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move the square horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition = xPosition + distance;
        draw();
    }

    /**
     * Move the square vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition = yPosition + distance;
        draw();
    }

    /**
     * Slowly move the square horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;
        if (distance < 0) {
            delta = -1;
            distance =  - distance;
        }
        else {
            delta = 1;
        }
        int i = 0;
        while (i < distance) {
            xPosition = xPosition + delta;
            draw();
            i = i + 1;
        }
    }

    /**
     * Slowly move the square vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;
        if (distance < 0) {
            delta = -1;
            distance =  - distance;
        }
        else {
            delta = 1;
        }
        int i = 0;
        while (i < distance) {
            yPosition = yPosition + delta;
            draw();
            i = i + 1;
        }
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize)
    {
        erase();
        size = newSize;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green", "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

    /**
     * Draw the square with current specifications on screen.
     */
    private void draw()
    {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Rectangle(xPosition, yPosition, size, size));
            canvas.wait(10);
        }
    }

    /**
     * Erase the square on screen.
     */
    private void erase()
    {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    public void posicion(int xdistance, int ydistance){
        erase();
        xPosition = xdistance;
        yPosition = ydistance;
        draw();
    
    }
    public void setLetra(String letra){
        this.letra=letra;        
    }
    public void verLetra(){
        Canvas canvas=Canvas.getCanvas();
        canvas.drawString(String.valueOf(letra),xPosition + size / 2, yPosition+ size / 2, color="black",size/2);
    }
    
    
    public void tablero(){
        int numFilas = 6;
        int numColumnas = 5;
        int tamanoCuadrado = 65;
        int separacionHorizontal = 5;
        int separacionVertical = 5;
        int posXInicial = 20;
        int posYInicial = -110;
        
        ArrayList<String> palabras = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        // Crear un arreglo de cuadrados
        Square[] cuadrados = new Square[numFilas * numColumnas];

        // Usar un bucle for para crear los cuadrados y establecer sus posiciones
        for (int fila = 0; fila < numFilas; fila++) {
            for (int columna = 0; columna < numColumnas; columna++) {
                int indice = fila * numColumnas + columna;
                cuadrados[indice] = new Square();
                cuadrados[indice].changeSize(tamanoCuadrado);
                int posX = posXInicial + columna * (tamanoCuadrado + separacionHorizontal);
                int posY = posYInicial + fila * (tamanoCuadrado + separacionVertical);
                cuadrados[indice].moveHorizontal(posX);
                cuadrados[indice].moveVertical(posY);
                cuadrados[indice].makeVisible();
                
                
                }
            }
           
        }
    
    public void teclado()
    {
    int numFilas = 3;
    int[] numColumnas = { 10, 10, 6 };
    int tamanoCuadrado = 50;
    int separacionHorizontal = 5;
    int separacionVertical = 5;
    int posXInicial = -75;
    int posYInicial = 350;
    
    String[] abecedario={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    // Crear un arreglo de cuadrados
    Square[] cuadrados = new Square[numFilas * Arrays.stream(numColumnas).sum()];
    

    int indice = 0; // Variable para rastrear el índice del arreglo cuadrados[]

    // Usar un bucle for para crear los cuadrados y establecer sus posiciones
    for (int fila = 0; fila < numFilas; fila++) {
        for (int columna = 0; columna < numColumnas[fila]; columna++) {
            cuadrados[indice] = new Square();
            
            int posX = posXInicial + columna * (tamanoCuadrado + separacionHorizontal);
            int posY = posYInicial + fila * (tamanoCuadrado + separacionVertical);
            cuadrados[indice].changeSize(tamanoCuadrado);
            cuadrados[indice].moveHorizontal(posX);
            cuadrados[indice].moveVertical(posY);
            
            cuadrados[indice].makeVisible();
            indice++; // Incrementar el índice para el siguiente cuadrado
       
        }
    }
    for(int i=0; i<abecedario.length;i++){
        cuadrados[i].setLetra(abecedario[i]);
        cuadrados[i].verLetra();
    }
    
    }
    
private String generarPalabraAleatoria() {
    String[] palabras = {"BLUEJ", "JUEGO", "CARRO", "MOUSE", "NARIZ", "TILIN", "CLASE", "OSCAR", "LECHE", "PARIS"};
    Random random = new Random();
    int indice = random.nextInt(palabras.length);
    return palabras[indice].toUpperCase();
}
    
    public static void main(String[] args) {
        
       Square juego= new Square();
       Square teclado= new Square();
       
       juego.tablero();
       teclado.teclado();
    
    }
}

