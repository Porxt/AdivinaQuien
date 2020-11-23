package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private JButton buttonPlay;
    private JButton buttonAbout;

    public Menu() {
        super("Adivina Quien");
        initComponents(this.getContentPane());
        setBounds(5, 5, 1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents(Container pane) {
        JButton buttonExit;
        pane.setLayout(new GridLayout(0, 3, 80, 120));

        pane.add(new JLabel(""));
        pane.add(new JLabel(""));
        pane.add(new JLabel(""));

        buttonPlay = new JButton("Jugar");
        buttonPlay.addActionListener(this);
        pane.add(new JLabel(""));
        pane.add(buttonPlay);
        pane.add(new JLabel(""));

        buttonAbout = new JButton("Creditos");
        buttonAbout.addActionListener(this);
        pane.add(new JLabel(""));
        pane.add(buttonAbout);
        pane.add(new JLabel(""));

        buttonExit = new JButton("Salir");
        buttonExit.addActionListener(this);
        pane.add(new JLabel(""));
        pane.add(buttonExit);
        pane.add(new JLabel(""));

        pane.add(new JLabel(""));
        pane.add(new JLabel(""));
        pane.add(new JLabel(""));
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonPlay) {
            setVisible(false);
            new Board(this);
        } else if(e.getSource() == buttonAbout) {
            JOptionPane.showMessageDialog(
                    this,
                    "Creado por Team1234",
                    "Saber más...",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Menu();
    }
}
