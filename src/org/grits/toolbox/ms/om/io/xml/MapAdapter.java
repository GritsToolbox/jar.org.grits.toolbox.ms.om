/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

/**
 *
 * @author ubuntu
 */
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.grits.toolbox.ms.om.data.Scan;

public class MapAdapter extends XmlAdapter<MapElements[], Map<Integer, Scan>> {
    public MapElements[] marshal(Map<Integer, Scan> arg0) throws Exception {
        MapElements[] mapElements = new MapElements[arg0.size()];
        int i = 0;
        for (Map.Entry<Integer, Scan> entry : arg0.entrySet())
            mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());

        return mapElements;
    }

    public Map<Integer, Scan> unmarshal(MapElements[] arg0) throws Exception {
        Map<Integer, Scan> r = new HashMap<Integer, Scan>();
        for (MapElements mapelement : arg0)
            r.put(mapelement.key, mapelement.value);
        return r;
    }
}