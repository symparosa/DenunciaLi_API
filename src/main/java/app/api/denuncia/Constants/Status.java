package app.api.denuncia.Constants;

public class Status {
    
    private final int Ativo = 1;
    private final int Inativo = 0;
    private final int Eliminado = -1;

    public Status() {
    }
    
    public int getAtivo() {
        return Ativo;
    }
    public int getInativo() {
        return Inativo;
    }
    public int getEliminado() {
        return Eliminado;
    }
}
