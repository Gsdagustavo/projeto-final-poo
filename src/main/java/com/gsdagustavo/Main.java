package com.gsdagustavo;

import com.gsdagustavo.model.system.GiroBluSystem;
import com.gsdagustavo.view.MainUi;

public class Main {
    static void main() {
        GiroBluSystem system = new GiroBluSystem();
        new MainUi(system).setVisible(true);
    }
}
