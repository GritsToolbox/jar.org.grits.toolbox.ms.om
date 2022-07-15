package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ReducingEnd
{
    private String m_type = null;
    private String m_label = null;
    private Double m_mass = null;
    public String getType()
    {
        return m_type;
    }
    @XmlAttribute(name="type")
    public void setType(String a_type)
    {
        m_type = a_type;
    }
    public String getLabel()
    {
        return m_label;
    }
    @XmlAttribute(name="label")
    public void setLabel(String a_label)
    {
        m_label = a_label;
    }
    public Double getMass()
    {
        return m_mass;
    }
    @XmlAttribute(name="mass")
    public void setMass(Double a_mass)
    {
        m_mass = a_mass;
    }
}
