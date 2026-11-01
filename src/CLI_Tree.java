import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.lang.Exception;
import java.nio.file.Path;



public class CLI_Tree implements Parametros {

    public static void main(String[] args) throws Exception {


        Parametros metodosParametros = new Parametros() {
        };

        while (true) {
            //raiz definida
            File raiz = new File("C:\\Users\\josea\\Desktop");
            MetodosCLI Arbol = new MetodosCLI();


            //texto de HELP
            File archivoHelp = new File("C:\\Users\\josea\\Desktop\\LCC\\3er semestre\\PROGRAMACION III\\CLI Tree\\CLI_HELP_TEXT.txt");
            String contenido = Files.readString(archivoHelp.toPath());


            Scanner terminal = new Scanner(System.in);

            String comando = MetodosCLI.validacion_tree(terminal);


            String[] parametros = MetodosCLI.validacion_parametros(comando);

            List<String> comandoArray = Arrays.asList("a", "d", "f", "t", "L", "P", "I");
            boolean condicion = Arrays.stream(parametros).allMatch(comandoArray::contains);



            if (condicion) {
                for (String parametro : parametros) {
                    switch (parametro) {
                        case "a":


                            Parametros.parametro_a(raiz, parametros);


                        case "d":

                            Parametros.parametro_d(raiz);


                        case "f":

                            Parametros.parametro_f(raiz);

                        case "t":

                            Parametros.parametro_t(raiz);



                    }
                }

            } else {
                IO.println(contenido);
            }
        }
    }

}





