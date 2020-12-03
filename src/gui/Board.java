package gui;

import logic.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends JFrame implements ActionListener {

    private JButton buttonAsk;
    private JComboBox<String> questions;
    private JTextField textFieldValue;
    private final Menu parent;
    private GridCharacters canvas;

    public Board(Menu parent) {
        super("Jugando!!!");
        this.parent = parent;
        initComponents();
        setBounds(5, 5, 1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // super.windowClosed(e);
                parent.setVisible(true);
            }
        });
    }

    private void initComponents() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton buttonBack = new JButton("Salir");
        canvas = new GridCharacters();
        canvas.setDoubleBuffered(true);

        textFieldValue = new JTextField(15);
        questions = new JComboBox<>(Question.questions);
        buttonAsk = new JButton("Preguntar");
        buttonAsk.addActionListener(this);
        buttonBack.addActionListener(this);

        panel.add(new JLabel("Pregunta: "));
        panel.add(questions);
        panel.add(new JLabel("Valor: "));
        panel.add(textFieldValue);
        panel.add(buttonAsk);
        panel.add(buttonBack);

        add(canvas, "Center");
        add(panel, "South");
    }

    public void actionPerformed(ActionEvent e) {
        int index;
        String value;
        boolean[] states = {
                true, false, true, true, true, true, true, true, true, true,
                true, true, true, false, false, true, false, true, true, true
        };
        if(e.getSource() == buttonAsk) {
            index = questions.getSelectedIndex();
            value = textFieldValue.getText();
            Question.run(index, value);
            canvas.updateImages(states);
        } else {
            setVisible(false);
            parent.setVisible(true);
            dispose();
        }
    }
}
