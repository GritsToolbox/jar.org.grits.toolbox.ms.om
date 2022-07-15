package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.grits.toolbox.ms.om.io.xml.FeatureAdapter;
import org.grits.toolbox.ms.om.io.xml.StringBooleanMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringDoubleMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringIntegerMapAdapter;
import org.grits.toolbox.ms.om.io.xml.StringStringMapAdapter;

@XmlJavaTypeAdapter(FeatureAdapter.class)
public abstract class Feature
{
    private String m_id = null;
    private String m_parentFeatureId = null;
    private String m_type = null;
    @XmlElement(name="peak")
    private List<Integer> m_peaks = new ArrayList<Integer>();  // deprecated
    @XmlElement(name="featureSelection")
    private List<FeatureSelection> m_featureSelections = new ArrayList<FeatureSelection>();
    private String m_sequence = null;
	private Double m_mz = null;
    private Double m_deviation = null;
    @XmlElement(name="Fragment")
    private List<Fragment> m_fragments = null;
    @XmlElement(name="MoleculeSettings")
    private List<MoleculeSettings> m_neutralLoss = new ArrayList<MoleculeSettings>();
    @XmlElement(name="IonExchange")
    private List<IonAdduct> m_neutralexchange = new ArrayList<IonAdduct>();
    @XmlElement(name="Ion")
    private List<IonAdduct> m_ions = new ArrayList<IonAdduct>();
    /*
    private HashMap<String,Double> m_scores = new HashMap<String,Double>();
     */
    private HashMap<String, Integer> m_integerProp = new HashMap<String,Integer>();
    private HashMap<String, Double> m_doubleProp = new HashMap<String,Double>();
    private HashMap<String, String> m_stringProp = new HashMap<String,String>();
    private HashMap<String, Boolean> m_booleanProp = new HashMap<String,Boolean>();

    private Integer m_charge = null;
    private Integer m_annotationId = null;
    private Integer m_precursor = null;
    private Boolean selected = true;
    private Boolean manually_selected = false;
    private String fragmentType = null;
    
    private Annotation parentAnnotation = null;
    
    @Override
    public String toString() {
    	String out = "Feature id: " + m_id + ", m/z: " + m_mz + ", num peaks: " + m_peaks.size();
    	return out;
    }
   
    @Deprecated
    public Boolean getManuallySelected() {
		return manually_selected;
	}
    @XmlAttribute(name="manually_selected")@Deprecated
	public void setManuallySelected(Boolean manually_selected) {
		this.manually_selected = manually_selected;
	}
    
    @Deprecated
    public Boolean getSelected() {
		return selected;
	}
    @XmlAttribute(name="selected")@Deprecated
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

    public String getParentId()
    {
        return m_parentFeatureId;
    }
    @XmlAttribute(name="parentId")
    public void setParentId(String a_parentFeatureId)
    {
    	m_parentFeatureId = a_parentFeatureId;
    }
    
    public String getId()
    {
        return m_id;
    }
    @XmlAttribute(name="id")
    public void setId(String a_id)
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
    public Double getMz()
    {
        return m_mz;
    }
    @XmlAttribute(name="mz")
    public void setMz(Double a_mz)
    {
        m_mz = a_mz;
    }
    public Double getDeviation()
    {
        return m_deviation;
    }
    @XmlAttribute(name="dev")
    public void setDeviation(Double a_deviation)
    {
        m_deviation = a_deviation;
    }
     @XmlTransient
    public List<Fragment> getFragments()
    {
        return m_fragments;
    }
    public void setFragments(List<Fragment> a_fragments)
    {
        m_fragments = a_fragments;
    }
     @XmlTransient
    public List<MoleculeSettings> getNeutralLoss()
    {
        return m_neutralLoss;
    }
    public void setNeutralLoss(List<MoleculeSettings> a_neutralLoss)
    {
        m_neutralLoss = a_neutralLoss;
    }
     @XmlTransient
    public List<IonAdduct> getNeutralexchange()
    {
        return m_neutralexchange;
    }
    public void setNeutralexchange(List<IonAdduct> a_neutralexchange)
    {
        m_neutralexchange = a_neutralexchange;
    }
     @XmlTransient
    public List<IonAdduct> getIons()
    {
        return m_ions;
    }
    public void setIons(List<IonAdduct> a_ions)
    {
        m_ions = a_ions;
    }
    
    // we have moved away from just storing the peak ids. We want to associate both the peak id and the scan num if the scan num exiss
    @XmlTransient @Deprecated
    public List<Integer> getPeaks()
    {
        return m_peaks;
    }
    @Deprecated
    public void setPeaks(List<Integer> a_peaks)
    {
        m_peaks = a_peaks;
    }

    public static String getRowId( Integer a_peakId, Integer a_scanNo, boolean a_usesComplexRowId ) {
    	String sRowId = a_peakId.toString();
    	if( a_scanNo != null && a_usesComplexRowId) {
    		sRowId += ":" + a_scanNo;
    	}
    	return sRowId;
    }
    
	public static FeatureSelection getFeatureSelection( Feature feature, String sRowId ) {
		if( feature == null || feature.getFeatureSelections() == null || feature.getFeatureSelections().isEmpty() ) {
			return null;
		}
		for( FeatureSelection fs : feature.getFeatureSelections() ) {
			if ( fs.getRowId().equals(sRowId) ) {
				return fs;
			}
		}
		return null;
	}

	@XmlTransient
    public List<FeatureSelection> getFeatureSelections() {
		return m_featureSelections;
	}
    
    public void setFeatureSelections(List<FeatureSelection> m_featureSelections) {
		this.m_featureSelections = m_featureSelections;
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
	public Integer getPrecursor() {
		return m_precursor;
	}
	@XmlAttribute(name="preId")
	public void setPrecursor(Integer m_precursor) {
		this.m_precursor = m_precursor;
	}
	public Integer getAnnotationId() {
		return m_annotationId;
	}
	@XmlAttribute(name="annId")
	public void setAnnotationId(Integer m_annotationId) {
		this.m_annotationId = m_annotationId;
	}
	public String getFragmentType() {
		return fragmentType;
	}
	@XmlAttribute(name="fragType")
	public void setFragmentType(String fragmentType) {
		this.fragmentType = fragmentType;
	}

	public Annotation getParentAnnotation() {
		return parentAnnotation;
	}
	@XmlTransient
	public void setParentAnnotation(Annotation parentAnnotation) {
		this.parentAnnotation = parentAnnotation;
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
