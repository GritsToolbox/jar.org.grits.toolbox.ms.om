package org.grits.toolbox.ms.om.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Lipid fragment pattern and number of cleavages per MS level
 * @author Masaaki Matsubara
 *
 */
@XmlType
public class LipidFragmentPerMSLevel {
	private Integer m_msLevel = null;
	@XmlElement(name="fragments")
	private List<LipidFragment> m_fragments;
	private int m_maxNumOfCleavages = 0;
	 
	public Integer getMsLevel() {
		return m_msLevel;
	}
	@XmlAttribute(name="msLevel")
	public void setMsLevel(Integer msLevel) {
		this.m_msLevel = msLevel;
	}
	@XmlTransient
	public List<LipidFragment> getFragments() {
		return m_fragments;
	}
	public void setFragments(List<LipidFragment> fragments) {
		this.m_fragments = fragments;
	}
	public int getM_maxNumOfCleavages() {
		return m_maxNumOfCleavages;
	}
	@XmlAttribute(name="maxNumOfCleavages")
	public void setM_maxNumOfCleavages(int m_maxNumOfCleavages) {
		this.m_maxNumOfCleavages = m_maxNumOfCleavages;
	}
	
}
