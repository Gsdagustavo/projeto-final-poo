package com.gsdagustavo.view;

import com.gsdagustavo.model.system.GiroBluSystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class RegistrationUi extends JFrame {
    private GiroBluSystem system;

    private JButton btnBack = new JButton("Voltar");

    public RegistrationUi(GiroBluSystem system) {
        super("Cadastros");
        this.system = system;

        setLayout(new FlowLayout());
        add(btnBack);

        btnBack.addActionListener(e -> dispose());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
    }
}
