package studentsmarkingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Saif Asad
 */
public class GUITree extends JPanel implements ActionListener {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private final JLabel lblName;
    private final JLabel lblMark;
    private final JTextField txtName;
    private final JTextField txtMark;
    private final JButton insert;
    private final JButton remove;
    private final JButton search;
    private final JButton exit;

    private TreePanel treePanel;
    BinarySearchTree binarySearchTree;

    //private final JLabel lblChoice;
    //private final JButton binarySearchTree;
    //private final JButton splayTree;
    private String name;
    private int mark;

    public GUITree() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        /*
         JPanel startPanel = new JPanel();
         lblChoice = new JLabel("Please choose the type of Tree to be used: ");
         lblChoice.setHorizontalAlignment(SwingConstants.CENTER);
         startPanel.add(lblChoice);

         binarySearchTree = new JButton("BinarySearchTree");
         binarySearchTree.addActionListener(this);
         startPanel.add(binarySearchTree);

         splayTree = new JButton("SplayTree");
         splayTree.addActionListener(this);
         startPanel.add(splayTree);
        
         add(startPanel, BorderLayout.CENTER);
         */
        binarySearchTree = new BinarySearchTree();
        //SplayTree splayTree  = new SplayTree();
        //BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert("New", 1);
        
        binarySearchTree.insert("Zealand", 2);
        binarySearchTree.insert("Light", 3);
        binarySearchTree.insert("Addidas", 4);
        binarySearchTree.insert("Django", 5);
        binarySearchTree.insert("Route", 6);
        binarySearchTree.insert("UnderArmor", 7);
        binarySearchTree.insert("Google", 8);
        binarySearchTree.insert("BigFoot", 9);
        binarySearchTree.insert("Yesterday", 10);

        treePanel = new TreePanel(binarySearchTree);
        add(treePanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(2, 4));

        //ControlPanel controlPanel = new ControlPanel();
        lblName = new JLabel("Name");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(lblName);

        txtName = new JTextField();
        controlPanel.add(txtName);

        lblMark = new JLabel("Mark");
        lblMark.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(lblMark);

        txtMark = new JTextField();
        controlPanel.add(txtMark);

        insert = new JButton("Insert");
        insert.addActionListener(this);
        controlPanel.add(insert);

        remove = new JButton("Remove");
        remove.addActionListener(this);
        controlPanel.add(remove);

        search = new JButton("Search");
        search.addActionListener(this);
        controlPanel.add(search);

        exit = new JButton("Exit");
        exit.addActionListener(this);
        controlPanel.add(exit);

        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get the value of the node from the textFields
        /*name = txtName.getText();
         if(txtMark.getText() != null){
         mark = Integer.parseInt(txtMark.getText());
         }*/
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == insert) {
            if (txtName.getText() != null) {
                name = txtName.getText();
            }
            if (txtMark.getText() != null) {
                mark = Integer.parseInt(txtMark.getText());
                binarySearchTree.insert(name, mark);
                clear();
                repaint();
            }
        } else if (e.getSource() == remove) {
            System.out.println("remove is detected");
            //check if the tree contain's a node with the given name
            if (binarySearchTree.contains(txtName.getText())) {
                binarySearchTree.delete(binarySearchTree.get(txtName.getText()));
                clear();
                repaint();
            }
        } else if (e.getSource() == search) {
            clear();
            System.out.println("search is detected");
            //look up the node
            
            //display the node if found
        }
    }
    
    public void clear() {
        txtName.setText("");
        txtMark.setText("");
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
