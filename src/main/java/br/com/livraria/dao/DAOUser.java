package br.com.livraria.dao;

import br.com.livraria.entity.User;

import javax.persistence.TypedQuery;

public class DAOUser extends DAO<User> {
    public DAOUser(Class<User> userClass) {
        super(userClass);
    }

    public boolean isExists(User user) {
        this.em = getManager();
        User singleResult = this.em.createQuery("SELECT  U FROM User U " +
                "WHERE U.email = :pEmail AND U.pass = :pPass", User.class)
                .setParameter("pEmail", user.getEmail())
                .setParameter("pPass", user.getPass())
                .getSingleResult();

        this.em.close();
        return singleResult != null;
    }
}
