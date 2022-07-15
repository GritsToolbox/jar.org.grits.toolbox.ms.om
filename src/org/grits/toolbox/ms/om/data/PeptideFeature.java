package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class PeptideFeature extends Feature
{
    private Integer m_numberMissCleavage = null;
    @XmlElement(name="modification")
    private List<Modification> m_modifcation = new ArrayList<Modification>();
    public Integer getNumberMissCleavage()
    {
        return m_numberMissCleavage;
    }
    @XmlAttribute(name="numMissCleavage")
    public void setNumberMissCleavage(Integer a_numberMissCleavage)
    {
        m_numberMissCleavage = a_numberMissCleavage;
    }
    @XmlTransient
    public List<Modification> getModifcation()
    {
        return m_modifcation;
    }
    public void setModifcation(List<Modification> a_modifcation)
    {
        m_modifcation = a_modifcation;
    }
}
