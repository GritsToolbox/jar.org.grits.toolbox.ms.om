package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;

public class PositionedGlycanAnnotation extends GlycanAnnotation
{
    private Integer m_position = null;
    private String m_glycanAttachmentType = null;
    public Integer getPosition()
    {
        return m_position;
    }
    @XmlAttribute(name="position")
    public void setPosition(Integer a_position)
    {
        m_position = a_position;
    }
    @XmlAttribute(name="attachmentType")
    public String getGlycanAttachmentType()
    {
        return m_glycanAttachmentType;
    }
    public void setGlycanAttachmentType(String a_glycanAttachmentType)
    {
        m_glycanAttachmentType = a_glycanAttachmentType;
    }
}
