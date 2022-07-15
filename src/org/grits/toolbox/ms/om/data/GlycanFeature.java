package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class GlycanFeature extends Feature
{
    @XmlElement(name="feature")
    private List<GlycanFeature> m_glycanFragment = new ArrayList<GlycanFeature>();
    public GlycanFeature()
    {
        super();
        this.setType("org.grits.toolbox.ms.om.data.GlycanFeature");
    }
    
    @XmlTransient
    public List<GlycanFeature> getGlycanFragment()
    {
        return m_glycanFragment;
    }

    public void setGlycanFragment(List<GlycanFeature> a_glycanFragment)
    {
        m_glycanFragment = a_glycanFragment;
    }

}
