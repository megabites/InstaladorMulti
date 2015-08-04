/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instaladormultimagem;

/**
 *
 * @author Leonardo Bites
 */
public class EquipamentoDTO {
    private String _tipo; //tipo do equipamento (ATM, THINK CLIENT, TOTEN, LOTERICO, ... ) 
    private String _nome; //Nome lógico do equipamento na rede 
    private String _cgcUnidade; //CGC da unidade onte esta o equipamento
    private String _id;//Numero de identificação do Equipamento
    private String _modelo;//Modelo do equipamento (TFL 4020, ATM CD, ... etc)
    private String _tipoRede;//Tipo de rede em DHPC ou IP FIXO
    private String _enderecoIP; //Case de IP fixo endereço de rede 
    private String _gateway; //Case de IP fixo gateway da rede
    private String _mascaraRede; //Case de IP fixo mascara da rede
    private String _discoLogico; //nome do HD
    private String _tamanhoDisco; //Tamanho do HD

    public String getDiscoLogico() {
        return _discoLogico;
    }

    public void setDiscoLogico(String _discoLogico) {
        this._discoLogico = _discoLogico;
    }

    public String getTamanhoDisco() {
        return _tamanhoDisco;
    }

    public void setTamanhoDisco(String _tamanhoDisco) {
        this._tamanhoDisco = _tamanhoDisco;
    }

    public String getTipo() {
        return _tipo;
    }

    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }

    public String getNome() {
        return _nome;
    }

    public void setNome(String _nome) {
        this._nome = _nome;
    }

    public String getCgcUnidade() {
        return _cgcUnidade;
    }

    public void setCgcUnidade(String _cgcUnidade) {
        this._cgcUnidade = _cgcUnidade;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getModelo() {
        return _modelo;
    }

    public void setModelo(String _modelo) {
        this._modelo = _modelo;
    }

    public String getTipoRede() {
        return _tipoRede;
    }

    public void setTipoRede(String _tipoRede) {
        this._tipoRede = _tipoRede;
    }

    public String getEnderecoIP() {
        return _enderecoIP;
    }

    public void setEnderecoIP(String _enderecoIP) {
        this._enderecoIP = _enderecoIP;
    }

    public String getGateway() {
        return _gateway;
    }

    public void setGateway(String _gateway) {
        this._gateway = _gateway;
    }

    public String getMascaraRede() {
        return _mascaraRede;
    }

    public void setMascaraRede(String _mascaraRede) {
        this._mascaraRede = _mascaraRede;
    }
    
    
    
    

}
