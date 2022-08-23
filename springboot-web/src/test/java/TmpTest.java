import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: codefans
 * @Date: 2022-06-02 16:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TmpTest {

    @Test
    public void availableProcessorsTest() {

        System.out.println(Runtime.getRuntime().availableProcessors());

    }

}
