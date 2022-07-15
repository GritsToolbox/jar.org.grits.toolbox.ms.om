package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.grits.toolbox.ms.om.io.xml.StringDoubleMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringIntegerMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringStringMapAdapter;


@XmlType
public class Peak implements Comparable<Peak>
{
    private Integer m_id = null;
    private Double m_mz = null;
    private Double m_intensity = null;
    private Double m_relativeIntensity = null;
    private Integer m_charge = null; 
    private List<Integer> m_features = new ArrayList<Integer>();
    private Double m_precursorIntensity = null;
    private Double m_precursorMz = null;
    private Integer m_precursorCharge = null;
    private boolean isPrecursor = false; // Added 07/01 by DBW    
    
    // Note that there is no CustomExtractData list
    private HashMap<String, Double> m_doubleProp = new HashMap<String,Double>(); // Added 07/01 by DBW  mostly to hold extract mz
    private HashMap<String, Integer> m_integerProp = new HashMap<String,Integer>(); // Added 07/01 by DBW  mostly to hold extract mz
    private HashMap<String, String> m_stringProp = new HashMap<String,String>(); // Added 09/16 by DBW  mostly to hold special peak sequence
    
    private Boolean isSelectionsLocked = false;
    private String comment = "";
    
    @Override
    public String toString() {
    	String out = "Peak id: " + m_id + ", m/z: " + m_mz + ", intensity: " + m_intensity;
    	return out;
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
    public Double getMz()
    {
        return m_mz;
    }
    @XmlAttribute(name="mz")
    public void setMz(Double a_mz)
    {
        m_mz = a_mz;
    }
    public Double getIntensity()
    {
        return m_intensity;
    }
    @XmlAttribute(name="intensity")
    public void setIntensity(Double a_intensity)
    {
    	m_intensity = a_intensity;
    }
    @XmlAttribute(name="relativeIntensity")
    public void setRelativeIntensity(Double a_relativeIntensity)
    {
    	m_relativeIntensity = a_relativeIntensity;
    }
    public Double getRelativeIntensity()
    {
        return m_relativeIntensity;
    }
    public Double getPrecursorMz()
    {
        return m_precursorMz;
    }
    @XmlAttribute(name="precursorMz")
    public void setPrecursorMz(Double a_precursorMz)
    {
    	m_precursorMz = a_precursorMz;
    }    
    public Double getPrecursorIntensity()
    {
        return m_precursorIntensity;
    }
    @XmlAttribute(name="precursorIntensity")
    public void setPrecursorIntensity(Double a_precursorIntensity)
    {
    	m_precursorIntensity = a_precursorIntensity;
    }    
    public Integer getPrecursorCharge()
    {
        return m_precursorCharge;
    }
    @XmlAttribute(name="precursorCharge")
    public void setPrecursorCharge(Integer a_precursorCharge)
    {
    	m_precursorCharge = a_precursorCharge;
    }    
    public Integer getCharge()
    {
        return m_charge;
    }
    @XmlAttribute(name="charge")
    public void setCharge(Integer a_charge)
    {
        m_charge = a_charge;
    }
    public List<Integer> getFeatures()
    {
        return m_features;
    }
    public void setFeatures(List<Integer> a_features)
    {
        m_features = a_features;
    }
    
 	public boolean getIsPrecursor() {
 		return isPrecursor;
 	}

    @XmlAttribute(name="isPrecursor")
 	public void setIsPrecursor(boolean isPrecursor) {
 		this.isPrecursor = isPrecursor;
 	}    
 	
    public HashMap<String, Double> getDoubleProp()
    {
        return m_doubleProp;
    }

    @XmlJavaTypeAdapter(StringDoubleMapAdapter.class)
    public void setDoubleProp(HashMap<String, Double> a_doubleProp)
    {
        m_doubleProp = a_doubleProp;
    }

    public HashMap<String, Integer> getIntegerProp()
    {
        return m_integerProp;
    }

    @XmlJavaTypeAdapter(StringIntegerMapAdapter.class)
    public void setIntegerProp(HashMap<String, Integer> a_integerProp)
    {
    	m_integerProp = a_integerProp;
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

    public HashMap<String, String> getStringProp()
    {
        return m_stringProp;
    }

    @XmlJavaTypeAdapter(StringStringMapAdapter.class)
    public void setStringProp(HashMap<String, String> a_stringProp)
    {
    	m_stringProp = a_stringProp;
    }
    
    public boolean addStringProp(String a_key, String a_value)
    {
        boolean t_overwrite = false;
        if ( this.m_integerProp.get(a_key) != null )
        {
            t_overwrite = true;
        }
        this.m_stringProp.put(a_key, a_value);
        return t_overwrite;
    }
    
	@Override
	public int compareTo(Peak o) {
		if( o.getMz() < getMz() ) {
			return 1;
		} else if ( o.getMz() > getMz() ) {
			return -1;
		}
		return 0;
	}

	/**
	 * @return the isSelectionsLocked
	 */
	@XmlAttribute
	public Boolean getIsSelectionsLocked() {
		return isSelectionsLocked;
	}

	/**
	 * @param isSelectionsLocked the isSelectionsLocked to set
	 */
	public void setIsSelectionsLocked(Boolean isSelectionsLocked) {
		this.isSelectionsLocked = isSelectionsLocked;
	}
	
	@XmlAttribute
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
    
//	@Override
//	public boolean equals(Object obj) {
//		if( ! (obj instanceof Peak) ) 
//			return false;
//		return compareTo((Peak) obj) == 0;
//	}
}
