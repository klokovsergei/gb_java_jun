package hw1.common;

import hw1.common.interfaces.Snack;

/**
 * Шоколадка
 */
public class ChocolateBar implements Snack {
    @Override
    public boolean getProteins() {
        return false;
    }
    @Override
    public boolean getFats() {
        return false;
    }
    @Override
    public boolean getCarbohydrates() {
        return true;
    }
    @Override
    public String getName() {
        return "Шоколадный батончик";
    }
}
