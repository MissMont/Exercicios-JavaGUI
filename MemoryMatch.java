import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MemoryMatch {

    private static JButton[] botoes;
    private static Icon iconeFundo;
    private static final int NUM_JANELAS_PARABENS = 15;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo da Memória Impossível!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Somente 0,000001% da população consegue reselver esse jogo da memória!!!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        titulo.setBackground(new Color(0,128,128));

        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0,128,128));

        Icon carta1 = redimensionarImagem("images/Carta1.jpg", 150, 150);
        Icon carta2 = redimensionarImagem("images/Carta2.jpg", 150, 150);
        Icon carta3 = redimensionarImagem("images/Carta3.jpg", 150, 150);
        Icon carta4 = redimensionarImagem("images/Carta4.jpg", 150, 150);

        Icon[] icones = {
            carta1, carta1,
            carta2, carta2,
            carta3, carta3,
            carta4, carta4
        };

        iconeFundo = redimensionarImagem("images/Capa.jpg", 150, 150);

        embaralhar(icones);

        botoes = new JButton[icones.length];
        final JButton[] primeiroSelecionado = {null};
        final JButton[] segundoSelecionado = {null};

        for (int i = 0; i < icones.length; i++) {
            JButton botao = new JButton();
            botao.setPreferredSize(new Dimension(50, 50));
            botao.setIcon(iconeFundo);
            botao.putClientProperty("icone", icones[i]);

            botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (botao == primeiroSelecionado[0] || botao == segundoSelecionado[0] || !botao.isEnabled()) {
                        return;
                    }

                    botao.setIcon((Icon) botao.getClientProperty("icone"));

                    if (primeiroSelecionado[0] == null) {
                        primeiroSelecionado[0] = botao;
                    } else if (segundoSelecionado[0] == null) {
                        segundoSelecionado[0] = botao;

                        Timer timer = new Timer(1000, ev -> {
                            Icon ic1 = (Icon) primeiroSelecionado[0].getClientProperty("icone");
                            Icon ic2 = (Icon) segundoSelecionado[0].getClientProperty("icone");

                            if (ic1.equals(ic2)) {
                                primeiroSelecionado[0].setEnabled(false);
                                segundoSelecionado[0].setEnabled(false);

                                if (checarFimDeJogo()) {
                                    mostrarMultiplasJanelasParabens();
                                }

                            } else {
                                JFrame frameErro = new JFrame("Errou!");
                                frameErro.setSize(300, 250);
                                frameErro.setLayout(new BorderLayout());
                                frameErro.setLocationRelativeTo(null);

                                Icon imagemErro = redimensionarImagem("errou.jpg", 280, 150); 
                                JLabel labelImagemErro = new JLabel(imagemErro);
                                labelImagemErro.setHorizontalAlignment(SwingConstants.CENTER);

                                JLabel labelTextoErro = new JLabel("ERROU KKKKKKKKKKKKK.", SwingConstants.CENTER);
                                labelTextoErro.setFont(new Font("Arial", Font.BOLD, 18));

                                frameErro.add(labelTextoErro, BorderLayout.NORTH);
                                frameErro.add(labelImagemErro, BorderLayout.CENTER);

                                frameErro.setVisible(true);

                                new Thread(() -> tocarSomWav("sounds/cat-laugh-meme-1.wav")).start();
                                
                                new Timer(5000, evt -> {
                                    frameErro.dispose();
                                }) {{
                                    setRepeats(false);
                                    start();
                                }};

                                primeiroSelecionado[0].setIcon(iconeFundo);
                                segundoSelecionado[0].setIcon(iconeFundo);
                            }

                            primeiroSelecionado[0] = null;
                            segundoSelecionado[0] = null;
                        });

                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            });

            botoes[i] = botao;
            panel.add(botao);
        }

        frame.add(titulo, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static boolean checarFimDeJogo() {
        for (JButton botao : botoes) {
            if (botao.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private static void mostrarMultiplasJanelasParabens() {
        SwingUtilities.invokeLater(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Random rand = new Random();

            for (int i = 0; i < NUM_JANELAS_PARABENS; i++) {
                JFrame frameParabens = new JFrame("Parabéns!");
                frameParabens.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameParabens.setSize(400, 400);
                frameParabens.setLayout(new BorderLayout());

                Icon imagemParabens = redimensionarImagem("nerd.jpg", 350, 300);
                JLabel labelImagem = new JLabel(imagemParabens);
                labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel labelTexto = new JLabel("Parabéns você faz parte de 0,000001% da população!", SwingConstants.CENTER);
                labelTexto.setFont(new Font("Arial", Font.BOLD, 15));

                frameParabens.add(labelTexto, BorderLayout.NORTH);
                frameParabens.add(labelImagem, BorderLayout.CENTER);

                int x = rand.nextInt(Math.max(screenSize.width - frameParabens.getWidth(), 1));
                int y = rand.nextInt(Math.max(screenSize.height - frameParabens.getHeight(), 1));
                frameParabens.setLocation(x, y);

                frameParabens.setVisible(true);
            }

            new Thread(() -> tocarSomWav("sounds/jojo.wav")).start();
        });
    }

    private static void tocarSomWav(String caminhoWav) {
        try {
            File arquivo = new File(caminhoWav);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao tocar som WAV: " + e.getMessage());
        }
    }

    public static void embaralhar(Icon[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Icon temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static Icon redimensionarImagem(String caminho, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminho);
        Image imagem = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagem);
    }
}

