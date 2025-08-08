import java.awt.*;
import javax.swing.*;
 
public class App {
 
    public static void main(String[] args) {
        System.out.println("Olá Mundo GUI");
 
        // Define o título da minha janela
        JFrame frame = new JFrame("Janela");
        //             Altura  e Largura
        frame.setSize(400, 350);
        // Quando a janela for fechada o programa termina com esse EXIT_ON_CLOSE
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        // Defino o painel
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(new BorderLayout());
 
        // Defino o botão o que vou passar de parametro
        JButton button = new JButton("Clique aqui!");
        button.setForeground(Color.BLACK);
        button.setBackground(Color.lightGray);
        button.setPreferredSize(new Dimension (100, 30 ));
 
        // Segundo botão
        JButton button2 = new JButton("Clique aqui para sair");
        button2.setForeground(Color.BLACK);
        button2.setBackground(Color.lightGray);
        button2.setPreferredSize(new Dimension (100, 30 ));
 
 
        // Campo para que possa preencher
        JTextField textField = new JTextField(10);
        textField.setFont(new Font("Serif", Font.ITALIC, 28));
 
        // Adiciona um Listener no clique do botão
        button2.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, textField.getText(), "Titulo", 1);
        });
 
        // Mostra texto, instruções ou rótulos (como "Nome:", "Senha:", "Resultado:")
        JLabel label = new JLabel("Digite o seu nome: ");
        label.setFont(new Font("Serif", Font.ITALIC, 28));
 
 
        JTextArea textArea = new JTextArea("Hoje esta sendo um dia chave!");
        JTextArea textArea1 = new JTextArea("Hoje esta frio!");
 
       
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formPanel.add(label);
        formPanel.add(textField);
 
        // Adiciono o botão no painel (panel)
        panel.add(formPanel, BorderLayout.CENTER);
        //panel.add(button, BorderLayout.NORTH);
        panel.add(button2, BorderLayout.SOUTH);
        panel.add(textArea, BorderLayout.EAST);
        panel.add(textArea1, BorderLayout.WEST);
 
        // Adiciono o painel na janela (frame)
        frame.add(panel);
        // Faço minha janela (frame) abrir no centro da tela
        frame.setLocationRelativeTo(null);
        // Deixo a janela (frame) visivel
        frame.setVisible(true);
 
        // Janela de aviso (Splash)
        //JOptionPane.showMessageDialog(null, "Oi Linda, rs...", "Titulo", 1);
 
    }
}