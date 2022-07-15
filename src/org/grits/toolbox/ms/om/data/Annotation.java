package org.grits.toolbox.ms.om.data;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.grits.toolbox.ms.om.io.xml.AnnotationAdapter;
import org.grits.toolbox.ms.om.io.xml.StringBooleanMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringDoubleMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringIntegerMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringStringMapAdapter;

@XmlJavaTypeAdapter(AnnotationAdapter.class)
public abstract class Annotation
{
    private Integer m_id = null;
    private String m_stringId = null;
    private String m_type = null;
    private String m_sequence = null;
    private String m_sequenceFormat = Annotation.UNKNOWN;
    private Boolean selected = true;
    private HashMap<String, Double> m_scores = new HashMap<String,Double>();
    private HashMap<String, Integer> m_integerProp = new HashMap<String,Integer>();
    private HashMap<String, Double> m_doubleProp = new HashMap<String,Double>();
    private HashMap<String, String> m_stringProp = new HashMap<String,String>();
    private HashMap<String, Boolean> m_booleanProp = new HashMap<String,Boolean>();
    
    public static String UNKNOWN = "Unknown";
    @Override
    public String toString() {
    	String out = "Annotation id: " + m_id + ", type: " + m_type;
    	return out;
    }
    
    public Boolean getSelected() {
		return selected;
	}
    @XmlAttribute(name="selected")
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
    public String getStringId() {
		return m_stringId;
	}
    @XmlAttribute(name="stringId")
    public void setStringId(String m_stringId) {
		this.m_stringId = m_stringId;
	}
    
    public Integer getId()
    {
        return m_id;
    }
    @XmlAttribute(name="id")
    public void setId(Integer a_id)
    {
        m_id = a_id;
    }
    public String getType()
    {
        return m_type;
    }
    @XmlAttribute(name="type")
    public void setType(String a_type)
    {
        m_type = a_type;
    }
    public String getSequence()
    {
        return m_sequence;
    }
    @XmlAttribute(name="seq")
    public void setSequence(String a_sequence)
    {
        m_sequence = a_sequence;
    }

    public String getSequenceFormat()
    {
        return m_sequenceFormat;
    }
    
    @XmlAttribute(name="sequenceFormat")
    public void setSequenceFormat(String a_sequenceFormat)
    {
    	m_sequenceFormat = a_sequenceFormat;
    }
    
    @XmlJavaTypeAdapter(StringDoubleMapAdapter.class)
    public HashMap<String, Double> getScores()
    {
        return m_scores;
    }
    public void setScores(HashMap<String, Double> a_scores)
    {
        m_scores = a_scores;
    }

    @XmlJavaTypeAdapter(StringIntegerMapAdapter.class)
    public HashMap<String, Integer> getIntegerProp()
    {
        return m_integerProp;
    }
    public void setIntegerProp(HashMap<String, Integer> a_integerProp)
    {
        m_integerProp = a_integerProp;
    }
    @XmlJavaTypeAdapter(StringDoubleMapAdapter.class)
    public HashMap<String, Double> getDoubleProp()
    {
        return m_doubleProp;
    }
    public void setDoubleProp(HashMap<String, Double> a_doubleProp)
    {
        m_doubleProp = a_doubleProp;
    }
    @XmlJavaTypeAdapter(StringStringMapAdapter.class)
    public HashMap<String, String> getStringProp()
    {
        return m_stringProp;
    }
    public void setStringProp(HashMap<String, String> a_stringProp)
    {
        m_stringProp = a_stringProp;
    }
    @XmlJavaTypeAdapter(StringBooleanMapAdapter.class)
    public HashMap<String, Boolean> getBooleanProp()
    {
        return m_booleanProp;
    }
    public void setBooleanProp(HashMap<String, Boolean> a_booleanProp)
    {
        m_booleanProp = a_booleanProp;
    }
    public boolean addIntegerProp(String a_key, Integer a_value)
    {
        boolean t_overwrite = false;
        if ( this.m_integerProp.get(a_key) != null )
        {
            t_overwrite = true;
        }
        this.m_integerProp.put(a_key, a_value);
        return t_overwrite;
    }

    public boolean addDoubleProp(String a_key, Double a_value)
    {
        boolean t_overwrite = false;
        if ( this.m_doubleProp.get(a_key) != null )
        {
            t_overwrite = true;
        }
        this.m_doubleProp.put(a_key, a_value);
        return t_overwrite;
    }

    public boolean addbooleanProp(String a_key, Boolean a_value)
    {
        boolean t_overwrite = false;
        if ( this.m_booleanProp.get(a_key) != null )
        {
            t_overwrite = true;
        }
        this.m_booleanProp.put(a_key, a_value);
        return t_overwrite;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if( ! (obj instanceof Annotation) ) {
    		return false;
    	}
    	Annotation annotObj = (Annotation) obj;
    	return annotObj.getId().equals(getId());
    }
}
