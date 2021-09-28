package br.com.livraria.bean;

import br.com.livraria.dao.DAO;
import br.com.livraria.entity.Autor;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AutorBean {
    private Autor autor;

    public AutorBean() {
        this.autor = new Autor();
    }

    public AutorBean(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public String gravar(){
        new DAO<Autor>(Autor.class).save(this.autor);
        this.autor = new Autor();
        return "livro?faces-redirect=true";
    }
}
