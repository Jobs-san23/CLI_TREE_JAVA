import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MetodosCLI {

    public static String imprimirArbol(File Folder, String[] parametros,int parametroNumero){
        //metemos un condicional para verificar si la ruta es un directorio
        if(!Folder.isDirectory()){
            throw new IllegalArgumentException("La ruta especificada no es un directorio");
        }
        int indentacion = 0;
        //Creamos un StringBuilder para almacenar la estructura del Arbol
        StringBuilder sb = new StringBuilder();
        if (parametroNumero != -1 && indentacion >= parametroNumero) {
            arbolRecursivo(Folder,sb,indentacion,parametros);
        }
        arbolRecursivo(Folder,sb,indentacion,parametros);
        return sb.toString();
    }

    public static void arbolRecursivo(File Folder,StringBuilder sb, int indentacion, String[] parametros){
        if (!Folder.isDirectory()){
            throw new IllegalArgumentException("La ruta especificada no es un directorio");
        }

        sb.append(generarIndentacion(indentacion));
        sb.append("+-- ");
        sb.append(Folder.getName());
        sb.append("/");
        sb.append("\n");
        for(File file:Folder.listFiles()){
            if(file.isDirectory()){
                arbolRecursivo(file,sb,indentacion+1,parametros);
            } else if (parametros.equals("d")){
                arbolRecursivo(file,sb,indentacion,parametros);
            } else {
                imprimirFolder(file,indentacion + 1,sb);
            }
        }

    }

    private static void imprimirFolder(File Folder,int indentacion, StringBuilder sb){
        sb.append(generarIndentacion(indentacion));
        sb.append("+-- ");
        sb.append(Folder.getName());
        sb.append("\n");

    }

    private static String generarIndentacion(int indentacion){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentacion;i++){
            sb.append("|  ");
        }
        return sb.toString();
    }





    protected static String validacion_tree(Scanner terminal) {
        String comando;
        while (true){
            IO.println("Comando: ");
            comando = terminal.nextLine();
            if (comando.startsWith("tree")){
                break;
            }
            IO.println("Error: ¿Estás queriendo ejercutar el programa 'Tree'?");
        }
        return comando;
    }

    protected static String[] validacion_parametros (String comando){
        String[] parametros;

        if (comando.contains("-")) {
            String opciones = comando.substring(comando.indexOf("-"))
                    .replace("-", "");
            return parametros = opciones.split("");
        } else {
            return parametros = new String[0];
        }

    }



}
