/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

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
    
    private Gain gain = null;
    private int nbre_ligne_avant = 0;
    public GainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("manomboka ny test rehetra!");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("tapitra ny test rehetra!");
    }
    
    @Before
    public void setUp() throws Exception {
        gain = new Gain(4,4,10000);
        nbre_ligne_avant =  (int)Gain.getLastIndex()-1;
        System.out.println("nombre ligne avant : "+nbre_ligne_avant);
    }
    
    @After
    public void tearDown() throws Exception {
        boolean res = Gain.deleteGainById(gain.getId_gain());
        if(res) {
            gain = null;
        }
        System.out.println("nombre ligne apres : "+nbre_ligne_avant);

    }

    /**
     * Test of getId_gain method, of class Gain.
     */
    /*
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
    /*
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
    /*
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
    /*
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
    /*
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
    /*
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
        System.out.println("test de la méthode setGain");
        Gain instance = gain;
        boolean expResult = true;
        boolean result = instance.setGain();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setGain method, of class Gain.
     */
    /*
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
    /*
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
    /*
    @Test
    public void testGetLastIndex() throws Exception {
        System.out.println("getLastIndex");
        long expResult = 0L;
        long result = Gain.getLastIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
}
