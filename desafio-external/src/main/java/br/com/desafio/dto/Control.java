package br.com.desafio.dto;

public class Control {

	private Integer page;
    private Integer pages;
    private Integer per_page;
    private Integer total;
    private String sourceid;
    private String lastupdated;
    private EconomicIndicator[] indicadores;
    		
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPer_page() {
		return per_page;
	}

	public void setPer_page(Integer per_page) {
		this.per_page = per_page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public EconomicIndicator[] getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(EconomicIndicator[] indicadores) {
		this.indicadores = indicadores;
	}
}
