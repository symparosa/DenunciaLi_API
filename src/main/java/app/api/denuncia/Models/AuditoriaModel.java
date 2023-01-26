package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dn_t_auditoria")
public class AuditoriaModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int id_objeto;

    @Column(nullable = false)
    private String tipo_objeto;

    @Column(nullable = false)
    private String valor_atual;

    @Column(nullable = false)
    private String valor_novo;

    @Column(nullable = false)
    private int id_utilizador;

    private int estado;

    private Date data_criacao;

    public AuditoriaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_objeto() {
        return id_objeto;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
    }

    public String getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(String valor_atual) {
        this.valor_atual = valor_atual;
    }

    public String getValor_novo() {
        return valor_novo;
    }

    public void setValor_novo(String valor_novo) {
        this.valor_novo = valor_novo;
    }

    public int getId_utilizador() {
        return id_utilizador;
    }

    public void setId_utilizador(int id_utilizador) {
        this.id_utilizador = id_utilizador;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getTipo_objeto() {
        return tipo_objeto;
    }

    public void setTipo_objeto(String tipo_objeto) {
        this.tipo_objeto = tipo_objeto;
    }
}
