package calc.master.util;

public class Util {

    public static boolean verificaOperador(String calculo){

        if(calculo.contains("+") ||
                calculo.contains("-") ||
                calculo.contains("*") ||
                calculo.contains("/") ||
                calculo.contains("√") ||
                calculo.contains("%")){
            return true;
        }

        return false;
    }

    public static String getOperador(String calculo){
        if (calculo.contains("+")) {
            return "+";
        }
        if (calculo.contains("-")) {
            return "-";
        }
        if (calculo.contains("*")) {
            return "*";
        }
        if (calculo.contains("/")) {
            return "/";
        }
        if (calculo.contains("√")) {
            return "√";
        }
        if (calculo.contains("%")) {
            return "%";
        }
        return null;
    }

    public static String ponto2virguta(String s){
        return s.replaceAll(".", ",");
    }
}

