import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.List;

public class WordTree {
    private CharNode root;
    private int totalWords = 0;

    public WordTree() {
        root = new CharNode();
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return sumNodes(root, root.getNumberOfChildren());
    }

    public String getWordMeaning(String word) {
        CharNode charNode = findCharNodeForWord(word);
        if (charNode != null) {
            Palavra palavra = charNode.getWord();
            if (palavra != null) return palavra.getSignificado();
        }
        return null;
    }

    /**
     * Adiciona palavra na estrutura em árvore
     *
     * @param word
     */

    public void addWord(Palavra word) {
        char[] letters = word.getPalavra().toCharArray();
        for (int i = 0; i < letters.length; i++) {
            boolean isFinal = i == letters.length - 1;
            if (isFinal) {
                root.addChild(letters[i], true, word.getSignificado());
            } else {
                root.addChild(letters[i], false, null);
            }
        }
        totalWords++;
    }

    /**
     * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
     * Tipicamente, um método recursivo.
     *
     * @param prefix
     */

    public List<String> searchAll(String prefix) {
        CharNode charNode = findCharNodeForWord(prefix);
        if (charNode == null) return new ArrayList<>();
        else {
            List<String> words = new ArrayList<>();
            getWordForCharNode(charNode, words);
        }
        return new ArrayList<>();
    }

    private void getWordForCharNode(CharNode charNode, List<String> words) {
        for (int i = 0; i < charNode.getNumberOfChildren(); i++) {
            CharNode child = charNode.getChild(i);
            Palavra word = child.getWord();
            if (word != null) words.add(word.getPalavra());
            getWordForCharNode(child, words);
        }
    }

    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     *
     * @param word
     * @return o nodo final encontrado
     */

    private CharNode findCharNodeForWord(String word) {
        char[] letter = word.toCharArray();
        CharNode lastNode = null;
        for (int i = 1; i < letter.length; i++) {
            lastNode = root.findChildChar(letter[i]);
            if (lastNode == null) return null;
        }
        return lastNode;
    }

    private int sumNodes(CharNode node, int numberOfChildren) {
        int totalNodes = numberOfChildren;
        for (int i = 0; i < numberOfChildren; i++) {
            CharNode child = node.getChild(i);
            totalNodes += sumNodes(child, child.getNumberOfChildren());
        }
        return totalNodes;
    }

    private CharNode insertCharacter(CharNode charNode, ArrayList<Characters> characters, int index) {

    }
}
