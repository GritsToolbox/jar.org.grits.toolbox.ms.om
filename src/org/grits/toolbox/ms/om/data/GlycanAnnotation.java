package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="glycanAnnotation")
public class GlycanAnnotation extends DerivitizedAnnotation
{
//    private String m_glycanId = null;
//    private String m_perDerivatisationType = null;
    
    @XmlElement(name="reducingEnd")
    private ReducingEnd m_reducingEnd = null;
    private String m_composition = null;
    private String m_sequenceGWB = null;
    
    private String glytoucanId = null;
    
    @XmlElement(name="resource")
    private List<ResourceEntry> m_resourceEntries = new ArrayList<ResourceEntry>();

    public static String SEQ_FORMAT_GLYDEII = "GlydeII";
    public static String SEQ_FORMAT_GLYCOCT_XML = "GlycoCTXML";
    public static String SEQ_FORMAT_GLYCOCT_CONDENSED = "GlycoCTCondensed";
    
    public GlycanAnnotation()
    {
        super();
        this.setType("org.grits.toolbox.ms.om.data.GlycanAnnotation");
    }
    
    @Override
    public String getSequenceFormat() {
    	if( super.getSequenceFormat() == null ) { // old version, set default
    		setSequenceFormat(GlycanAnnotation.SEQ_FORMAT_GLYDEII);
    	}
    	return super.getSequenceFormat();    	
    }
    
    
    @Override
    public String toString() {
    	String out = super.toString() + ", glycan id: " + getStringId();
    	return out;
    }
    
    @Deprecated
    public String getGlycanId()
    {
        return null;
    }
    @XmlAttribute(name="glycanId")
    @Deprecated
    public void setGlycanId(String a_glycanId)
    {
//        m_glycanId = a_glycanId;
        super.setStringId(a_glycanId);
    }
    
    @XmlTransient
    public ReducingEnd getReducingEnd()
    {
        return m_reducingEnd;
    }
    public void setReducingEnd(ReducingEnd a_reducingEnd)
    {
        m_reducingEnd = a_reducingEnd;
    }

    public String getComposition()
    {
        return m_composition;
    }
    @XmlAttribute(name="composition")
    public void setComposition(String a_composition)
    {
        m_composition = a_composition;
    }
    @XmlTransient
    public List<ResourceEntry> getResourceEntries()
    {
        return m_resourceEntries;
    }
    public void setResourceEntries(List<ResourceEntry> a_resourceEntries)
    {
        m_resourceEntries = a_resourceEntries;
    }
    public String getSequenceGWB()
    {
        return m_sequenceGWB;
    }
    @XmlAttribute(name="seqGWB")
    public void setSequenceGWB(String a_sequenceGWB)
    {
        m_sequenceGWB = a_sequenceGWB;
    }
    
    @XmlAttribute(name="glytoucanId")
    public String getGlytoucanId() {
		return glytoucanId;
	}
    
    public void setGlytoucanId(String glytoucanId) {
		this.glytoucanId = glytoucanId;
	}

//    @Deprecated
//    public String getPerDerivatisationType() {
//        return m_perDerivatisationType;
//    }
//    @XmlAttribute(name="derivatisationType")
//    @Deprecated
//    public void setGlycanPerDerivatisationType(String perDerivatisationType) {
//        this.m_perDerivatisationType = perDerivatisationType;
//        super.setPerDerivatisationType(perDerivatisationType);
//    }
}
