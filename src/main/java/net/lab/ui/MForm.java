package net.lab.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MForm extends JFrame {
    private JPanel top,bot;
    private JMenuBar mbar;

public MForm(){
    Init();InitPanels();InitMenu();
}

    public void Init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);

        setLayout(new BorderLayout());
        setBounds(10,10,800,600);
        setPreferredSize(new Dimension(800, 600));
        setTitle("Справочники");
        setVisible(true);

    }

    public void InitPanels(){
        //top = new JPanel();
        //top.setBackground(Color.black);

        bot = new JPanel();
        bot.setLayout(new BorderLayout());
        bot.setFocusable(false);

        //add(top,BorderLayout.NORTH);
        add(bot,BorderLayout.SOUTH);
    }

    public void InitMenu() {
        mbar = new JMenuBar();
        JMenu menu = new JMenu("Данные");
        JMenu menuopt = new JMenu("Настройки");
        JMenu menuhelp = new JMenu("Помощь");

        JMenuItem users = new JMenuItem("Пользователи");
        JMenuItem options = new JMenuItem("Прочитать файл настроек");

        JMenuItem help = new JMenuItem("О программе");

        JMenuItem mitem = new JMenuItem("Справочник 'Улицы'");
        JMenuItem mitem1 = new JMenuItem("Выход");
        //***********************************************//
        mitem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //***********************************************//
        mitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tabs tab = Tabs.getInstance();
                add(tab,BorderLayout.CENTER);
                tab.NewTab("Улицы  ", 0);
            }
        });
        //***********************************************//
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tabs tab = Tabs.getInstance();
                add(tab,BorderLayout.CENTER);
                tab.NewTab("Настройки  ", 1);
            }
        });
        //***********************************************//
        menu.add(mitem);
        menu.add(mitem1);
        menuopt.add(users);
        menuopt.add(options);
        menuhelp.add(help);
        mbar.add(menu);
        mbar.add(menuopt);
        mbar.add(menuhelp);

        setJMenuBar(mbar);
    }


    }
