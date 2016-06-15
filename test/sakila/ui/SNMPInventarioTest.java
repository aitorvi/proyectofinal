/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sakila.ui;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snmp.util.NewHibernateUtil;

/**
 *
 * @author Aitor
 */
public class SNMPInventarioTest {
    
    public SNMPInventarioTest() {
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
    
    @Test
    
    public void testDelete() {
        System.out.println("delete");
         String ip1 = "1.0.0.1";
        SNMPInventario instance = new SNMPInventario();
        instance.delete(ip1);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        String hql="from Dispositivos";
            Query q = session.createQuery(hql);
            List resultList = q.list();
            int result=resultList.size();
            int expResult=4;
                
            session.getTransaction().commit();
        
       
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    /**
     * Test of insert method, of class SNMPInventario.
     * Not existing row
     */
 @Test
    // mirar lineas que tiene la tabla para verificar si esta bien
    public void testDelete2() {
        System.out.println("delete");
        String ip1 = "test";
        SNMPInventario instance = new SNMPInventario();
        instance.delete(ip1);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        String hql="from Dispositivos";
            Query q = session.createQuery(hql);
            List resultList = q.list();
            int result=resultList.size();
            int expResult=3;
            
            session.getTransaction().commit();
        
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    /**
     * Test of delete method, of class SNMPInventario.
     * Existing row
     */
     @Test
    // mirar lineas que tiene la tabla para verificar si esta bien
    public void testInsert() {
        System.out.println("delete");
                SNMPInventario instance = new SNMPInventario();
        instance.insert("test","10.5.5.50","test");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        String hql="from Dispositivos";
            Query q = session.createQuery(hql);
            List resultList = q.list();
            int result=resultList.size();
            int expResult=4;
            
            session.getTransaction().commit();
        
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    /**
     * Test of insert method, of class SNMPInventario.
     * 
     */    
    }
   


    
}
