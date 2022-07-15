package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Masaaki Matsubara
 *
 */
@XmlType
public class LipidSettings {

	@XmlElement(name="LipidDatabase")
	private LipidDatabase m_database = null;
	private String m_perDerivatisationType = null;
	@XmlElement(name="Fragments")
	private List<LipidFragment> m_lipidFragments = new ArrayList<>();
	@XmlElement(name="FragPerMSLevel")
	private List<LipidFragmentPerMSLevel> m_lipidFragmentsPerMSLevel = new ArrayList<>();
	@XmlElement(name="FragPerActivation")
	private List<LipidFragmentPerActivationMethod> m_lipidFragmentsPerActivationMethod = new ArrayList<>();
	
	private int m_maxNumOfCleavages = 0;

	public String getPerDerivatisationType() {
		return m_perDerivatisationType;
	}

	@XmlAttribute(name="DerivType")
	public void setPerDerivatisationType(String a_perDerivatisationType) {
		m_perDerivatisationType = a_perDerivatisationType;
	}

	@XmlTransient
	public List<LipidFragment> getLipidFragments() {
		return m_lipidFragments;
	}

	public void setLipidFragments(List<LipidFragment> a_lipidFragments) {
		m_lipidFragments = a_lipidFragments;
	}

	@XmlTransient
	public List<LipidFragmentPerMSLevel> getLipidFragmentsPerMSLevel() {
		return m_lipidFragmentsPerMSLevel;
	}

	public void setLipidFragmentsPerMSLevel(List<LipidFragmentPerMSLevel> a_lipidFragments) {
		m_lipidFragmentsPerMSLevel = a_lipidFragments;
	}

	@XmlTransient
	public List<LipidFragmentPerActivationMethod> getLipidFragmentsPerActivationMethod() {
		return m_lipidFragmentsPerActivationMethod;
	}

	public void setLipidFragmentsPerActivationMethod(List<LipidFragmentPerActivationMethod> a_lipidFragments) {
		m_lipidFragmentsPerActivationMethod = a_lipidFragments;
	}

	@XmlTransient
	public LipidDatabase getDatabase() {
		return m_database;
	}

	public void setDatabase(LipidDatabase a_database) {
		m_database = a_database;
	}

	public int getMaxNumOfCleavages() {
		return m_maxNumOfCleavages;
	}

	@XmlAttribute(name="maxNumOfCleavages")
	public void setMaxNumOfCleavages(int m_maxNumOfCleavages) {
		this.m_maxNumOfCleavages = m_maxNumOfCleavages;
	}

}
