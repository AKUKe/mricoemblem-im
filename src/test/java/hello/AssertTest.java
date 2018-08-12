package hello;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class AssertTest {

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        try {
            testAssert(a);
        } catch (Exception e) {
            System.out.println(e.getMessage()+"asd");
        }
        System.out.println("AssertTest.main");
    }

    private static void testAssert(List<String> a){
        try {
            Assert.notEmpty(a, "No transports provided");
        } catch (Exception e) {
            throw e;
        }
    }
}
