package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class GlycoProteinSettings {
	
	@XmlElement(name = "GlycanDatabase")
	private GlycanDatabase	m_database				= null;
	private String			m_perDerivatisationType	= null;
	private String			m_proteinSequence		= null;
	private Integer			pronaseMin				= 0;
	private Integer			pronaseMax				= 0;
	private String			enzyme					= null;
	private boolean			nTerminalElimination	= false;
	
	public String getPerDerivatisationType() {
		return m_perDerivatisationType;
	}
	
	@XmlAttribute(name = "DerivType")
	public void setPerDerivatisationType(String a_perDerivatisationType) {
		m_perDerivatisationType = a_perDerivatisationType;
	}
	
	public String getProteinSequence() {
		return m_proteinSequence;
	}
	
	@XmlAttribute(name = "ProteinSequence")
	public void setProteinSequence(String a_ProteinSequence) {
		m_proteinSequence = a_ProteinSequence;
	}
	
	@XmlTransient
	public GlycanDatabase getDatabase() {
		return m_database;
	}
	
	public void setDatabase(GlycanDatabase a_database) {
		m_database = a_database;
	}
	
	public Integer getPronaseMin() {
		return pronaseMin;
	}
	
	@XmlAttribute(name = "PronaseMin")
	public void setPronaseMin(Integer pronaseMin) {
		this.pronaseMin = pronaseMin;
	}
	
	public Integer getPronaseMax() {
		return pronaseMax;
	}
	
	@XmlAttribute(name = "PronaseMax")
	public void setPronaseMax(Integer pronaseMax) {
		this.pronaseMax = pronaseMax;
	}
	
	public String getEnzyme() {
		return this.enzyme;
	}
	
	@XmlAttribute(name = "Enzyme")
	public void setEnzyme(String a_enzyme) {
		this.enzyme = a_enzyme;
	}
	
	public boolean getNTerminalElimination() {
		return this.nTerminalElimination;
	}
	
	public void setNTerminalTermination(boolean nterminalelimination) {
		this.nTerminalElimination = nterminalelimination;
	}
}
