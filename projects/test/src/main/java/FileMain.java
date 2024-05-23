import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileMain {

//    public static void main(String[] args) {
//        // Especifica la ruta de la carpeta que deseas listar
//        String rutaCarpeta = "C:\\Users\\Bobsu\\Documents\\proyectos\\robinfood - Copy";
//
//        List<String> filePackages = new ArrayList<>();
//        filePackages.add(".git");
//        filePackages.add(".gitignore");
//        filePackages.add("1.0-entregables");
//        filePackages.add("pythonScript");
//        filePackages.add("script-python");
//        filePackages.add("services-ou");
//        filePackages.add("services-ou-deploy");
//        filePackages.add("services-changestatus");
//        filePackages.add("GitSync.bat");
//        // Crea un objeto File con la ruta de la carpeta
//        File carpeta = new File(rutaCarpeta);
//
//        // Verifica si la ruta corresponde a un directorio
//        if (carpeta.isDirectory()) {
//            // Obtiene la lista de archivos en la carpeta
//            File[] archivos = carpeta.listFiles();
//
//            // Muestra el nombre de cada archivo en la carpeta
//            if (archivos != null) {
//                for (File archivo : archivos) {
//
//                    if(filePackages.contains(archivo.getName())){
//                        System.out.println(archivo.getName() + "/");
//                        continue;
//                    }
//
//                    if(archivo.getName().contains("txt")){
//                        continue;
//                    }
//
//                    if (archivo.getName().equals("morty")) {
//                        System.out.println(archivo.getName() + "/.dart_tool");
//                        System.out.println(archivo.getName() + "/.git");
//                        System.out.println(archivo.getName() + "/.idea");
//                        System.out.println(archivo.getName() + "/build");
//                        continue;
//                    }
//
//                    if (archivo.getName().equals("modular")) {
//                        System.out.println(archivo.getName() + "/");
//                        continue;
//                    }
//                    System.out.println(archivo.getName() + "/.git");
//                    System.out.println(archivo.getName() + "/.mvn");
//                    System.out.println(archivo.getName() + "/.gradle");
//                    System.out.println(archivo.getName() + "/.jpb");
//                    System.out.println(archivo.getName() + "/.serverless");
//                    System.out.println(archivo.getName() + "/.idea");
//                    System.out.println(archivo.getName() + "/target");
//                }
//            } else {
//                System.out.println("La carpeta está vacía.");
//            }
//        } else {
//            System.out.println("La ruta especificada no es un directorio.");
//        }
//    }

    public static void main(String[] args) {
        // Especifica la ruta del directorio principal
        String rutaDirectorioPrincipal = "C:\\Users\\Bobsu\\Documents\\proyectos\\robinfood - Copy";

        // Llama a la función para eliminar archivos .git recursivamente
        eliminarArchivosGit(rutaDirectorioPrincipal);
    }

    public static void eliminarArchivosGit(String rutaDirectorio) {
        try {
            // Utiliza Files.walk para obtener una lista recursiva de todos los archivos y directorios
            // en la ruta proporcionada
            try (Stream<Path> paths = Files.walk(Paths.get(rutaDirectorio))) {
                // Filtra los archivos .git
                paths.filter(path -> {
                            return path.toFile().getName().equals(".git")
                                    || path.toFile().getName().equals(".idea")
                                    || path.toFile().getName().equals(".serverless")
                                    || path.toFile().getName().equals(".gitignore")
                                    || path.toFile().getName().equals("target")
                                    || path.toFile().getName().equals(".gradle")
                                    || path.toFile().getName().equals(".mvn")
                                    || path.toFile().getName().equals(".iml")
                                    || path.toFile().getName().equals("mvnw.cmd")
                                    || path.toFile().getName().equals("mvnw");
                        })
                        // Elimina cada archivo .git
                        .forEach(FileMain::eliminarArchivoGit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarArchivoGit(Path path) {
        try {
            // Elimina el directorio .git
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            System.out.println("Se eliminó el directorio .git en: " + path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
