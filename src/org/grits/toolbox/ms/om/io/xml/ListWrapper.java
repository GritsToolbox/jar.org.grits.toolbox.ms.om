package org.grits.toolbox.ms.om.io.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class ListWrapper {
	@XmlElement
    private List<MapOfListEntry> list = new ArrayList<MapOfListEntry>();
     
	@XmlTransient
	public List<MapOfListEntry> getList() {
		return list;
	}

	public void setList(List<MapOfListEntry> list) {
		this.list = list;
	}

}
