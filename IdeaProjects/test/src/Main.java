import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sound.sampled.*;

public class Main {
    private static AtomicInteger counter = new AtomicInteger(0);
    private static JLabel counterLabel;

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        JFrame frame = basic(frame1);

        JButton pray_Mohamed = pray_on_mohamed(frame);

        JButton state = getJButton(frame);

        counterLabel = count();

        frame.add(state);
        frame.add(pray_Mohamed);

        JPanel panel = Footer(frame);

        JButton Azan = getpage(frame);

        frame.setVisible(true);


    }


    private static JPanel Header(JFrame frame) {
        JPanel panel = new JPanel();

        panel.setBackground(new Color(50, 50, 50));

        panel.setBounds(0, 0, 800, 50);
        JLabel name = new JLabel("Reminder", SwingConstants.CENTER);
        name.setFont(new Font("mv Boli", Font.ITALIC, 25));
        name.setForeground(Color.WHITE);
        panel.add(name);
        frame.add(panel);

        return panel;
    }

    private static JPanel Footer(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 515, 800, 50);
        JLabel name = new JLabel("© 2025 Ahmed Hussein", SwingConstants.CENTER);
        name.setFont(new Font("", Font.BOLD, 25));
        name.setForeground(Color.WHITE);
        panel.add(name);
        frame.add(panel);

        return panel;
    }

    private static JFrame basic(JFrame frame) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.gray);
        frame.setTitle("الصلاه علي النبي");
        frame.setLayout(null);

        return frame;
    }

    private static JButton pray_on_mohamed(JFrame frame) {

        JButton pray_Mohamed = new JButton();

        pray_Mohamed.setFocusable(false);

        pray_Mohamed.setText("تشغيل الصلاه علي النبي ");
        pray_Mohamed.setHorizontalAlignment(JLabel.CENTER);
        pray_Mohamed.setVerticalAlignment(JLabel.CENTER);

        Border border = BorderFactory.createLineBorder(Color.black, 4);

        pray_Mohamed.setBounds(300, 100, 400, 100);


        pray_Mohamed.setOpaque(true);
        pray_Mohamed.setBackground(Color.white);
        pray_Mohamed.setSize(120, 50);

        pray_Mohamed.setBorder(BorderFactory.createEtchedBorder());
        pray_Mohamed.setBorder(border);
        AtomicBoolean isRunning = new AtomicBoolean(false);


        Timer timer = new Timer(100_00, e -> {
            playSound("pray_mohaamed.wav");
            JOptionPane.showMessageDialog(frame, "اللهم صل وسلم على سيدنا محمد ﷺ");
            counter.incrementAndGet();
            updateCounterLabel();
        });


        pray_Mohamed.addActionListener(e -> {
            if (isRunning.get()) {
                pray_Mohamed.setBackground(Color.white);
                timer.stop();
                counter.set(0);
                counterLabel.setVisible(false);

            } else {
                pray_Mohamed.setBackground(Color.green);
                timer.start();
                playSound("pray_mohaamed.wav");
                JOptionPane.showMessageDialog(frame, "تم تشغيل التذكير بالصلاة على النبي ﷺ", "تذكير", JOptionPane.INFORMATION_MESSAGE);
                counter.incrementAndGet();
                frame.add(counterLabel);
                counterLabel.setVisible(true);
            }
            updateCounterLabel();
            isRunning.set(!isRunning.get());
        });

        return pray_Mohamed;
    }

    private static JLabel count() {
        JLabel label = new JLabel("0", SwingConstants.CENTER);
        label.setBounds(425, 100, 50, 50);
        label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        label.setOpaque(true);
        label.setBackground(Color.white);
        return label;
    }

    private static void updateCounterLabel() {
        counterLabel.setText(String.valueOf(counter.get()));
    }

    private static JButton getJButton(JFrame frame) {
        JButton state = new JButton();
        state.setFocusable(false);
        JPanel panel = Header(frame);
        state.setText("Light");
        state.setHorizontalAlignment(JLabel.CENTER);
        state.setVerticalAlignment(JLabel.CENTER);

        state.setBorder(BorderFactory.createLineBorder(Color.black, 0));

        state.setBounds(0, 52, 50, 50);

        AtomicBoolean light = new AtomicBoolean(false);

        state.addActionListener(e -> {
            if (light.get()) {
                frame.getContentPane().setBackground(Color.gray);
                state.setText("Light");
                panel.setBackground(new Color(50, 50, 50));
            } else {
                frame.getContentPane().setBackground(new Color(50, 50, 50));
                state.setText("Dark");
                panel.setBackground(Color.gray);

            }
            light.set(!light.get());
        });

        state.setOpaque(true);
        state.setBackground(Color.white);
        return state;
    }

    public static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------

    private static JButton getpage(JFrame frame) {
        JButton Azan = new JButton("الاذكار");
        Azan.setBounds(300, 155, 400, 100);
        Azan.setBackground(Color.white);
        Azan.setForeground(Color.black);
        Azan.setFocusable(false);

        Azan.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        Azan.setSize(120, 50);
        Azan.addActionListener(e -> new secondWindow());

        frame.add(Azan);
        return Azan;
    }

    static class secondWindow {
        JFrame frame = new JFrame();

        public secondWindow() {

            frame.setSize(800, 600);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.gray);
            frame.setTitle("الاذكار");
            frame.setLayout(null);
            frame.setVisible(true);

            JButton button1 = elmasaa(frame);
            JButton button2 = elsabah(frame);

            JButton button3 = afterpray(frame);
            JButton button4 = elmozakra(frame);
            JButton button5 = sleep(frame);


        }


        static JButton elmasaa(JFrame frame) {
            JButton button1 = new JButton();
            button1.setBackground(Color.white);
            button1.setForeground(Color.black);
            button1.setSize(800, 600);
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.setText("اذكار المساء");
            button1.setBounds(100, 100, 200, 100);

            button1.addActionListener(e -> new azkar_elmassa());
            frame.add(button1);

            return button1;
        } // done

        static JButton elmozakra(JFrame frame) {
            JButton button1 = new JButton();
            button1.setBackground(Color.white);
            button1.setForeground(Color.black);
            button1.setSize(800, 600);
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.setText("اذكار المذاكره");
            button1.setBounds(400, 400, 200, 100);

            button1.addActionListener(e -> new stydy_azkar());
            frame.add(button1);

            return button1;
        } // done

        static JButton afterpray(JFrame frame) {
            JButton button1 = new JButton();
            button1.setBackground(Color.white);
            button1.setForeground(Color.black);
            button1.setSize(800, 600);
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.setText("اذكار بعد الصلاه");
            button1.setBounds(100, 400, 200, 100);

            button1.addActionListener(e -> new azkar_after_pray());
            frame.add(button1);

            return button1;
        } // done


        static JButton elsabah(JFrame frame) {
            JButton button1 = new JButton();
            button1.setBackground(Color.white);
            button1.setForeground(Color.black);
            button1.setSize(800, 600);
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.setText("اذكار الصباح");
            button1.setBounds(400, 100, 200, 100);

            button1.addActionListener(e -> new azkar_elSabah());
            frame.add(button1);

            return button1;
        } // done


        static JButton sleep(JFrame frame) {
            JButton button1 = new JButton();
            button1.setBackground(Color.white);
            button1.setForeground(Color.black);
            button1.setSize(800, 600);
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.setText("اذكار قبل النوم");
            button1.setBounds(250, 250, 200, 100);

            button1.addActionListener(e -> new azker_before_sleep());
            frame.add(button1);

            return button1;
        }


    }


    // ------------------------------------------

    static class azkar_elmassa {
        JFrame frame = new JFrame();
        JButton button1;
        JButton button2;
        JTextArea masa;
        int counter = 0;

        azkar_elmassa() {

            frame.setSize(800, 600);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.gray);
            frame.setTitle("اذكار المساء");
            masa = new JTextArea();
            masa.setEditable(false);
            masa.setLineWrap(true);
            masa.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(masa);
            SwingUtilities.invokeLater(() -> masa.setCaretPosition(0));
            frame.setLayout(new BorderLayout());
            masa.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            frame.add(scrollPane);
            loadZaker("night.txt");
            masa.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));


            button1 = new JButton(String.valueOf(counter));
            button1.setBackground(Color.black);
            button1.setForeground(Color.white);
            button1.setPreferredSize(new Dimension(50, 50));
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.addActionListener(e -> {
                counter++;
                button1.setText(String.valueOf(counter));
            });


            button2 = new JButton("Reset");
            button2.setBackground(Color.black);
            button2.setForeground(Color.white);
            button2.setPreferredSize(new Dimension(50, 50));
            button2.setBounds(0, 60, 50, 50);
            button2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button2.setOpaque(true);
            button2.setFocusable(false);
            button2.addActionListener(e -> {
                counter = 0;
                button1.setText(String.valueOf(counter));
            });

