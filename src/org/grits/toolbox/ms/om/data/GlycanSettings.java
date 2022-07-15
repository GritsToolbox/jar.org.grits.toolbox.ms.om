package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.grits.toolbox.util.structure.glycan.filter.om.FilterSetting;

@XmlType
public class GlycanSettings
{
	@XmlElement(name="GlycanFilter")
    private GlycanFilter m_filter = null;
    private String m_perDerivatisationType = null;
    @XmlElement(name="ReducingEnd")
    private ReducingEnd m_reducingEnd = null;
    @XmlElement(name="FragPerActivation")
    private List<FragmentPerActivationMethod> m_perActivation = new ArrayList<FragmentPerActivationMethod>();
    @XmlElement(name="FragPerMsLevel")
    private List<FragmentPerMsLevel> m_perMsLevel = new ArrayList<FragmentPerMsLevel>();
    @XmlElement(name="Fragments")
    private List<Fragment> m_glycanFragments = new ArrayList<Fragment>();
    private Boolean m_allowInnerFragments = Boolean.FALSE;
    private int m_maxNumOfCleavages = 0;
    private int m_maxNumOfCrossRingCleavages = 0; 
    @XmlElement(name="filter-setting")
    private FilterSetting filterSetting;
    
    @XmlTransient
    public FilterSetting getFilterSetting() {
		return filterSetting;
	}
    
    public void setFilterSetting(FilterSetting filterSetting) {
		this.filterSetting = filterSetting;
	}
    
    public String getPerDerivatisationType()
    {
        return m_perDerivatisationType;
    }
    @XmlAttribute(name="DerivType")
    public void setPerDerivatisationType(String a_perDerivatisationType)
    {
        m_perDerivatisationType = a_perDerivatisationType;
    }
    @XmlTransient
    public ReducingEnd getReducingEnd()
    {
        return m_reducingEnd;
    }
    public void setReducingEnd(ReducingEnd a_reducingEnd)
    {
        m_reducingEnd = a_reducingEnd;
    }
    @XmlTransient
    public List<Fragment> getGlycanFragments()
    {
        return m_glycanFragments;
    }
    public void setGlycanFragments(List<Fragment> a_glycanFragments)
    {
        m_glycanFragments = a_glycanFragments;
    }
    public Boolean getAllowInnerFragments()
    {
        return m_allowInnerFragments;
    }
    @XmlAttribute(name="allowInnerFrags")
    public void setAllowInnerFragments(Boolean a_allowInnerFragments)
    {
        m_allowInnerFragments = a_allowInnerFragments;
    }

    @XmlTransient
    public GlycanFilter getFilter()
    {
        return m_filter;
    }
    public void setFilter(GlycanFilter a_filter)
    {
        m_filter = a_filter;
    }
    
	public int getMaxNumOfCrossRingCleavages() {
		return m_maxNumOfCrossRingCleavages;
	}
	@XmlAttribute(name="maxNumOfCrossRingCleavages")
	public void setMaxNumOfCrossRingCleavages(int m_maxNumOfCrossRingCleavages) {
		this.m_maxNumOfCrossRingCleavages = m_maxNumOfCrossRingCleavages;
	}
	public int getMaxNumOfCleavages() {
		return m_maxNumOfCleavages;
	}
	@XmlAttribute(name="maxNumOfCleavages")
	public void setMaxNumOfCleavages(int m_maxNumOfCleavages) {
		this.m_maxNumOfCleavages = m_maxNumOfCleavages;
	}
	@XmlTransient
	public List<FragmentPerActivationMethod> getPerActivation() {
		return m_perActivation;
	}
	public void setPerActivation(List<FragmentPerActivationMethod> a_perActivation) {
		this.m_perActivation = a_perActivation;
	}
	@XmlTransient
	public List<FragmentPerMsLevel> getPerMsLevel() {
		return m_perMsLevel;
	}
	public void setPerMsLevel(List<FragmentPerMsLevel> a_perMsLevel) {
		this.m_perMsLevel = a_perMsLevel;
	}
}
