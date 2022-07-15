package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class PeptideSettings
{
    private String m_enzyme = null;
    private List<Fragment> m_fragments = new ArrayList<Fragment>();
    private Integer m_numberMissCleavages = null;
    private List<ModificationSettings> m_ptm = new ArrayList<ModificationSettings>();
    
    public String getEnzyme()
    {
        return m_enzyme;
    }
    @XmlAttribute(name="enzyme")
    public void setEnzyme(String a_enzyme)
    {
        m_enzyme = a_enzyme;
    }
    public List<Fragment> getFragments()
    {
        return m_fragments;
    }
    public void setFragments(List<Fragment> a_fragments)
    {
        m_fragments = a_fragments;
    }
    public Integer getNumberMissCleavages()
    {
        return m_numberMissCleavages;
    }
    @XmlAttribute(name="numberMissCleavages")
    public void setNumberMissCleavages(Integer a_numberMissCleavages)
    {
        m_numberMissCleavages = a_numberMissCleavages;
    }
    public List<ModificationSettings> getPtm()
    {
        return m_ptm;
    }
    public void setPtm(List<ModificationSettings> a_ptm)
    {
        m_ptm = a_ptm;
    }
}
