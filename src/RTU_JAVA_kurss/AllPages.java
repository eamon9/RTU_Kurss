package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MainPage implements ActionListener {
    // zemāk deklarējam un inicializējam JFrame, JLabel, JButton utt, lai būtu pieejami arī ārpus MainPage()
    MyFrame mainPage= new MyFrame("Document Solutions Main Page");
    JLabel titleLabel= new JLabel("Document Solutions");
    MyLabel subTitlelabel1= new MyLabel("Mēs GLABĀJAM", 250, 270, 250, 50, 20);
    MyLabel subTitlelabel2= new MyLabel("SMALCINĀM,", 400, 290, 250, 50, 20);
    MyLabel subTitlelabel3= new MyLabel("IZNĪCINĀM.", 520, 310, 250, 50, 20);
    MyButton startBtn= new MyButton("Sākt", 350, 450, 200, 50);

    MainPage() {
        titleLabel.setForeground(new Color(11, 87, 3));
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
        titleLabel.setBounds(200, 100, 600, 300);

        startBtn.addActionListener(this);

        //Šeit jāpačeko- kautkas neiet....
        MyLabel[] subLabels= new MyLabel[] {subTitlelabel1, subTitlelabel2, subTitlelabel3};
        for (int i = 0; i < subLabels.length; i++) {
            mainPage.add(subLabels[i]);
        }

        mainPage.add(startBtn);
        mainPage.add(titleLabel);
        mainPage.setLayout(null);
        mainPage.setVisible(true); // padara rāmi redzamu
    } //End MainPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startBtn)) {
            startBtn.setBackground(new Color(141, 210, 93));
            mainPage.dispose();
            new LoginPage();
        } //End if else statement
    } //End actionPerformed()
} //End MainPage class

class LoginPage implements ActionListener, MouseListener {

    MyFrame loginPageFrame= new MyFrame("Document Solutions Login Page");
    MyTransparentTextLabel registeredUserLabel= new MyTransparentTextLabel("Esmu reģistrēts lietotājs", 85, 60, 250, 50);
    MyTransparentTextLabel notRegisteredUserLabel= new MyTransparentTextLabel("Jums nav lietotāja profila?", 75, 60, 250, 50);
    MyTransparentLabel leftSideLabel= new MyTransparentLabel(65, 100, 365, 500);
    MyTransparentLabel rightSideLabel= new MyTransparentLabel(480, 100, 350, 500);
    MyTextField mailTextField= new MyTextField("Jūsu e-pasta adrese", 60, 140, 250, 45, true);
    MyPasswordField passwordField= new MyPasswordField(60, 220, 250, 45);
    MyTextField fakePasswordField= new MyTextField("Parole", 60, 220, 250, 45, true);
    MyButton loginBtn= new MyButton("Pieslēgties", 80, 300, 200, 50);
    MyButton registrationBtn= new MyButton("Reģistrēties", 80, 140, 200, 50);
    JTextField textField = new JTextField(); // ??

