package seminar1.common;

import seminar1.common.interfaces.Thing;

/**
 *  Блокнот
 */
public class Notebook implements Thing {
    @Override
    public String getName() {
        return "Блокнот";
    }
}
