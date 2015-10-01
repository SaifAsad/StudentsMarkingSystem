package studentsmarkingsystem;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import studentsmarkingsystem.BinaryTree.Node;

public class ControlPanel extends JPanel implements ActionListener {

    private final JLabel lblKey;
    private final JLabel lblValue;
    private final JTextField txtKey;
    private final JTextField txtValue;
    private final JButton insert;
    private final JButton remove;
    private final JButton search;
    private final JButton leftRotate;
    private final JButton rightRotate;
    private final JButton exit;
    private final BinaryTree<String, Integer> binaryTree;
    private final TreePanel treePanel;

    public ControlPanel(BinaryTree binaryTree, TreePanel treePanel) {
        super(new GridLayout(3, 4));

        this.binaryTree = binaryTree;
        this.treePanel = treePanel;

        lblKey = new JLabel("Key");
        lblKey.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblKey);

        txtKey = new JTextField();
        add(txtKey);

        lblValue = new JLabel("Value");
        lblValue.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValue);

        txtValue = new JTextField();
        add(txtValue);

        insert = new JButton("Insert");
        insert.addActionListener(this);
        add(insert);

        remove = new JButton("Remove");
        remove.addActionListener(this);
        add(remove);

        search = new JButton("Search");
        search.addActionListener(this);
        add(search);

        add(Box.createRigidArea(new Dimension(0, 0)));

        leftRotate = new JButton("Left Rotate");
        leftRotate.addActionListener(this);
        add(leftRotate);

        rightRotate = new JButton("Right Rotate");
        rightRotate.addActionListener(this);
        add(rightRotate);

        exit = new JButton("Exit");
        exit.addActionListener(this);
        add(exit);
    }
    
    public void clear() {
        txtKey.setText("");
        txtValue.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == insert) {
            if(!(txtKey.getText().isEmpty() && txtKey.getText().isEmpty())){
                binaryTree.insert(txtKey.getText(), Integer.parseInt(txtValue.getText()));
                treePanel.repaint();
                clear();
            }
        } else if (e.getSource() == remove) {
            if(!(txtKey.getText().isEmpty())){
                Node<String, Integer> node = binaryTree.search(txtKey.getText());
                node = binaryTree.delete(node);
                treePanel.repaint();
                txtKey.setText(node.getKey());
                txtValue.setText(node.getValue().toString());
            }
        } else if (e.getSource() == search) {
            if (!txtKey.getText().isEmpty()) {
                Node<String, Integer> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    txtValue.setText(node.getValue().toString());
                    treePanel.setCurrentNode(node);
                    treePanel.repaint();
                }
            }
        } else if (e.getSource() == leftRotate) {
            if (!txtKey.getText().isEmpty()) {
                Node<String, Integer> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    binaryTree.leftRotate(node);
                    treePanel.repaint();
                }
            }
        } else if (e.getSource() == rightRotate) {
            if (!txtKey.getText().isEmpty()) {
                Node<String, Integer> node = binaryTree.search(txtKey.getText());
                clear();
                if (node != null) {
                    binaryTree.rightRotate(node);
                    treePanel.repaint();
                }
            }
        }
    }

}
