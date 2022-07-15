package org.grits.toolbox.ms.om.io.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class AnnotationObjectCache {
	private static final Logger logger = Logger.getLogger(AnnotationObjectCache.class);
	// Map of File type, to Path, to Java Object
	private static final Map<String, Map<String, Object>> OBJECT_CACHE = new HashMap<String, Map<String,Object>>();
	private static final String TEMP_FOLDER = ".temp";
	
	public static Object getObject( String sType, File fData ) {
		if( OBJECT_CACHE.containsKey(sType) ) {
			Map<String, Object> objCache = OBJECT_CACHE.get(sType);
			return objCache.get(fData.getAbsolutePath());
		} 
		return null;	
	}
	
	public static void cacheObject( String sType, File fData, Object obj ) {
		Map<String, Object> objCache = null;
		if( OBJECT_CACHE.containsKey(sType) ) {
			objCache = OBJECT_CACHE.get(sType);
		} else {
			objCache = new HashMap<>();
			OBJECT_CACHE.put(sType, objCache);
		}
		objCache.put(fData.getAbsolutePath(), obj);
	}
	
	public static void clearCache() {
		OBJECT_CACHE.clear();
	}
	
	public static void clearCache( String sType ) {
		if( OBJECT_CACHE.containsKey(sType) ) {
			Map<String, Object> objCache = OBJECT_CACHE.get(sType);
			objCache.clear();
		}
	}
	
	
	public static String getTempPath( String sParentPath, String fldrID ) {
		String sTempFolder = sParentPath + File.separator + AnnotationObjectCache.TEMP_FOLDER + File.separator + fldrID ;
		return sTempFolder;
	}
	
	/*
	public static Object unmarshalObject( String sType, File fData, Unmarshaller jaxbUnmarshaller ) {
		Object obj = AnnotationObjectCache.getObject(sType, fData);
		if( obj != null ) {
			return obj;
		}
		try {
			obj = jaxbUnmarshaller.unmarshal(fData);
			AnnotationObjectCache.cacheObject(sType, fData, obj);
			return obj;
		} catch (JAXBException e) {
			logger.error("Exception in AnnotationReader:readDataWithoutFeatures." , e);
			return null;
		}
	}

	public static void marshalObject( File fData, Object obj, Marshaller jaxbMarshaller ) {
		try {
			jaxbMarshaller.marshal(obj, fData);
		} catch (JAXBException e) {
			logger.error("Exception in AnnotationReader:readDataWithoutFeatures." , e);
		}
	}
	*/
	
}
