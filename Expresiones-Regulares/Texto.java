// Este es el paquete de manipulación de texto
package Texto;

// Estas son las librerías que se necesitan para usar expresiones regulares
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;



// Esta es la clase principal del paquete
public class Texto {

    // Este es el constructor de la clase
    public Texto() {
        // No hace nada
    }

    // Este es el primer método, que cuenta todas las palabras de un texto
    public void contarPalabras(String texto) {
        // Se crea un patrón para identificar las palabras usando una expresión regular
        Pattern patron = Pattern.compile("[\\w.*(ñ|á|é|í|ó|ú|Ñ|Á|É|Í|Ó|Ú)]+");
        // Se crea un objeto Matcher para buscar las coincidencias en el texto
        Matcher buscador = patron.matcher(texto);
        // Se crea un mapa para almacenar las palabras y sus frecuencias
        Map<String, Integer> mapa = new HashMap<>();
        // Se recorre el texto buscando las palabras
        while (buscador.find()) {
            // Se obtiene la palabra encontrada
            String palabra = buscador.group();
            // Se verifica si la palabra ya está en el mapa
            if (mapa.containsKey(palabra)) {
                // Se incrementa la frecuencia de la palabra en el mapa
                mapa.put(palabra, mapa.get(palabra) + 1);
            } else {
                // Se agrega la palabra al mapa con frecuencia 1
                mapa.put(palabra, 1);
            }
        }
        // Se imprime el resultado
        System.out.println(" ");
        System.out.println("El texto tiene " + mapa.size() + " palabras distintas.");
        System.out.println("Las palabras y sus frecuencias son:");
        for (String clave : mapa.keySet()) {
            System.out.println(clave + ": " + mapa.get(clave));
        }
    }

    // Este es el segundo método, que extrae o borra los conectores textuales de un texto
    public void extraerBorrarConectores(String texto, boolean extraer) {
        // Se crea un patrón para identificar los conectores textuales usando una expresión regular
        Pattern patron = Pattern.compile("\\b(y|o|pero|porque|aunque|sin embargo|además|así que|entonces|luego|por lo tanto|en cambio|no obstante|con|por|su|como|con|el|la|los|un|una|unos|unas)\\b", Pattern.CASE_INSENSITIVE);
        // Se crea un objeto Matcher para buscar las coincidencias en el texto
        Matcher buscador = patron.matcher(texto);
        // Se crea una variable para almacenar el resultado
        String resultado = "";
        // Se verifica si se quiere extraer o borrar los conectores
        if (extraer) {          
            // Se recorre el texto buscando los conectores
            while (buscador.find()) {
                // Se obtiene el conector encontrado
                String conector = buscador.group();
                // Se agrega el conector al resultado con un espacio al final
                resultado += conector + " ";
            }
            // Se imprime el resultado
            System.out.println(" ");
            System.out.println("Los conectores textuales del texto son:");
            System.out.println(resultado);
        } else {
            // Se reemplaza los conectores por espacios vacíos en el texto usando el método replaceAll del Matcher
            resultado = buscador.replaceAll("");
            // Se imprime el resultado
            System.out.println(" ");
            System.out.println("El texto sin los conectores textuales es:");
            System.out.println(resultado);
        }
    }

    // Este es el tercer método, que interpreta las oraciones de un texto
    public void interpretarOraciones(String texto) {
        // Se crea un patrón para identificar las oraciones usando una expresión regular
        Pattern patron = Pattern.compile("[A-Za-z][^.?!]+[.?!]");
        // Se crea un objeto Matcher para buscar las coincidencias en el texto
        Matcher buscador = patron.matcher(texto);
        // Se recorre el texto buscando las oraciones
        while (buscador.find()) {
            // Se obtiene la oración encontrada
            String oracion = buscador.group();
            // Se imprime la oración
            System.out.println("Oración: " + oracion);
            // Se llama al método para identificar el sujeto de la oración
            identificarSujeto(oracion);
            // Se llama al método para identificar el verbo o los verbos de la oración
            identificarVerbos(oracion);
            // Se llama al método para identificar el predicado de la oración
            identificarPredicado(oracion);
        }
    }
    
    // Este es el método para identificar el sujeto de una oración
    public void identificarSujeto(String oracion) {
        // Se crea un patrón para identificar el sujeto usando una expresión regular
        Pattern patron = Pattern.compile("^(YO|EL|LA|LOS|UN|UNA|UNOS|UNAS|ME|MI|NOSOTROS|USTEDES|Yo|El|La|Los|Las|Un|Una|Unos|Unas|Me|Mi|Nosotros|Ustedes|yo|el|la|los|las|un|una|unos|unas|me|mi|nosotros|ustedes\\w*)\\b[^.,;:]*");
        // Se crea un objeto Matcher para buscar la coincidencia en la oración
        Matcher buscador = patron.matcher(oracion);
        // Se verifica si se encuentra el sujeto
        if (buscador.find()) {
            // Se obtiene el sujeto encontrado
            String sujeto = buscador.group();
            // Se imprime el sujeto
            System.out.println(" ");
            System.out.println("Sujeto: " + sujeto);
        } else {
            // Se imprime un mensaje de error
            System.out.println(" ");
            System.out.println("No se pudo identificar el sujeto.");
        }
    }

    // Este es el método para identificar el verbo o los verbos de una oración
    public void identificarVerbos(String oracion) {
        // Se crea un patrón para identificar los verbos usando una expresión regular
        Pattern patron = Pattern.compile("\\b\\w+(anta|anto|in|mos|ne|nen|ar|er|ir|an|en|ado|ido|ando|iendo|to|tes|so|cho|ga|gas|as)\\b", Pattern.CASE_INSENSITIVE);
        // Se crea un objeto Matcher para buscar las coincidencias en la oración
        Matcher buscador = patron.matcher(oracion);
        // Se crea una variable para almacenar los verbos encontrados
        String verbos = "";
        // Se recorre la oración buscando los verbos
        while (buscador.find()) {
            // Se obtiene el verbo encontrado
            String verbo = buscador.group();
            // Se agrega el verbo a la variable con un espacio al final
            verbos += verbo + " ";
        }
        // Se verifica si se encontraron verbos
        if (!verbos.isEmpty()) {
            // Se imprime los verbos
            System.out.println(" ");
            System.out.println("Verbo(s): " + verbos);
        } else {
            // Se imprime un mensaje de error
            System.out.println(" ");
            System.out.println("No se pudo identificar el verbo o los verbos.");
        }
    }

    // Este es el método para identificar el predicado de una oración
    public void identificarPredicado(String oracion) {
        // Se crea un patrón para identificar el predicado usando una expresión regular
        Pattern patron = Pattern.compile("\\b\\w+(a|an|ar|er|ir|ado|ido|ando|iendo|to|so|cho)\\b(.*)$", Pattern.CASE_INSENSITIVE);
        // Se crea un objeto Matcher para buscar la coincidencia en la oración
        Matcher buscador = patron.matcher(oracion);
        // Se verifica si se encuentra el predicado
        if (buscador.find()) {
            // Se obtiene el predicado encontrado
            String predicado = buscador.group(2);
            // Se imprime el predicado
            System.out.println(" ");
            System.out.println("Predicado: " + predicado);
        } else {
            // Se imprime un mensaje de error
            System.out.println(" ");
            System.out.println("No se pudo identificar el predicado.");
        }
    }
}
        


