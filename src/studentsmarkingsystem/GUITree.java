
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
        
        BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert("N");
        binarySearchTree.insert("Z");
        binarySearchTree.insert("L");
        binarySearchTree.insert("A");
        binarySearchTree.insert("D");
        binarySearchTree.insert("R");
        binarySearchTree.insert("U");
        binarySearchTree.insert("G");
        binarySearchTree.insert("B");
        binarySearchTree.insert("Y");
        
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
