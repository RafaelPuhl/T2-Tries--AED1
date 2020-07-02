package AlgoritimoEstrutura1.Trees;

import java.util.ArrayList;

public class AppTree {
    public static void main(String[] args) {
        ArrayList<String> list;

        GenericTree genericTree = new GenericTree();
        genericTree.add("A", null);
        genericTree.add("B", "A");
        genericTree.add("C", "A");
        genericTree.add("D", "C");

        System.out.println("Traversal Pre");
        list = genericTree.traversalPre();

        for (String str:list ) {
            System.out.print(str);
        }

        System.out.println();
        System.out.println("Traversal Pos");
        list = genericTree.traversalPos();

        for (String str:list ) {
            System.out.print(str);
        }
        genericTree.removeBranch("C");

        System.out.println();
        System.out.println("Item C removido");
        list = genericTree.traversalPre();

        for (String str:list ) {
            System.out.print(str);
        }

    }
}
