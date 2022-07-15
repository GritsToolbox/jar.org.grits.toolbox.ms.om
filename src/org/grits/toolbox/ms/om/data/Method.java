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
import org.grits.toolbox.util.structure.glycan.filter.om.FilterSetting;

@XmlRootElement(name="method")
public class Method
{
    public static final String ANNOTATION_TYPE_GLYCAN = "GLYCAN";
    public static final String ANNOTATION_TYPE_GLYCOPEPTIDE = "GLYCOPEPTIDE";
    public static final String ANNOTATION_TYPE_GLYCOLIPID = "GLYCOLIPID";
    public static final String ANNOTATION_SRC_SIMGLYCAN = "SimGlycan";
    public static final String ANNOTATION_SRC_SIMIAN = "GRITS";
    public static final String MS_TYPE_INFUSION = "Direct Infusion";
    public static final String MS_TYPE_INFUSION_LABEL = "Tandem MS (MS2, MS3,...)";
    public static final String MS_TYPE_LC = "LC-MS/MS";
    public static final String MS_TYPE_LC_LABEL = "LC-MS/MS";
    public static final String MS_TYPE_TIM = "Total Ion Mapping (TIM)";
    public static final String MS_TYPE_TIM_LABEL = "Total Ion Mapping (TIM)";
    public static final String MS_TYPE_MSPROFILE = "MS Profile";
    public static final String MS_TYPE_MSPROFILE_LABEL = "MS Profile";
    public static final String[] MS_TYPES = new String[] {MS_TYPE_INFUSION_LABEL, MS_TYPE_TIM_LABEL, MS_TYPE_LC_LABEL, MS_TYPE_MSPROFILE_LABEL};
    private String m_version = "1.02";
    /*
     * 01/29/15:  DBW added MS Profile method. Changed to ver 1.01
     */
   
    private String m_annotationType = null;
    private String m_annotationSource = null;
    private String m_msType = null;
    private Boolean m_monoisotopic = Boolean.TRUE;
    private Double m_accuracy = null;
    private Boolean m_accuracyPpm = Boolean.TRUE;
    private Double m_fragAccuracy = null;
    private Boolean m_fragAccuracyPpm = Boolean.TRUE;
    private Boolean m_trustMzCharge = Boolean.TRUE;
    private Double m_shift = null;
    // for now on this will be the fragment cutoff, which is how it was used everywhere except for MS Profile mode
    private Double m_intensityCutoff = null;
    private Double m_precursorIntensityCutoff = null;
    private String m_intensityCutoffType = null;
    private String m_precursorIntensityCutoffType = null;
    
    @XmlElement(name="ion")
    private List<IonSettings> m_ions = new ArrayList<IonSettings>();
    @XmlElement(name="ionExchange")
    private List<IonSettings> m_ionExchanges = new ArrayList<IonSettings>();
    private List<MoleculeSettings> m_neutralLoss = new ArrayList<MoleculeSettings>();
    @XmlElement(name="AnalyteSetting")
    private List<AnalyteSettings> m_analyteSettings = new ArrayList<AnalyteSettings>();
    private Integer m_maxIonCount = null;
    private Integer m_maxIonExchangeCount = null;

    // This list of CED details the additional settings for which a Method instance may provide a value (in hashmaps below)
    @XmlElement(name="methodCustomExtraData")
    private List<CustomExtraData> m_MethodCustomExtraData = new ArrayList<CustomExtraData>();
    
    // The filters used during annotation
    @XmlTransient
    private FilterSetting filterSetting;

    private HashMap<String, Integer> m_integerProp = new HashMap<String,Integer>();
    private HashMap<String, Double> m_doubleProp = new HashMap<String,Double>();
    private HashMap<String, String> m_stringProp = new HashMap<String,String>();
    private HashMap<String, Boolean> m_booleanProp = new HashMap<String,Boolean>();
    
