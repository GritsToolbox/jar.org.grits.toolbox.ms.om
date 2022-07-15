package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;

public class LipidAnnotation extends DerivitizedAnnotation {

//	private String m_perDerivatisationType = null;
	private String m_composition = null;

	public LipidAnnotation() {
		super();
		this.setType("org.grits.toolbox.ms.om.data.LipidAnnotation");
	}

	public String getComposition() {
		return m_composition;
	}
	@XmlAttribute(name="composition")
	public void setComposition(String a_composition) {
		m_composition = a_composition;
	}

//	public String getPerDerivatisationType() {
//		return m_perDerivatisationType;
//	}
//	@XmlAttribute(name="derivatisationType")
//	public void setPerDerivatisationType(String perDerivatisationType) {
//		this.m_perDerivatisationType = perDerivatisationType;
//	}

}
