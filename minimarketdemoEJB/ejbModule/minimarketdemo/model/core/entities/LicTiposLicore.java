package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lic_tipos_licores database table.
 * 
 */
@Entity
@Table(name="lic_tipos_licores")
@NamedQuery(name="LicTiposLicore.findAll", query="SELECT l FROM LicTiposLicore l")
public class LicTiposLicore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lic_tipos_licores", unique=true, nullable=false)
	private Integer idLicTiposLicores;

	@Column(length=255)
	private String descripcion;

	@Column(name="nombre_tipo", nullable=false, length=50)
	private String nombreTipo;

	//bi-directional many-to-one association to LicLicore
	@OneToMany(mappedBy="licTiposLicore")
	private List<LicLicore> licLicores;

	public LicTiposLicore() {
	}

	public Integer getIdLicTiposLicores() {
		return this.idLicTiposLicores;
	}

	public void setIdLicTiposLicores(Integer idLicTiposLicores) {
		this.idLicTiposLicores = idLicTiposLicores;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreTipo() {
		return this.nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<LicLicore> getLicLicores() {
		return this.licLicores;
	}

	public void setLicLicores(List<LicLicore> licLicores) {
		this.licLicores = licLicores;
	}

	public LicLicore addLicLicore(LicLicore licLicore) {
		getLicLicores().add(licLicore);
		licLicore.setLicTiposLicore(this);

		return licLicore;
	}

	public LicLicore removeLicLicore(LicLicore licLicore) {
		getLicLicores().remove(licLicore);
		licLicore.setLicTiposLicore(null);

		return licLicore;
	}

}