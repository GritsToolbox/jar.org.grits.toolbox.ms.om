package org.grits.toolbox.ms.om.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class LipidFeature extends Feature {

	private String m_strLipidName;
	@XmlElement(name="feature")
	private List<LipidFeature> m_lipidFragments;

	public LipidFeature() {
		super();
		this.setType("org.grits.toolbox.ms.om.data.LipidFeature");
	}

	public String getLipidName() {
		return m_strLipidName;
	}

	@XmlAttribute(name="lipid")
	public void setLipidName(String a_strLipidName) {
		this.m_strLipidName = a_strLipidName;
	}

	@XmlTransient
	public List<LipidFeature> getLipidFragments() {
		return this.m_lipidFragments;
	}

	public void setLipidFragments(List<LipidFeature> a_lipidFragments) {
		this.m_lipidFragments = a_lipidFragments;
	}
}
