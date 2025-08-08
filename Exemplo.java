import java.awt.*;
import javax.swing.*;



public class Exemplo {
    public static void main(String[] args) {

        //componente Jframe
        JFrame miJFrame = new JFrame("Exemplo - Java Swing");
        miJFrame.setSize(500,300);
        //componentes Jpanel
        JPanel miJPanel = new JPanel();
        miJPanel.setSize(300,500);
        //usamos para centralizar os componentes de Jpanel
        miJPanel.setLayout(new GridLayout());
        //componete JTextField
        JLabel miJLabel = new JLabel();
        miJLabel.setText("Diga-me sua opnião sobre Java Swing: ");
        //componente JTextArea
        JTextArea miJTextArea = new JTextArea(5,20);
        //conecta os componentes JLabel e JtextField em JPanel
        miJPanel.add(miJLabel); 
        miJPanel.add(miJTextArea);
        //conectar Jpanel e Jframe
        miJFrame.add(miJPanel);
        //tornar visivel Jframe
        miJFrame.setVisible(true);

        miJFrame.setLocationRelativeTo(null);
    }   
}
