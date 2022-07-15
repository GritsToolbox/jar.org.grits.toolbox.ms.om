package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.grits.toolbox.ms.om.io.xml.StringBooleanMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringDoubleMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringIntegerMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringStringMapAdapter;

@XmlRootElement(name="dataHeader")
public class DataHeader
{
    public static final String SPECTRUM_TYPE_FULL_MS = "full ms";
    public static final String SPECTRUM_TYPE_MSN = "MSn";
    public static final String SPECTRUM_TYPE_LCMSN = "LC-MSn";
    private String m_version = "1.0";
    @XmlTransient
    private Method m_method = null;

    // This list of CED details the additional data for which each Feature may provide a value (in Feature hashmaps)
    @XmlElement(name="annotationCustomExtraData")
    private List<CustomExtraData> m_annotationCustomExtraData = new ArrayList<CustomExtraData>();

    // This list of CED details the additional data for which each Feature may provide a value (in Feature hashmaps)
    @XmlElement(name="featureCustomExtraData")
    private List<CustomExtraData> m_featureCustomExtraData = new ArrayList<CustomExtraData>();

    // This list of CED details the additional data for which each Peak may provide a value (in Peak hashmaps)
    @XmlElement(name="peakCustomExtraData")
    private List<CustomExtraData> m_peakCustomExtraData = new ArrayList<CustomExtraData>();

    private HashMap<String, Integer> m_integerProp = new HashMap<String,Integer>();
    private HashMap<String, Double> m_doubleProp = new HashMap<String,Double>();
    private HashMap<String, String> m_stringProp = new HashMap<String,String>();
    private HashMap<String, Boolean> m_booleanProp = new HashMap<String,Boolean>();

    @XmlTransient
    public List<CustomExtraData> getAnnotationCustomExtraData() {
		return m_annotationCustomExtraData;
	}   
    public void setCustomAnnotationExtraData(List<CustomExtraData> a_CustomExtraData) {
		this.m_annotationCustomExtraData = a_CustomExtraData;
	}     
    
    @XmlTransient
    public List<CustomExtraData> getFeatureCustomExtraData() {
		return m_featureCustomExtraData;
	}   
    public void setCustomFeatureExtraData(List<CustomExtraData> a_CustomExtraData) {
		this.m_featureCustomExtraData = a_CustomExtraData;
	}     
    
    @XmlTransient
    public List<CustomExtraData> getPeakCustomExtraData() {
		return m_peakCustomExtraData;
	}   
    public void setCustomPeakExtraData(List<CustomExtraData> a_CustomExtraData) {
		this.m_peakCustomExtraData = a_CustomExtraData;
	}    
    
    public Method getMethod()
    {
        return m_method;
    }
    public void setMethod(Method a_method)
    {
        m_method = a_method;
    }
    public String getVersion()
    {
        return m_version;
    }
    @XmlAttribute(name="version")
    public void setVersion(String a_version)
    {
        m_version = a_version;
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
    
}
