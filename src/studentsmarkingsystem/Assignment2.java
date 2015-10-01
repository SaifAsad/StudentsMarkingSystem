package studentsmarkingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
;


public class Assignment2 extends JPanel {
    
    private static final int DEFAULT_WIDTH = 1800;
    private static final int DEFAULT_HEIGHT = 600;
    
    public Assignment2() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        BinaryTree<Integer> binaryTree = buildTree(8);
        TreePanel<Integer> treePanel = new TreePanel<>(binaryTree);
        add(treePanel, BorderLayout.CENTER);
        ControlPanel controlPanel = new ControlPanel(binaryTree, treePanel);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Assignment2 assignment2 = new Assignment2();
        frame.setContentPane(assignment2);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void buildChildren(int start, int end, BinaryTree<Integer> binaryTree) {
        if (start < end + 1) {
            int mid = start + (end - start) / 2; 
            binaryTree.insert(mid);
            buildChildren(start, mid - 1, binaryTree);
            buildChildren(mid + 1, end, binaryTree);
        }
    }

    private  BinaryTree<Integer> buildTree(int n) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        buildChildren(1, n - 1, binaryTree);
        return binaryTree;
    }
    
}
