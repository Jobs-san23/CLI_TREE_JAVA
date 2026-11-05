import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.stream.Stream;
import java.util.Comparator;

public interface Parametros {
    MetodosCLI arbol = new MetodosCLI();
    //***************************************************************
    static void parametro_a (File raiz, String[] parametros,int nivel){
        System.out.print(arbol.imprimirArbol(raiz, parametros,nivel));
    }
    //***************************************************************
    static void parametro_d (File raiz, String[] parametros,int  nivel){
        System.out.print(arbol.imprimirArbol(raiz, parametros,nivel));
    }
    //***************************************************************
    static void parametro_f (File raiz){
        //la clase Stream hace que se pueda recorrer el directorio y sus subdirectorios de manera recursiva y se imprime la ruta absoluta de cada archivo
        try (Stream<Path> stream = Files.list(raiz.toPath())){
            stream.forEach(Path -> System.out.println(Path.toAbsolutePath()));
        } catch (IOException e) {
            IO.println("Error al listar los archivos: " + e.getMessage());
        }
    }
    //***************************************************************
    static void parametro_t (File raiz){

        Comparator<Path> PathFecha = Comparator.comparing((Path p) -> {
            try {
                return Files.getLastModifiedTime(p);
            } catch (IOException e) {

                return FileTime.fromMillis(0);
            }
        });

        try (Stream<Path> stream = Files.list(raiz.toPath())) {
            stream.sorted(PathFecha.reversed())
                    .forEach(System.out::println);
        } catch (IOException e) {
            IO.println("Error al listar los archivos: " + e.getMessage());
        }
    }
    //***************************************************************

    static void parametro_L (File raiz, String[] parametros, int  nivel) {
        System.out.print(arbol.imprimirArbol(raiz, parametros, nivel));
    }
    //***************************************************************
    static void parametro_P (File raiz, String[] parametros, int nivel) {
        System.out.print(MetodosCLI.imprimirArbol(raiz, parametros, nivel));
        }

    //***************************************************************
    static void parametro_I(File raiz, String[] parametros, int nivel) {
        System.out.print(MetodosCLI.imprimirArbol(raiz, parametros, nivel));
    }

    static void help() {
        IO.println("Uso: tree [-a] [-d] [-f] [-t] [-L nivel] [-P patron] [-I patron]");
        IO.println("-a  muestra todo el arbol");
        IO.println("-d  solo directorios");
        IO.println("-f  lista rutas absolutas");
        IO.println("-t  ordena por fecha de modificacion");
        IO.println("-L  limita la profundidad (ejemplo: -L 2)");
        IO.println("-P  solo archivos que coincidan con el patron");
        IO.println("-I  ignora archivos que coincidan con el patron");
        IO.println("Escribe 'exit' para salir");
    }

}

