import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class bigdecimalMain {


    private static final int NUMBER_DECIMAL_PLACES = 4;

    public static void main(String[] args) {


        BigDecimal totalDiscounts =  new BigDecimal("17800.0000");
        BigDecimal valueDiscount = new BigDecimal("14000.0000");

        BigDecimal total = new BigDecimal("41600.0000");



        BigDecimal calculateDiscount = totalDiscounts
                .multiply(valueDiscount)
                .divide(total, NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN);

        BigDecimal calculateDiscountForUnit =
                calculateDiscount.divide(BigDecimal.valueOf(2),
                                NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN)
                        .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.CEILING);

        System.out.println(calculateDiscount);

        System.out.println(calculateDiscountForUnit.multiply(new BigDecimal("2.0")));
    }
}
