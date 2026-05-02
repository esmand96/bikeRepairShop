package bikerepairshop.util;

import java.util.UUID;

public final class Util {
    public static String generateRandomId (){
        return UUID.randomUUID().toString();
    }

}
