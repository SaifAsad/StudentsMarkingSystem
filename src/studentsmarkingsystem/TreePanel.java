package studentsmarkingsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import studentsmarkingsystem.BinarySearchTree.Node;


/**
 *
 * @author Saif Asad
 */
public class TreePanel extends JPanel {
    //--------------------------------------------------------------------------
    public enum DrawType {

        LINKS, NODES, ITEMS
    }
    //--------------------------------------------------------------------------
    private final BinarySearchTree binarySearchTree;
    private final Map<Node, Point> nodePostions;
    private int nodeDiameter;

    public TreePanel(BinarySearchTree binarySearchTree) {
        this.binarySearchTree = binarySearchTree;
        nodePostions = new HashMap<>();
    }
    //--------------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawTree(g);
    }
    //--------------------------------------------------------------------------
    private void drawTree(Graphics g) {
        nodeDiameter = (int) (Math.max(getWidth(), getHeight())
                / Math.pow(2, binarySearchTree.getHeight() - 1));
        nodePostions.put(binarySearchTree.getRoot(),
                new Point(getWidth() / 2 - nodeDiameter / 2, nodeDiameter / 2));
        drawTree(g, binarySearchTree.getRoot(), DrawType.LINKS);
        drawTree(g, binarySearchTree.getRoot(), DrawType.NODES);
        drawTree(g, binarySearchTree.getRoot(), DrawType.ITEMS);
    }
    //--------------------------------------------------------------------------
    public void drawTree(Graphics g, Node node, DrawType drawType) {
        if (node != null) {
            int nodeXPos;
            int nodeYPos;
            int parentNodeXPos;
            int parentNodeYPos;
            if (node.getParent() == null) {
                nodeXPos = nodePostions.get(node).x;
                nodeYPos = nodePostions.get(node).y;
            } else {
                parentNodeXPos = nodePostions.get(node.getParent()).x;
                parentNodeYPos = nodePostions.get(node.getParent()).y;
                nodeYPos = parentNodeYPos + 2 * nodeDiameter;
                if (node.isLeftChild()) {
                    nodeXPos = parentNodeXPos - 2 * nodeDiameter;
                } else {
                    nodeXPos = parentNodeXPos + 2 * nodeDiameter;
                }
                nodePostions.put(node, new Point(nodeXPos, nodeYPos));
                if (drawType == DrawType.LINKS) {
                    g.setColor(Color.BLACK);
                    g.drawLine(nodeXPos + nodeDiameter / 2,
                            nodeYPos + nodeDiameter / 2,
                            parentNodeXPos + nodeDiameter / 2,
                            parentNodeYPos + nodeDiameter / 2);
                }
            }
            if (drawType == DrawType.NODES) {
                g.setColor(Color.WHITE);
                g.fillOval(nodeXPos, nodeYPos, nodeDiameter, nodeDiameter);
                g.setColor(Color.BLACK);
                g.drawOval(nodeXPos, nodeYPos, nodeDiameter, nodeDiameter);
            } else if (drawType == DrawType.ITEMS) {
                g.setColor(Color.BLACK);
                Font nodeFont = new Font("SansSerif", Font.BOLD, 12);
                g.setFont(nodeFont);
                FontMetrics nodeFontMetrics = g.getFontMetrics(nodeFont);
                int stringWidth = nodeFontMetrics.stringWidth(node.toString());
                int stringHeight = nodeFontMetrics.getHeight();
                g.drawString(node.toString(), nodeXPos + nodeDiameter / 2 - stringWidth / 2,
                        nodeYPos + nodeDiameter / 2 + stringHeight / 3);
            }
            drawTree(g, node.getLeftChild(), drawType);
            drawTree(g, node.getRightChild(), drawType);
        }
    }
}
