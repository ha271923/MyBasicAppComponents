package sample.hawk.com.mybasicappcomponents;

import org.junit.Test;

import sample.hawk.com.mybasicappcomponents.misc.MyClassForUnitTest;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 * <b href="https://developer.android.com/training/testing/unit-testing/local-unit-tests.html">Building Local Unit Tests</b>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void check_output_str() throws Exception {
        MyClassForUnitTest t1 = new MyClassForUnitTest();
        assertEquals( "BaaaaaaaaaaaaaaaaaaaD string",t1.validate_output_string());
    }

}