/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apkastatystyka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class ApkaStatystykaTest {
    
    public ApkaStatystykaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of punktowy method, of class ApkaStatystyka.
     */
    @Test
    public void testPunktowy() {
        System.out.println("punktowy");
        ApkaStatystyka instance = new ApkaStatystyka();
        instance.punktowy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ApkaStatystyka.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        ApkaStatystyka.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
