package br.com.livraria.bean;

import br.com.livraria.dao.DAOUser;
import br.com.livraria.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;

@ManagedBean
@ViewScoped
public class UserBean {
    private User user;

    public UserBean() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public String login() {
        boolean isExist = new DAOUser(User.class).isExists(this.user);
        if (isExist) {
            return "livro?faces-redirect=true";
        } else {
            throw new ValidatorException(new FacesMessage("Usuario ou senha invalido"));
        }


    }
}
