package com.fourchet.recipe;

import java.util.Arrays;
import java.util.List;

public enum TypeOfRecipe {
    BREAKFAST,
    LUNCH,
    DINNER,
    DESSERT,
    SNACK,
    DRINK;

    public static TypeOfRecipe getType(String type) {
        switch (type) {
            case "BREAKFAST":
                return BREAKFAST;
            case "LUNCH":
                return LUNCH;
            case "DINNER":
                return DINNER;
            case "DESSERT":
                return DESSERT;
            case "SNACK":
                return SNACK;
            case "DRINK":
                return DRINK;
            default:
                return null;
        }
    }

    public static List<String> getAllType() {
        return Arrays.asList("BREAKFAST", "LUNCH", "DINNER", "DESSERT", "SNACK", "DRINK");
    }
}
