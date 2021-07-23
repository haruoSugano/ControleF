package model.entities;

import java.util.Date;

public class Conta {

	private Integer id;
	private String tipoDespesa;
	private Date dataVencimento;
	private Date dataEmitida;
	private Boolean despesaFixa;
	private Double valor;
	private Double acrescimo;
	private String formaPagamento;
	
	public Conta() {
	}

	public Conta(Integer id, String tipoDespesa, Date dataVencimento, Date dataEmitida, Boolean despesaFixa, Double valor, 
			Double acrescimo, String formaPagamento) {
		this.id = id;
		this.tipoDespesa = tipoDespesa;
		this.dataVencimento = dataVencimento;
		this.dataEmitida = dataEmitida;
		this.despesaFixa = despesaFixa;
		this.valor = valor;
		this.acrescimo = acrescimo;
		this.formaPagamento = formaPagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataEmitida() {
		return dataEmitida;
	}

	public void setDataEmitida(Date dataEmitida) {
		this.dataEmitida = dataEmitida;
	}

	public Boolean getDespesaFixa() {
		return despesaFixa;
	}

	public void setDespesaFixa(Boolean despesaFixa) {
		this.despesaFixa = despesaFixa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(Double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", tipoDespesa=" + tipoDespesa + ", dataVencimento=" + dataVencimento
				+ ", dataEmitida=" + dataEmitida + ", valor=" + valor + ", acrescimo=" + acrescimo + ", formaPagamento="
				+ formaPagamento + "]";
	}
	
	
	
}
