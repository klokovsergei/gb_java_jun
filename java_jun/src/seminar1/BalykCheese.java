package seminar1;

/**
 * Сыр Балыковый (Балык)
 */
public class BalykCheese implements Shack{
    @Override
    public boolean getProteins() {
        return true;
    }
    @Override
    public boolean getFats() {
        return false;
    }
    @Override
    public boolean getCarbohydrates() {
        return false;
    }
    @Override
    public String getName() {
        return "Сыр (Балык)";
    }
}
