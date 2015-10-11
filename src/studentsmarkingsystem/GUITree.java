package studentsmarkingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUITree extends JPanel {

    private static final int DEFAULT_WIDTH = 1800;
    private static final int DEFAULT_HEIGHT = 600;
    private int returnValue = 0;
    private BinaryTree<String, Integer> binaryTree;
    private BinaryTree<String, Integer> binaryTreeNamesIndex;
    
    public GUITree() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        final ImageIcon icon = new ImageIcon(getClass().getResource("/question.jpg"));
        String[] buttons = {"Binary Tree", "Red Black Tree"};
        returnValue = JOptionPane.showOptionDialog(null, "Select Tree Type", "Tree Type",
                JOptionPane.OK_CANCEL_OPTION, 0, icon, buttons, buttons[0]);
        
        if (returnValue == 1) {
            binaryTree = buildRBTree(26);
            binaryTreeNamesIndex = new RedBlackTree<>();
            //binaryTree = new RedBlackTree<>();
        } else {
            binaryTree = buildTree(8);
            binaryTreeNamesIndex = new BinaryTree<>();
            //binaryTree = new BinaryTree<>();
        }
        TreePanel<String, Integer> treePanel = new TreePanel<>(binaryTree);
        add(treePanel, BorderLayout.CENTER);
        ControlPanel controlPanel = new ControlPanel(binaryTree, binaryTreeNamesIndex, treePanel);
        add(controlPanel, BorderLayout.SOUTH);
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
    private static int index = 0;
    private final String c = "a";
    private int asc;

    //BinaryTree test
    private void buildChildren(int start, int end,
            BinaryTree<String, Integer> binaryTree) {
        asc = c.charAt(0);
        asc += index;
        index++;
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

    //RedBlackTree test
    private void buildChildren(int start, int end,
            RedBlackTree<String, Integer> binaryTree) {
        asc = c.charAt(0);
        asc += index;
        index++;
        if (start < end + 1) {
            int mid = start + (end - start) / 2;
            binaryTree.insert(String.valueOf(Character.toChars(asc)), mid);
            buildChildren(start, mid - 1, binaryTree);
            buildChildren(mid + 1, end, binaryTree);
        }
    }

    private RedBlackTree<String, Integer> buildRBTree(int n) {
        RedBlackTree<String, Integer> binaryTree = new RedBlackTree<>();
        for (int i = 0; i < n; i++) {
            asc = c.charAt(0);
            asc += i;
            binaryTree.insert(String.valueOf(Character.toChars(asc)), i);

        }
        return binaryTree;
    }
}
