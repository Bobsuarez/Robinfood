package com.robinfood.localprinterbc.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.FILE_PATH_QR;

@Slf4j
public class GenerateQR {

    public static void generateQRCode(String value, String imageName, int width, int height) throws IOException {
        try {
            // Configurar parámetros de codificación
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Generar el código QR
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(value, BarcodeFormat.QR_CODE, width, height, hints);

            // Crear una imagen BufferedImage a partir de BitMatrix
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            // Guardar la imagen en el archivo
            File outputFile = new File(FILE_PATH_QR + imageName);
            ImageIO.write(image, "png", outputFile);

            System.out.println("Código QR generado y guardado exitosamente en src/main/resources/qrcode/.");
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(String imageName) throws IOException {
        String imagePath = FILE_PATH_QR + imageName;
        File imageFile = new File(imagePath);
        return ImageIO.read(imageFile);
    }

    public static void deleteImageQR(String filePath) {

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
