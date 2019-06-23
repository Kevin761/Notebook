import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Vector;

public class AppGUI extends JFrame {
    private JButton readFileButton;
    private JButton saveAsButton;
    private JPanel panel1;
    private JTextArea textArea;
    private JComboBox comboBoxSetSize;
    private JScrollPane scrollPane;




    public AppGUI() {

        panel1 = new JPanel();
        panel1.setBackground(Color.BLACK);
        this.setSize(800, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Notebook");



        Vector v = new Vector();
        for (int i=8; i<51; i+=1) {
            v.addElement(""+i);
        }

        comboBoxSetSize = new JComboBox(v);
        panel1.add(comboBoxSetSize);
        comboBoxSetSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setFont(new Font(textArea.getFont().getName(),textArea.getFont().getStyle(),comboBoxSetSize.getSelectedIndex()+12));


            }
        });





        readFileButton = new JButton("Read File");
        panel1.add(readFileButton);
        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                String filename = file.getAbsolutePath();

                try {
                    FileReader reader = new FileReader(filename);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    textArea.read(bufferedReader, null);
                    bufferedReader.close();
                    textArea.requestFocus();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        saveAsButton = new JButton("Save File");
        panel1.add(saveAsButton);
        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showSaveDialog(null);
                File file = chooser.getSelectedFile();
                String filename = file.getAbsolutePath();

                try {
                    FileWriter writer = new FileWriter(filename);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                    textArea.requestFocus();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });




        textArea = new JTextArea(35,70);
        //textArea.setFont(new Font("Arial", Font.PLAIN,16));

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        //textArea.setSize(750,600);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel1.add(scrollPane, BorderLayout.PAGE_START);


        this.add(panel1);
        this.setVisible(true);
        setFocusableWindowState(true);
        this.setResizable(false);




    }

}
