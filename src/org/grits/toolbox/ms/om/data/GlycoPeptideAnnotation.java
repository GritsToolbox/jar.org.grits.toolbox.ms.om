package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="pepAnnotation")
public class GlycoPeptideAnnotation extends AminoAcidAnnotation
{
    @XmlElement(name="glycanAnnotation")
    private List<PositionedGlycanAnnotation> m_glycans = new ArrayList<PositionedGlycanAnnotation>();
    @XmlTransient
    public List<PositionedGlycanAnnotation> getGlycans()
    {
        return m_glycans;
    }
    public void setGlycans(List<PositionedGlycanAnnotation> a_glycans)
    {
        m_glycans = a_glycans;
    }
}
