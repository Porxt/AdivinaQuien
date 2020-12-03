package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridCharacters extends JPanel {

    private final String dirname = "D:\\Users\\Germa\\Documents\\Proyectos\\Java\\AdivinaQuien\\out\\production\\AdivinaQuien\\gui\\personajes\\";
    private final String[] path = {
            dirname + "01.jpg", dirname + "03.jpg", dirname + "05.jpg", dirname + "07.jpg",
            dirname + "09.jpg", dirname + "11.jpg", dirname + "13.jpg", dirname + "15.jpg",
            dirname + "17.jpg", dirname + "19.jpg", dirname + "21.jpg", dirname + "23.jpg",
            dirname + "25.jpg", dirname + "27.jpg", dirname + "29.jpg", dirname + "31.jpg",
            dirname + "33.jpg", dirname + "35.jpg", dirname + "37.jpg", dirname + "39.jpg"
    };

    public void paint(Graphics g) {
        super.paint(g);
        final int columns = 5;
        final int rows = 4;
        BufferedImage image;

        // Calculamos el tamaño de las imagenes a lo ancho y largo utilizando un 85%
        // del espacio disponible y conservamos la medida más pequeña para mantener
        // una relación 1:1 en las medidas de la imagen
        int figureWidth =  (int) Math.floor(getWidth() * 0.85) / columns;
        int figureHeight = (int) Math.floor(getHeight() * 0.85) / rows;
        int size = Math.min(figureWidth, figureHeight);

        int marginX = (getWidth() - size * columns) / (columns + 1);
        int marginY = (getHeight() - size * rows) / (rows + 1);
        int pointerX = marginX;
        int pointerY = marginY;
        for(int x = 0, imageN = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++, imageN++) {
                try {
                    image = ImageIO.read(new File(path[imageN]));
                } catch(IOException ex) {
                    image = null;
                    System.out.println("No encotre la imagen");
                }
                g.drawImage(image, pointerX, pointerY, size, size, null);
                pointerY += size + marginY;
            }
            pointerX += size + marginX;
            pointerY = marginY;
        }
    }

    public void updateImages(boolean[] characters) {
        final String[] gris = {
                dirname + "02.jpg", dirname + "04.jpg", dirname + "06.jpg", dirname + "08.jpg",
                dirname + "10.jpg", dirname + "12.jpg", dirname + "14.jpg", dirname + "16.jpg",
                dirname + "18.jpg", dirname + "20.jpg", dirname + "22.jpg", dirname + "24.jpg",
                dirname + "26.jpg", dirname + "28.jpg", dirname + "30.jpg", dirname + "32.jpg",
                dirname + "34.jpg", dirname + "36.jpg", dirname + "38.jpg", dirname + "39.jpg"
        };

        for(int n = 0; n < characters.length; n++) {
            path[n] = characters[n] ? path[n] : gris[n];
        }

        repaint();
    }
}
