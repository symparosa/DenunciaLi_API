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
@Table(name = "dn_t_botao")
public class BotaoModel implements Serializable{
    
    @Schema(description = "O identificador (ID) do botão")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O código do botão")
    @Column(nullable = false)
    private String codigo;

    @Schema(description = "A descrição do botão")
    @Lob
    private String descricao;

    @Schema(description = "O icon do botão")
    @Lob
    private String botao_icon;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do botão", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do botão", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do botão", hidden = true)
    private Date data_atualizacao;

    public BotaoModel() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBotao_icon() {
        return botao_icon;
    }

    public void setBotao_icon(String botao_icon) {
        this.botao_icon = botao_icon;
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
