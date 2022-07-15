package org.grits.toolbox.ms.om.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class FragmentPerActivationMethod {
	private String m_activationMethod;
	@XmlElement(name="fragments")
	private List<Fragment> fragments;
	private int m_maxNumOfCleavages = 0;
    private int m_maxNumOfCrossRingCleavages = 0; 
	
	public String getActivationMethod() {
		return m_activationMethod;
	}
	
	@XmlAttribute(name="activationMethod")
	public void setActivationMethod(String a_activationMethod) {
		this.m_activationMethod = a_activationMethod;
	}
	@XmlTransient
	public List<Fragment> getFragments() {
		return fragments;
	}
	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
	}
	
	public int getMaxNumOfCrossRingCleavages() {
		return m_maxNumOfCrossRingCleavages;
	}
	@XmlAttribute(name="maxNumOfCleavages")
	public void setMaxNumOfCrossRingCleavages(int m_maxNumOfCrossRingCleavages) {
		this.m_maxNumOfCrossRingCleavages = m_maxNumOfCrossRingCleavages;
	}
	
	public int getMaxNumOfCleavages() {
		return m_maxNumOfCleavages;
	}
	@XmlAttribute(name="maxNumOfCrossRingCleavages")
	public void setMaxNumOfCleavages(int m_maxNumOfCleavages) {
		this.m_maxNumOfCleavages = m_maxNumOfCleavages;
	}

}
