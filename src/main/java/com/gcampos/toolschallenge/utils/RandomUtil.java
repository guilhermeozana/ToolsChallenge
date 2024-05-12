package com.gcampos.toolschallenge.utils;

import java.util.UUID;

public class RandomUtil {

    //Simula IDs gerados pela base de dados da operadora do cartão

    public static String gerarIdNumeros(int tamanho){
        UUID uuid = UUID.randomUUID();

        String uuidString = uuid.toString().replace("-", "");

        StringBuilder id = new StringBuilder();

        for (char c : uuidString.toCharArray()) {
            if (Character.isDigit(c)) {
                id.append(c);
            }
        }

        return id.substring(0, Math.min(id.length(), tamanho));

    }
}
