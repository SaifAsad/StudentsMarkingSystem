package studentsmarkingsystem;

/**
 *
 * @author Saif Asad
 * @param <K>
 * @param <V>
 */
public class RedBlackTree<K extends Comparable<? super K>, V> extends BinaryTree<K, V> {

    @Override
    public Node<K, V> insert(K key, V value) {
        Node<K, V> insertedNode = super.insert(key, value);
        Node<K, V> result = insertedNode;
        System.out.println("this is the parent of the node " + insertedNode.getParent());
        if(insertedNode.getParent() != null)
            System.out.println("this is the color of the parent " + insertedNode.getParent().getColor());
        while ((insertedNode.getParent() != null) && (insertedNode.getParent().getColor() == Color.RED)) {
            //PARENT OF Z IS THE LEFT CHILD 
            if (insertedNode.getParent().equals(insertedNode.getParent().getParent().getLeftChild())) {
                Node<K, V> uncle = insertedNode.getParent().getParent().getRightChild();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    insertedNode.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    insertedNode.getParent().getParent().setColor(Color.RED);
                    insertedNode = insertedNode.getParent().getParent();
                } else {
                    if (insertedNode.equals(insertedNode.getParent().getRightChild())) {
                        insertedNode = insertedNode.getParent();
                        leftRotate(insertedNode);
                    }
                    insertedNode.getParent().setColor(Color.BLACK);
                    insertedNode.getParent().getParent().setColor(Color.RED);
                    System.out.println("this is the grand parent of the node before right roatation " + insertedNode.getParent().getParent());
                    rightRotate(insertedNode.getParent().getParent());
                }

            } //PARENT OF Z IS THE RIGHT CHILD
            else { 
                Node<K, V> uncle = insertedNode.getParent().getParent().getLeftChild();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    insertedNode.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    insertedNode.getParent().getParent().setColor(Color.RED);
                    insertedNode = insertedNode.getParent().getParent();
                } else {
                    //node is inserted as left child of it's parent
                    if (insertedNode.equals(insertedNode.getParent().getLeftChild())) {
                        insertedNode = insertedNode.getParent();
                        rightRotate(insertedNode);
                    }
                    insertedNode.getParent().setColor(Color.BLACK);
                    insertedNode.getParent().getParent().setColor(Color.RED);
                    leftRotate(insertedNode.getParent().getParent());
                }
            }
        }
        this.getRoot().setColor(Color.BLACK);
        return result;
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Node<K,V> temp;
    //DELETION
    @Override
    public Node<K, V> delete(Node<K, V> node) {
        //there are 4 cases in delete
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            //if the node is a leaf
            if (node == root) {
                root = null;
            } else if (node.isLeftChild()) {
                //dereference the node to be ready for garbage collection
                node.getParent().setLeftChild(null);
            } else {
                node.getParent().setRightChild(null);
            }
        } else if (node.getRightChild() == null) {
            if (node == root) {
                root = node.getLeftChild();
                deleteFixUp(root);
            } else if (node.isLeftChild()) {
                temp = node.getLeftChild();
                node.getParent().setLeftChild(node.getLeftChild());
                deleteFixUp(temp);
            } else {
                temp = node.getLeftChild();
                node.getParent().setRightChild(node.getLeftChild());
                deleteFixUp(temp);
            }
        } else if (node.getLeftChild() == null) {
            if (node == root) {
                root = node.getRightChild();
                deleteFixUp(root); // this will ensure that the colour of the root is black
            } else if (node.isLeftChild()) {
                temp = node.getRightChild();
                node.getParent().setLeftChild(node.getRightChild());
                deleteFixUp(temp);
            } else {
                temp = node.getRightChild();
                node.getParent().setRightChild(node.getRightChild());
                deleteFixUp(temp);
            }
            //delete a node that has 2 children
        } else {
            Node<K, V> successor = super.getSuccessor(node);
            if (node == root) {
                root = successor;
            } else if (node.isLeftChild()) {
                node.getParent().setLeftChild(successor);
            } else {
                node.getParent().setRightChild(successor);
            }
            successor.setLeftChild(node.getLeftChild());
            deleteFixUp(node);
        }
        node.setParent(null);
        node.setLeftChild(null);
        node.setRightChild(null);
        node.setIsRightChild(false);
        node.setIsLeftChild(false);
        size--;
        return node;
    }
    private Node<K, V> deleteFixUp(Node<K, V> node) {
        Node<K, V> deletedNode = node;
        Node<K, V> result = deletedNode;
        Node<K, V> sibling;
        while (!(deletedNode.equals(this.getRoot())) && deletedNode.getColor() == Color.BLACK) {
            //deletedNode it left child of it's parent
            if (deletedNode.equals(deletedNode.getParent().getLeftChild())) {
                sibling = deletedNode.getParent().getRightChild();
                if (sibling != null && sibling.getColor() == Color.RED) {
                    //CASE ONE
                    sibling.setColor(Color.BLACK);
                    deletedNode.getParent().setColor(Color.RED);
                    leftRotate(deletedNode.getParent());
                    sibling = deletedNode.getParent().getRightChild();
                }
                if (sibling == null || ((sibling.getLeftChild() == null
                        || sibling.getLeftChild().getColor() == Color.BLACK)
                        && (sibling.getRightChild() == null
                        || sibling.getRightChild().getColor() == Color.BLACK))) {
                    //CASE TWO
                    if (sibling != null) {
                        sibling.setColor(Color.RED);
                    }
                    deletedNode = deletedNode.getParent();
                } else {
                    if (sibling.getRightChild() == null || sibling.getRightChild().getColor() == Color.BLACK) {
                        //CASE THREE
                        sibling.getLeftChild().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        rightRotate(sibling);
                        sibling = deletedNode.getParent().getRightChild();
                    }
                    sibling.setColor(deletedNode.getParent().getColor());
                    deletedNode.getParent().setColor(Color.BLACK);
                    sibling.getRightChild().setColor(Color.BLACK);
                    leftRotate(deletedNode.getParent());
                    deletedNode = this.getRoot();
                }
            } //deleted node is the right child of it's parent
            else {
                sibling = deletedNode.getParent().getLeftChild();
                if (sibling != null && sibling.getColor() == Color.RED) {
                    //CASE ONE
                    sibling.setColor(Color.BLACK);
                    deletedNode.getParent().setColor(Color.RED);
                    rightRotate(deletedNode.getParent());
                    sibling = deletedNode.getParent().getLeftChild();
                }
                if (sibling == null || ((sibling.getRightChild() == null
                        || sibling.getRightChild().getColor() == Color.BLACK)
                        && (sibling.getLeftChild() == null
                        || sibling.getLeftChild().getColor() == Color.BLACK))) {
                    //CASE TWO
                    if (sibling != null) {
                        sibling.setColor(Color.RED);
                    }
                    deletedNode = deletedNode.getParent();
                } else {
                    if (sibling.getLeftChild() == null || sibling.getLeftChild().getColor() == Color.BLACK) {
                        //CASE THREE
                        sibling.getRightChild().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        leftRotate(sibling);
                        sibling = deletedNode.getParent().getLeftChild();
                    }
                    sibling.setColor(deletedNode.getParent().getColor());
                    deletedNode.getParent().setColor(Color.BLACK);
                    sibling.getLeftChild().setColor(Color.BLACK);
                    rightRotate(deletedNode.getParent());
                    deletedNode = this.getRoot();
                }
            }
        }
        deletedNode.setColor(Color.BLACK);
        return result;
    }
}
