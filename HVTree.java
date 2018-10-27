import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Line2D.*;

public class HVTree extends JPanel{
    private double factor = 0.0;
    private int order = 0;
    private JTextField orderField;
    private JTextField factorField;
    private DrawingPanel drawingPanel = new DrawingPanel();
    private JLabel errorLabel1;
    private JLabel errorLabel2;

    public static void main(String [] args){
        JFrame frame = new JFrame();
        frame.getContentPane().add(new HVTree());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("HVTree");

    }

    public HVTree(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,180));
        orderField = new JTextField(2);
        factorField = new JTextField(2);
        JButton drawButton = new JButton("Draw");
        ButtonListener listener = new ButtonListener();
        JLabel orderLabel = new JLabel("Order");
        JLabel factorLabel = new JLabel("Factor");
        errorLabel1 = new JLabel("");
        errorLabel2 = new JLabel("");

        orderLabel.setForeground(Color.black);
        factorLabel.setForeground(Color.black);
        drawButton.addActionListener(listener);
        panel.add(orderLabel);
        panel.add(orderField);
        panel.add(factorLabel);
        panel.add(factorField);
        panel.add(drawButton);
        panel.add(errorLabel1);
        panel.add(errorLabel2);
        add(panel);
        add(drawingPanel);
    }
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
          if(orderField.getText().equals("")){
                errorLabel1.setText("Order input must be non-empty");
                errorLabel2.setText("and a valid integer");
            }else if(factorField.getText().equals("")){
              errorLabel1.setText("Factor input must be non-empty");
              errorLabel2.setText("and a valid floating point number");
            }else{
              errorLabel1.setText("");
              errorLabel2.setText("");
                order = Integer.parseInt(orderField.getText());
                factor = java.lang.Float.parseFloat(factorField.getText());
            }
            drawingPanel.repaint();
        }
    }
    private class DrawingPanel extends JPanel{
        private Graphics2D g2;
        public DrawingPanel(){
            setPreferredSize(new Dimension(400,400));
            setBackground(Color.lightGray);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            drawHorizontal(order,200.0,200.0,100.0);
        }
        public void drawHorizontal(int order,double x, double y, double length){
          if(order < 1 || length < 1){
            return;
          }
            double x1 = (x-(length/2.0));
            double x2 = (x+(length/2.0));
            Shape l = new java.awt.geom.Line2D.Double(x1, y, x2, y);
            g2.draw(l);
            length = factor * length;
            drawVertical(order-1,x1,y,length);
            drawVertical(order-1,x2,y,length);
    }
        public void drawVertical(int order,double x,double y, double length){
          if(order < 1 || length < 1){
            return;
          }
          double y1 = (y-(length/2.0));
          double y2 = (y+(length/2.0));
          Shape l = new java.awt.geom.Line2D.Double(x, y1, x, y2);
          g2.draw(l);
          length = factor * length;
          drawHorizontal(order-1,x,y1,length);
          drawHorizontal(order-1,x,y2,length);
        }
    }
}
