import java.io.File;
import java.util.*;
import java.nio.file.Files;
import java.lang.Exception;

public class CLI_Tree implements Parametros {

    public static void main(String[] args) throws Exception {

        //raiz definida
        File raiz = new File("C:\\Users\\josea\\Desktop");
        MetodosCLI Arbol = new MetodosCLI();

        Scanner terminal = new Scanner(System.in);

        List<String> comandoArray = Arrays.asList("a", "d", "f", "t", "L", "P", "I");


        while (true) {

            String comando = MetodosCLI.validacion_tree(terminal);
            if (comando.equals("exit")) {
                break;
            }

            //aqui si se ingresan los parametros
            String[] parametros = MetodosCLI.validacion_parametros(comando);
//            System.out.println(Arrays.toString(parametros));
            String[] opciones = MetodosCLI.quitarPatrones(parametros);
            int nivel = Integer.MAX_VALUE;


            for (String parametro : opciones) {
                try {
                    nivel = Integer.parseInt(parametro);
                    break;
                } catch (NumberFormatException e) {
                }
            }

            List<String> lista = Arrays.asList(opciones);
            boolean condicionNumero = Arrays.stream(opciones).anyMatch(p -> p.matches("\\d+"));

            boolean condicion = Arrays.stream(opciones)
                    .allMatch(p -> comandoArray.contains(p) || p.matches("\\d+"))
                    && (lista.contains("L") == condicionNumero)
                    && (!lista.contains("P") || MetodosCLI.obtenerPatron(parametros, "P") != null)
                    && (!lista.contains("I") || MetodosCLI.obtenerPatron(parametros, "I") != null);


            if (condicion) {
                if (parametros.length == 0) {
                    Parametros.parametro_a(raiz, parametros, nivel);
                } else {
                    boolean arbolImpreso = false;
                    for (String parametro : parametros) {
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





