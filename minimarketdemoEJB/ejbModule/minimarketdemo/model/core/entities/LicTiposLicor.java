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
@NamedQuery(name="LicTiposLicore.findAll", query="SELECT l FROM LicTiposLicor l")
public class LicTiposLicor implements Serializable {
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
	@OneToMany(mappedBy="licTiposLicor")
	private List<LicLicor> licLicores;

	public LicTiposLicor() {
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

	public List<LicLicor> getLicLicores() {
		return this.licLicores;
	}

	public void setLicLicores(List<LicLicor> licLicores) {
		this.licLicores = licLicores;
	}

	public LicLicor addLicLicore(LicLicor licLicore) {
		getLicLicores().add(licLicore);
		licLicore.setLicTiposLicor(this);

		return licLicore;
	}

	public LicLicor removeLicLicore(LicLicor licLicor) {
		getLicLicores().remove(licLicor);
		licLicor.setLicTiposLicor(null);

		return licLicor;
	}

}