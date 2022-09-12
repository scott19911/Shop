package server.http;

import com.epam.verizhenko_andrii.electronicsStore.controller.Demo;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.server.http.GetCountCommand;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class GetCountCommandTest {
    public static MockedStatic<Demo> mocked;
    @Test
    public void shouldReturn_Count5_whenProductMapSize5(){
        mocked = mockStatic(Demo.class);
        Map<Product,Integer> expected = new HashMap<>();
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        mocked.when(Demo::getProductsMap).thenReturn(expected);
        GetCountCommand getCountCommand = new GetCountCommand();
        assertEquals("{count:5}", getCountCommand.execute());
        mocked.close();
    }
}
