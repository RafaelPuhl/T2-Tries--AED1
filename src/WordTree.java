import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordTree {
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    public WordTree(String word) {
        addWord(word);
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return sumNodes(root, root.getNumberOfChildren());
    }

    private int sumNodes(CharNode node, int numberOfChildren) {
        int totalNodes = numberOfChildren;
        for (int i = 0; i < numberOfChildren; i++) {
            CharNode child = node.getChild(i);
            totalNodes += sumNodes(child, child.getNumberOfChildren());
        }
        return totalNodes;
    }

    /**
         *Adiciona palavra na estrutura em árvore
         *@param word
     */

    public void addWord(String word) {
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            boolean isFinal = i == letters.length - 1;
            if (root == null) {
                root = new CharNode(letters[i]);
            } else {
                root.addChild(letters[i], isFinal);
            }
        }
        totalWords++;
    }

    /**
         * Vai descendo na árvore até onde conseguir encontrar a palavra
         * @param word
         * @return o nodo final encontrado
     */

    private CharNode findCharNodeForWord(String word) {
        char[] letter = word.toCharArray();
        if (root == null) return null;
        else if (root.getCharacter() != letter[0]) return null;
        CharNode lastNode = null;
        for (int i = 1; i < letter.length; i++){
            lastNode = root.findChildChar(letter[i]);
            if (lastNode == null) return null;
        }
        return lastNode;
    }

    /**
         * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
         * Tipicamente, um método recursivo.
         * @param prefix
     */

    public List<String> searchAll(String prefix) {
        return new ArrayList<>();
    }
}
