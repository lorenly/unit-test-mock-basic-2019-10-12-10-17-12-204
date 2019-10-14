package posmachine;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);
        String productName = "Coke";
        //when
        //then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            posMachine.getReceipt(productName);
        });
    }

    @Test
    public void should_get_receipt_using_sub_price_calculator() {
        //given
        PriceCalculator priceCalculator = new StubPriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);
        String productName = "Coke";
        //when
        String receipt =  posMachine.getReceipt(productName);
        //then
        Assertions.assertEquals(receipt, "Name: Coke, Price: 10.0");
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        PriceCalculator priceCalculator = Mockito.mock(PriceCalculator.class);
        PosMachine posMachine = new PosMachine(priceCalculator);
        String productName = "Coke";
        //when
        when(priceCalculator.calculate(productName)).thenReturn(10.0);
        String receipt = posMachine.getReceipt(productName);
        //then
        Assertions.assertEquals(receipt, "Name: Coke, Price: 10.0");
    }

    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 10;
        }
    }
}
