package studentsmarkingsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
    private final JButton exit;
    private BinaryTree<String, Integer> binaryTree;
    private BinaryTree<String, Integer> binaryTreeNamesIndex;
    private final TreePanel treePanel;

    public ControlPanel(BinaryTree binaryTree, BinaryTree binaryTreeNamesIndex, TreePanel treePanel) {
        super(new GridLayout(2, 4));

        this.binaryTree = binaryTree;
        this.binaryTreeNamesIndex = binaryTreeNamesIndex;
        this.treePanel = treePanel;

        lblKey = new JLabel("Key");
        lblKey.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblKey);

        txtKey = new JTextField();
        add(txtKey);
        txtKey.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtKey.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        lblValue = new JLabel("Value");
        lblValue.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValue);

        txtValue = new JTextField();
        add(txtValue);
        txtValue.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtValue.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        insert = new JButton("Insert");
        insert.addActionListener(this);
        add(insert);

        remove = new JButton("Remove");
        remove.addActionListener(this);
        add(remove);

        search = new JButton("Search");
        search.addActionListener(this);
        add(search);

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
            treePanel.setCurrentNode(null);
            if (!(txtKey.getText().isEmpty())) {
                if (!(txtValue.getText().isEmpty())) {
                    try {
                        Integer.parseInt(txtValue.getText());
                        binaryTree.insert(txtKey.getText(), Integer.parseInt(txtValue.getText()));
                        treePanel.repaint();
                        clear();
                    } catch (NumberFormatException numberFormatException) {
                        //Not an integer
                        txtValue.setText("NOT A NUMBER!");
                    }
                }else{
                    txtValue.setText("Enter a value!!");
                }
            }
        } else if (e.getSource() == remove) {
            treePanel.setCurrentNode(null);
            if (!(txtKey.getText().isEmpty())) {
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
        } 
    }
}
