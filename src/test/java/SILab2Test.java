import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SILab2Test {
    private List<Item> createItems(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }
    @Test
    void everyBranchTest(){
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart( createItems(new Item("Test1", "269a", 50, 0)),100));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        assertTrue(SILab2.checkCart( createItems(new Item(null, "269003", 100, 0)),120));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart( createItems(new Item("Test2", null, 100, 0)),100));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart( createItems(new Item("Test3", "0269", 350, 10)),150));
        assertFalse(SILab2.checkCart( createItems(new Item("Test3", "0269", 350, 10)),150));

    }
    @Test
    void MultipleConditionTest(){
        RuntimeException ex;

        //T, T, T
        Item TTT = new Item("Test1", "0123", 350, 2);
        assertFalse(SILab2.checkCart(createItems(TTT), 150));

        //T, T, F
        Item TTF = new Item("Test2", "123", 350, 2);
        assertFalse(SILab2.checkCart(createItems(TTF), 150));

        //T, F, X
        Item TFX = new Item("Test3", "273", 350, -1);
        assertFalse(SILab2.checkCart(createItems(TFX), 150));

        //F, X, X
        Item FXX = new Item("Test4", "273", 100, 0);
        assertFalse(SILab2.checkCart(createItems(FXX), 50));
    }


}