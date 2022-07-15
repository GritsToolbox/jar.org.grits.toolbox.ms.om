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
public class StringIntegerMapAdapter extends XmlAdapter<StringIntegerMapElements[], Map<String, Integer>> {

    public StringIntegerMapElements[] marshal(Map<String, Integer> arg0) throws Exception {
        StringIntegerMapElements[] mapElements = new StringIntegerMapElements[arg0.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : arg0.entrySet()) {
            mapElements[i++] = new StringIntegerMapElements(entry.getKey(), entry.getValue());
        }

        return mapElements;
    }

    public Map<String, Integer> unmarshal(StringIntegerMapElements[] arg0) throws Exception {
        Map<String, Integer> r = new HashMap<String, Integer>();
        for (StringIntegerMapElements mapelement : arg0) {
            r.put(mapelement.key, mapelement.value);
        }
        return r;
    }
    
}
