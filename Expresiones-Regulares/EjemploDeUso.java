// Usamos el paquete que creamos denominado Texto
package Texto;
// Importamos la clase scanner, ya que esta nos ayudara con los registros del usuario
import java.util.Scanner;


/**
 * Write a description of class EjemploDeUso here.
 * Usaremos un ejemplo basico para ver si nustro programa se efectua como deberia
 * @author (Julio Cesar Severiche Orellana) 
 * @version (1.0)
 */
public class EjemploDeUso extends Texto
{
    public EjemploDeUso(){
        // Inicializamos el objeto Scanner
        Scanner teclado = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println(" Bienvenido al Manipulador de Texto ");
        System.out.println("======================================");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Porfavor, escribe tu texto en el campo de abajo :)");
        String texto = teclado.nextLine();
        if(texto == null){
            System.out.println("Porfavor, escribe tu texto en el campo de abajo :)");
        }else{
            System.out.println("=============================================");
            System.out.println(" Ahora dime, como quieres procesar el texto ");
            System.out.println("=============================================");
            System.out.println(" ");
            System.out.println("1: Contabilizar las palabras del texto");
            System.out.println("2: Extraer los conectores textuales/gramaticales del texto");
            System.out.println("3: Identificar al sujeto, el verbo y el predicado del texto");
            int opcion = teclado.nextInt();
            switch(opcion){
                case 1: 
                    contarPalabras(texto);
                    break;
                case 2: 
                    extraerBorrarConectores(texto, false);
                    break;
                case 3:
                    interpretarOraciones(texto);
                    break;
                default:
                    System.out.println("La opcion que marcaste no existe");
            }
        }
    }
}