    /**
     * return the experiment type associated with the experiment label (which is used solely for display)
     * 
     * @param label the label to match with the experiment type constant 
     * @return the experiment type constant to store in the archive
     */
    public static String getMsTypeByLabel (String label) {
    	if (label.equals(MS_TYPE_INFUSION_LABEL))
    		return MS_TYPE_INFUSION;
    	else if (label.equals(MS_TYPE_LC_LABEL))
    		return MS_TYPE_LC;
    	else if (label.equals(MS_TYPE_MSPROFILE_LABEL))
    		return MS_TYPE_MSPROFILE;
    	else if (label.equals(MS_TYPE_TIM_LABEL))
    		return MS_TYPE_TIM;
    	return null;
    }
    
    /**
     * return the experiment type label associated with the experiment type
     * 
     * @param msType the experiment type constant 
     * @return the experiment type label for display
     */
    public static String getMsLabelByType (String msType) {
    	if (msType.equals(MS_TYPE_INFUSION))
    		return MS_TYPE_INFUSION_LABEL;
    	else if (msType.equals(MS_TYPE_LC))
    		return MS_TYPE_LC_LABEL;
    	else if (msType.equals(MS_TYPE_MSPROFILE))
    		return MS_TYPE_MSPROFILE_LABEL;
    	else if (msType.equals(MS_TYPE_TIM))
    		return MS_TYPE_TIM_LABEL;
    	return null;
    }
 
    @XmlTransient
    public List<CustomExtraData> getMethodCustomExtraData() {
		return m_MethodCustomExtraData;
	}   
    public void setCustomMethodExtraData(List<CustomExtraData> a_MethodCustomExtraData) {
		this.m_MethodCustomExtraData = a_MethodCustomExtraData;
	}  
    
    /**
     * @deprecated filterSettings are now stored under @see org.grits.toolbox.ms.om.data.GlycanSettings GlycanSettings. This will always return null
     * @return null
     */
    @Deprecated
    @XmlElement(name="filterSetting")
    public FilterSetting getFilterSetting() {
		return null;
	}
    
    /**
     * for backward compatibility. sets the filterSetting into all @see org.grits.toolbox.ms.om.data.GlycanSettings GlycanSettings objects
     * There should only be a single GlycanSettings for these old archives
     * 
     * @param filterSetting filterSetting to set
     */
    public void setFilterSetting(FilterSetting filterSetting) {
    	for (AnalyteSettings setting: this.getAnalyteSettings()) {
    		if (setting.getGlycanSettings() != null)
    			setting.getGlycanSettings().setFilterSetting(filterSetting);
    	}
		//this.filterSetting = filterSetting;
	}

//    public GlycanSettings getGlycanSettings()
//    {
//        return m_glycanSettings;
//    }
//    
//    public void setGlycanSettings(GlycanSettings a_glycanSettings)
//    {
//    	m_glycanSettings = a_glycanSettings;
//    }
    
