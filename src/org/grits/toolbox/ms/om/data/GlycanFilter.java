package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class GlycanFilter
{
    public static final String GLYCAN_TYPE_OGLYCAN = "O-glycan";
    public static final String GLYCAN_TYPE_NGLYCAN = "N-glycan";
    
    private String m_database = null;
    private String m_glycanType = null;
    private Boolean m_useDatabaseStructureMetaInfo = false;
    private String m_version = "1.0";
    
    public String getDatabase()
    {
        return m_database;
    }
    @XmlAttribute(name="database")
    public void setDatabase(String a_database)
    {
        m_database = a_database;
    }
    
    @XmlAttribute(name="glycanType")
    public String getGlycanType()
    {
        return m_glycanType;
    }
    public void setGlycanType(String a_glycanType)
    {
        m_glycanType = a_glycanType;
    }
    
    @XmlAttribute(name="useDatabaseStructureMetaInfo")
    public Boolean getUseDatabaseStructureMetaInfo() {
		return m_useDatabaseStructureMetaInfo;
	}
    
    public void setUseDatabaseStructureMetaInfo(Boolean m_useDatabaseStructureMetaInfo) {
		this.m_useDatabaseStructureMetaInfo = m_useDatabaseStructureMetaInfo;
	}
    
    @XmlAttribute(name="version")
    public String getVersion() {
    	return m_version;
    }
    
    public void setVersion (String v) {
    	this.m_version = v;
    }
}
