package com.gsdagustavo.model.entities.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class MoneyUtils {
    public static double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
