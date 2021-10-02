package br.com.livraria.bean;

import br.com.livraria.dao.DAO;
import br.com.livraria.entity.Autor;
import br.com.livraria.entity.Livro;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.List;

//@RequestScoped
@ManagedBean
@ViewScoped
public class LivroBean {
    private Livro livro;
    private Integer autorId;

    public LivroBean() {
        this.livro = new Livro();
    }

    public Livro getLivro() {
        return livro;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public void setAutor() {
        Autor autor = new DAO<Autor>(Autor.class).getById(autorId);
        this.livro.addAutor(autor);
    }

    public void comecaComDigitoUm(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new ValidatorException(new FacesMessage("Deveria começa com 1"));
        }
    }

    public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutors();
    }

    public List<Livro> getAllLivros() {
        return new DAO<Livro>(Livro.class).getAll();
    }

    public void save() {
        if (livro.getAutors().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
        }
        if (this.livro.getId() == null) {
            new DAO<Livro>(Livro.class).save(livro);
        }else {
            new DAO<Livro>(Livro.class).update(livro);
        }
        this.livro = new Livro();
    }

    public List<Autor> getAutores() {
        return new DAO<Autor>(Autor.class).getAll();
    }

    public void remove(Livro livro) {
        new DAO<Livro>(Livro.class).remove(livro);
    }

    public void carregar(Livro livro) {
        this.livro = livro;
    }

    public String formAutor() {
        return "autor?faces-redirect=true";
    }

    public void removeAutorDoLivro(Autor autor) {
        this.livro.removeAutor(autor);
    }

    private Livro getLivroId(Livro livro){
        return new DAO<Livro>(Livro.class).getById(livro.getId());
    }

}
