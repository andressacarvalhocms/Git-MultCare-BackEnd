package br.edu.ufersa.multcare.util;

import java.text.Normalizer;

public class StringUtil {

    public static String substituirEspacosPorUnderScore(String str) {
        return str.replace(" ", "_");
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
