package org.grits.toolbox.ms.om.io.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.grits.toolbox.ms.om.data.GlycanFeature;

public class MapOfListsAdapter extends XmlAdapter<ListWrapper, Map<Integer, List<GlycanFeature>>> {

    @Override
    public ListWrapper marshal(Map<Integer, List<GlycanFeature>> v) {
    	ListWrapper wrap = new ListWrapper();
    	
    	for(Map.Entry<Integer, List<GlycanFeature>> entry: v.entrySet()){
    		wrap.getList().add(new MapOfListEntry(entry.getKey(),entry.getValue()));
    	}
    	return wrap;
    }

    @Override
    public Map<Integer, List<GlycanFeature>> unmarshal(ListWrapper v){
    	HashMap<Integer, List<GlycanFeature>> map = new HashMap<Integer, List<GlycanFeature>>();
    	for(MapOfListEntry entry : v.getList()){
    		map.put(entry.getKey(), entry.getValue());
    	}
    	return map;
    }
}