package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the lic_licores database table.
 * 
 */
@Entity
@Table(name="lic_licores")
@NamedQuery(name="LicLicore.findAll", query="SELECT l FROM LicLicore l")
public class LicLicore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_licores", unique=true, nullable=false)
	private Integer idLicores;

	@Column(length=255)
	private String descripcion;

	@Column(name="nombre_licor", nullable=false, length=100)
	private String nombreLicor;

	@Column(name="porcentaje_alcohol", precision=5, scale=2)
	private BigDecimal porcentajeAlcohol;

	@Column(precision=7, scale=2)
	private BigDecimal volumen;

	//bi-directional many-to-one association to LicTiposLicore
	@ManyToOne
	@JoinColumn(name="id_lic_tipos_licores", nullable=false)
	private LicTiposLicore licTiposLicore;

	public LicLicore() {
	}

	public Integer getIdLicores() {
		return this.idLicores;
	}

	public void setIdLicores(Integer idLicores) {
		this.idLicores = idLicores;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreLicor() {
		return this.nombreLicor;
	}

	public void setNombreLicor(String nombreLicor) {
		this.nombreLicor = nombreLicor;
	}

	public BigDecimal getPorcentajeAlcohol() {
		return this.porcentajeAlcohol;
	}

	public void setPorcentajeAlcohol(BigDecimal porcentajeAlcohol) {
		this.porcentajeAlcohol = porcentajeAlcohol;
	}

	public BigDecimal getVolumen() {
		return this.volumen;
	}

	public void setVolumen(BigDecimal volumen) {
		this.volumen = volumen;
	}

	public LicTiposLicore getLicTiposLicore() {
		return this.licTiposLicore;
	}

	public void setLicTiposLicore(LicTiposLicore licTiposLicore) {
		this.licTiposLicore = licTiposLicore;
	}

}