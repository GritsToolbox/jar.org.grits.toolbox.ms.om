package org.grits.toolbox.ms.om.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class GlycolipidFeature extends Feature {

	private String m_strLipidName;
	@XmlElement(name="glycolipidFeature")
	private List<GlycolipidFeature> m_glycolipidFragments;
	@XmlElement(name="glycanFeature")
	private List<GlycanFeature> m_glycanFragments;
	@XmlElement(name="lipidFeature")
	private List<LipidFeature> m_lipidFragments;

	public GlycolipidFeature() {
		super();
		this.setType("org.grits.toolbox.ms.om.data.GlycolipidFeature");
	}

	public String getLipidName() {
		return m_strLipidName;
	}

	@XmlAttribute(name="lipid")
	public void setLipidName(String a_strLipidName) {
		this.m_strLipidName = a_strLipidName;
	}

	@XmlTransient
	public List<GlycolipidFeature> getGlycolipidFragments() {
		return this.m_glycolipidFragments;
	}

	public void setGlycolipidFragments(List<GlycolipidFeature> a_glycolipidFragments) {
		this.m_glycolipidFragments = a_glycolipidFragments;
	}

	@XmlTransient
	public List<GlycanFeature> getGlycanFragments() {
		return this.m_glycanFragments;
	}

	public void setGlycanFragments(List<GlycanFeature> a_glycanFragments) {
		this.m_glycanFragments = a_glycanFragments;
	}

	@XmlTransient
	public List<LipidFeature> getLipidFragments() {
		return this.m_lipidFragments;
	}

	public void setLipidFragments(List<LipidFeature> a_lipidFragments) {
		this.m_lipidFragments = a_lipidFragments;
	}

}
