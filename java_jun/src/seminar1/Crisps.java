package seminar1;

/**
 * Чипсы
 */
public class Crisps implements Shack{
    @Override
    public boolean getProteins() {
        return false;
    }
    @Override
    public boolean getFats() {
        return true;
    }
    @Override
    public boolean getCarbohydrates() {
        return false;
    }
    @Override
    public String getName() {
        return "Чипсы";
    }
}
