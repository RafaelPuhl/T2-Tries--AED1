
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CharNode {

    private final char character;
    private boolean isFinal;
    private CharNode father;
    private List<CharNode> children;

    public CharNode(char character) {
        this(character, false, null);
    }

    private CharNode(char character, boolean isFinal, CharNode father) {
        this.character = character;
        this.isFinal = isFinal;
        this.father = father;
        this.children = new ArrayList<>();
    }

    /**
         * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
         * @param character - caracter a ser adicionado
         * @param isFinal - se é final da palavra ou não
     */

    public CharNode addChild (char character, boolean isFinal) {
        CharNode charNode = findChildChar(character);
        CharNode child;
        if (charNode != null) {
            child = charNode;
            if (!child.isFinal && isFinal) child.isFinal = true;
        }else {
            child = new CharNode(character, isFinal, this);
            this.children.add(child);
        }
        return child;
    }

    public int getNumberOfChildren () {
        return this.children.size();
    }

    public CharNode getChild (int index) {
        return children.get(index);
    }

    /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * @return a palavra
     */
    private String getWord() {
        List<Character> letters = new ArrayList<>();
        letters.add(character);
        CharNode parent = this.father;
        while(parent != null) {
            letters.add(parent.character);
            parent = parent.father;
        }
        StringBuilder word = new StringBuilder("");
        for (int i = letters.size() - 1; i >= 0; i--) {
            word.append(letters.get(i));
        }
        return word.toString();
    }

    /**
         * Encontra e retorna o nodo que tem determinado caracter.
         * @param character - caracter a ser encontrado.
     */
    public CharNode findChildChar (char character) {
        for (CharNode charNode: this.children){
            if (charNode.getCharacter() == character) return charNode;
        }
        return null;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public char getCharacter() {
        return character;
    }
}