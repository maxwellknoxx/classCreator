/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Macbook
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @Test
    public void givenRegexWithPipeEscaped_whenSplitStr_thenSplits() {
        String strInput = "foo|bar|hello|world";
        String strRegex = "\\Q|\\E";

        assertEquals(4, strInput.split(strRegex).length);
    }

}
