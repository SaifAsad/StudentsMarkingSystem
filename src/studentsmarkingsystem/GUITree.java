package studentsmarkingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUITree extends JPanel {

    private static final int DEFAULT_WIDTH = 1800;
    private static final int DEFAULT_HEIGHT = 600;

    public GUITree() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        JOptionPane start = new JOptionPane("Pl");
        //StartPanel startPanel = new StartPanel(frame);
        //add(startPanel);
        //if (startPanel.treeType.equalsIgnoreCase("binaryTree")) {
           // System.out.println("BinaryTree");
            //remove(startPanel);
        //} else if(startPanel.treeType.equalsIgnoreCase("rbTree")) {
          //  remove(startPanel);
            //System.out.println("RBBinaryTree");
            
            //RBTree<String, Integer> binaryTree = buildRBTree(8);
            
            //RedBlackTree<String, Integer> binaryTree = buildRBTree(8);
            RedBlackTree<String, Integer> binaryTree = new RedBlackTree<>();
            TreePanel<String, Integer> treePanel = new TreePanel<>(binaryTree);
            add(treePanel, BorderLayout.CENTER);
            ControlPanel controlPanel = new ControlPanel(binaryTree, treePanel);
            add(controlPanel, BorderLayout.SOUTH);
            
        //}
        /*
         //BinaryTree<String, Integer> binaryTree = buildTree(8);
         RBTree<String, Integer> binaryTree = buildRBTree(8);
         TreePanel<String, Integer> treePanel = new TreePanel<>(binaryTree);
         add(treePanel, BorderLayout.CENTER);
         ControlPanel controlPanel = new ControlPanel(binaryTree, treePanel);
         add(controlPanel, BorderLayout.SOUTH);*/
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment 2 : Students Marking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUITree assignment2 = new GUITree();
        frame.setContentPane(assignment2);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //--------------------------------------------------------------------------
    //TESTING
    //those variables are for testing
    private static int index = 10;
    private final String c = "a";
    private int asc;

    //BinaryTree test
    private void buildChildren(int start, int end,
            BinaryTree<String, Integer> binaryTree) {
        asc = c.charAt(0);
        asc += index;
        index++;
        if (start < end + 1) {
            System.out.println("index = " + index);
            int mid = start + (end - start) / 2;
            binaryTree.insert(String.valueOf(Character.toChars(asc)), mid);
            buildChildren(start, mid - 1, binaryTree);
            buildChildren(mid + 1, end, binaryTree);
        }
    }

    private BinaryTree<String, Integer> buildTree(int n) {
        BinaryTree<String, Integer> binaryTree = new BinaryTree<>();
        buildChildren(1, n - 1, binaryTree);
        return binaryTree;
    }

    //RedBlackTree test
    private void buildChildren(int start, int end,
            RedBlackTree<String, Integer> binaryTree) {
        asc = c.charAt(0);
        asc += index;
        index++;
        if (start < end + 1) {
            System.out.println("index = " + index);
            int mid = start + (end - start) / 2;
            binaryTree.insert(String.valueOf(Character.toChars(asc)), mid);
            buildChildren(start, mid - 1, binaryTree);
            buildChildren(mid + 1, end, binaryTree);
        }
    }

    private RedBlackTree<String, Integer> buildRBTree(int n) {
        RedBlackTree<String, Integer> binaryTree = new RedBlackTree<>();
        buildChildren(1, n - 1, binaryTree);
        return binaryTree;
    }

}
