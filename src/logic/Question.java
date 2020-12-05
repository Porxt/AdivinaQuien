package logic;

import org.jpl7.Query;

public class Question {

    public static String[] questions = {
            "¿El personaje es [hombre/mujer]?",
            "¿Su piel es [clara/morena/azul/verde/roja]?",
            "¿El personaje es calvo?",
            "¿Su cabello es color [negro/cafe/rubio/rojo/azul/blanco]?",
            "¿Su cabello esta [lacio/ondulado]?",
            "¿Su cabello es [corto/medio/largo]?",
            "¿Tiene ojos [cafes/azules/amarillos/verdes]?"
    };
    public final int index;
    public final String parameter;
    public final boolean result;
    public final String[] characters;

    public Question(int index, String parameter, boolean result, String[] characters) {
        this.index = index;
        this.parameter = parameter;
        this.result = result;
        this.characters = characters;
    }

    public static Question run(int index, String value, String character) {
        String question;
        Query ask;
        boolean result;
        String charactersString;
        String[] characters;

        // Iniciamos la conexión
        String connection = "consult('adivinaquien.pl')";
        Query queryConnection = new Query(connection);
        if(queryConnection.hasSolution()) {
            // Hacemos la pregunta
            switch (index) {
                case 0 -> {
                    question = "pregunta(" + value + ", " + character + ")";
                    ask = new Query(question);
                    result = ask.hasSolution();
                    ask.close();
                    question = "caracteristica_de(" + value + ", X)";
                    ask = new Query(question);
                    charactersString = ask.oneSolution().toString();
                    charactersString = charactersString.substring(4, charactersString.lastIndexOf(']'));
                    characters = charactersString.split(", ");
                }
                case 1 -> {
                    question = "pregunta('piel " + value + "', " + character + ")";
                    ask = new Query(question);
                    result = ask.hasSolution();
                    ask.close();
                    question = "caracteristica_de('piel " + value + "', X)";
                    ask = new Query(question);
                    charactersString = ask.oneSolution().toString();
                    charactersString = charactersString.substring(4, charactersString.lastIndexOf(']'));
                    characters = charactersString.split(", ");
                }
                case 2 -> {
                    question = "pregunta('sin cabello', " + character + ")";
                    ask = new Query(question);
                    result = ask.hasSolution();
                    ask.close();
                    question = "caracteristica_de('sin cabello', X)";
                    ask = new Query(question);
                    charactersString = ask.oneSolution().toString();
                    charactersString = charactersString.substring(4, charactersString.lastIndexOf(']'));
                    characters = charactersString.split(", ");
                }
                case 3, 4, 5 -> {
                    question = "pregunta('cabello " + value + "', " + character + ")";
                    ask = new Query(question);
                    result = ask.hasSolution();
                    ask.close();
                    question = "caracteristica_de('cabello " + value + "', X)";
                    ask = new Query(question);
                    charactersString = ask.oneSolution().toString();
                    charactersString = charactersString.substring(4, charactersString.lastIndexOf(']'));
                    characters = charactersString.split(", ");
                }
                case 6 -> {
                    question = "pregunta('ojos " + value + "', " + character + ")";
                    ask = new Query(question);
                    result = ask.hasSolution();
                    ask.close();
                    question = "caracteristica_de('ojos " + value + "', X)";
                    ask = new Query(question);
                    charactersString = ask.oneSolution().toString();
                    charactersString = charactersString.substring(4, charactersString.lastIndexOf(']'));
                    characters = charactersString.split(", ");
                }
                default -> {
                    result = true;
                    characters = new String[0];
                }
            }
        } else {
            result = false;
            characters = new String[0];
            index = -1;
        }
        return new Question(index, value, result, characters);
    }

    public boolean search(String character) {
        boolean found = false;
        for(String c : characters) {
            if(c.equals(character)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
