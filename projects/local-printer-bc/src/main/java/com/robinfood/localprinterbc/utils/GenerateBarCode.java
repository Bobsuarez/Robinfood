package com.robinfood.localprinterbc.utils;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.FILE_PATH_BAR_CODE;

@Slf4j
public class GenerateBarCode {

    public static void buildBarCode(String barcodeData, String filePath) throws IOException {

       // String filePath = "barcode1.png"; // Ruta del archivo de salida

        int width = 600; // Ancho del código de barras
        int height = 50; // Altura del código de barras

        // Configurar parámetros de codificación
        com.google.zxing.Writer writer = new MultiFormatWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = writer.encode(barcodeData, BarcodeFormat.CODE_128, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
            return;
        }

        String outputPath = FILE_PATH_BAR_CODE + filePath;

        generateImage(bitMatrix, outputPath, barcodeData);
        System.out.println("Imagen generada y guardada exitosamente en resources.");
    }


    public static BufferedImage getImage(String imageName) throws IOException {
        String imagePath = FILE_PATH_BAR_CODE + imageName;
        File imageFile = new File(imagePath);
        return ImageIO.read(imageFile);
    }

    // =================================


    private static void generateImage1(BitMatrix bitMatrix, String outputPath) throws IOException {
        // Obtener las coordenadas de los límites del contenido del código de barras
        int left = getLeftBoundary(bitMatrix);
        int right = getRightBoundary(bitMatrix);
        int top = getTopBoundary(bitMatrix);
        int bottom = getBottomBoundary(bitMatrix);

        // Calcular el ancho y la altura del contenido del código de barras
        int width = right - left + 1;
        int height = bottom - top + 1;

        // Crear BufferedImage del tamaño del contenido del código de barras
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Copiar los píxeles del contenido del código de barras al BufferedImage
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(left + x, top + y) ? 0x000000 : 0xFFFFFF);
            }
        }

        // Crear archivo de salida de imagen
        File outputFile = new File(outputPath);

        // Escribir el BufferedImage en el archivo de salida
        ImageIO.write(image, "png", outputFile);
    }

    private static void generateImage(BitMatrix bitMatrix, String outputPath, String barcodeData) throws IOException {
        // Obtener las coordenadas de los límites del contenido del código de barras
        int left = getLeftBoundary(bitMatrix);
        int right = getRightBoundary(bitMatrix);
        int top = getTopBoundary(bitMatrix);
        int bottom = getBottomBoundary(bitMatrix);

        // Calcular el ancho y la altura del contenido del código de barras
        int width = right - left + 1;
        int height = bottom - top + 1;

        // Calcular la altura total incluyendo el espacio adicional para el valor del código de barras
        int totalHeight = height + 20;

        // Crear BufferedImage con la altura total
        BufferedImage image = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_RGB);

        // Rellenar la parte superior con blanco para el valor del código de barras
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, totalHeight);

        // Copiar los píxeles del contenido del código de barras al BufferedImage
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y + 20, bitMatrix.get(left + x, top + y) ? 0x000000 : 0xFFFFFF);
            }
        }

        // Escribir el valor del código de barras centrado en la parte superior
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.PLAIN, 22));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(barcodeData);
        int textX = (width - textWidth) / 2; // Calcular la posición horizontal centrada
        int textY = 15; // Coordenada vertical fija para el valor del código de barras
        graphics.drawString(barcodeData, textX, textY);

        // Crear archivo de salida de imagen
        File outputFile = new File(outputPath);

        // Escribir el BufferedImage en el archivo de salida
        ImageIO.write(image, "png", outputFile);
    }


    private static int getLeftBoundary(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        for (int x = 0; x < width; x++) {
            if (hasNonWhitePixelInColumn(bitMatrix, x)) {
                return x;
            }
        }
        return 0;
    }

    private static int getRightBoundary(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        for (int x = width - 1; x >= 0; x--) {
            if (hasNonWhitePixelInColumn(bitMatrix, x)) {
                return x;
            }
        }
        return width - 1;
    }

    private static int getTopBoundary(BitMatrix bitMatrix) {
        int height = bitMatrix.getHeight();
        for (int y = 0; y < height; y++) {
            if (hasNonWhitePixelInRow(bitMatrix, y)) {
                return y;
            }
        }
        return 0;
    }

    private static int getBottomBoundary(BitMatrix bitMatrix) {
        int height = bitMatrix.getHeight();
        for (int y = height - 1; y >= 0; y--) {
            if (hasNonWhitePixelInRow(bitMatrix, y)) {
                return y;
            }
        }
        return height - 1;
    }

    private static boolean hasNonWhitePixelInColumn(BitMatrix bitMatrix, int column) {
        int height = bitMatrix.getHeight();
        for (int y = 0; y < height; y++) {
            if (bitMatrix.get(column, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNonWhitePixelInRow(BitMatrix bitMatrix, int row) {
        int width = bitMatrix.getWidth();
        for (int x = 0; x < width; x++) {
            if (bitMatrix.get(x, row)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteImageBarCode(String filePath) {

        // Crear una instancia de File con la ruta del archivo
        File file = new File(filePath);

        // Verificar si el archivo existe y es un archivo PNG
        if (file.exists() && file.isFile() && file.getName().endsWith(".png")) {
            // Intentar eliminar el archivo
            boolean deleted = file.delete();

            // Verificar si el archivo se eliminó correctamente
            if (deleted) {
                log.info("Archivo eliminado: " + file.getName());
            } else {
                log.info("No se pudo eliminar el archivo: " + file.getName());
            }
        } else {
            log.info("El archivo no existe o no es un archivo PNG válido.");
        }

    }

}
