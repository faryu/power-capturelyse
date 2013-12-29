package client;

import java.math.BigDecimal;
import java.util.List;


/**
 * 
 * OutputHelper Klasse uebernommen aus dem Buch "Java Persistence API_2 Hibernate"
 *
 */
public class OutputHelper {

    public static void output(Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof List) {
            List<?> l = (List<?>) o;
            outputList(l);
        }
    }

    public static void outputList(List<?> l) {
        System.out.println("==========================");
        for (Object object : l) {
            if (object instanceof Object[]) {
                Object[] arr = (Object[]) object;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] instanceof BigDecimal) {
                        System.out.print(" " + (BigDecimal) arr[i]);
                    } else {
                        System.out.print(" "+arr[i].toString());
                    }
                }
            } else {
                System.out.print(object);
            }
            System.out.println("");
        }
        System.out.println("==========================");
    }

}
