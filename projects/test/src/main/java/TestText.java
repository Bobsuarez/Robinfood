import java.util.ArrayList;
import java.util.List;

public class TestText {


    public static void main(String[] args) {

        List<String> nameList = new ArrayList<>();
        nameList.add("300 gramos de chatas con ensalda + papa a la francesa o papa criolla");
        nameList.add("Pincho de carne y pollo + vegetales salteados acompañado de papas a la francesa");
        nameList.add("8 alitas de pollo panadas picantes + papas a la francesa");
        nameList.add("150 gramos de maiz + pollo + tocineta + salchicha, con queso gratinado encima, acompañado de " +
                "papas a la francesa");
        nameList.add("120 gramos de carne + tomate + cebolla grille+ jamon + lechuga + queso derretido, acompañado de" +
                " papas a la francesa");
        nameList.add("");

        String expresion = "300 gramos de chatas con ensalda + papa a la francesa o papa criolla";

        // Utilizar regex para reemplazar los operadores
        String resultado = expresion.replaceAll("\\s\\+\\s|\\+", ", ");

        nameList.forEach(data -> {
            // Convierte la cadena a un StringBuilder
            System.out.println("Texto original: " + data);
            var label = data.replaceAll("\\s\\+\\s|\\+", ", ");
            var index = label.lastIndexOf(",");
            replaceText(label, index);
        });
    }

    private static void replaceText(String texto, int indice) {
        char nuevaLetra = 'y';
        char letraO = 'o';
        // Convierte la cadena a un arreglo de caracteres
        char[] caracteres = texto.toCharArray();
        System.out.println("Texto sin caracteres: " + texto);


        if (texto.contains(" o ")) {
            System.out.println("\n");
            return;

        }


        // Verifica que el índice esté dentro de los límites
        if (indice >= 0 && indice < caracteres.length) {
            // Cambia la letra en el índice especificado

            caracteres[indice] = nuevaLetra;

            StringBuilder stringBuilder = new StringBuilder(new String(caracteres));
            stringBuilder.insert(indice, " ");
            // Imprime el resultado
            System.out.println("Texto final : " + stringBuilder + "\n");
        }

    }
}