    public Boolean getMonoisotopic()
    {
        return m_monoisotopic;
    }
    @XmlAttribute(name="monoisotopic")
    public void setMonoisotopic(Boolean a_monoisotopic)
    {
        m_monoisotopic = a_monoisotopic;
    }
    public Double getAccuracy()
    {
        return m_accuracy;
    }
    @XmlAttribute(name="accuracy")
    public void setAccuracy(Double a_accuracy)
    {
        m_accuracy = a_accuracy;
    }
    public Boolean getAccuracyPpm()
    {
        return m_accuracyPpm;
    }
    @XmlAttribute(name="accuracyPpm")
    public void setAccuracyPpm(Boolean a_accuracyPpm)
    {
        m_accuracyPpm = a_accuracyPpm;
    }
    public Double getShift()
    {
        return m_shift;
    }
    @XmlAttribute(name="shift")
    public void setShift(Double a_shift)
    {
        m_shift = a_shift;
    }
    @XmlTransient
    public List<IonSettings> getIons()
    {
        return m_ions;
    }
    public void setIons(List<IonSettings> a_ions)
    {
        m_ions = a_ions;
    }
    @XmlTransient
    public List<IonSettings> getIonExchanges()
    {
        return m_ionExchanges;
    }
    public void setIonExchanges(List<IonSettings> a_ionExchanges)
    {
        m_ionExchanges = a_ionExchanges;
    }
    public List<MoleculeSettings> getNeutralLoss()
    {
        return m_neutralLoss;
    }
    public void setNeutralLoss(List<MoleculeSettings> a_neutralLoss)
    {
        m_neutralLoss = a_neutralLoss;
    }
    public String getAnnotationType()
    {
        return m_annotationType;
    }
    @XmlAttribute(name="annotationType")
    public void setAnnotationType(String a_annotationType)
    {
        m_annotationType = a_annotationType;
    }
    public Integer getMaxIonCount()
    {
        return m_maxIonCount;
    }
    @XmlAttribute(name="maxIonCount")
    public void setMaxIonCount(Integer a_maxIonCount)
    {
        m_maxIonCount = a_maxIonCount;
    }
    public Integer getMaxIonExchangeCount()
    {
        return m_maxIonExchangeCount;
    }
    @XmlAttribute(name="maxIonExchangeCount")
    public void setMaxIonExchangeCount(Integer a_maxIonExchangeCount)
    {
        m_maxIonExchangeCount = a_maxIonExchangeCount;
    }
    @XmlTransient
    public List<AnalyteSettings> getAnalyteSettings()
    {
        return m_analyteSettings;
    }
    public void setAnalyteSettings(List<AnalyteSettings> a_analyteSettings)
    {
        m_analyteSettings = a_analyteSettings;
    }
	public String getAnnotationSource() {
		return m_annotationSource;
	}
	@XmlAttribute(name="annotationSrc")
	public void setAnnotationSource(String annotationSource) {
		this.m_annotationSource = annotationSource;
	}
	public String getMsType() {
		return m_msType;
	}
	@XmlAttribute(name="msType")
	public void setMsType(String m_msType) {
		this.m_msType = m_msType;
	}
	public Boolean getFragAccuracyPpm() {
		return m_fragAccuracyPpm;
	}
	@XmlAttribute(name="fragAccuracyPpm")
	public void setFragAccuracyPpm(Boolean m_FragAccuracyPpm) {
		this.m_fragAccuracyPpm = m_FragAccuracyPpm;
	}
	public Double getFragAccuracy() {
		return m_fragAccuracy;
	}
	@XmlAttribute(name="fragAccuracy")
	public void setFragAccuracy(Double m_FragAccuracy) {
		this.m_fragAccuracy = m_FragAccuracy;
	}
	public Boolean getTrustMzCharge() {
		return m_trustMzCharge;
	}
	@XmlAttribute(name="trustCharge")
	public void setTrustMzCharge(Boolean m_trustMzCharge) {
		this.m_trustMzCharge = m_trustMzCharge;
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
    public String getVersion()
    {
        return m_version;
    }
    @XmlAttribute(name="version")
    public void setVersion(String a_version)
    {
        m_version = a_version;
    }
    
    public Double getIntensityCutoff() {
		return m_intensityCutoff;
	}
    @XmlAttribute(name="intensityCutoff")
    public void setIntensityCutoff(Double m_intensityCutoff) {
		this.m_intensityCutoff = m_intensityCutoff;
	}

    public Double getPrecursorIntensityCutoff() {
		return m_precursorIntensityCutoff;
	}
    @XmlAttribute(name="precursorIntensityCutoff")
    public void setPrecursorIntensityCutoff(Double m_intensityCutoff) {
		this.m_precursorIntensityCutoff = m_intensityCutoff;
	}
    
    public String getIntensityCutoffType() {
		return m_intensityCutoffType;
	}
    @XmlAttribute(name="intensityCutoffType")
    public void setIntensityCutoffType(String m_intensityCutoffType) {
		this.m_intensityCutoffType = m_intensityCutoffType;
	}
    public String getPrecursorIntensityCutoffType() {
		return m_precursorIntensityCutoffType;
	}
    @XmlAttribute(name="precursorIntensityCutoffType")
    public void setPrecursorIntensityCutoffType(String m_precursorIntensityCutoffType) {
		this.m_precursorIntensityCutoffType = m_precursorIntensityCutoffType;
	}
}
