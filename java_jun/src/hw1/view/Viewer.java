package hw1.view;

import hw1.Cart;
import hw1.UMarket;
import hw1.common.interfaces.*;

import java.util.Scanner;

public class Viewer {
    private final static Scanner scanner = new Scanner(System.in);

    public static void start() {

        UMarket market = new UMarket();
        System.out.println("Добро пожаловать в магазин U-Market");

        while (true) {
            System.out.println("========================================================================");
            System.out.println("0 - Завершение работы приложения");
            System.out.println("1 - Отобразить полный список товаров");
            System.out.println("2 - Сформировать онлайн корзину из снеков");
            System.out.println("3 - Сформировать онлайн корзину из полуфабрикатов");
            System.out.println("4 - Сформировать онлайн корзину из продуктов для приготовления");
            System.out.println("5 - Сформировать онлайн корзину из любых продовольственных товаров");
            System.out.println("Выберите пункт меню: ");

            if (scanner.hasNextInt()) {
                int no = scanner.nextInt();
                scanner.nextLine();

                switch (no) {
                    case 0 -> {
                        System.out.println("Завершение работы приложения.");
                        System.exit(0);
                    }
                    case 1 -> {
                        System.out.println("Список товаров:");
                        market.printThings(Thing.class);
                    }
                    case 2 -> createCart(Snack.class, market);
                    case 3 -> createCart(SemiFinishedFood.class, market);
                    case 4 -> createCart(HealthyFood.class, market);
                    case 5 -> createCart(Food.class, market);
                    default -> System.out.println("Пункт меню не существует.\nПожалуйста, повторите попытку ввода.");
                }

            } else {
                System.out.println("Некорректный пункт меню.\nПожалуйста, повторите попытку ввода.");
                scanner.nextLine();
            }
        }

    }
    private static <T extends Food> void createCart(Class<T> clazz, UMarket market) {
        Cart<T> cart = new Cart<>(clazz, market);

        while (true) {
            System.out.println("Список доступных товаров:");
            System.out.println("[0] Завершение формирования корзины + балансировка.");
            market.printThings(clazz);
            System.out.println("Укажите номер товара для добавления: ");
            if (scanner.hasNextInt()) {
                int no = scanner.nextInt();
                scanner.nextLine();
                if (no == 0) {
                    cart.cartBalancing(clazz);
                    System.out.println("\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2");
                    System.out.println("Ваша корзина содержит продукты:");
                    cart.printFoodstuffs();
                    System.out.println("\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2");
                    return;
                } else {
                    T thing = market.getThingsByIndex(clazz, no);
                    if (thing == null) {
                        System.out.println("Некорректный номер товара.\nПожалуйста, повторите попытку ввода.");
                        continue;
                    }
                    cart.getFoodstuffs().add(thing);
                    System.out.println("Товар успешно добавлен в корзину.");
                    System.out.println("\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2");
                    System.out.println("Ваша корзина содержит продукты:");
                    cart.printFoodstuffs();
                    System.out.println("\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2");
                }
            } else {
                System.out.println("Некорректный пункт меню.\nПожалуйста, повторите попытку ввода.");
                scanner.nextLine();
            }
        }
    }
}
