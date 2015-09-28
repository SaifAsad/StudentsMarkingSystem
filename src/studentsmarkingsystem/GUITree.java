
package studentsmarkingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Saif Asad
 */
public class GUITree extends JPanel {
    
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    
    public GUITree() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert("N", 1);
        binarySearchTree.insert("Z", 2);
        binarySearchTree.insert("L", 3);
        binarySearchTree.insert("A", 4);
        binarySearchTree.insert("D", 5);
        binarySearchTree.insert("R", 6);
        binarySearchTree.insert("U", 7);
        binarySearchTree.insert("G", 8);
        binarySearchTree.insert("B", 9);
        binarySearchTree.insert("Y", 10);
        
        TreePanel treePanel = new TreePanel(binarySearchTree);
        add(treePanel, BorderLayout.CENTER);
        
        ControlPanel controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment 2: Students Marking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUITree assignment2 = new GUITree();
        frame.setContentPane(assignment2);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
