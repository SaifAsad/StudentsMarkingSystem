package studentsmarkingsystem;


/*
 enum Color { RED, BLACK }

 class Node<K extends Comparable<? super K>,V>
 {
 public K key;
 public V value;
 public Node<K,V> left;
 public Node<K,V> right;
 public Node<K,V> parent;
 public Color color;

 public Node(K key, V value, Color nodeColor, Node<K,V> left, Node<K,V> right) {
 this.key = key;
 this.value = value;
 this.color = nodeColor;
 this.left = left;
 this.right = right;
 if (left  != null)  left.parent = this;
 if (right != null) right.parent = this;
 this.parent = null;
 }
 public Node<K,V> grandparent() {
 assert parent != null; // Not the root node
 assert parent.parent != null; // Not child of root
 return parent.parent;
 }
 public Node<K,V> sibling() {
 assert parent != null; // Root node has no sibling
 if (this == parent.left)
 return parent.right;
 else
 return parent.left;
 }
 public Node<K,V> uncle() {
 assert parent != null; // Root node has no uncle
 assert parent.parent != null; // Children of root have no uncle
 return parent.sibling();
 }
 }
 */
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
public class RBTree<K extends Comparable<? super K>, V> extends BinaryTree<K, V> {

    public static final boolean VERIFY_RBTREE = true;
    private static final int INDENT_STEP = 4;

    //public Node<K,V> root;
    public RBTree() {
        root = null;
        verifyProperties();
    }

    public void verifyProperties() {
        if (VERIFY_RBTREE) {
            verifyProperty1(root);
            verifyProperty2(root);
            // Property 3 is implicit
            verifyProperty4(root);
            verifyProperty5(root);
        }
    }

    private static void verifyProperty1(Node<?, ?> n) {
        if (nodeColor(n) == Color.RED || nodeColor(n) == Color.BLACK) {
            if (n == null) {
                return;
            }
            verifyProperty1(n.getLeftChild());
            verifyProperty1(n.getRightChild());
        }
    }

    //make sure the root node is black
    private static void verifyProperty2(Node<?, ?> root) {
        if(nodeColor(root) == Color.BLACK){
        
        }
    }
    //All leaves (shown as NIL in the above diagram) are black and contain no data.
    //Since we represent these empty leaves using null, 
    //this property is implicitly assured by always treating null as black.
    //To this end we create a nodeColor() helper function:
    private static Color nodeColor(Node<?, ?> n) {
        return n == null ? Color.BLACK : n.color;
    }

    private static void verifyProperty4(Node<?, ?> n) {
        if (nodeColor(n) == Color.RED) {
            assert nodeColor(n.getLeftChild()) == Color.BLACK;
            assert nodeColor(n.getRightChild()) == Color.BLACK;
            assert nodeColor(n.getParent()) == Color.BLACK;
        }
        if (n == null) {
            return;
        }
        verifyProperty4(n.getLeftChild());
        verifyProperty4(n.getRightChild());
    }

    private static void verifyProperty5(Node<?, ?> root) {
        verifyProperty5Helper(root, 0, -1);
    }

    private static int verifyProperty5Helper(Node<?, ?> n, int blackCount, int pathBlackCount) {
        if (nodeColor(n) == Color.BLACK) {
            blackCount++;
        }
        if (n == null) {
            if (pathBlackCount == -1) {
                pathBlackCount = blackCount;
            } else {
                assert blackCount == pathBlackCount;
            }
            return pathBlackCount;
        }
        pathBlackCount = verifyProperty5Helper(n.getLeftChild(), blackCount, pathBlackCount);
        pathBlackCount = verifyProperty5Helper(n.getRightChild(), blackCount, pathBlackCount);
        return pathBlackCount;
    }

    private Node<K, V> lookupNode(K key) {
        Node<K, V> n = root;
        while (n != null) {
            int compResult = key.compareTo(n.getKey());
            if (compResult == 0) {
                return n;
            } else if (compResult < 0) {
                n = n.getLeftChild();
            } else {
                assert compResult > 0;
                n = n.getRightChild();
            }
        }
        return n;
    }

    public V lookup(K key) {
        Node<K, V> n = lookupNode(key);
        return n == null ? null : n.getValue();
    }

    private void rotateLeft(Node<K, V> n) {
        Node<K, V> r = n.getRightChild();
        replaceNode(n, r);
        n.setRightChild(r.getLeftChild());
        if (r.getLeftChild() != null) {
            //r.getLeftChild().parent = n;
            r.getLeftChild().setParent(n);
        }
        r.setLeftChild(n);
        n.setParent(r);
    }