    LoginPage() {
//Reģistrēta lietoytāja komponentes

        mailTextField.addMouseListener(this);
        loginBtn.addActionListener(this);
        registrationBtn.addActionListener(this);
        fakePasswordField.addMouseListener(this);

        passwordField.setVisible(false);

//left side label
        leftSideLabel.add(loginBtn);
        leftSideLabel.add(mailTextField);
        leftSideLabel.add(fakePasswordField);
        leftSideLabel.add(passwordField);
        leftSideLabel.add(textField);
        leftSideLabel.add(registeredUserLabel);

//right side label
        rightSideLabel.add(registrationBtn);
        rightSideLabel.add(notRegisteredUserLabel);

// loginPage nobeiguma daļa, gan .add, gan Layout un Visible set
        loginPageFrame.add(leftSideLabel); // pievieno iekavās norādīto pie galvenā rāmja- loginPageFrame
        loginPageFrame.add(rightSideLabel);
        loginPageFrame.setLayout(null);
        loginPageFrame.setVisible(true);
    } //End LoginPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        // !!!!!!!  Projektam tuvojoties beigām, šeit jāsasaista ar datubāzi,
        // kas pārbauda, vai ir šāds lietotājvārds ar attiecīgo paroli
        // !!!!!!!                                     !!!!!!!!!!!!!!!!!!!!!!
        if(e.getSource().equals(loginBtn)) {
            loginBtn.setBackground(new Color(141, 210, 93));
            registrationBtn.setBackground(new Color(184, 229, 154));
            String pas= String.valueOf(passwordField.getPassword());
            boolean isAlpha= CheckIfFieldsCorrect(mailTextField.getText());
            System.out.println("CheckFieldCorrect= "+isAlpha);
            if(isAlpha && pas.equals("qwerty")) {
                loginPageFrame.dispose();
                new OrderePageSelectItem();
            } //End inner if else statement
        } //End if else statement
        if(e.getSource().equals(registrationBtn)) {
            registrationBtn.setBackground(new Color(141, 210, 93));
            loginBtn.setBackground(new Color(184, 229, 154));
            loginPageFrame.dispose();
            new RegistrationPage();
        }
    } //End actionPerformed()

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(mailTextField) && (mailTextField.getText().equals("Jūsu e-pasta adrese"))) {
            mailTextField.setEditable(true);
            mailTextField.setForeground(Color.BLACK);
            mailTextField.setText("");
            fakePasswordField.setVisible(false);
            passwordField.setVisible(true);
        }
        if(e.getSource().equals(fakePasswordField)) {
            fakePasswordField.setVisible(false);
            passwordField.setVisible(true);
            //passwordField.setCaretPosition(0);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    boolean CheckIfFieldsCorrect(String name) {
        if(name.contains("@") && name.contains(".")) {
            System.out.println("Yes "+name+" contains '@' and '.'");
            return true;
        } else{
            System.out.println("No "+name+" doesn't contains '@' and '.'");
            return false;
        }

    }
} //End LoginPage class

class RegistrationPage implements ActionListener {
    MyFrame registrationPageFrame= new MyFrame("Document Solutions registration Form");
    MyTransparentLabel mainLabel= new MyTransparentLabel(65, 100, 770, 500);
    MyRadioButton individualRadioBtn= new MyRadioButton("Privātpersona", 50, 10, 200, 50);
    MyRadioButton juridicalRadioBtn= new MyRadioButton("Juridiska persona", 50, 70, 200, 50);
    ButtonGroup personGroup;
    MyButton backBtn= new MyButton("< Atpakaļ", 290, 50, 200, 50);
    MyButton nextBtn= new MyButton(" Turpināt >", 510, 50, 200, 50);
    MyLabel nameLabel= new MyLabel("Vārds", 60, 120, 200, 50);
    MyLabel surnameLabel= new MyLabel("Uzvārds", 220, 120, 200, 50);
    MyLabel mailLabel= new MyLabel("E-pasts", 380, 120, 300, 50);
    MyLabel mobNrLabel= new MyLabel("Mob. Tālrunis", 60, 200, 200, 50);
    MyLabel passwordLabel= new MyLabel("Parole", 280, 200, 200, 50);
    MyLabel repeatPasswordLabel= new MyLabel("Atkārtojiet Paroli", 500, 200, 200, 50);

    MyLabel companyNameLabel= new MyLabel("Uzņēmuma nosaukums", 60, 280, 200, 50);
    MyLabel companyRegLabel= new MyLabel("Uzņēmuma reģ. Nr.", 380, 280, 200, 50);
    MyLabel pvnLabel= new MyLabel("PVN reģ. NR.", 540, 280, 200, 50);
    MyLabel bankLabel= new MyLabel("Banka, kods(SWIFT)", 540, 360, 200, 50);
    MyLabel companyAdressLabel= new MyLabel("Uzņēmuma adrese", 60, 360, 200, 50);
    MyLabel ibanLabel= new MyLabel("IBAN konts",  380, 360, 200, 50);

    MyTextField nameTF= new MyTextField(60, 160, 150, 40);
    MyTextField surnameTF= new MyTextField(220, 160, 150, 40);
    MyTextField mailTF= new MyTextField(380, 160, 330, 40);
    MyTextField mobNrTF= new MyTextField(60, 240, 210, 40);

    MyPasswordField passwordTF= new MyPasswordField(280, 240, 210, 40);
    MyPasswordField repeatPasswordTF= new MyPasswordField(500, 240, 210, 40);

    MyTextField companyNameTF= new MyTextField(60, 320, 310, 40);
    MyTextField companyRegTF= new MyTextField(380, 320, 150, 40);
    MyTextField pvnTF= new MyTextField(540, 320, 170, 40);

    MyTextField companyAdressTF= new MyTextField(60, 400, 310, 40);
    MyTextField ibanTF= new MyTextField(380, 400, 150, 40);
    MyTextField bankTF= new MyTextField(540, 400, 170, 40);


