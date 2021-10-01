package br.com.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateDAO {
    private static final String PERSISTENCE = "livraria";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);

    protected static EntityManager getManager() {
        return factory.createEntityManager();
    }

}
