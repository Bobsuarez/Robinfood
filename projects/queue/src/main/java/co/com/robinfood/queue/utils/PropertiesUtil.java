/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.utils;

import co.com.robinfood.queue.Exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author Bobsu
 */
@Slf4j
public class PropertiesUtil {

    public Properties cargarArchivoProperties(String nombreArchivo) {

        Properties prop = new Properties();

        String addPath = "/propertiesfiles/" + nombreArchivo + ".properties";

        System.out.println(addPath);

        try {
            var fileProperties = getClass().getResourceAsStream(addPath);
            prop.load(fileProperties);
            return prop;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(25, "Error cargando archivo properties");
        }
    }

}
