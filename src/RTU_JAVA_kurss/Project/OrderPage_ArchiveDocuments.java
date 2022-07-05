package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.MyExtensions.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrderPage_ArchiveDocuments implements ActionListener, MouseListener {
    MyFrame archiveDocumentsFrame = new MyFrame("Document Solutions Archive Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

    // components #1
    MyTransparentTextLabel rightSLabel1Text = new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea = new MyTextArea(20, 40, 200, 160);

    // components #2
    MyTransparentTextLabel leftSLabel2Text = new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF = new MyTextField(40, 70, 160, 40);

    // components #3
    MyTransparentTextLabel middleSLabel1Text2 = new MyTransparentTextLabel("Telpas izmēri", 50, 0, 240, 50);
    MyTextField boxTextF2 = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2 = new MyTransparentTextLabel("(- m2)", 150, 160, 100, 40);

    // components #4
    MyTransparentTextLabel middleSLabel2Text = new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2 = new MyTextArea(20, 40, 200, 160);

    // components #6
    MyButton nextBtn = new MyButton("Iesniegt", 20, 100, 200, 50);

    public OrderPage_ArchiveDocuments() {

        // components ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
        // ############################################################### #1

        // components ################################################### #2
        workingTimeTextF.setText("09:00 - 17:00");
        workingTimeTextF.setEditable(false);
        workingTimeTextF.setForeground(Color.GRAY);
        workingTimeTextF.addMouseListener(this);

        label_2.add(workingTimeTextF);
        label_2.add(leftSLabel2Text);
        // ############################################################### #2

        // components ################################################### #5
        label_3.add(boxTextF2);
        label_3.add(boxSideText2);
        label_3.add(middleSLabel1Text2);
        // ############################################################### #5

        // components ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
        // ############################################################### #4

        // components ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
        // ############################################################### #6


        MyTransparentLabel[] labels = new MyTransparentLabel[]{label_3, label_5, label_1, label_2, label_4, label_6};
        for (int i = 0; i < labels.length; i++) {
            archiveDocumentsFrame.add(labels[i]);
        }
        archiveDocumentsFrame.setLayout(null);
        archiveDocumentsFrame.setVisible(true);
    } //End OrderPage_ArchiveDocuments()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextBtn)) {
            // šeit ir nepieciešams kods, kas aptver visu ievadīto info un nogādā tālāk
            archiveDocumentsFrame.dispose();
            new PreviousOrders();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

} //End class OrderPage_ArchiveDocuments
