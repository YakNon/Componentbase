/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LENOVO
 */
public class EmployeeMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Department dp1 = new Department(1,"IT");
        Department dp2 = new Department(2,"HR");
        persist(dp1);
        persist(dp2);
        Employee emp1 = new Employee(1,"John", "Network Admin", 56789,dp1);
        Employee emp2 = new Employee(2,"Marry", "HR Manager", 46789,dp2);
        Employee emp3 = new Employee(3,"Golf", "Programmer", 67890 ,dp1);
        Employee emp4 = new Employee(4,"Clark", "HR recruiter", 36789,dp2);
        persist(emp1);
        persist(emp2);
        persist(emp3);
        persist(emp4);
        
        
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
