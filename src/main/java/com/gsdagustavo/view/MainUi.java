package com.gsdagustavo.view;

import com.gsdagustavo.model.system.GiroBluSystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class MainUi extends JFrame {
    private GiroBluSystem system;

    private JLabel lblTitle = new JLabel("Painel de operação");
    private JButton btnRegistration = new JButton("Cadastros");
    private JButton btnUser = new JButton("Usuário");
    private JButton btnFleet = new JButton("Frota");
    private JButton btnBilling = new JButton("Faturamento");

    public MainUi(GiroBluSystem system) {
        super("GiroBlu");
        this.system = system;

        setLayout(new FlowLayout());
        add(lblTitle);
        add(btnRegistration);
        add(btnUser);
        add(btnFleet);
        add(btnBilling);

        btnRegistration.setEnabled(false);
        btnUser.setEnabled(false);
        btnFleet.setEnabled(false);
        btnBilling.setEnabled(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(180, 200);
    }
}
