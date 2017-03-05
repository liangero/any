import org.springframework.stereotype.Controller;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by avaio on 2016/12/17.
 */
@Controller
public class Test {
    public static void main(String[] args) {
        String [] a = {"1","2","3"};
        List<String> strings = Arrays.asList(a);
        System.out.print(strings);
    }
}
