package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.Extensions.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrderPage_ShreddingDocuments implements ActionListener, MouseListener {
    MyFrame shredDocumentsFrame = new MyFrame("Document Solutions Shredding Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5_0 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

    // components #1
    MyTransparentTextLabel rightSLabel1Text = new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea = new MyTextArea(20, 40, 200, 160);

    // components #2
    MyTransparentTextLabel needTransportText = new MyTransparentTextLabel("Nepieciešama aizvešana?", 20, 0, 240, 50);
    MyButton yesBtn1 = new MyButton("JĀ!", 20, 40, 90, 50);
    MyButton noBtn1 = new MyButton("NĒ!", 130, 40, 90, 50);

    // components #3
    MyTransparentTextLabel leftSLabel1Text = new MyTransparentTextLabel("Aptuvenais svars", 40, 0, 240, 50);
    MyTextField boxTextF = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText = new MyTransparentTextLabel("KG", 150, 160, 100, 40);

    // components #4
    MyTransparentTextLabel middleSLabel2Text = new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2 = new MyTextArea(20, 40, 200, 160);

    // components #5
    MyTransparentTextLabel middleSLabel1Text = new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2 = new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn = new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn = new MyButton("NAV", 130, 40, 90, 50);
    MyTextField boxTextF2 = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2 = new MyTransparentTextLabel("(- stāvi)", 150, 160, 100, 40);

    // components #6
    MyButton nextBtn = new MyButton("Iesniegt", 20, 100, 200, 50);


    public OrderPage_ShreddingDocuments() {
        // components ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
        // ############################################################### #1

        // components ################################################### #2
        yesBtn1.addActionListener(this);
        noBtn1.addActionListener(this);

        label_2.add(noBtn1);
        label_2.add(yesBtn1);
        label_2.add(needTransportText);
        // ############################################################### #2

        // components ################################################### #3
        // leftSLabel1Text add Mouse Listener
        leftSLabel1Text.addMouseListener(this);
        // leftSideLabel1 setUp
        label_3.add(boxSideText);
        label_3.add(boxTextF);
        label_3.add(leftSLabel1Text);
        // ############################################################### #3

        // components ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
        // ############################################################### #4

        // components ################################################### #5
        label_5.setVisible(false);
        boxTextF2.setVisible(false);
        boxSideText2.setVisible(false);

        yesBtn.addActionListener(this);
        noBtn.addActionListener(this);

        label_5.add(boxTextF2);
        label_5.add(boxSideText2);
        label_5.add(noBtn);
        label_5.add(yesBtn);
        label_5.add(middleSLabel1Text);
        label_5.add(middleSLabel1Text2);
        // ############################################################### #5

        // components ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
        // ############################################################### #6

        MyTransparentLabel[] labels = new MyTransparentLabel[]{label_1, label_2, label_3, label_4, label_5_0, label_5, label_6};
        for (int i = 0; i < labels.length; i++) {
            shredDocumentsFrame.add(labels[i]);
        }

        shredDocumentsFrame.setLayout(null);
        shredDocumentsFrame.setVisible(true);
    } //End OrderPage_ShreddingDocuments()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextBtn)) {
            // šeit ir nepieciešams kods, kas aptver visu ievadīto info un nogādā tālāk
            shredDocumentsFrame.dispose();
            new PreviousOrders();
        }
        if (e.getSource().equals(yesBtn1)) {
            yesBtn1.setBackground(new Color(141, 210, 93));
            noBtn1.setBackground(new Color(184, 229, 154));
            label_5_0.setVisible(false);
            label_5.setVisible(true);
        }
        if (e.getSource().equals(noBtn1)) {
            yesBtn1.setBackground(new Color(184, 229, 154));
            noBtn1.setBackground(new Color(141, 210, 93));
            label_5.setVisible(false);
            label_5_0.setVisible(true);
        }
        if (e.getSource().equals(yesBtn)) {
            yesBtn.setBackground(new Color(141, 210, 93));
            noBtn.setBackground(new Color(184, 229, 154));
            boxTextF2.setVisible(false);
            boxSideText2.setVisible(false);
        }
        if (e.getSource().equals(noBtn)) {
            yesBtn.setBackground(new Color(184, 229, 154));
            noBtn.setBackground(new Color(141, 210, 93));
            boxTextF2.setVisible(true);
            boxSideText2.setVisible(true);
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
} //End class OrderPage_ShreddingDocuments
