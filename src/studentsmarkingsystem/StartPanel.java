package studentsmarkingsystem;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Saif Asad
 */
public class StartPanel extends JPanel implements ActionListener {

    public static String treeType;
    private final JButton binTree;
    private final JButton rbTree;

    public StartPanel(Panel p) {
        binTree = new JButton("Binary Tree");
        binTree.addActionListener(this);
        add(binTree);

        rbTree = new JButton("Red Black Tree");
        rbTree.addActionListener(this);
        add(rbTree);
        
        treeType = "";    
        setBackground(java.awt.Color.RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == binTree) {
            treeType = "binaryTree";
            this.setVisible(false);
            
        } else if (e.getSource() == rbTree) {
            treeType = "redBlackTree";
            this.setVisible(false);
        }
    }
}
