package br.com.desafio.dto;

import java.util.List;

public class Resultado {

	private Control control;
	
    private List<EconomicIndicator> indicadores;

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public List<EconomicIndicator> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<EconomicIndicator> indicadores) {
		this.indicadores = indicadores;
	}

}
