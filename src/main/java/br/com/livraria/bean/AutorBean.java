package br.com.livraria.bean;

import br.com.livraria.dao.DAO;
import br.com.livraria.entity.Autor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class AutorBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Autor autor;
    private Integer autorId;

    public AutorBean() {
        this.autor = new Autor();
    }

    public AutorBean(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public String gravar() {

        if (this.autor.getId() == null) {
            new DAO<Autor>(Autor.class).save(this.autor);
        } else {
            new DAO<Autor>(Autor.class).update(this.autor);
        }

        this.autor = new Autor();
        return "livro?faces-redirect=true";
    }

    public List<Autor> getAllAutores() {
        return new DAO<Autor>(Autor.class).getAll();
    }

    public void carregar(Autor autor){
        this.autor = autor;
    }

    public void removeAutor(Autor autor) {
        new DAO<Autor>(Autor.class).remove(autor);
    }

    public void carregarAutorId() {
        this.autor = new DAO<Autor>(Autor.class).getById(this.autorId);
    }
}
