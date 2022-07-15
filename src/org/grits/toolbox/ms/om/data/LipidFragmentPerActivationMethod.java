package org.grits.toolbox.ms.om.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Lipid fragment pattern and number of cleavages per activation method
 * @author Masaaki Matsubara
 *
 */
@XmlType
public class LipidFragmentPerActivationMethod {
	private String m_activationMethod;
	@XmlElement(name="fragments")
	private List<LipidFragment> fragments;
	private int m_maxNumOfCleavages = 0;
	
	public String getActivationMethod() {
		return m_activationMethod;
	}
	
	@XmlAttribute(name="activationMethod")
	public void setActivationMethod(String a_activationMethod) {
		this.m_activationMethod = a_activationMethod;
	}
	@XmlTransient
	public List<LipidFragment> getFragments() {
		return fragments;
	}
	public void setFragments(List<LipidFragment> fragments) {
		this.fragments = fragments;
	}
	
	public int getMaxNumOfCleavages() {
		return m_maxNumOfCleavages;
	}
	@XmlAttribute(name="maxNumOfCleavages")
	public void setMaxNumOfCleavages(int m_maxNumOfCleavages) {
		this.m_maxNumOfCleavages = m_maxNumOfCleavages;
	}

}