    RegistrationPage() {
        personGroup= new ButtonGroup(); // grupēšana nepieciešama, lai varētu atzīmēt tikai vienu no izvēlētajiem variantiem
        personGroup.add(individualRadioBtn);
        personGroup.add(juridicalRadioBtn);
// visi 'label' priekš juridiskās daļas, kam noklusējumā jābūt slēptiem
        MyLabel[] labelsForJuridical= new MyLabel[] {companyNameLabel, companyRegLabel, pvnLabel, bankLabel, companyAdressLabel, ibanLabel};
        for (int i = 0; i < labelsForJuridical.length; i++) {
            labelsForJuridical[i].setVisible(false);
            mainLabel.add(labelsForJuridical[i]);
        }
// visi 'label' priekš privātpersonas daļas, kam noklusējumā jābūt redzamiem
        MyLabel[] labelsForIndividual= new MyLabel[] {mailLabel, mobNrLabel, surnameLabel, nameLabel};
        for (int i = 0; i < labelsForIndividual.length; i++) {
            mainLabel.add(labelsForIndividual[i]);
        }
// visi 'TextField' priekš juridiskās daļas, kam noklusējumā jābūt slēptiem un tiek pievienoti mainLabel klasei ar .add() Metodi
        MyTextField[] juridicalTF= new MyTextField[] {companyNameTF, companyRegTF, pvnTF, bankTF, companyAdressTF, ibanTF};
        for (int i = 0; i < juridicalTF.length; i++) {
            juridicalTF[i].setVisible(false);
            mainLabel.add(juridicalTF[i]);
        }
// visi 'TextField' priekš privātpersonu daļas tiek pievienoti mainLabel klasei ar Metodi .add();
        MyTextField[] individualTF= new MyTextField[] {mobNrTF, mailTF, surnameTF, nameTF};
        for (int i = 0; i < individualTF.length; i++) {
            mainLabel.add(individualTF[i]);
        }

        individualRadioBtn.addActionListener(this);
        juridicalRadioBtn.addActionListener(this);
        backBtn.addActionListener(this);
        nextBtn.addActionListener(this);

        mainLabel.add(repeatPasswordTF);
        mainLabel.add(passwordTF);
        mainLabel.add(repeatPasswordLabel);
        mainLabel.add(passwordLabel);

        mainLabel.add(nextBtn);
        mainLabel.add(backBtn);

        mainLabel.add(individualRadioBtn);
        mainLabel.add(juridicalRadioBtn);

        registrationPageFrame.add(mainLabel);
        registrationPageFrame.setLayout(null);
        registrationPageFrame.setVisible(true);
    } //End RegistrationPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(backBtn)) {
            backBtn.setBackground(new Color(141, 210, 93));
            nextBtn.setBackground(new Color(184, 229, 154));
            registrationPageFrame.dispose();
            new LoginPage();
        }
        if(e.getSource().equals(nextBtn)) {
            nextBtn.setBackground(new Color(141, 210, 93));
            backBtn.setBackground(new Color(184, 229, 154));
            // jāpārbauda vai visi lauki ir aizpildīti un vai atbilst rakstzīmes norādītajam,
            // lai varētu turpināt..
        }
        if(e.getSource().equals(individualRadioBtn)) {
            MyLabel[] labelsForJuridical= new MyLabel[] {companyNameLabel, companyRegLabel, pvnLabel, bankLabel, companyAdressLabel, ibanLabel};
            for (int i = 0; i < labelsForJuridical.length; i++) {
                labelsForJuridical[i].setVisible(false);
            }

            MyTextField[] juridicalTF= new MyTextField[] {companyNameTF, companyRegTF, pvnTF, bankTF, companyAdressTF, ibanTF};
            for (int i = 0; i < juridicalTF.length; i++) {
                juridicalTF[i].setVisible(false);
            }
        }

        if(e.getSource().equals((juridicalRadioBtn))) {
            MyLabel[] labelsForJuridical= new MyLabel[] {companyNameLabel, companyRegLabel, pvnLabel, bankLabel, companyAdressLabel, ibanLabel};
            for (int i = 0; i < labelsForJuridical.length; i++) {
                labelsForJuridical[i].setVisible(true);
            }

            MyTextField[] juridicalTF= new MyTextField[] {companyNameTF, companyRegTF, pvnTF, bankTF, companyAdressTF, ibanTF};
            for (int i = 0; i < juridicalTF.length; i++) {
                juridicalTF[i].setVisible(true);
            }
        }
    } //End actionPerformed()
} //End RegistrationPage class

