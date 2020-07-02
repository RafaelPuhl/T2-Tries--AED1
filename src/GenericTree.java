package AlgoritimoEstrutura1.Trees;

import java.util.ArrayList;
import java.util.List;

public class GenericTree {
    NodeTree refRoot;

    public GenericTree() {
        this.refRoot = null;
    }

    public boolean add(String item, String father) {
        NodeTree node = new NodeTree(item);
        NodeTree nodeAux = null;
        boolean result = false;

        if (father == null) {
            if (this.refRoot != null) {
                node.addSubTree(this.refRoot);
                this.refRoot.setFather(node);
            }
            this.refRoot = node;
            result = true;
        } else {
            nodeAux = searchNodeRef(father, this.refRoot);
            if (nodeAux != null) {
                nodeAux.addSubTree(node);
                node.setFather(nodeAux);
                result = true;
            }
        }
        return result;
    }

    private NodeTree searchNodeRef(String element, NodeTree target) {
        if (target == null) return null;
        if (element.equals(target.getElement())) {
            return target;
        } else {
            for (int i = 0; i < target.contSubTree(); i++) {
                NodeTree aux = searchNodeRef(element, target.getSubTree(i));
                if (aux != null) return aux;
            }
        }
        return null;
    }

    public boolean removeBranch(String item){

        if (item.equals(this.refRoot.getElement())){
            this.refRoot = null;
            return true;
        } else {
            NodeTree aux;
            aux = searchNodeRef(item, this.refRoot);
            aux = aux.getFather();
            for (int i =0; i< aux.contSubTree(); i++){
                if (aux.getSubTree(i).getElement().equals(item)){
                    aux.removeSubTree(i);
                    return true;
                }
            }
            return false;
        }
    }

    public ArrayList<String> traversalPre(){
        ArrayList<String> list = new ArrayList<>();
        searchTreePre(refRoot, list);
        return list;
    }

    private void searchTreePre(NodeTree reference, ArrayList<String> list){
        if(reference == null) return;
        list.add(reference.getElement());
        for (int i = 0; i < reference.contSubTree(); i++) {
            searchTreePre(reference.getSubTree(i), list);
        }
        return ;
    }

    public ArrayList<String> traversalPos(){
        ArrayList<String> list = new ArrayList<>();
        searchTreePos(refRoot, list);
        return list;
    }

    private void searchTreePos(NodeTree reference, ArrayList<String> list){
        if(reference == null) return;
        for (int i = 0; i < reference.contSubTree(); i++) {
            searchTreePos(reference.getSubTree(i), list);
        }
        list.add(reference.getElement());
        return ;
    }
}
