package model.entities;

public class Entrada {
	
	private Integer id;
	private String tipoEntrada;
	private Double valor;
	private Double acrescimo;
	private Boolean fixa;
	
	public Entrada() {
	}

	public Entrada(Integer id, String tipoEntrada, Double valor, Double acrescimo, Boolean fixa) {
		this.id = id;
		this.tipoEntrada = tipoEntrada;
		this.valor = valor;
		this.acrescimo = acrescimo;
		this.fixa = fixa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(String tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
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

	public Boolean getFixa() {
		return fixa;
	}

	public void setFixa(Boolean fixa) {
		this.fixa = fixa;
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
		Entrada other = (Entrada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", tipoEntrada=" + tipoEntrada + ", valor=" + valor + ", acrescimo=" + acrescimo
				+ ", fixa=" + fixa + "]";
	}

	
}
