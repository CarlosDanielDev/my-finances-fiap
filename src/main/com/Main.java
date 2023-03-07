import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.myfinances.model.Users;

import com.myfinances.model.Account;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_finances");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Users newUser = new Users();
            newUser.setName("João");
            newUser.setEmail("joao@example.com");
            newUser.setPassword("123456");
            em.persist(newUser);

            TypedQuery<Users> query = em.createQuery("SELECT u FROM users u", Users.class);
            List<Users> userList = query.getResultList();
            for (Users user : userList) {
                System.out.println(user);
            }

            Users userToUpdate = em.find(Users.class, 1L);
            userToUpdate.setEmail("joao.silva@example.com");

            Users userToRemove = em.find(Users.class, 2L);
            em.remove(userToRemove);


            Account newAccount = new Account();
            newAccount.setName("Conta corrente");
            newAccount.setBalance(1000.0);
            newAccount.setType("Corrente");


            newAccount.setUser(newUser);

            em.persist(newAccount);

            TypedQuery<Account> query = em.createQuery("SELECT a FROM account a", Account.class);
            List<Account> accountList = query.getResultList();

            for (Account account : accountList) {
                System.out.println(account);
            }

            Account accountToUpdate = em.find(Account.class, 1L);
            accountToUpdate.setName("Conta poupança");
            accountToUpdate.setBalance(2000.0);

            Account accountToRemove = em.find(Account.class, 2L);
            em.remove(accountToRemove);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

