import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        LinkedList<Palavra> lista = new LinkedList<>();
        Path path1 = Paths.get("nomes.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] aux = line.split(";");
                Palavra p = new Palavra(aux[0], aux[1]);
                lista.add(p);
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
        System.out.println("Lista de palavras e seus significados" + lista);
    }



}
