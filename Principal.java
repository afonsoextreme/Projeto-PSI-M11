package Principal;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Button;


public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Clip clip;
    private JTextArea textArea;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnPlay = new JButton("Play");
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (clip != null) {
                        clip.close();
                    }
                    AudioInputStream audioInputStream = AudioSystem
                            .getAudioInputStream(new File("C:\\Users\\25872\\Documents\\Musica\\Frolic.wav"));

                    clip = AudioSystem.getClip();

                    clip.open(audioInputStream);

                    clip.start();

                } catch (Exception exc) {
                    System.out.println("Erro" + exc.getMessage());
                }
            }
        });
        btnPlay.setBounds(10, 32, 89, 23);
        contentPane.add(btnPlay);

        JButton btnPausa = new JButton("Pause");
        btnPausa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                } else if (clip != null) {
                    clip.start();
                }
            }
        });
        btnPausa.setBounds(10, 66, 89, 23);
        contentPane.add(btnPausa);

        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clip != null) {
                    clip.stop();
                    clip.close();
                }
            }
        });
        btnStop.setBounds(10, 100, 89, 23);
        contentPane.add(btnStop);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(200, 10, 291, 200);
        contentPane.add(scrollPane);
    
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        
        Canvas canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                
                Graphics2D g2d = (Graphics2D) g;

                
                g2d.setColor(Color.RED);

                
                g2d.fillRect(50, 50, 100, 50);

                
                g2d.setColor(Color.BLUE);

                
                g2d.fillOval(200, 100, 80, 120);
            }
        };
        canvas.setBackground(new Color(0, 0, 0));
        canvas.setBounds(10, 248, 303, 217);
        contentPane.add(canvas);

        // Preenchendo JTextArea com números de 1 a 30 e aplicando operações
        fillTextAreaWithNumbersAndOperations(1, 30);
    }

    private void fillTextAreaWithNumbersAndOperations(int start, int end) {
        List<Integer> numbersList = IntStream.rangeClosed(start, end)
                                            .boxed()
                                            .collect(Collectors.toList());
        
        // Operações
        int max = numbersList.stream().max(Integer::compareTo).orElse(0);
        int min = numbersList.stream().min(Integer::compareTo).orElse(0);
        List<Integer> skipList = numbersList.stream().skip(5).collect(Collectors.toList());
        List<Integer> limitList = numbersList.stream().limit(10).collect(Collectors.toList());
        List<Integer> distinctList = numbersList.stream().distinct().collect(Collectors.toList());
        List<Integer> mappedList = numbersList.stream().map(n -> n * 2).collect(Collectors.toList());
        numbersList.forEach(System.out::println);
        long count = numbersList.stream().count();
        List<Integer> sortedList = numbersList.stream().sorted().collect(Collectors.toList());
        int sum = numbersList.stream().reduce(0, Integer::sum);
        
        // Exibindo os resultados na JTextArea
        textArea.append("Max: " + max + "\n");
        textArea.append("Min: " + min + "\n");
        textArea.append("Skip (5 primeiros elementos): " + skipList + "\n");
        textArea.append("Limit (10 primeiros elementos): " + limitList + "\n");
        textArea.append("Distinct: " + distinctList + "\n");
        textArea.append("Mapped (n * 2): " + mappedList + "\n");
        textArea.append("Count: " + count + "\n");
        textArea.append("Sorted: " + sortedList + "\n");
        textArea.append("Sum: " + sum + "\n");
    }
}
