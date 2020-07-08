
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CharNode {

    private final Character character;
    private boolean isFinal;
    private CharNode father;
    private String wordMeaning;
    private List<CharNode> children;

    public CharNode() {
        this(null, false, null, null);
    }

    private CharNode(Character character, boolean isFinal, String wordMeaning, CharNode father) {
        this.character = character;
        this.isFinal = isFinal;
        this.father = father;
        this.wordMeaning = wordMeaning;
        this.children = new ArrayList<>();
    }

    /**
     * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
     *
     * @param character - caracter a ser adicionado
     * @param isFinal   - se é final da palavra ou não
     */

    public CharNode addChild(char character, boolean isFinal, String meaning) {
        CharNode charNode = findChildChar(character);
        CharNode child;
        if (charNode != null) {
            child = charNode;
            if (!child.isFinal && isFinal) {
                child.isFinal = true;
                this.wordMeaning = meaning;
            }
        } else {
            child = new CharNode(character, isFinal, meaning, this);
            this.children.add(child);
        }
        return child;
    }

    public int getNumberOfChildren() {
        return this.children.size();
    }

    public CharNode getChild(int index) {
        return children.get(index);
    }

    /**
     * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
     *
     * @return a palavra
     */
    public Palavra getWord() {
        if (isFinal) {
            List<Character> letters = new ArrayList<>();
            letters.add(character);
            CharNode parent = this.father;
            while (parent != null && parent.character != null) {
                letters.add(parent.character);
                parent = parent.father;
            }
            StringBuilder word = new StringBuilder("");
            for (int i = letters.size() - 1; i >= 0; i--) {
                word.append(letters.get(i));
            }
            return new Palavra(word.toString(), wordMeaning);
        } else {
            return null;
        }
    }

    /**
     * Encontra e retorna o nodo que tem determinado caracter.
     *
     * @param character - caracter a ser encontrado.
     */
    public CharNode findChildChar(char character) {
        for (CharNode charNode : this.children) {
            if (charNode.getCharacter() == character) return charNode;
        }
        return null;
    }

    public char getCharacter() {
        return character;
    }
}