import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Notepad1 extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;
    JFrame f;
    JMenuBar mb;
    JMenu m1;
    JMenu m2;
    JMenuItem open, newfile, save, exit;
    JMenuItem undo, paste, selectall;
    JFileChooser fc;
    JTextArea t;
    Clipboard cb;

    Notepad1() {
        f = new JFrame("NotePad");
        mb = new JMenuBar();
        m1 = new JMenu("File");
        m2 = new JMenu("Edit");
        open = new JMenuItem("Open");
        newfile = new JMenuItem("New");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        undo = new JMenuItem("Undo               Ctrl+Z");
        paste = new JMenuItem("Paste             Ctrl+V");
        selectall = new JMenuItem("SelectAll       Ctrl+A");
        t = new JTextArea();

        fc = new JFileChooser();
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.add(t);
        f.setJMenuBar(mb);
        mb.add(m1);
        mb.add(m2);
        m1.add(open);
        m1.add(newfile);
        m1.add(save);
        m1.add(exit);
        m2.add(undo);
        m2.add(paste);
        m2.add(selectall);

        OpenListener openL = new OpenListener();
        NewListener NewL = new NewListener();
        ExitListener ExitL = new ExitListener();
        PasteListener PasteL = new PasteListener();

        open.addActionListener(openL);
        newfile.addActionListener(NewL);
        exit.addActionListener(ExitL);
        paste.addActionListener(PasteL);

        f.setSize(600, 600);
        f.setVisible(true);

    }

    class OpenListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(f)) {
                File fl = fc.getSelectedFile();
                t.setText("");
                Scanner in = null;

                try {
                    in = new Scanner(fl);
                    while (in.hasNext()) {
                        String line = in.nextLine();
                        t.append(line + "\n");

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    in.close();
                }
            }
        }
    }

    class NewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t.setText("");
        }
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t.setText("");
        }
    }

    class PasteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Transferable trans = clip.getContents(Notepad1.this);
            try {
                String str = (String) trans.getTransferData(DataFlavor.stringFlavor);
                t.replaceRange(str, t.getSelectionStart(), t.getSelectionEnd());
            } catch (Exception exc) {
                System.out.println("Not String Flovour");
            }
        }
    }

    public static void main(String[] args) {
        Notepad1 n = new Notepad1();
    }

}