class OrderePageSelectItem implements ActionListener, MouseListener {
    MyFrame orderPageFrame= new MyFrame("Document Solutions Order Page");
    MyTransparentLabel sideLabel1= new MyTransparentLabel(65, 100, 365, 500);
    MyTransparentLabel sideLabel2= new MyTransparentLabel(480, 100, 350, 500);
    MyTransparentLabel leftSideLabel= new MyTransparentLabel(65, 100, 240, 500);
    MyTransparentLabel middleSideLabel= new MyTransparentLabel(330, 100, 240, 500);
    MyTransparentLabel rightSideLabel= new MyTransparentLabel(600, 100, 240, 500);
    MyTransparentTextLabel sevicesTextLabel= new MyTransparentTextLabel("Pakalpojumi", 135, 60, 250, 50);
    MyTransparentTextLabel previousOrdersTextLabel= new MyTransparentTextLabel("Iepriekšējie pasūtījumi", 90, 60, 250, 50);
    MyTransparentTextLabel leftSideTextLabel= new MyTransparentTextLabel("Dokumentu glabāšana", 30, 20, 240, 100);
    MyTransparentTextLabel middleSideTextLabel= new MyTransparentTextLabel("Smalcināšana / iznīcināšana", 10, 20, 240, 100);
    MyTransparentTextLabel rightSideTextLabel= new MyTransparentTextLabel("Arhīva sakārtošana", 40, 20, 240, 100);

    OrderePageSelectItem() {

        sideLabel2.add(previousOrdersTextLabel);
        sideLabel1.add(sevicesTextLabel);
        leftSideLabel.add(leftSideTextLabel);
        middleSideLabel.add(middleSideTextLabel);
        rightSideLabel.add(rightSideTextLabel);

        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setVisible(false);
            orderPageFrame.add(labels[i]);
            labels[i].addMouseListener(this);
        }

        sideLabel1.addMouseListener(this);
        sideLabel2.addMouseListener(this);

        orderPageFrame.add(sideLabel2);
        orderPageFrame.add(sideLabel1);
        orderPageFrame.setLayout(null);
        orderPageFrame.setVisible(true);
    } //End OrderePageSelectItem()

    @Override
    public void actionPerformed(ActionEvent e) {
    } //End actionPerformed()

    @Override
    public void mouseClicked(MouseEvent e) {
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if(e.getSource().equals(labels[0])) {
                orderPageFrame.dispose();
                new OrderPage_StoreDocuments();
            }
            if(e.getSource().equals(labels[1])) {
                orderPageFrame.dispose();
                new OrderPage_ShreddingDocuments();
            }
            if(e.getSource().equals(labels[2])) {
                orderPageFrame.dispose();
                new OrderPage_ArchiveDocuments();
            }
        }
        MyTransparentLabel[] labels2= new MyTransparentLabel[] {sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if(e.getSource().equals(labels2[0])) {
                labels2[i].setVisible(false);
                leftSideLabel.setVisible(true);
                middleSideLabel.setVisible(true);
                rightSideLabel.setVisible(true);
            }
            if(e.getSource().equals(labels2[1])) {
                orderPageFrame.dispose();
                new PreviousOrders();
            }
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
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if(e.getSource().equals(labels[i])) {
                labels[i].setBackground(new Color(184, 229, 154)); //new Color(46, 149, 169, 255) color before entered
            }
        }
        MyTransparentLabel[] labels2= new MyTransparentLabel[] {sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if(e.getSource().equals(labels2[i])) {
                labels2[i].setBackground(new Color(184, 229, 154)); //new Color(46, 149, 169, 255) color before entered
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if(e.getSource().equals(labels[i])) {
                labels[i].setBackground(new Color(46, 149, 169, 255));
            }
        }
        MyTransparentLabel[] labels2= new MyTransparentLabel[] {sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if(e.getSource().equals(labels2[i])) {
                labels2[i].setBackground(new Color(46, 149, 169, 255));
            }
        }
    }
} //End OrderePageSelectItem class

