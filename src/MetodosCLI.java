import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class MetodosCLI {

    public static String imprimirArbol(File Folder, String[] parametros, int nivel) {
        //metemos un condicional para verificar si la ruta es un directorio
        if (!Folder.isDirectory()) {
            throw new IllegalArgumentException("La ruta especificada no es un directorio");
        }
        int indentacion = 0;
        boolean condicionDirectorio = Arrays.asList((quitarPatrones(parametros))).contains("d");
        String patronP = obtenerPatron(parametros, "P");
        String patronI = obtenerPatron(parametros, "I");
        //Creamos un StringBuilder para almacenar la estructura del Arbol
        StringBuilder sb = new StringBuilder();
        arbolRecursivo(Folder, sb, 0, condicionDirectorio, nivel,patronP, patronI);
        return sb.toString();
    }

    public static void arbolRecursivo(File Folder, StringBuilder sb, int indentacion, boolean condicionDirectorio, int nivel,String patronP, String patronI) {
        if (!Folder.isDirectory()) {
            throw new IllegalArgumentException("La ruta especificada no es un directorio");
        }

        sb.append(generarIndentacion(indentacion));
        sb.append("+-- ");
        sb.append(Folder.getName());
        sb.append("/");
        sb.append("\n");

        if (indentacion >= nivel) {
            return;
        }

        File[] archivos = Folder.listFiles();
        if (archivos == null) return;

        Arrays.sort(archivos);


        for (File file : archivos) {
            if (patronI != null && coincidePatron(file.getName(), patronI)) {
                continue;
            }

            if (file.isDirectory()) {
                arbolRecursivo(file, sb, indentacion + 1, condicionDirectorio, nivel,patronP, patronI);
            } else if (!condicionDirectorio) {
                if (patronP == null || coincidePatron(file.getName(), patronP)) {
                    imprimirFolder(file, indentacion + 1, sb);
                }
            }
        }

    }

//        for(File file:Folder.listFiles()){
//            if(file.isDirectory()){
//                arbolRecursivo(file,sb,indentacion+1,parametros);
//            } else if (parametros.equals("d")){
//                arbolRecursivo(file,sb,indentacion,parametros);
//            } else {
//                imprimirFolder(file,indentacion + 1,sb);
//            }
//        }


    private static void imprimirFolder(File Folder, int indentacion, StringBuilder sb) {
        sb.append(generarIndentacion(indentacion));
        sb.append("+-- ");
        sb.append(Folder.getName());
        sb.append("\n");

    }

    private static String generarIndentacion(int indentacion) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentacion; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }


    protected static String obtenerPatron(String[] parametros, String letras) {
        for(int i = 0;i < parametros.length - 1; i++){
            if(parametros[i].equals(letras)){
                return parametros[i + 1].replace("\"", "");
            }

        }
        return null;
    }


    public static String[] quitarPatrones(String[] parametros) {
        List<String> opciones = new ArrayList<>();
        for (int i = 0; i < parametros.length; i++) {
            opciones.add(parametros[i]);
            if ((parametros[i].equals("P") || parametros[i].equals("I")) && i +1 < parametros.length) {
                i++;
            }
        }
        return opciones.toArray(new String[0]);
    }

    protected static boolean coincidePatron(String nombre, String patron) {
        String regex = patron.replace(".","\\.").replace("*", ".*").replace("?", ".");
        return nombre.matches(regex);
    }


    protected static String validacion_tree(Scanner terminal) {
        String comando;
        while (true){
            IO.println("Comando: ");
            comando = terminal.nextLine();
            if (comando.equals("exit")) {
                break;
            } else if (comando.startsWith("tree")){
                break;
            }
            IO.println("Error: ¿Estás queriendo ejercutar el programa 'Tree'?");
        }
        return comando;
    }

    protected static String[] validacion_parametros(String comando) {
        List<String> parametros = new ArrayList<>();
        String[] tokens = comando.trim().split("\\s+");
        for (int i = 1; i < tokens.length; i++) {
            String t = tokens[i];
            if (t.startsWith("-")) {
                for (char c : t.substring(1).toCharArray()) {
                    parametros.add(String.valueOf(c));
                }
            } else {
                parametros.add(t);
            }
        }
        return parametros.toArray(new String[0]);
    }



}
