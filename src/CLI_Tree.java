import java.io.File;
import java.util.*;
import java.nio.file.Files;
import java.lang.Exception;

public class CLI_Tree implements Parametros {

    public static void main(String[] args) throws Exception {

        //raiz definida
        File raiz = new File("C:\\Users\\josea\\Desktop");
        MetodosCLI Arbol = new MetodosCLI();
        //Scanner para leer la entrada del usuario
        Scanner terminal = new Scanner(System.in);
        //se define un arreglo de comandos validos
        List<String> comandoArray = Arrays.asList("a", "d", "f", "t", "L", "P", "I");
        //se hace unbucle infinito para que el programa siga ejecutandose hasta que el usuario decida salir
        while (true) {
            //se llama al metodo validacion_tree para validar el comando ingresado por el usuario
            String comando = MetodosCLI.validacion_tree(terminal);
            if (comando.equals("exit")) {
                break;
            }

            //aqui si se ingresan los parametros
            String[] parametros = MetodosCLI.validacion_parametros(comando);
            //se quitan los patrones de los parametros
            String[] opciones = MetodosCLI.quitarPatrones(parametros);
            //se define un nivel por defecto
            int nivel = Integer.MAX_VALUE;

            // se busca si hay un parametro que sea un numero para definir el nivel y se obtiene un unico valor
            for (String parametro : opciones) {
                try {
                    nivel = Integer.parseInt(parametro);
                    break;
                } catch (NumberFormatException e) {
                }
            }
            //se crea una lista a partir del arreglo de opciones para poder usar el metodo contains
            List<String> lista = Arrays.asList(opciones);
            //se crea la variable condicionNumero para verificar si hay un parametro que sea un numero
            boolean condicionNumero = Arrays.stream(opciones).anyMatch(p -> p.matches("\\d+"));
            // se crea la variable condicion para verificar si todos los parametros son validos y si se cumplen las condiciones de los parametros L,P e I
            boolean condicion = Arrays.stream(opciones)
                    .allMatch(p -> comandoArray.contains(p) || p.matches("\\d+"))
                    && (lista.contains("L") == condicionNumero)
                    && (!lista.contains("P") || MetodosCLI.obtenerPatron(parametros, "P") != null)
                    && (!lista.contains("I") || MetodosCLI.obtenerPatron(parametros, "I") != null);

            //se hace una condicional para verificar si la variable condicion es verdadera o o no
            if (condicion) {
                if (parametros.length == 0) { //Si se escribe "tree" sin ningun parametro se ejecuta el metodo parametro_a que imprime el arbol completo
                    Parametros.parametro_a(raiz, parametros, nivel);
                } else {
                    boolean arbolImpreso = false;
                    for (String parametro : parametros) { //
                        switch (parametro) {
                            case "a":
                                if (!arbolImpreso) {
                                    Parametros.parametro_a(raiz, parametros, nivel);
                                    arbolImpreso = true;
                                }
                                break;
                            case "d":
                                if (!arbolImpreso) {
                                    Parametros.parametro_d(raiz, parametros, nivel);
                                    arbolImpreso = true;
                                }
                                break;
                            case "f":
                                Parametros.parametro_f(raiz);
                                break;
                            case "t":
                                Parametros.parametro_t(raiz);
                                break;
                            case "L":
                                if (!arbolImpreso) {
                                    Parametros.parametro_L(raiz, parametros, nivel);
                                    arbolImpreso = true;
                                }
                                break;
                            case "P":
                                if (!arbolImpreso) {
                                    Parametros.parametro_P(raiz, parametros, nivel);
                                    arbolImpreso = true;
                                }
                                break;
                            case "I":
                                if (!arbolImpreso) {
                                    Parametros.parametro_I(raiz, parametros, nivel);
                                    arbolImpreso = true;
                                }
                                break;
                        }
                    }
                }
                    } else {
                    File archivoHelp = new File("C:\\Users\\josea\\Desktop\\LCC\\3er semestre\\PROGRAMACION III\\CLI Tree\\CLI_HELP_TEXT.txt");
                    IO.println(Files.readString(archivoHelp.toPath()));
                    }
                }
            }

        }