class OrderPage_StoreDocuments implements ActionListener, MouseListener {
    MyFrame storeDocumentsFrame= new MyFrame("Document Solutions Store Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

// componentes #1
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea= new MyTextArea(20, 40, 200, 160);

// componentes #2
    MyTransparentTextLabel leftSLabel2Text= new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF= new MyTextField(40, 70, 160, 40);

// componentes #3
    MyTransparentLabel helpLabel1= new MyTransparentLabel(40, 40, 160, 70);
    MyTransparentTextLabel leftSLabel1Text= new MyTransparentTextLabel("Daudzums kastēs *", 40, 0, 240, 50);
    MyLabel helpTextLabel1= new MyLabel("VADOTIES PĒC TĀ, KA", 5, 10, 160, 15, 12);
    MyLabel helpTextLabel2= new MyLabel("VIENĀ KASTĒ SALIEN", 5, 30, 160, 15, 12);
    MyLabel helpTextLabel3= new MyLabel("5 REĢISTRA MAPES", 5, 50, 160, 15, 12);
    MyTextField boxTextF= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText= new MyTransparentTextLabel("(- kastes)", 150, 160, 100, 40);

// componentes #4
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2= new MyTextArea(20, 40, 200, 160);

// componentes #5
    MyTransparentTextLabel middleSLabel1Text= new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn= new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn= new MyButton("NAV", 130, 40, 90, 50);
    MyTextField boxTextF2= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- stāvi)", 150, 160, 100, 40);

// componentes #6
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);
    OrderPage_StoreDocuments() {
// componentes ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
// ############################################################### #1

// componentes ################################################### #2
        workingTimeTextF.setText("09:00 - 17:00");
        workingTimeTextF.setEditable(false);
        workingTimeTextF.setForeground(Color.GRAY);
        workingTimeTextF.addMouseListener(this);

        label_2.add(workingTimeTextF);
        label_2.add(leftSLabel2Text);
// ############################################################### #2

// componentes #######§########################################### #3
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
        label_3.add(boxSideText);
        label_3.add(boxTextF);
        label_3.add(leftSLabel1Text);
        label_3.add(helpLabel1);
// ############################################################### #3

// componentes ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
// ############################################################### #4

// componentes ################################################### #5
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

// componentes ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
// ############################################################### #6
// All labels added to storeDocumentsFrame
        MyTransparentLabel[] labels= new MyTransparentLabel[] {label_3, label_5, label_1, label_2, label_4, label_6};
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
            new PreviousOrders();
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
} //End class OrderPage_StoreDocuments

