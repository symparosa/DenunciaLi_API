package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "dn_t_menu")
public class MenuModel implements Serializable{
    
    @Schema(description = "O identificador (ID) do menu")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O código do menu")
    @Column(nullable = false, unique = true)
    private String codigo;

    @Schema(description = "O título do menu")
    @Column(nullable = false)
    private String titulo;

    @Schema(description = "A visibilidade do menu")
    @Column(nullable = false)
    private Integer visibilidade;

    @Schema(description = "O identificador (ID) do menu pai")
    private Integer id_menu_pai;

    @Schema(description = "O icon do menu")
    @Lob
    private String menu_icon;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do menu", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do menu", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do menu", hidden = true)
    private Date data_atualizacao;

    public MenuModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(Integer visibilidade) {
        this.visibilidade = visibilidade;
    }

    public Integer getId_menu_pai() {
        return id_menu_pai;
    }

    public void setId_menu_pai(Integer id_menu_pai) {
        this.id_menu_pai = id_menu_pai;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public Integer getLast_user_change() {
        return last_user_change;
    }

    public void setLast_user_change(Integer last_user_change) {
        this.last_user_change = last_user_change;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Date getData_atualizacao() {
        return data_atualizacao;
    }

    public void setData_atualizacao(Date data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }
}
