package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Modification
{
    private String m_modificationType = null;
    private Integer m_modificationPosition = null;
    public String getModificationType()
    {
        return m_modificationType;
    }
    @XmlAttribute(name="modType")
    public void setModificationType(String a_modificationType)
    {
        m_modificationType = a_modificationType;
    }
    public Integer getModificationPosition()
    {
        return m_modificationPosition;
    }
    @XmlAttribute(name="modPosition")
    public void setModificationPosition(Integer a_modificationPosition)
    {
        m_modificationPosition = a_modificationPosition;
    }
}
