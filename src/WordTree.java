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
        insertCharacter(root, word.getSignificado(), letters, 0);
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
        List<String> words = new ArrayList<>();
        Palavra palavra = charNode.getWord();
        if (palavra != null) words.add(palavra.getPalavra());
        getWordForCharNode(charNode, words);
        return words;
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
        return findLastNodeOfWordLetters(root, word.toCharArray(), 0);
    }

    private CharNode findLastNodeOfWordLetters(CharNode charNode, char[] letter, int index) {
        CharNode child = charNode.findChildChar(letter[index]);
        if (child == null) return null;
        if (index == letter.length - 1) return child;
        return findLastNodeOfWordLetters(child, letter, index + 1);
    }

    private int sumNodes(CharNode node, int numberOfChildren) {
        int totalNodes = numberOfChildren;
        for (int i = 0; i < numberOfChildren; i++) {
            CharNode child = node.getChild(i);
            totalNodes += sumNodes(child, child.getNumberOfChildren());
        }
        return totalNodes;
    }

    private void insertCharacter(CharNode charNode, String meaning, char[] letters, int index) {
        boolean isFinal = index == letters.length - 1;
        if (isFinal) {
            charNode.addChild(letters[index], true, meaning);
        } else {
            CharNode child = charNode.findChildChar(letters[index]);
            if (child == null) {
                charNode.addChild(letters[index]);
                insertCharacter(charNode.findChildChar(letters[index]), meaning, letters, index + 1);
            } else {
                insertCharacter(child, meaning, letters, index + 1);
            }
        }
    }
}
