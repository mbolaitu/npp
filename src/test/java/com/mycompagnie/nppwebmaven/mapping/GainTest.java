/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mbola
 */
public class GainTest {
    
    public GainTest() {
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
     * Test of getId_gain method, of class Gain.
     */
    @Test
    public void testGetId_gain() {
        System.out.println("getId_gain");
        Gain instance = null;
        long expResult = 0L;
        long result = instance.getId_gain();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId_gain method, of class Gain.
     */
    @Test
    public void testSetId_gain() {
        System.out.println("setId_gain");
        long id_gain = 0L;
        Gain instance = null;
        instance.setId_gain(id_gain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId_utilisateur method, of class Gain.
     */
    @Test
    public void testGetId_utilisateur() {
        System.out.println("getId_utilisateur");
        Gain instance = null;
        long expResult = 0L;
        long result = instance.getId_utilisateur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId_utilisateur method, of class Gain.
     */
    @Test
    public void testSetId_utilisateur() {
        System.out.println("setId_utilisateur");
        long id_utilisateur = 0L;
        Gain instance = null;
        instance.setId_utilisateur(id_utilisateur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGain method, of class Gain.
     */
    @Test
    public void testGetGain() {
        System.out.println("getGain");
        Gain instance = null;
        int expResult = 0;
        int result = instance.getGain();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGain method, of class Gain.
     */
    @Test
    public void testSetGain_int() {
        System.out.println("setGain");
        int gain = 0;
        Gain instance = null;
        instance.setGain(gain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGain method, of class Gain.
     */
    @Test
    public void testSetGain_0args() throws Exception {
        System.out.println("setGain");
        Gain instance = null;
        boolean expResult = false;
        boolean result = instance.setGain();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGain method, of class Gain.
     */
    @Test
    public void testSetGain_Connection() throws Exception {
        System.out.println("setGain");
        Connection c = null;
        Gain instance = null;
        boolean expResult = false;
        boolean result = instance.setGain(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGainById method, of class Gain.
     */
    @Test
    public void testGetGainById() throws Exception {
        System.out.println("getGainById");
        long id = 0L;
        Gain expResult = null;
        Gain result = Gain.getGainById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastIndex method, of class Gain.
     */
    @Test
    public void testGetLastIndex() throws Exception {
        System.out.println("getLastIndex");
        long expResult = 0L;
        long result = Gain.getLastIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
