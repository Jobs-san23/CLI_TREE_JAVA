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
        //se crea una variable para verificar si se debe imprimir solo los directorios o no
        boolean condicionDirectorio = Arrays.asList((quitarPatrones(parametros))).contains("d");
        String patronP = obtenerPatron(parametros, "P");
        String patronI = obtenerPatron(parametros, "I");
        //se crea un StringBuilder para almacenar el arbol y se llama al metodo arbolRecursivo para imprimir el arbol
        StringBuilder sb = new StringBuilder();
        //se llama al metodo arbolRecursivo para imprimir el arbol
        arbolRecursivo(Folder, sb, 0, condicionDirectorio, nivel,patronP, patronI);
        //AQUI SE IMPRIME EL ARBOL
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

        //se hace una condicional para verificar si la indentacion es mayor o igual al nivel especificado, si es asi se termina la recursion
        if (indentacion >= nivel) {
            return;
        }



        File[] archivos = Folder.listFiles();
        if (archivos == null) return;

        Arrays.sort(archivos);
        //se hace un bucle para recorrer los archivos y directorios del directorio actual
        for (File file : archivos) {
            //
            if (patronI != null && coincidePatron(file.getName(), patronI)) {
                continue;
            }


            if (file.isDirectory()) {
                arbolRecursivo(file, sb, indentacion + 1, condicionDirectorio, nivel,patronP, patronI);
            } else if (!condicionDirectorio) {
                //
                if (patronP == null || coincidePatron(file.getName(), patronP)) {
                    imprimirFolder(file, indentacion + 1, sb);
                }
            }
        }
    }

    // se crea el metodo imprimirFolder para imprimir los archivos y directorios del arbol
    private static void imprimirFolder(File Folder, int indentacion, StringBuilder sb) {
        //se hace una condicional para verificar si la ruta es un directorio
        sb.append(generarIndentacion(indentacion));
        sb.append("+-- ");
        sb.append(Folder.getName());
        sb.append("\n");

    }
    // se crea el metodo generarIndentacion para generar la indentacion del arbol
    private static String generarIndentacion(int indentacion) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentacion; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    // se crea el metodo obtenerPatron para obtener el patron especificado por el usuario
    protected static String obtenerPatron(String[] parametros, String letras) {
        //se hace un bucle para recorrer los parametros y buscar el patron especificado por el usuario
        for(int i = 0;i < parametros.length - 1; i++){
            if(parametros[i].equals(letras)){
                //se hace un condicional para verificar si el patron especificado por el usuario esta entre comillas, si es asi se quitan las comillas
                return parametros[i + 1].replace("\"", "");
            }

        }
        return null;
    }

    //||||||||||||||||||||||||
    // se crea el metodo quitarPatrones para quitar los patrones especificados por el usuario de los parametros
    public static String[] quitarPatrones(String[] parametros) {
        //se crea una lista para almacenar los parametros sin los patrones
        List<String> opciones = new ArrayList<>();
        for (int i = 0; i < parametros.length; i++) {
            //se hace un condicional para verificar si el parametro es un patron, si es asi se omite y se agrega el siguiente parametro a la lista
            opciones.add(parametros[i]);
            //se hace un condicional para verificar si el parametro es un patron, si es asi se omite y se agrega el siguiente parametro a la lista
            if ((parametros[i].equals("P") || parametros[i].equals("I")) && i +1 < parametros.length) {
                i++;
            }
        }
        return opciones.toArray(new String[0]);
    }
    //|||||||||||||||||||||||||||||

    //se crea el metodo coincidePatron para verificar si el nombre del archivo o directorio coincide con el patron especificado por el usuario
    protected static boolean coincidePatron(String nombre, String patron) {
        //se hace un condicional para verificar si el patron es nulo, si es asi se retorna true
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

    //se crea el metodo validacion_parametros para validar los parametros ingresados por el usuario
    protected static String[] validacion_parametros(String comando) {
        List<String> parametros = new ArrayList<>();
        //se hace un bucle para recorrer los charParametro del comando y separar los parametros de los patrones
        String[] charParametro = comando.trim().split("\\s+");
        for (int i = 1; i < charParametro.length; i++) {
            String t = charParametro[i];
            if (t.startsWith("-")) {
                //
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
