package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
@XmlType
public class Scan implements Comparable<Scan>
{
    private Integer m_scanNo = null;
    private Double m_retentionTime = null;
    @XmlElement(name="precursor")
    private Peak m_precursor = null;
    @XmlElement(name="peak")
    private List<Peak> m_peaklist = new ArrayList<Peak>();
    @XmlElement(name="subScan")
    private List<Integer> m_subScans = new ArrayList<Integer>();
    private Integer m_parentScan = null;
    private Double m_scanStart = null;
    private Double m_scanEnd = null;
    private Integer m_msLevel = null;
    private Boolean m_polarity = Boolean.TRUE;
    private String m_activationMethode = null;
    private Double m_mostAbundantPeak = null;
    private Integer m_numAnnotatedPeaks = Integer.valueOf(0);
    // Temporary field only used to count annotated peaks during annotation.
    private Map<Integer,Boolean> m_annotatedPeaks = new HashMap<>();
    private Integer m_totalNumPeaks = 0;
    private Double m_totalIntensity = 0.0;
    private Boolean m_isCentroided = Boolean.FALSE;
    
    @Override
    public String toString() {
    	String out = "Scan num: " + m_scanNo + ", parent scan: " + m_parentScan + ", num peaks: " + m_peaklist.size();
    	return out;
    }
    
    public Integer getScanNo()
    {
        return m_scanNo;
    }
     @XmlAttribute(name="scanNo")
    public void setScanNo(Integer a_scanNo)
    {
        m_scanNo = a_scanNo;
    }
    public Double getRetentionTime()
    {
        return m_retentionTime;
    }
    @XmlAttribute(name="retentionTime")
    public void setRetentionTime(Double a_retentionTime)
    {
        m_retentionTime = a_retentionTime;
    }
    @XmlTransient
    public Peak getPrecursor()
    {
        return m_precursor;
    }
    
    public void setPrecursor(Peak a_precursor)
    {
        m_precursor = a_precursor;
    }
    @XmlTransient
    public List<Peak> getPeaklist()
    {
        return m_peaklist;
    }
    public void setPeaklist(List<Peak> a_peaklist)
    {
        m_peaklist = a_peaklist;
    }
    public Double getScanStart()
    {
        return m_scanStart;
    }
    @XmlAttribute(name="scanStart")
    public void setScanStart(Double a_scanStart)
    {
        m_scanStart = a_scanStart;
    }
    public Double getScanEnd()
    {
        return m_scanEnd;
    }
    // Highest peak in the scan, added 08/21/14 by DBW. Used for relative intensity calculation
    @XmlAttribute(name="mostAbundantPeak")
    public void setMostAbundantPeak(Double a_mostAbundantPeak)
    {
        m_mostAbundantPeak = a_mostAbundantPeak;
    }
    public Double getMostAbundantPeak()
    {
        return m_mostAbundantPeak;
    }
    @XmlAttribute(name="scanEnd")
    public void setScanEnd(Double a_scanEnd)
    {
        m_scanEnd = a_scanEnd;
    }
    
    public Integer getMsLevel()
    {
        return m_msLevel;
    }
    @XmlAttribute(name="msLevel")
    public void setMsLevel(Integer a_msLevel)
    {
        m_msLevel = a_msLevel;
    }
    public Boolean getPolarity()
    {
        return m_polarity;
    }
    @XmlAttribute(name="polarity")
    public void setPolarity(Boolean a_polarity)
    {
        m_polarity = a_polarity;
    }
    public String getActivationMethode()
    {
        return m_activationMethode;
    }
    @XmlAttribute(name="activationMethode")
    public void setActivationMethode(String a_activationMethode)
    {
        m_activationMethode = a_activationMethode;
    }
    public Integer getParentScan()
    {
        return m_parentScan;
    }
    @XmlAttribute(name="parentScan")
    public void setParentScan(Integer a_parentScan)
    {
        m_parentScan = a_parentScan;
    }
    @XmlTransient
    public List<Integer> getSubScans()
    {
        return m_subScans;
    }
    public void setSubScans(List<Integer> a_subScans)
    {
        m_subScans = a_subScans;
    }  
    
    public Integer getNumAnnotatedPeaks()
    {
        return m_numAnnotatedPeaks;
    }
    
    @XmlAttribute(name="numAnnotatedPeaks")
    public void setNumAnnotatedPeaks(Integer a_numAnnotatedPeaks)
    {
    	m_numAnnotatedPeaks = a_numAnnotatedPeaks;
    }
    
    public Integer getTotalNumPeaks()
    {
        return m_totalNumPeaks;
    }    
    @XmlAttribute(name="totalNumPeaks")
    public void setTotalNumPeaks(Integer a_totalNumPeaks)
    {
    	m_totalNumPeaks = a_totalNumPeaks;
    }

    public Double getTotalIntensity()
    {
        return m_totalIntensity;
    }    
    @XmlAttribute(name="totalIntensity")
    public void setTotalIntensity(Double a_totalIntensity)
    {
    	m_totalIntensity = a_totalIntensity;
    }
    
    @XmlTransient
    public Map<Integer, Boolean> getAnnotatedPeaks() {
    	return m_annotatedPeaks;
    }
    
    public void setAnnotatedPeaks(Map<Integer, Boolean> m_annotatedPeaks) {
		this.m_annotatedPeaks = m_annotatedPeaks;
	}

    public Boolean getIsCentroided() {
		return m_isCentroided;
	}
    @XmlAttribute(name="isCentroided")
    public void setIsCentroided(Boolean isCentroided) {
		this.m_isCentroided = isCentroided;
	}
    
	@Override
	public int compareTo(Scan arg0) {
		return getScanNo() - arg0.getScanNo();
	}

    
}
