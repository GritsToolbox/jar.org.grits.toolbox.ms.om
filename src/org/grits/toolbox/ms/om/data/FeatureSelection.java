package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class FeatureSelection {
	private Boolean selected = true;
    private Boolean manually_selected = false;
    private String m_rowId = null; // replacing array of peak ids w/ array os row ids <peak_id>:<scan_num> (if no scan_num, then just peak)
    private String comment = "";
    private Double ratio = null;
    
    public String getRowId() {
		return m_rowId;
	}
    @XmlAttribute(name="rowId")  
    public void setRowId(String a_rowId) {
		this.m_rowId = a_rowId;
	}
    
    public Boolean getManuallySelected() {
		return manually_selected;
	}
    @XmlAttribute(name="manually_selected")
	public void setManuallySelected(Boolean manually_selected) {
		this.manually_selected = manually_selected;
	}
    
    public Boolean getSelected() {
		return selected;
	}
    @XmlAttribute(name="selected")
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}  
    
    @XmlAttribute
    public String getComment() {
		return comment;
	}
    
    public void setComment(String comment) {
		this.comment = comment;
	}
    
    @XmlAttribute
    public Double getRatio() {
		return ratio;
	}
    
    public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
    
}
