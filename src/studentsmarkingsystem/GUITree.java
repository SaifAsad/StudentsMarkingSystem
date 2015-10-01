package studentsmarkingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUITree extends JPanel {

    private static final int DEFAULT_WIDTH = 1800;
    private static final int DEFAULT_HEIGHT = 600;

    public GUITree() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        BinaryTree<String, Integer> binaryTree = buildTree(8);
        TreePanel<String, Integer> treePanel = new TreePanel<>(binaryTree);
        add(treePanel, BorderLayout.CENTER);
        ControlPanel controlPanel = new ControlPanel(binaryTree, treePanel);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUITree assignment2 = new GUITree();
        frame.setContentPane(assignment2);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private int index = 0;
    private String c = "a";
    private int asc;
    
    //System.out.println(String.valueOf(Character.toChars(asc)));

    private void buildChildren(int start, int end, BinaryTree<String, Integer> binaryTree) {
        asc = c.charAt(0);
        System.out.println("index = " + index);
        asc += index++;
        if (start < end + 1) {
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

}
