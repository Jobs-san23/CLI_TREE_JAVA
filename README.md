# 🌳 mytree

Una versión propia del comando `tree`, escrita en **Java**. Muestra la estructura de carpetas y archivos como un árbol directamente en la terminal, y se ejecuta escribiendo solo `mytree` desde cualquier carpeta de Windows.

```
C:\proyectos\mi-proyecto> mytree -L 2
+-- mi-proyecto/
|  +-- docs/
|  |  +-- guia.txt
|  +-- notas.txt
|  +-- src/
|  |  +-- Main.java
```

## ✨ Características

- Árbol recursivo de la carpeta donde estés parado, sin pedir ninguna ruta.
- Filtros por profundidad (`-L`), solo carpetas (`-d`) y patrones con comodines (`-P` para incluir, `-I` para ignorar).
- Listados por ruta absoluta (`-f`) y por fecha de modificación (`-t`).
- Opciones combinables, incluso juntas: `-dL 2` es lo mismo que `-d -L 2`.
- Ayuda editable (`--help`) leída desde un archivo de texto, sin recompilar.
- Se instala como un comando más del sistema, igual que `tree`.

## 🚀 Uso rápido

```
mytree                        Árbol completo de la carpeta actual
mytree -d                     Solo las carpetas
mytree -L 2                   Hasta 2 niveles de profundidad
mytree -P "*.java"            Solo los archivos .java
mytree -I "node_modules"      Ignora node_modules
mytree -P "*.txt" -I "old*"   Solo .txt, ignorando lo que empiece con old
mytree -f                     Rutas completas
mytree -t                     Ordenado por fecha de modificación
mytree --help                 Muestra la ayuda
```

> Escribe los patrones entre comillas (`-P "*.java"`). Sin comillas, Java en Windows puede expandir el `*` y el patrón llega mal.

### Opciones

| Opción | Descripción |
|---|---|
| `-a` | Muestra todo el árbol |
| `-d` | Solo directorios |
| `-f` | Lista las rutas absolutas del primer nivel |
| `-t` | Lista el primer nivel por fecha de modificación |
| `-L <n>` | Limita la profundidad a `n` niveles |
| `-P <patrón>` | Solo archivos que coinciden con el patrón (`*` y `?`) |
| `-I <patrón>` | Ignora lo que coincide con el patrón |
| `--help` | Muestra la ayuda |

## 📦 Instalación

**Requisitos:** Windows y **Java 25 o superior** (el código usa `IO.println`).

1. Genera el `.jar` (con IntelliJ: *Build Artifacts*, o con `javac` y `jar cfe`) usando `CLI_Mytree` como clase principal.
2. Crea una carpeta, por ejemplo `C:\Users\usuario\Mytree`, y copia dentro:
   - el `.jar`
   - `CLI_HELP_TEXT.txt`
   - `mytree.bat`:
     ```bat
     @echo off
     java -jar "%~dp0CLI Tree.jar" %*
     ```
     (el nombre del jar debe coincidir con el tuyo)
3. Agrega esa carpeta al **PATH** de Windows.
4. Abre una terminal nueva y escribe `mytree`.

## 🧠 Cómo funciona

```
mytree.bat ──► CLI_Mytree ──► MetodosCLI ──► Parametros
 lanzador      lee y valida    arma el árbol   un método por
               las opciones    (recursivo)     cada opción
```

| Archivo | Qué hace |
|---|---|
| `CLI_Mytree.java` | Punto de entrada. Toma los argumentos, valida las opciones y decide qué imprimir. |
| `MetodosCLI.java` | Recorrido recursivo con `StringBuilder`, filtros por patrón, separación de opciones y patrones, y ayuda. |
| `Parametros.java` | Interfaz con un método por opción y la ayuda de respaldo. |
| `CLI_HELP_TEXT.txt` | Texto de ayuda, buscado junto al `.jar`. |
| `mytree.bat` | Lanzador que permite usar `mytree` como comando. |

Detalles de diseño:

- La carpeta raíz es la carpeta actual de la terminal (`user.dir`), por eso el árbol cambia según dónde ejecutes el comando.
- Los comodines `*` y `?` se convierten a expresiones regulares en `coincidePatron`.
- Las opciones juntas (`-dL`) se descomponen letra por letra en `validacion_parametros`.

## 🛣️ Pendientes

Cosas que se pueden mejorar para parecerse más al `tree` de Linux:

- [ ] Dibujar con `├──` y `└──` y mostrar el resumen final (`N directories, M files`).
- [ ] Ocultar archivos ocultos salvo con `-a`.
- [ ] Aceptar una ruta como argumento (`mytree C:\ruta`).
- [ ] Que `-f` y `-t` respeten la estructura de árbol.
- [ ] Patrones con espacios y mensajes de error que indiquen qué falló.
- [ ] Script `.sh` para Linux y Mac.

## 🛠️ Hecho con

Java · `java.io.File` · `java.nio.file` · Streams · Expresiones regulares

---

Proyecto de la materia de Programación III.
