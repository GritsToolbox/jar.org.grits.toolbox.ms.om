package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="glycolipidAnnotation")
public class GlycolipidAnnotation extends DerivitizedAnnotation {

//	private String m_perDerivatisationType = null;

	@XmlElement(name="glycan")
	private GlycanAnnotation m_glycan = null;
	@XmlElement(name="lipid")
	private LipidAnnotation m_lipid = null;

	public GlycolipidAnnotation() {
		super();
		this.setType("org.grits.toolbox.ms.om.data.GlycolipidAnnotation");
	}

	@Override
	public String toString() {
		String out = super.toString()+", glycan id: "+this.m_glycan.getStringId()+", lipid id: "+this.m_lipid.getStringId();
		return out;
	}

	@XmlTransient
	public GlycanAnnotation getGlycanAnnotation() {
		return m_glycan;
	}

	public void setGlycanAnnotation(GlycanAnnotation a_gAnnot) {
		this.m_glycan = a_gAnnot;
	}

	@XmlTransient
	public LipidAnnotation getLipidAnnotation() {
		return m_lipid;
	}

	public void setLipidAnnotation(LipidAnnotation a_lAnnot) {
		this.m_lipid = a_lAnnot;
	}

//	public String getPerDerivatisationType() {
//		return m_perDerivatisationType;
//	}
//	@XmlAttribute(name="derivatisationType")
//	public void setPerDerivatisationType(String perDerivatisationType) {
//		this.m_perDerivatisationType = perDerivatisationType;
//	}
}
