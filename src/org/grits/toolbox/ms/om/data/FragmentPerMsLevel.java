package org.grits.toolbox.ms.om.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class FragmentPerMsLevel {
	private Integer msLevel = null;
	@XmlElement(name="fragments")
	private List<Fragment> fragments;
	private int m_maxNumOfCleavages = 0;
	private int m_maxNumOfCrossRingCleavages = 0; 
	 
	public Integer getMsLevel() {
		return msLevel;
	}
	@XmlAttribute(name="msLevel")
	public void setMsLevel(Integer msLevel) {
		this.msLevel = msLevel;
	}
	@XmlTransient
	public List<Fragment> getFragments() {
		return fragments;
	}
	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
	}
	public int getM_maxNumOfCrossRingCleavages() {
		return m_maxNumOfCrossRingCleavages;
	}
	@XmlAttribute(name="maxNumOfCleavages")
	public void setM_maxNumOfCrossRingCleavages(int m_maxNumOfCrossRingCleavages) {
		this.m_maxNumOfCrossRingCleavages = m_maxNumOfCrossRingCleavages;
	}
	public int getM_maxNumOfCleavages() {
		return m_maxNumOfCleavages;
	}
	@XmlAttribute(name="maxNumOfCrossRingCleavages")
	public void setM_maxNumOfCleavages(int m_maxNumOfCleavages) {
		this.m_maxNumOfCleavages = m_maxNumOfCleavages;
	}
	

}
