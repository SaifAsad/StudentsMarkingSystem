package studentsmarkingsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Saif Asad
 */
public class ControlPanel extends JPanel implements ActionListener {

    private final JLabel lblName;
    private final JLabel lblMark;
    private final JTextField txtName;
    private final JTextField txtMark;
    private final JButton insert;
    private final JButton remove;
    private final JButton search;
    private final JButton exit;

    private String name;
    private int mark;

    public ControlPanel() {
        super(new GridLayout(2, 4));

        lblName = new JLabel("Name");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblName);

        txtName = new JTextField();
        add(txtName);

        lblMark = new JLabel("Mark");
        lblMark.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblMark);

        txtMark = new JTextField();
        add(txtMark);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        //get the value of the node from the textFields
        name = txtName.getText();
        mark = Integer.parseInt(txtMark.getText());
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == insert) {
            
        } else if (e.getSource() == remove) {

            //remove a node from the tree
            //deleteNode();
            //redraw
        } else if (e.getSource() == search) {

        }
    }

}
