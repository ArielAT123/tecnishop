package prueba.cosas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ImageProcessors extends JComponent {
    private BufferedImage originalImage;
    private BufferedImage glowedImage;

    public ImageProcessors(String imagePath) throws Exception {
        originalImage = ImageIO.read(getClass().getResource(imagePath));
        if (originalImage == null) {
            throw new Exception("No se pudo cargar la imagen desde: " + imagePath);
        }
        glowedImage = createGlowEffect(originalImage);
    }

    private BufferedImage createGlowEffect(BufferedImage src) {
        // Definir el tamaño del glow
        int glowRadius = 20; // Espacio para el resplandor
        int blurRadius = 10; // Radio del difuminado
        int padding = glowRadius + blurRadius; // Total de espacio adicional
        int width = src.getWidth() + padding * 2;
        int height = src.getHeight() + padding * 2;

        // Crear una imagen para la silueta blanca brillante
        BufferedImage whiteShape = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D shapeG = whiteShape.createGraphics();
        
        // Crear una máscara basada en el canal alfa
        BufferedImage mask = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D maskG = mask.createGraphics();
        maskG.drawImage(src, 0, 0, null); // Dibujar la imagen para usar su alfa
        maskG.dispose();

        // Llenar la silueta visible con blanco puro
        shapeG.setColor(Color.WHITE);
        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                int alpha = (mask.getRGB(x, y) >> 24) & 0xff; // Obtener el canal alfa
                if (alpha > 0) { // Si el píxel es visible
                    whiteShape.setRGB(x, y, Color.WHITE.getRGB()); // Pintarlo blanco
                }
            }
        }
        shapeG.dispose();

        // Crear una imagen ampliada para el glow
        BufferedImage glow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D glowG = glow.createGraphics();
        glowG.setColor(Color.WHITE);
        glowG.setComposite(AlphaComposite.SrcOver);
        glowG.drawImage(whiteShape, padding, padding, null); // Colocar la silueta blanca en el centro
        glowG.dispose();

        // Aplicar desenfoque gaussiano para el glow
        float[] blurKernel = createGaussianKernel(blurRadius);
        ConvolveOp blur = new ConvolveOp(new Kernel(blurRadius, blurRadius, blurKernel), ConvolveOp.EDGE_NO_OP, null);
        BufferedImage blurredGlow = blur.filter(glow, null);

        // Crear el resultado final
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.setComposite(AlphaComposite.SrcOver);
        g.drawImage(blurredGlow, 0, 0, null); // Dibujar el glow primero
        g.drawImage(whiteShape, padding, padding, null); // Silueta blanca encima, sin difuminar
        g.dispose();

        return result;
    }

    private float[] createGaussianKernel(int radius) {
        int size = radius * 2 + 1;
        float[] kernel = new float[size * size];
        float sigma = radius / 3.0f;
        float sum = 0;

        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                int index = (y + radius) * size + (x + radius);
                kernel[index] = (float) Math.exp(-(x * x + y * y) / (2 * sigma * sigma));
                sum += kernel[index];
            }
        }

        for (int i = 0; i < kernel.length; i++) {
            kernel[i] /= sum;
        }
        return kernel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getParent() != null) {
            int headerHeight = getParent().getHeight();
            if (headerHeight > 0) {
                
                double scaleFactor = (0.9 * headerHeight) / glowedImage.getHeight();
                int newHeight = (int) (headerHeight * 0.9); // 
                int newWidth = (int) (glowedImage.getWidth() * scaleFactor); // Mantener proporciones
                Image scaledImage = glowedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                // Centrar verticalmente en el header
                int yOffset = (headerHeight - newHeight) / 2;
                g.drawImage(scaledImage, 0, yOffset, this);
            } else {
                g.drawImage(glowedImage, 0, 0, this);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (getParent() != null && getParent().getHeight() > 0) {
            int headerHeight = getParent().getHeight();
            double scaleFactor = (double) headerHeight / glowedImage.getHeight();
            int newWidth = (int) (glowedImage.getWidth() * scaleFactor);
            return new Dimension(newWidth, headerHeight);
        }
        return new Dimension(glowedImage.getWidth(), glowedImage.getHeight());
    }
}