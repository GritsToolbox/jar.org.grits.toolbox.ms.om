package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.grits.toolbox.ms.om.io.xml.MapAdapter;

@XmlRootElement(name="data")
public class Data
{
    @XmlTransient
    protected Integer featureIndex = 1; // feature index is an incrementing id 
    private String m_spectrumType = null;
    @XmlTransient
    private HashMap<Integer,ScanFeatures> scanFeatures = new HashMap<Integer,ScanFeatures>(); 
    private HashMap<Integer,Scan> m_scans = new HashMap<Integer,Scan>();
    @XmlElement(name="annotation")
    private List<Annotation> m_annotation = new ArrayList<Annotation>();
    @XmlTransient
    private HashMap<Integer,List<String>> annotatedScan = new HashMap<Integer,List<String>>();
    @XmlTransient
    private DataHeader m_dataHeader = null;
    private String m_version = "1.01";

    public Integer getNextFeatureIndex() {
    	return featureIndex++;
    }
    
    public Integer getFeatureIndex() {
		return featureIndex;
	}
    
    public void setFeatureIndex(Integer featureIndex) {
		this.featureIndex = featureIndex;
	}
    
    public DataHeader getDataHeader()
    {
        return m_dataHeader;
    }
    public void setDataHeader(DataHeader a_dataHeader)
    {
        m_dataHeader = a_dataHeader;
    }    
    public String getSpectrumType()
    {
        return m_spectrumType;
    }
    @XmlAttribute(name="spectrumType")
    public void setSpectrumType(String a_spectrumType)
    {
        m_spectrumType = a_spectrumType;
    }
    @XmlJavaTypeAdapter(MapAdapter.class)
    public HashMap<Integer,Scan> getScans()
    {
        return m_scans;
    }
    public void setScans(HashMap<Integer,Scan> a_scans)
    {
        m_scans = a_scans;
    }
     @XmlTransient
    public List<Annotation> getAnnotation()
    {
        return m_annotation;
    }
    public void setAnnotation(List<Annotation> a_annotation)
    {
        m_annotation = a_annotation;
    }
    @XmlTransient
	public HashMap<Integer,ScanFeatures> getScanFeatures() {
		return scanFeatures;
	}
	public void setScanFeatures(HashMap<Integer,ScanFeatures> scanFeatures) {
		this.scanFeatures = scanFeatures;
	}
	@XmlTransient
	public HashMap<Integer,List<String>> getAnnotatedScan() {
		return annotatedScan;
	}
	public void setAnnotatedScan(HashMap<Integer,List<String>> nnotatedScan) {
		this.annotatedScan = nnotatedScan;
	}
	
	public int getFirstMS1Scan() {
		if( getScans().isEmpty() )
			return -1;
		int iMinScanNum = Integer.MAX_VALUE;
		for( Integer iScanNo : getScans().keySet() ) {
			Scan scan = getScans().get(iScanNo);
			if(scan.getMsLevel() == 1 && iScanNo < iMinScanNum) {
				iMinScanNum = iScanNo;
			}
		}
		return iMinScanNum;
		
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

}
