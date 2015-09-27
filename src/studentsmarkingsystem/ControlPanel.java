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
    
    private final JLabel lblKey;
    private final JLabel lblValue;
    private final JTextField txtKey;
    private final JTextField txtValue;
    private final JButton insert;
    private final JButton remove;
    private final JButton search;
    private final JButton exit;
    
    public ControlPanel() {
        super(new GridLayout(2,4));
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
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == insert) {
            
        } else if (e.getSource() == remove) {
            
        } else if (e.getSource() == search) {
            
        }
    }
    
}
