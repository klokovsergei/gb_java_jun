package seminar1;

/**
 * Шоколадка
 */
public class ChocolateBar implements Shack{
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
