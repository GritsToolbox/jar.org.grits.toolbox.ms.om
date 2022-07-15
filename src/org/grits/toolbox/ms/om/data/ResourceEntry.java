package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;

public class ResourceEntry
{
    private String m_databaseName = null;
    private String m_databaseId = null;
    
    public String getDatabaseName()
    {
        return m_databaseName;
    }
    @XmlAttribute(name="databaseName")
    public void setDatabaseName(String a_databaseName)
    {
        m_databaseName = a_databaseName;
    }
    public String getDatabaseId()
    {
        return m_databaseId;
    }
    @XmlAttribute(name="databaseId")
    public void setDatabaseId(String a_databaseId)
    {
        m_databaseId = a_databaseId;
    }
}
