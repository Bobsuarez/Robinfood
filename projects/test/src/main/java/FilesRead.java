import java.io.File;

public class FilesRead {


    public static void main(String[] args) {

//        D:\Documents\proyectos\robinfood
        String folderPath = "D:\\Documents\\proyectos\\robinfood";

        // Crear un objeto File para la carpeta
        File folder = new File(folderPath);

        // Verificar si la ruta apunta a una carpeta válida
        if (folder.isDirectory()) {
            // Obtener la lista de archivos y subdirectorios dentro de la carpeta
            File[] files = folder.listFiles();
            if (files != null) {
                String pathfinal = "\"D:\\Documents\\proyectos\\robinfood\\1" +
                        ".1-robinfood-cloud-git\\Robinfood\\proyects\\";
                // Iterar sobre los archivos y subdirectorios
                for (File file : files) {
                    if (file.getName()
                            .contains(".txt") || file.getName()
                            .contains("modular") || file.getName()
                            .contains(".") || file.getName()
                            .contains("1") || file.getName()
                            .contains("morty") || file.getName()
                            .contains("Dockerfile")) {
                        continue;
                    }

                    System.out.println("echo  ..::: Start " + file.getName() + "  :::.. !");
//                    System.out.println("cd ../"+file.getName());
//                    System.out.println("cd ../"+file.getName()+" && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%");
//                    System.out.println("echo  ************************************ !\n");

                    String pathMain = "\"D:\\Documents\\proyectos\\robinfood\\" + file.getName() + "\\";


                    System.out.println("xcopy  " + pathMain + "\" " + pathfinal + file.getName() + "\\\" " + " /s" +
                                               " /e /h /i /y" + "\n");
                }
                System.out.println("cd " + pathfinal);

                String deleteFolder = """                            
                        set "extensions=.git .serverless .idea .gradle build target .mvn"
                          
                          rem Recorre todos los directorios y elimina los que coincidan con las extensiones
                          for %%e in (%extensions%) do (
                              for /d /r %%i in (%%e) do (
                                  echo Eliminando directorio %%i
                                  rmdir /s /q "%%i"
                              )
                          )
                            """;
                String deleteFiles = """                            
                        set "fileExtensions=.gitignore mvnw mvnw.cmd HELP.md"
                                                
                         rem Recorre todos los archivos y elimina los que coincidan con las extensiones
                         for %%f in (%fileExtensions%) do (
                             for /r %%i in (*%%f) do (
                                 echo Eliminando archivo %%i
                                 del /f /q "%%i"
                             )
                         )
                        """;

                System.out.println(deleteFolder);
                System.out.println(deleteFiles);

            }
        } else {
            System.out.println("La ruta no corresponde a una carpeta válida.");
        }
    }

}
