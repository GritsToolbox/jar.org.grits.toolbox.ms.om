package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class PeptideAnnotation extends AminoAcidAnnotation
{
    @XmlElement(name="pepFeature")
    private List<PeptideFeature> m_fragment = new ArrayList<PeptideFeature>();

    @XmlTransient
    public List<PeptideFeature> getFragment()
    {
        return m_fragment;
    }

    public void setFragment(List<PeptideFeature> a_fragment)
    {
        m_fragment = a_fragment;
    }
}
