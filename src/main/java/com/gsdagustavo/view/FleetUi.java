package com.gsdagustavo.view;

import com.gsdagustavo.model.system.GiroBluSystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class FleetUi extends JFrame {
    private GiroBluSystem system;

    private JButton btnBack = new JButton("Voltar");

    public FleetUi(GiroBluSystem system) {
        super("Frota");
        this.system = system;

        setLayout(new FlowLayout());
        add(btnBack);

        btnBack.addActionListener(e -> dispose());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
    }
}