//            new FlowLayout(FlowLayout.LEFT
            JPanel panel = new JPanel();
            panel.setBackground(Color.gray);
            panel.setBounds(0, 0, 60, 60);

            panel.add(button1);
            panel.add(button2);
            frame.add(panel, BorderLayout.NORTH);


            frame.setVisible(true);

        }


        private void loadZaker(String name) {
            try {
                String content = Files.readString(Paths.get(name));
                masa.setText(content);

            } catch (IOException e) {
                masa.setText("error" + name);
            }
        }
    } // done

    static class azkar_elSabah {

        JFrame frame = new JFrame();
        JTextArea azkar;
        JButton button1;
        JButton button2;
        int counter = 0;

        azkar_elSabah() {

            frame.setSize(800, 600);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.gray);
            frame.setTitle("اذكار الصباح");
            frame.setLayout(new BorderLayout());


            button1 = new JButton(String.valueOf(counter));
            button1.setBackground(Color.black);
            button1.setForeground(Color.white);
            button1.setPreferredSize(new Dimension(50, 50));
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.addActionListener(e -> {
                counter++;
                button1.setText(String.valueOf(counter));
            });


            button2 = new JButton("Reset");
            button2.setBackground(Color.black);
            button2.setForeground(Color.white);
            button2.setPreferredSize(new Dimension(50, 50));
            button2.setBounds(0, 60, 50, 50);
            button2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button2.setOpaque(true);
            button2.setFocusable(false);
            button2.addActionListener(e -> {
                counter = 0;
                button1.setText(String.valueOf(counter));
            });


            JPanel panel = new JPanel();
            panel.setBackground(Color.gray);
            panel.setBounds(0, 0, 60, 60);


            panel.add(button1);
            panel.add(button2);
            frame.add(panel, BorderLayout.NORTH);


            azkar = new JTextArea();
            azkar.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
            azkar.setLineWrap(true);
            azkar.setWrapStyleWord(true);

            azkar.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(azkar);
            SwingUtilities.invokeLater(() -> azkar.setCaretPosition(0));

            frame.add(scrollPane);

            azkar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


            loadAzkarElsabah("morningAzkay.txt");

            frame.setVisible(true);

        }

        private void loadAzkarElsabah(String fileName) {
            try {
                String content = Files.readString(Paths.get(fileName));
                azkar.setText(content);
            } catch (IOException e) {
                azkar.setText("تعذر تحميل الأذكار. تأكد من وجود الملف: " + fileName);
            }
        }

    } // done

    static class stydy_azkar {
        JFrame frame = new JFrame();
        JTextArea azkar;

        stydy_azkar() {
            frame.setSize(800, 600);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.gray);
            frame.setTitle("اذكار المذاكره");
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setLayout(new BorderLayout());

            azkar = new JTextArea();
            azkar.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
            azkar.setLineWrap(true);
            azkar.setWrapStyleWord(true);

            azkar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            azkar.setEditable(false);
            frame.add(azkar);

            LoadAzkar("stydyAzkdar.txt");
        }

        private void LoadAzkar(String fileName) {
            try {
                String content = Files.readString(Paths.get(fileName));
                azkar.setText(content);
            } catch (IOException e) {
                azkar.setText("Error when loading file: " + fileName);
            }
        }
    } // done


    static class azkar_after_pray {
        JFrame frame = new JFrame();
        JButton button1;
        JButton button2;
        JTextArea azkar;
        int Counter = 0;

        azkar_after_pray() {
            frame.setSize(800, 600);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.gray);
            frame.setTitle("اذكار بعد الصلاه");
            frame.setLayout(new BorderLayout());

            button1 = new JButton(String.valueOf(Counter));
            button1.setBackground(Color.black);
            button1.setForeground(Color.white);
            button1.setPreferredSize(new Dimension(50, 50));
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.addActionListener(e -> {
                Counter++;
                button1.setText(String.valueOf(Counter));
            });

            button2 = new JButton("Reset");
            button2.setBackground(Color.black);
            button2.setForeground(Color.white);
            button2.setPreferredSize(new Dimension(50, 50));
            button2.setBounds(0, 60, 50, 50);
            button2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button2.setOpaque(true);

            button2.setFocusable(false);
            button2.addActionListener(e -> {
                Counter = 0;
                button1.setText(String.valueOf(Counter));
            });


            JPanel panel = new JPanel();
            panel.setBackground(Color.gray);
            panel.setBounds(0, 0, 60, 60);
            panel.add(button1);
            panel.add(button2);
            frame.add(panel, BorderLayout.NORTH);

            azkar = new JTextArea();
            azkar.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
            azkar.setLineWrap(true);
            azkar.setWrapStyleWord(true);
            azkar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            azkar.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(azkar);

            SwingUtilities.invokeLater(() -> azkar.setCaretPosition(0));
            frame.add(scrollPane);

            LoadAzkar_pray("after.txt");


            frame.setVisible(true);


        }

        private void LoadAzkar_pray(String fileName) {
            try {
                String content = Files.readString(Paths.get(fileName));
                azkar.setText(content);

            } catch (Exception e) {
                azkar.setText("Error when loading file: " + fileName);
            }
        }

    }

    static class azker_before_sleep {
        JFrame frame = new JFrame();
        JTextArea azkar;
        JButton button1;
        JButton button2;
        int counter = 0;
        ;

        azker_before_sleep() {

            frame.setSize(800, 600);
            frame.setResizable(false);
            frame.getContentPane().setBackground(Color.gray);
            frame.setTitle("اذكار الصباح");
            frame.setLayout(new BorderLayout());


            button1 = new JButton(String.valueOf(counter));
            button1.setBackground(Color.black);
            button1.setForeground(Color.white);
            button1.setPreferredSize(new Dimension(50, 50));
            button1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button1.setOpaque(true);
            button1.setFocusable(false);
            button1.addActionListener(e -> {
                counter++;
                button1.setText(String.valueOf(counter));
            });


            button2 = new JButton("Reset");
            button2.setBackground(Color.black);
            button2.setForeground(Color.white);
            button2.setPreferredSize(new Dimension(50, 50));
            button2.setBounds(0, 60, 50, 50);
            button2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            button2.setOpaque(true);
            button2.setFocusable(false);
            button2.addActionListener(e -> {
                counter = 0;
                button1.setText(String.valueOf(counter));
            });


            JPanel panel = new JPanel();
            panel.setBackground(Color.gray);
            panel.setBounds(0, 0, 60, 60);


            panel.add(button1);
            panel.add(button2);
            frame.add(panel, BorderLayout.NORTH);


            azkar = new JTextArea();
            azkar.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
            azkar.setLineWrap(true);
            azkar.setWrapStyleWord(true);

            azkar.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(azkar);
            SwingUtilities.invokeLater(() -> azkar.setCaretPosition(0));

            frame.add(scrollPane);

            azkar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


            loadAzkarElsabah("beforS.txt");

            frame.setVisible(true);

        }

        private void loadAzkarElsabah(String fileName) {
            try {
                String content = Files.readString(Paths.get(fileName));
                azkar.setText(content);
            } catch (IOException e) {
                azkar.setText("تعذر تحميل الأذكار. تأكد من وجود الملف: " + fileName);
            }
        }
    }
}