class OrderPage_ShreddingDocuments implements ActionListener, MouseListener {
    MyFrame shredDocumentsFrame= new MyFrame("Document Solutions Shredding Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5_0 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

// componentes #1
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea= new MyTextArea(20, 40, 200, 160);

// componentes #2
    MyTransparentTextLabel needTransportText= new MyTransparentTextLabel("Nepieciešama aizvešana?", 20, 0, 240, 50);
    MyButton yesBtn1= new MyButton("JĀ!", 20, 40, 90, 50);
    MyButton noBtn1= new MyButton("NĒ!", 130, 40, 90, 50);

// componentes #3
    MyTransparentTextLabel leftSLabel1Text= new MyTransparentTextLabel("Aptuvenais svars", 40, 0, 240, 50);
    MyTextField boxTextF= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText= new MyTransparentTextLabel("KG", 150, 160, 100, 40);

// componentes #4
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2= new MyTextArea(20, 40, 200, 160);

// componentes #5
    MyTransparentTextLabel middleSLabel1Text= new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn= new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn= new MyButton("NAV", 130, 40, 90, 50);
    MyTextField boxTextF2= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- stāvi)", 150, 160, 100, 40);

// componentes #6
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);


    OrderPage_ShreddingDocuments() {
// componentes ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
// ############################################################### #1

// componentes ################################################### #2
        yesBtn1.addActionListener(this);
        noBtn1.addActionListener(this);

        label_2.add(noBtn1);
        label_2.add(yesBtn1);
        label_2.add(needTransportText);
// ############################################################### #2

// componentes ################################################### #3
// leftSLabel1Text add Mouse Listiner
        leftSLabel1Text.addMouseListener(this);
// leftSideLabel1 setUp
        label_3.add(boxSideText);
        label_3.add(boxTextF);
        label_3.add(leftSLabel1Text);
// ############################################################### #3

// componentes ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
// ############################################################### #4

// componentes ################################################### #5
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

// componentes ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
// ############################################################### #6

        MyTransparentLabel[] labels= new MyTransparentLabel[] {label_1, label_2, label_3, label_4, label_5_0, label_5, label_6};
        for (int i = 0; i < labels.length; i++) {
            shredDocumentsFrame.add(labels[i]);
        }

        shredDocumentsFrame.setLayout(null);
        shredDocumentsFrame.setVisible(true);
    } //End OrderPage_ShreddingDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(nextBtn)) {
            // šeit ir nepieciešams kods, kas aptver visu ievadīto info un nogādā tālāk
            shredDocumentsFrame.dispose();
            new PreviousOrders();
        }
        if(e.getSource().equals(yesBtn1)) {
            yesBtn1.setBackground(new Color(141, 210, 93));
            noBtn1.setBackground(new Color(184, 229, 154));
            label_5_0.setVisible(false);
            label_5.setVisible(true);
        }
        if(e.getSource().equals(noBtn1)) {
            yesBtn1.setBackground(new Color(184, 229, 154));
            noBtn1.setBackground(new Color(141, 210, 93));
            label_5.setVisible(false);
            label_5_0.setVisible(true);
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

class OrderPage_ArchiveDocuments implements ActionListener, MouseListener {
    MyFrame archiveDocumentsFrame= new MyFrame("Document Solutions Archive Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

// componentes #1
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea= new MyTextArea(20, 40, 200, 160);

// componentes #2
    MyTransparentTextLabel leftSLabel2Text= new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF= new MyTextField(40, 70, 160, 40);

// componentes #3
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Telpas izmēri", 50, 0, 240, 50);
    MyTextField boxTextF2= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- m2)", 150, 160, 100, 40);

// componentes #4
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2= new MyTextArea(20, 40, 200, 160);

// componentes #6
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);
    OrderPage_ArchiveDocuments() {

// componentes ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
// ############################################################### #1

// componentes ################################################### #2
        workingTimeTextF.setText("09:00 - 17:00");
        workingTimeTextF.setEditable(false);
        workingTimeTextF.setForeground(Color.GRAY);
        workingTimeTextF.addMouseListener(this);

        label_2.add(workingTimeTextF);
        label_2.add(leftSLabel2Text);
// ############################################################### #2

// componentes ################################################### #5
        label_3.add(boxTextF2);
        label_3.add(boxSideText2);
        label_3.add(middleSLabel1Text2);
// ############################################################### #5

// componentes ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
// ############################################################### #4

// componentes ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
// ############################################################### #6


        MyTransparentLabel[] labels= new MyTransparentLabel[] {label_3, label_5, label_1, label_2, label_4, label_6};
        for (int i = 0; i < labels.length; i++) {
            archiveDocumentsFrame.add(labels[i]);
        }
        archiveDocumentsFrame.setLayout(null);
        archiveDocumentsFrame.setVisible(true);
    } //End OrderPage_ArchiveDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(nextBtn)) {
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

class PreviousOrders implements ActionListener, MouseListener {
    MyFrame previousOrdersFrame= new MyFrame("Document Solutions Previous Orders Page");
    PreviousOrders() {

        previousOrdersFrame.setLayout(null);
        previousOrdersFrame.setVisible(true);
    } //End PreviousOrders()

    @Override
    public void actionPerformed(ActionEvent e) {

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
} //End class PreviousOrders

class AdministratorPage implements ActionListener {
    String[] clients= {"ID=3 #001 G", "ID=1 #002 G", "ID=3 #003 S", "ID=3 #004 A", "ID=1 #005 S", "ID=2 #006 G", "ID=4 #007 A", };

    MyFrame adminPageFrame= new MyFrame("Administrator Page");
    MyTransparentLabel mainLabel= new MyTransparentLabel(65, 100, 770, 500);
    //MyComboBox comboBox= new MyComboBox(clients, 50, 50, 150, 50);

    JComboBox comboBox;
    AdministratorPage() {

        comboBox= new JComboBox(clients);
        comboBox.addActionListener(this);
        comboBox.setEditable(true);
        comboBox.setBounds(50, 50, 150, 50);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        mainLabel.add(comboBox);

        adminPageFrame.add(mainLabel);
        adminPageFrame.setLayout(null);
        adminPageFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== comboBox) {
            String ievade = (String) comboBox.getSelectedItem(); // var salīdzināt pēc nosaukuma
            int indexSelected = comboBox.getSelectedIndex(); // var salīdzināt pēc indexa
            System.out.println("Lietotāja ievade: " + comboBox.getSelectedItem());
        }
    }
}
