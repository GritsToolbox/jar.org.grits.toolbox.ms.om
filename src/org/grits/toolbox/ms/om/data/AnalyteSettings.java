package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 * XML class for AnalyteSettings object in Object Model
 * 
 * @see GlycanSettings
 * @see PeptideSettings
 * @see LipidSettings
 */
@XmlType
public class AnalyteSettings {
	@XmlElement(name = "GlycanSettings")
	private GlycanSettings			m_glycanSettings		= null;
	@XmlElement(name = "PeptideSettings")
	private PeptideSettings			m_peptideSettings		= null;
	@XmlElement(name = "LipidSettings")
	private LipidSettings			m_lipidSettings			= null;
	@XmlElement(name = "GlycoProteinSettings")
	private GlycoProteinSettings	m_glycoProteinSettings	= null;
	
	@XmlTransient
	public GlycanSettings getGlycanSettings() {
		return m_glycanSettings;
	}
	
	public void setGlycanSettings(GlycanSettings a_glycanSettings) {
		m_glycanSettings = a_glycanSettings;
	}
	
	@XmlTransient
	public PeptideSettings getPeptideSettings() {
		return m_peptideSettings;
	}
	
	public void setPeptideSettings(PeptideSettings a_peptideSettings) {
		m_peptideSettings = a_peptideSettings;
	}
	
	@XmlTransient
	public LipidSettings getLipidSettings() {
		return m_lipidSettings;
	}
	
	public void setLipidSettings(LipidSettings a_lipidSettings) {
		m_lipidSettings = a_lipidSettings;
	}
	
	@XmlTransient
	public GlycoProteinSettings getGlycoProteinSettings() {
		return m_glycoProteinSettings;
	}
	
	public void setGlycoProteinSettings(GlycoProteinSettings a_glycoProteinSettings) {
		m_glycoProteinSettings = a_glycoProteinSettings;
	}
}
