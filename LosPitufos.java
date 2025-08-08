import java.awt.*;
import javax.swing.*;

public class LosPitufos{
    public static void main(String[] args) {
        
        System.out.println("Inicializando mundo GUI...");

        JFrame frame = new JFrame("Você sabia que ...");
        JPanel panel = new JPanel();
        JButton button = new JButton("Papá Pitufo");
        JButton button2 = new JButton("Pitufina");
        JLabel label = new JLabel("          Los pitufos         ");
        

        JTextField textField = new JTextField(10);
        textField.setFont(new Font("Serif", Font.BOLD, 36));
        
        JTextArea textArea = new JTextArea("son pitufos");
        JTextArea textArea2 = new JTextArea("son pitufos");

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formPanel.add(label);
        formPanel.add(textField);

        panel.setLayout(new BorderLayout());

        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);       
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(button, BorderLayout.SOUTH);        
        panel.add(button2);        
        panel.add(textArea, BorderLayout.EAST);
        panel.add(textArea2, BorderLayout.WEST);
        panel.setBackground(Color.pink);    
        
        
        button.setBackground(Color.white);
        button.setForeground(Color.pink);
        button.setPreferredSize(new Dimension(100,50));

        button2.setBackground(Color.white);
        button2.setForeground(Color.pink);
        button2.setPreferredSize(new Dimension(100,50));

        button.addActionListener(e -> {
            ImageIcon imageIcon = new ImageIcon("images/Pitufo.png");
            JLabel picLabel = new JLabel(imageIcon);
            JOptionPane.showMessageDialog(null, picLabel, "LOS PITUFOS", JOptionPane.PLAIN_MESSAGE);
        });
        
        button2.addActionListener(e -> {
            ImageIcon imageIcon = new ImageIcon("images/Pitufina.png"); 
            JLabel picLabel = new JLabel(imageIcon);
            JOptionPane.showMessageDialog(null, picLabel, "LOS PITUFOS", JOptionPane.PLAIN_MESSAGE);
        });
        
        label.setFont(new Font("Serif", Font.BOLD, 36));


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        
    }
}