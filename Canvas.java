import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.*;

public class Canvas
{
  
    private static Canvas canvasSingleton;

    
    public static Canvas getCanvas()
    {
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("Ventana", 1000, 800, 
                                         Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private int keyboardLongitud = 50;
    private String currentText;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private Image canvasImage;
    private List<Object> objects;
    private HashMap<Object, ShapeDescription> shapes;

    private Canvas(String title, int width, int height, Color bgColor)
    {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        frame.setLocation(30, 30);
        frame.setLayout(new BorderLayout());
        
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = bgColor;
        frame.pack();
        objects = new ArrayList<Object>();
        shapes = new HashMap<Object, ShapeDescription>();
    }
    
    public String getPalabra()
    {
        return currentText; 
    }
    
    public void hacerVisible() {
        frame.setVisible(true);
    }
    
    public void hacerInvisible() {
        frame.setVisible(false);
    }
    
    public String getText() {
        return textField.getText();
    }

    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height - keyboardLongitud);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height - keyboardLongitud);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    public void draw(Object referenceObject, String color, Shape shape)
    {
        objects.remove(referenceObject);  
        objects.add(referenceObject);     
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
 
    public void erase(Object referenceObject)
    {
        objects.remove(referenceObject);  
        shapes.remove(referenceObject);
        redraw();
    }

    public void setForegroundColor(String colorString)
    {
        if(colorString.equals("red")) {
            graphic.setColor(new Color(235, 25, 25));
        }
        else if(colorString.equals("black")) {
            graphic.setColor(Color.black);
        }
        else if(colorString.equals("blue")) {
            graphic.setColor(new Color(30, 75, 220));
        }
        else if(colorString.equals("yellow")) {
            graphic.setColor(new Color(255, 230, 0));
        }
        else if(colorString.equals("green")) {
            graphic.setColor(new Color(80, 160, 60));
        }
        else if(colorString.equals("magenta")) {
            graphic.setColor(Color.magenta);
        }
        else if(colorString.equals("white")) {
            graphic.setColor(Color.white);
        }
        else if(colorString.equals("gris")){
            graphic.setColor(new Color(153,153,255));
        }
        else if(colorString.equals("Azul electrico destroza vistas")){
            graphic.setColor(new Color(0,255,255));
        }
        else if(colorString.equals("Verde bebe recien nacido")){
            graphic.setColor(new Color(153,255,102));
        }
        else if(colorString.equals("Cafe chocolate Abuelita")){
            graphic.setColor(new Color(153,102,0));
        }
        else {
            graphic.setColor(Color.black);
        }
    }

  
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
           
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    public void redraw()
    {
        erase();
        for(Object shape : objects) {
            shapes.get(shape).draw(graphic);
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    public void erase()
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }


    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription
    {
        private Shape shape;
        private String colorString;

        public ShapeDescription(Shape shape, String color)
        {
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic)
        {
            setForegroundColor(colorString);
            graphic.fill(shape);
        }
    }
    static void addMouseListener(MouseAdapter mouseAdapter) {
        JPanel panel = Canvas.getCanvas().getPanel();
        panel.addMouseListener(mouseAdapter); 
        }
    public JPanel getPanel(){
        
        return this.canvas;
    }
    public JFrame getFrame(){
        return this.frame;
    }
    
     public void drawString(String text, int x, int y, String color, int tamano)
    {
        graphic.setFont(new Font("Arial",Font.PLAIN,tamano));
        setForegroundColor(color);
        graphic.drawString(text, x, y);
        
        canvas.repaint();
    }

    /**
     * Erases a String on the Canvas.
     * @param  text     the String to be displayed 
     * @param  x        x co-ordinate for text placement 
     * @param  y        y co-ordinate for text placement
     */
    public void eraseString(String text, int x, int y)
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        canvas.repaint();
    }

}
