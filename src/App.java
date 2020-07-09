import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Palavra> lista = getList();
        WordTree wordTree = new WordTree();
        lista.forEach(wordTree::addWord);
        do {
            System.out.println("Entre com o prefixo da palavra desejada ou -1 para sair: ");
            String prefix = scanner.nextLine();
            if (prefix.equals("-1")) break;
            List<String> words = wordTree.searchAll(prefix);
            if (!words.isEmpty()) {
                for (String word : words) {
                    System.out.print(word + " ");
                }
                System.out.print("\n");
                System.out.println("Entre com uma palavra para saber o seu significado");
                String word = scanner.nextLine();
                String meaning = wordTree.getWordMeaning(word);
                if (meaning == null) System.out.println("Significado de " + word + " não encontrado");
                System.out.println("O Nome " + word + " " + meaning);
            } else System.out.println("Nenhuma palavra encontrada para este prefixo: " + prefix);
        } while (true);
    }

    public static List<Palavra> getList() {
        LinkedList<Palavra> lista = new LinkedList<>();
        Path path1 = Paths.get("nomes.csv");
        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] aux = line.split(";");
                Palavra p = new Palavra(aux[0], aux[1]);
                lista.add(p);
            }
            return lista;
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
            throw new RuntimeException(e);
        }
    }
}
