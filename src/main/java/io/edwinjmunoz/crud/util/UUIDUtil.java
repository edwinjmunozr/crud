package io.edwinjmunoz.crud.util;

import java.util.UUID;

public final class UUIDUtil {

    private UUIDUtil(){}

    public static String newUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
