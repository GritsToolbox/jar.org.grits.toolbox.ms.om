/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author ubuntu
 */
public class StringDoubleMapAdapter extends XmlAdapter<StringDoubleMapElements[], Map<String, Double>> {

    public StringDoubleMapElements[] marshal(Map<String, Double> arg0) throws Exception {
        StringDoubleMapElements[] mapElements = new StringDoubleMapElements[arg0.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : arg0.entrySet()) {
            mapElements[i++] = new StringDoubleMapElements(entry.getKey(), entry.getValue());
        }

        return mapElements;
    }

    public Map<String, Double> unmarshal(StringDoubleMapElements[] arg0) throws Exception {
        Map<String, Double> r = new HashMap<String, Double>();
        for (StringDoubleMapElements mapelement : arg0) {
            r.put(mapelement.key, mapelement.value);
        }
        return r;
    }
}
