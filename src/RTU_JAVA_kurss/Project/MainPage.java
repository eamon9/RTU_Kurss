package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.Extensions.MyButton;
import RTU_JAVA_kurss.Extensions.MyFrame;
import RTU_JAVA_kurss.Extensions.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage implements ActionListener {
    // zemāk deklarējam un inicializējam JFrame, JLabel, JButton utt, lai būtu pieejami arī ārpus MainPage()
    MyFrame mainPageFrame = new MyFrame("Document Solutions Main Page");
    JLabel titleLabel= new JLabel("Document Solutions");
    MyLabel subTitleLabel1 = new MyLabel("Mēs GLABĀJAM", 250, 270, 250, 50, 20);
    MyLabel subTitleLabel2 = new MyLabel("SMALCINĀM,", 400, 290, 250, 50, 20);
    MyLabel subTitleLabel3 = new MyLabel("IZNĪCINĀM", 520, 310, 250, 50, 20);
    MyButton startBtn= new MyButton("Sākt", 350, 450, 200, 50);

    public MainPage() {
        titleLabel.setForeground(new Color(11, 87, 3));
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
        titleLabel.setBounds(200, 100, 600, 300);

        startBtn.addActionListener(this);

        MyLabel[] subLabels= new MyLabel[] {subTitleLabel1, subTitleLabel2, subTitleLabel3};
        for (MyLabel subLabel : subLabels) {
            mainPageFrame.add(subLabel);
        }
        mainPageFrame.add(startBtn);
        mainPageFrame.add(titleLabel);
        mainPageFrame.setLayout(null);
        mainPageFrame.setVisible(true); // padara rāmi redzamu
    } //End MainPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startBtn)) {
            startBtn.setBackground(new Color(141, 210, 93));
            mainPageFrame.dispose();
            new LoginPage();
        } //End if else statement
    } //End actionPerformed()
} //End MainPage class
