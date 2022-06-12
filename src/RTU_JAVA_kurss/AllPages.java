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
    MyTransparentTextLabel registeredUserLabel= new MyTransparentTextLabel("Esmu reģistrēts lietotājs", 60, 60, 250, 50);
    MyTransparentTextLabel notRegisteredUserLabel= new MyTransparentTextLabel("Jums nav lietotāja profila?", 60, 60, 250, 50);
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
        }
        if(e.getSource().equals(fakePasswordField)) {
            fakePasswordField.setVisible(false);
            passwordField.setVisible(true);
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
    MyTransparentLabel leftSideLabel= new MyTransparentLabel(65, 100, 240, 500);
    MyTransparentLabel middleSideLabel= new MyTransparentLabel(330, 100, 240, 500);
    MyTransparentLabel rightSideLabel= new MyTransparentLabel(600, 100, 240, 500);
    MyTransparentTextLabel leftSideTextLabel= new MyTransparentTextLabel("Dokumentu glabāšana", 30, 20, 240, 100);
    MyTransparentTextLabel middleSideTextLabel= new MyTransparentTextLabel("Smalcināšana / iznīcināšana", 10, 20, 240, 100);
    MyTransparentTextLabel rightSideTextLabel= new MyTransparentTextLabel("Arhīva sakārtošana", 40, 20, 240, 100);

    OrderePageSelectItem() {

        leftSideLabel.add(leftSideTextLabel);
        middleSideLabel.add(middleSideTextLabel);
        rightSideLabel.add(rightSideTextLabel);

        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            orderPageFrame.add(labels[i]);
            labels[i].addMouseListener(this);
        }

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
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if(e.getSource().equals(labels[i])) {
                labels[i].setBackground(new Color(46, 149, 169, 255));
            }
        }
    }
} //End OrderePageSelectItem class

class OrderPage_StoreDocuments implements ActionListener, MouseListener {
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
    MyTransparentTextLabel middleSLabel1Text= new MyTransparentTextLabel("Stāvs / Pgrabs", 60, 0, 240, 50);
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

class OrderPage_ShreddingDocuments implements ActionListener {
    MyFrame shredDocumentsFrame= new MyFrame("Document Solutions Shredding Documents Page");
    OrderPage_ShreddingDocuments() {
        shredDocumentsFrame.setLayout(null);
        shredDocumentsFrame.setVisible(true);
    } //End OrderPage_ShreddingDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {

    }
} //End class OrderPage_ShreddingDocuments

class OrderPage_ArchiveDocuments implements ActionListener {
    MyFrame archiveDocumentsFrame= new MyFrame("Document Solutions Archive Documents Page");
    OrderPage_ArchiveDocuments() {
        archiveDocumentsFrame.setLayout(null);
        archiveDocumentsFrame.setVisible(true);
    } //End OrderPage_ArchiveDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {

    }
} //End class OrderPage_ArchiveDocuments


