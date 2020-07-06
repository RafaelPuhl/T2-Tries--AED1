
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

    public CharNode(char character, boolean isFinal, CharNode father) {
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
        Optional<CharNode> charNode = this.children.stream()
                .filter(it -> it.getCharacter() == character)
                .findFirst();
        CharNode child;
        if (charNode.isPresent()) {
            child = charNode.get();
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

    }

    /**
         * Encontra e retorna o nodo que tem determinado caracter.
         * @param character - caracter a ser encontrado.
     */
    public Optional<CharNode> findChildChar (char character) {

    }

    public char getCharacter() {
        return character;
    }
}