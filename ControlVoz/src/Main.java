import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        String[] array = {"Hola mundo", "Hola amigos", "Hola OpenAI"};

        String textoBuscado = "jhon";
        Pattern pattern = Pattern.compile(textoBuscado);

        for (String texto : array) {
            Matcher matcher = pattern.matcher(texto);
            if (matcher.find()) {
                System.out.println("Texto encontrado: " + texto);
            }
        }
    }
}