    private void rotateRight(Node<K, V> n) {
        Node<K, V> l = n.getLeftChild();
        replaceNode(n, l);
        n.setLeftChild(l.getRightChild());
        //n.left = l.right;
        if (l.getRightChild() != null) {
            l.getRightChild().setParent(n);
        }
        l.setRightChild(n);
        //l.right = n;
        //n.parent = l;
        n.setParent(l);      
    }

    private void replaceNode(Node<K, V> oldn, Node<K, V> newn) {
        if (oldn.getParent() == null) {
            root = newn;
        } else {
            if (oldn == oldn.getParent().getLeftChild()) {
                oldn.getParent().setLeftChild(newn);
                //oldn.parent.left = newn;
            } else {
                oldn.getParent().setRightChild(newn);
                //oldn.parent.right = newn;
            }
        }
        if (newn != null) {
            newn.setParent(oldn.getParent());
            //newn.parent = oldn.parent;
        }
    }

    public void insertRB(K key, V value) {
        Node<K, V> insertedNode = new Node<>(key, value, Color.RED, null, null);
        if (root == null) {
            root = insertedNode;
        } else {
            Node<K, V> n = root;
            while (true) {
                int compResult = key.compareTo(n.getKey());
                if (compResult == 0) {
                    //n.value = value;
                    n.setValue(value);
                    return;
                } else if (compResult < 0) {
                    if (n.getLeftChild() == null) {
                        //n.left = insertedNode;
                        n.setLeftChild(insertedNode);
                        break;
                    } else {
                        n = n.getLeftChild();
                    }
                } else {
                    assert compResult > 0;
                    if (n.getRightChild() == null) {
                        //n.right = insertedNode;
                        n.setRightChild(insertedNode);
                        break;
                    } else {
                        //n = n.right;
                        n = n.getRightChild();
                    }
                }
            }
            insertedNode.setParent(n);
        }
        insertCase1(insertedNode);
        verifyProperties();
    }

    private void insertCase1(Node<K, V> n) {
        if (n.getParent() == null) {
            n.color = Color.BLACK;
        } else {
            insertCase2(n);
        }
    }

    private void insertCase2(Node<K, V> n) {
        if (nodeColor(n.getParent()) == Color.BLACK) {
            return; // Tree is still valid
        } else {
            insertCase3(n);
        }
    }

    void insertCase3(Node<K, V> n) {
        if (nodeColor(n.uncle()) == Color.RED) {
            n.getParent().color = Color.BLACK;
            n.uncle().color = Color.BLACK;
            n.grandparent().color = Color.RED;
            insertCase1(n.grandparent());
        } else {
            insertCase4(n);
        }
    }

    void insertCase4(Node<K, V> n) {
        if (n == n.getParent().getRightChild() && n.getParent() == n.grandparent().getLeftChild()) {
            rotateLeft(n.getParent());
            n = n.getLeftChild();
        } else if (n == n.getParent().getLeftChild() && n.getParent() == n.grandparent().getRightChild()) {
            rotateRight(n.getParent());
            n = n.getRightChild();
        }
        insertCase5(n);
    }

    void insertCase5(Node<K, V> n) {
        n.getParent().color = Color.BLACK;
        n.grandparent().color = Color.RED;
        if (n == n.getParent().getLeftChild() && n.getParent() == n.grandparent().getLeftChild()) {
            rotateRight(n.grandparent());
        } else {
            assert n == n.getParent().getRightChild() && n.getParent() == n.grandparent().getRightChild();
            rotateLeft(n.grandparent());
        }
    }

    public void delete(K key) {
        Node<K, V> n = lookupNode(key);
        if (n == null) {
            return;  // Key not found, do nothing
        }
        if (n.getLeftChild() != null && n.getRightChild() != null) {
            // Copy key/value from predecessor and then delete it instead
            Node<K, V> pred = maximumNode(n.getLeftChild());
            n.setKey(pred.getKey());
            //n.key = pred.key;
            //n.value = pred.value;
            n.setValue(pred.getValue());
            n = pred;
        }

        assert n.getLeftChild() == null || n.getRightChild() == null;
        Node<K, V> child = (n.getRightChild() == null) ? n.getLeftChild() : n.getRightChild();
        if (nodeColor(n) == Color.BLACK) {
            n.color = nodeColor(child);
            deleteCase1(n);
        }
        replaceNode(n, child);

        verifyProperties();
    }

