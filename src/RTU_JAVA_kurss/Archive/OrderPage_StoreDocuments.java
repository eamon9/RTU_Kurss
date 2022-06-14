package RTU_JAVA_kurss.Archive;

import RTU_JAVA_kurss.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// šī ir vecā versija OrderPage

/*class OrderPage_StoreDocuments implements ActionListener, MouseListener {
    MyFrame storeDocumentsFrame= new MyFrame("Document Solutions Store Documents Page");
    MyTransparentLabel leftSideLabel1= new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel middleSideLabel1= new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel rightSideLabel1= new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel leftSideLabel2= new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel middleSideLabel2= new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel rightSideLabel2= new MyTransparentLabel(600, 365, 240, 240);
// leftSideLabel1 componentes
    MyTransparentLabel helpLabel1= new MyTransparentLabel(40, 40, 160, 70);
    MyTransparentTextLabel leftSLabel1Text= new MyTransparentTextLabel("Daudzums kastēs *", 40, 0, 240, 50);
    MyLabel helpTextLabel1= new MyLabel("VADOTIES PĒC TĀ, KA", 5, 10, 160, 15, 12);
    MyLabel helpTextLabel2= new MyLabel("VIENĀ KASTĒ SALIEN", 5, 30, 160, 15, 12);
    MyLabel helpTextLabel3= new MyLabel("5 REĢISTRA MAPES", 5, 50, 160, 15, 12);
    MyTextField boxTextF= new MyTextField(40, 140, 100, 40);
    MyTransparentTextLabel boxSideText= new MyTransparentTextLabel("(- kastes )", 150, 140, 100, 40);

// middleSideLabel1 componentes
    MyTransparentTextLabel middleSLabel1Text= new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 0, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Lifts", 100, 30, 240, 50);
    MyButton yesBtn= new MyButton("IR", 20, 70, 90, 50);
    MyButton noBtn= new MyButton("NAV", 130, 70, 90, 50);
    MyTextField boxTextF2= new MyTextField(40, 140, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- stāvi )", 150, 140, 100, 40);

// rightSideLabel1 componentes
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea= new MyTextArea(20, 40, 200, 160);

// leftSideLabel2 componentes
    MyTransparentTextLabel leftSLabel2Text= new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF= new MyTextField(40, 70, 160, 40);

// middleSideLabel2 componentes
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2= new MyTextArea(20, 40, 200, 160);

// rightSideLabel2 componentes
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);
    OrderPage_StoreDocuments() {
// rightSideLabel2 componentes ##################################

        nextBtn.addActionListener(this);

        rightSideLabel2.add(nextBtn);

// ###############################################################

// middleSideLabel2 componentes ##################################

        middleSideLabel2.add(boxTextArea2);
        middleSideLabel2.add(middleSLabel2Text);

// ###############################################################

// leftSideLabel2 componentes ####################################

        workingTimeTextF.setText("09:00 - 17:00");
        workingTimeTextF.setEditable(false);
        workingTimeTextF.setForeground(Color.GRAY);
        workingTimeTextF.addMouseListener(this);

        leftSideLabel2.add(workingTimeTextF);
        leftSideLabel2.add(leftSLabel2Text);

// ###############################################################

// rightSideLabel1 componentes ####################################

        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        rightSideLabel1.add(boxTextArea);
        rightSideLabel1.add(rightSLabel1Text);
// ###############################################################

// middleSideLabel1 componentes ####################################
        boxTextF2.setVisible(false);
        boxSideText2.setVisible(false);

        yesBtn.addActionListener(this);
        noBtn.addActionListener(this);

        middleSideLabel1.add(boxTextF2);
        middleSideLabel1.add(boxSideText2);
        middleSideLabel1.add(noBtn);
        middleSideLabel1.add(yesBtn);
        middleSideLabel1.add(middleSLabel1Text);
        middleSideLabel1.add(middleSLabel1Text2);

// ###############################################################

// leftSideLabel1 componentes ####################################
// Change color of helpText
        helpTextLabel1.setForeground(Color.BLACK);
        helpTextLabel2.setForeground(Color.BLACK);
        helpTextLabel3.setForeground(Color.BLACK);
// leftSLabel1Text add Mouse Listiner
        leftSLabel1Text.addMouseListener(this);
// helpLabel1 setUp
        helpLabel1.setBackground(new Color(173, 224, 203));
        helpLabel1.add(helpTextLabel1);
        helpLabel1.add(helpTextLabel2);
        helpLabel1.add(helpTextLabel3);
        helpLabel1.setVisible(false);
// leftSideLabel1 setUp
        leftSideLabel1.add(boxSideText);
        leftSideLabel1.add(boxTextF);
        leftSideLabel1.add(leftSLabel1Text);
        leftSideLabel1.add(helpLabel1);
// ###############################################################
// All labels added to storeDocumentsFrame
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel1, middleSideLabel1, rightSideLabel1, leftSideLabel2, middleSideLabel2, rightSideLabel2};
        for (int i = 0; i < labels.length; i++) {
            storeDocumentsFrame.add(labels[i]);
        }
        storeDocumentsFrame.setLayout(null);
        storeDocumentsFrame.setVisible(true);
    } // End OrderPage_StoreDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(nextBtn)) {
            // šeit ir nepieciešams kods, kas aptver visu ievadīto info un nogādā tālāk
            storeDocumentsFrame.dispose();
            new MainPage();
        }
        if(e.getSource().equals(yesBtn)) {
            yesBtn.setBackground(new Color(141, 210, 93));
            noBtn.setBackground(new Color(184, 229, 154));
            boxTextF2.setVisible(false);
            boxSideText2.setVisible(false);
        }
        if(e.getSource().equals(noBtn)) {
            yesBtn.setBackground(new Color(184, 229, 154));
            noBtn.setBackground(new Color(141, 210, 93));
            boxTextF2.setVisible(true);
            boxSideText2.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(boxTextArea)) {
            boxTextArea.setText("");
            boxTextArea.setForeground(Color.BLACK);
            boxTextArea.setEditable(true);
        }
        if(e.getSource().equals(workingTimeTextF)) {
            workingTimeTextF.setText("");
            workingTimeTextF.setEditable(true);
            workingTimeTextF.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(leftSLabel1Text)) {
            helpLabel1.setVisible(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(leftSLabel1Text)) {
            helpLabel1.setVisible(false);
        }
    }
}*/ //End class OrderPage_StoreDocuments


