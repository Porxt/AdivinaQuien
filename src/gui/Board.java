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
    private JButton buttonGuess;
    private JComboBox<String> questions;
    private JComboBox<String> characters;
    private JTextField textFieldValue;
    private final Menu parent;
    private GridCharacters canvas;
    private final String[] names = {
            "spiderman", "ironman", "'capitan america'", "'bruja escarlata'", "hulk",
            "loki", "'viuda negra'", "vision", "'carol danvers'", "gamora",
            "wolverine", "'jean grey'", "mystique", "rogue", "magneto",
            "'profesor x'", "bestia", "quicksilver", "tormenta", "unnamed"
    };
    private final boolean[] states = {
            true, true, true, true, true,
            true, true, true, true, true,
            true, true, true, true, true,
            true, true, true, true, true
    };
    private String correctCharacter;

    public Board(Menu parent) {
        super("Jugando!!!");
        this.parent = parent;
        chooseCharacter();
        initComponents();
        setBounds(5, 5, 1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.setVisible(true);
            }
        });
    }

    private void chooseCharacter() {
        int choosenOne = (int) Math.floor(Math.random() * 20);
        correctCharacter = names[choosenOne];
    }

    private void initComponents() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton buttonBack = new JButton("Salir");
        canvas = new GridCharacters();
        canvas.setDoubleBuffered(true);

        questions = new JComboBox<>(Question.questions);
        textFieldValue = new JTextField(15);
        buttonAsk = new JButton("Preguntar");
        buttonAsk.addActionListener(this);

        characters = new JComboBox<>(names);
        buttonGuess = new JButton("Adivinar");
        buttonGuess.addActionListener(this);

        buttonBack.addActionListener(this);

        panel.add(new JLabel("Pregunta: "));
        panel.add(questions);
        panel.add(new JLabel("Valor: "));
        panel.add(textFieldValue);
        panel.add(buttonAsk);
        panel.add(new JLabel("Personajes:"));
        panel.add(characters);
        panel.add(buttonGuess);
        panel.add(buttonBack);

        add(canvas, "Center");
        add(panel, "South");
    }

    public void actionPerformed(ActionEvent e) {
        int index;
        String value;
        Question question;
        if(e.getSource() == buttonAsk) {
            index = questions.getSelectedIndex();
            value = textFieldValue.getText();
            question = Question.run(index, value, correctCharacter);
            if(question.index != -1) {
                if(question.result) {
                    for(int i = 0; i < names.length; i++) {
                        if(!question.search(names[i])) {
                            states[i] = false;
                        }
                    }
                } else {
                    for(String character : question.characters) {
                        index = getCharacterIndex(character);
                        states[index] = false;
                    }
                }
                canvas.updateImages(states);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "El archivo adivinaquien.pl es necesario pero no se encuentra",
                        "Archivo requerido",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if(e.getSource() == buttonGuess) {
            value = characters.getSelectedItem() != null ? (String) characters.getSelectedItem() : "";
            if(value.equals(correctCharacter)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Has encontrado al personaje",
                        "Felicidades!!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
                buttonAsk.setEnabled(false);
                buttonGuess.setEnabled(false);
                textFieldValue.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Sigue intentando tu puedes",
                        "Todavia no",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else {
            setVisible(false);
            parent.setVisible(true);
            dispose();
        }
    }

    public int getCharacterIndex(String character) {
        int index = 0;
        for(int i = 0; i < names.length; i++) {
            if(names[i].equals(character)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