    private static <K extends Comparable<? super K>, V> Node<K, V> maximumNode(Node<K, V> n) {
        assert n != null;
        while (n.getRightChild() != null) {
            n = n.getRightChild();
        }
        return n;
    }

    private void deleteCase1(Node<K, V> n) {
        if (n.getParent() == null) {
            return;
        } else {
            deleteCase2(n);
        }
    }

    private void deleteCase2(Node<K, V> n) {
        if (nodeColor(n.sibling()) == Color.RED) {
            //n.parent.color = Color.RED;
            n.getParent().color = Color.RED;
            n.sibling().color = Color.BLACK;
            if (n == n.getParent().getLeftChild()) {
                rotateLeft(n.getParent());
            } else {
                rotateRight(n.getParent());
            }
        }
        deleteCase3(n);
    }

    private void deleteCase3(Node<K, V> n) {
        if (nodeColor(n.getParent()) == Color.BLACK
                && nodeColor(n.sibling()) == Color.BLACK
                && nodeColor(n.sibling().getLeftChild()) == Color.BLACK
                && nodeColor(n.sibling().getRightChild()) == Color.BLACK) {
            n.sibling().color = Color.RED;
            deleteCase1(n.getParent());
        } else {
            deleteCase4(n);
        }
    }

    private void deleteCase4(Node<K, V> n) {
        if (nodeColor(n.getParent()) == Color.RED
                && nodeColor(n.sibling()) == Color.BLACK
                && nodeColor(n.sibling().getLeftChild()) == Color.BLACK
                && nodeColor(n.sibling().getRightChild()) == Color.BLACK) {
            n.sibling().color = Color.RED;
            n.getParent().color = Color.BLACK;
        } else {
            deleteCase5(n);
        }
    }

    private void deleteCase5(Node<K, V> n) {
        if (n == n.getParent().getLeftChild()
                && nodeColor(n.sibling()) == Color.BLACK
                && nodeColor(n.sibling().getLeftChild()) == Color.RED
                && nodeColor(n.sibling().getRightChild()) == Color.BLACK) {
            n.sibling().color = Color.RED;
            n.sibling().getLeftChild().color = Color.BLACK;
            rotateRight(n.sibling());
        } else if (n == n.getParent().getRightChild()
                && nodeColor(n.sibling()) == Color.BLACK
                && nodeColor(n.sibling().getRightChild()) == Color.RED
                && nodeColor(n.sibling().getLeftChild()) == Color.BLACK) {
            n.sibling().color = Color.RED;
            n.sibling().getRightChild().color = Color.BLACK;
            rotateLeft(n.sibling());
        }
        deleteCase6(n);
    }

    private void deleteCase6(Node<K, V> n) {
        n.sibling().color = nodeColor(n.getParent());
        n.getParent().color = Color.BLACK;
        if (n == n.getParent().getLeftChild()) {
            assert nodeColor(n.sibling().getRightChild()) == Color.RED;
            n.sibling().getRightChild().color = Color.BLACK;
            rotateLeft(n.getParent());
        } else {
            assert nodeColor(n.sibling().getLeftChild()) == Color.RED;
            //n.sibling().left.color = Color.BLACK;
            n.sibling().getLeftChild().color = Color.BLACK;
            rotateRight(n.getParent());
        }
    }

    public void print() {
        printHelper(root, 0);
    }

    private static void printHelper(Node<?, ?> n, int indent) {
        if (n == null) {
            System.out.print("<empty tree>");
            return;
        }
        if (n.getRightChild() != null) {
            printHelper(n.getRightChild(), indent + INDENT_STEP);
        }
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        if (n.color == Color.BLACK) {
            System.out.println(n.getKey());
        } else {
            System.out.println("<" + n.getKey() + ">");
        }
        if (n.getLeftChild() != null) {
            printHelper(n.getLeftChild(), indent + INDENT_STEP);
        }
    }
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        RBTree<Integer, Integer> t = new RBTree<Integer, Integer>();
        t.print();

        java.util.Random gen = new java.util.Random();

        for (int i = 0; i < 5; i++) {
            int x = gen.nextInt(10000);
            int y = gen.nextInt(10000);

            t.print();
            System.out.println("Inserting " + x + " -> " + y);
            System.out.println();

            t.insert(x, y);
            assert t.lookup(x).equals(y);
        }
        for (int i = 0; i < 6; i++) {
            int x = gen.nextInt(10000);

            t.print();
            System.out.println("Deleting key " + x);
            System.out.println();

            t.delete(x);
        }
    }
}
