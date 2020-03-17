import Models.Note;
import Repositories.NoteRepo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    //normal objects..
    NoteRepo noteRepo = new NoteRepo();
    Object[] arrayofNames = initArrayOfNames();
    ArrayList<Note> listNotes = (ArrayList<Note>) noteRepo.readJsonFile();

    JPanel p = new JPanel();
    JButton b = new JButton("button");
    JButton z = new JButton("GetStuff");
    JLabel jLabel = new JLabel();
    JComboBox jComboBox = new JComboBox(arrayofNames); //list of things to choose from



    public static void main(String[] args) {
        new Main();
        NoteRepo noteRepo = new NoteRepo();

    }

    public Object[] initArrayOfNames(){
        ArrayList<Note> list = (ArrayList<Note>) noteRepo.readJsonFile();

        ArrayList<String> list2 = new ArrayList<>();

        for (Note note : list){
            String s = note.getName();
            list2.add(s);
        }
        Object[] array = list2.toArray();

        return array;
    }





    public Main() {
        super("basic Swing app");
        setSize(400, 300);

        //add button to panel

        b.addActionListener(this);
        jComboBox.addActionListener(this);



        p.add(jComboBox);
        p.add(jLabel);
        p.add(b);

        add(p);
        setResizable(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b) {

            noteRepo.saveToJson();
        }
        if (e.getSource() == jComboBox){
            JComboBox j = (JComboBox) e.getSource();
            String name = (String)j.getSelectedItem();

            for (Note note: listNotes){
                if (note.getName().equals(name)){
                    jLabel.setText(note.toString());
                }
            }
        }



    }
}
