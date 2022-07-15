package org.grits.toolbox.ms.om.io.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.grits.toolbox.ms.om.data.Data;
import org.grits.toolbox.ms.om.data.DataHeader;
import org.grits.toolbox.ms.om.data.IntensityFilter;
import org.grits.toolbox.ms.om.data.Method;
import org.grits.toolbox.ms.om.data.ScanFeatures;
import org.grits.toolbox.ms.om.data.ScansAnnotation;
import org.grits.toolbox.util.structure.glycan.filter.om.FilterSetting;
import org.grits.toolbox.util.structure.glycan.util.FilterUtils;

public class AnnotationReader {
	private static final Logger logger = Logger.getLogger(AnnotationReader.class);

	// need to update this context when new ms table specific filters are implemented
	public static final Class[] filterClassContext = {IntensityFilter.class};

	public ScanFeatures readScanAnnotation(String fileName, int scanId){
		try{
			File fArchive = new File(fileName);
			if( ! fArchive.exists() ) {
				throw new IOException("Can't find archive: " + fileName);				
			}
			String sArchive = fArchive.getName();
			int dotInx = sArchive.lastIndexOf(".");
			String sTempFolder = AnnotationObjectCache.getTempPath( fArchive.getParent(), sArchive.substring(0, dotInx) );
			String sXMLFile = scanId + ".xml";
			String sTempFile = sTempFolder + File.separator + sXMLFile;
			File fData = new File( sTempFile );
			ScanFeatures scanFeatures = (ScanFeatures) AnnotationObjectCache.getObject(sXMLFile, fData);			
			if( scanFeatures == null ) {
				File fldr = new File( sTempFolder );			
				if( ! fldr.exists() ) {
					fldr.mkdirs();
					fldr.deleteOnExit();
				}
				fData = extractXMLFile(fileName, sTempFolder, sXMLFile);
				
				List<Class> contextList = new ArrayList<>(Arrays.asList(filterClassContext));
				contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
				contextList.add(FilterSetting.class);
				contextList.add(ScanFeatures.class);
				JAXBContext jaxbContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				scanFeatures = (ScanFeatures) jaxbUnmarshaller.unmarshal(fData);
				AnnotationObjectCache.cacheObject(sXMLFile, fData, scanFeatures);

				//				scanFeatures = (ScanFeatures) AnnotationObjectCache.unmarshalObject( sXMLFile, fData, jaxbUnmarshaller );
				fData.deleteOnExit();
				//				verifyFeatureUsesRowIds(scanFeatures); // old schema stored peak id for feature, we now use the "row id"
				//				f.delete();
			}
			return scanFeatures;  
		}catch(Exception e){
			logger.error("Exception in AnnotationReader:readScanAnnotation." , e);
			return null;
		}
	}

	//	private static void verifyFeatureUsesRowIds( ScanFeatures scanFeatures ) {
	//		if( scanFeatures != null && scanFeatures.getFeatures() != null && ! scanFeatures.getFeatures().isEmpty() ) {
	//			for( Feature feature : scanFeatures.getFeatures() ) {
	//		    	if( feature.getRowIds().isEmpty() && ! feature.getPeaks().isEmpty() ) { // need to create row ids
	//		    		feature.convertPeakIdsToRowIds();
	//		    	}				
	//			}
	//		}
	//	}

	@SuppressWarnings("finally")
	private File extractXMLFile(String sourceFilePath, String targetFilePath, String targetFileName) throws IOException{
		// create a buffer to improve copy performance later.
		byte[] buffer = new byte[2048];

		// open the zip file stream
		InputStream theFile = new FileInputStream(sourceFilePath);
		ZipInputStream stream = new ZipInputStream(theFile);
		File outputFile = null;

		try
		{

			// now iterate through each item in the stream. The get next
			// entry call will return a ZipEntry for each file in the
			// stream
			ZipEntry entry;
			while((entry = stream.getNextEntry())!=null)
			{

				// Once we get the entry from the stream, the stream is
				// positioned read to read the raw data, and we keep
				// reading until read returns 0 or less.
				if(entry.getName().trim().equalsIgnoreCase(targetFileName)){
					//					File f = new File(sourceFilePath);
					//					String path = f.getParentFile().getAbsolutePath();
					//					String outpath = path + File.separator + entry.getName();
					String outpath = targetFilePath + File.separator + entry.getName();
					FileOutputStream output = null;
					try
					{
						outputFile = new File(outpath);
						output = new FileOutputStream(outputFile);

						int len = 0;
						while ((len = stream.read(buffer)) > 0)
						{
							output.write(buffer, 0, len);
						}
					}
					finally
					{
						// we must always close the output file
						if(output!=null)
							output.close();
						return outputFile;


					}
				}
			}
		}
		finally
		{
			// we must always close the zip file.
			stream.close();
			if(outputFile == null)
				return null;
			else
				return outputFile;
		}
	}


	public Data readDataWithoutFeatures(String fileName){
		try{
			File fArchive = new File(fileName);
			if( ! fArchive.exists() ) {
				throw new IOException("Can't find archive: " + fileName);				
			}
			String sArchive = fArchive.getName();
			int dotInx = sArchive.lastIndexOf(".");
			String sTempFolder = AnnotationObjectCache.getTempPath( fArchive.getParent(), sArchive.substring(0, dotInx) );

			String sTempFile = sTempFolder + File.separator + AnnotationWriter.DATA_FILE;
			File fData = new File( sTempFile );
			Data data = (Data) AnnotationObjectCache.getObject(AnnotationWriter.DATA_FILE, fData);
			if( data == null ) {
				File fldr = new File( sTempFolder );			
				if( ! fldr.exists() ) {
					fldr.mkdirs();
					fldr.deleteOnExit();
				}
				fData = extractXMLFile(fileName, sTempFolder, AnnotationWriter.DATA_FILE);
				
				List<Class> contextList = new ArrayList<>(Arrays.asList(filterClassContext));
				contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
				contextList.add(FilterSetting.class);
				contextList.add(Data.class);
				JAXBContext jaxbContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));				
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				data = (Data) jaxbUnmarshaller.unmarshal(fData);
				//				data = (Data) AnnotationObjectCache.unmarshalObject( AnnotationWriter.DATA_FILE, fData, jaxbUnmarshaller );
				AnnotationObjectCache.cacheObject(AnnotationWriter.DATA_FILE, fData, data);
				fData.deleteOnExit();
			}

			//get the dataHeader object deserialized
			DataHeader dataHeader = readDataHeader(fileName);
			if(dataHeader == null)
				throw new Exception("DataHeader object is null!");
			data.setDataHeader(dataHeader);

			//get the method object deserialized
			Method method = readMethod(fileName);
			dataHeader.setMethod(method);
			//			fData.delete();			
			return data;

		}catch(Exception e){
			//			e.printStackTrace();
			logger.error("Exception in AnnotationReader:readDataWithoutFeatures." , e);
			return null;
		}
	}

	public Data readData(String fileName){
		try{
			File fArchive = new File(fileName);
			if( ! fArchive.exists() ) {
				throw new IOException("Can't find archive: " + fileName);				
			}
			String sArchive = fArchive.getName();
			int dotInx = sArchive.lastIndexOf(".");
			String sTempFolder = AnnotationObjectCache.getTempPath( fArchive.getParent(), sArchive.substring(0, dotInx) );

			String sTempFile = sTempFolder + File.separator + AnnotationWriter.DATA_FILE;
			File fData = new File( sTempFile );
			Data data = (Data) AnnotationObjectCache.getObject(AnnotationWriter.DATA_FILE, fData);
			if( data == null ) {
				File fldr = new File( sTempFolder );			
				if( ! fldr.exists() ) {
					fldr.mkdirs();
					fldr.deleteOnExit();
				}
				fData = extractXMLFile(fileName, sTempFolder, AnnotationWriter.DATA_FILE);

				List<Class> contextList = new ArrayList<>(Arrays.asList(filterClassContext));
				contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
				contextList.add(FilterSetting.class);
				contextList.add(Data.class);
				JAXBContext jaxbContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				data = (Data) jaxbUnmarshaller.unmarshal(fData);
				//			data = (Data) AnnotationObjectCache.unmarshalObject( AnnotationWriter.DATA_FILE, fData, jaxbUnmarshaller );
				AnnotationObjectCache.cacheObject(AnnotationWriter.DATA_FILE, fData, data);
				fData.deleteOnExit();

				//			fData.delete();			
			}
			return data;
		}catch(Exception e){
			//			e.printStackTrace();
			logger.error("Exception in AnnotationReader:readDataWithoutFeatures." , e);
			return null;
		}
	}

	public Method readMethod(String filePath){
		try{
			File fArchive = new File(filePath);
			if( ! fArchive.exists() ) {
				throw new IOException("Can't find archive: " + filePath);				
			}
			String sArchive = fArchive.getName();
			int dotInx = sArchive.lastIndexOf(".");
			String sTempFolder = AnnotationObjectCache.getTempPath( fArchive.getParent(), sArchive.substring(0, dotInx) );

			String sTempFile = sTempFolder + File.separator + AnnotationWriter.SETTINGS_FILE;
			File fData = new File( sTempFile );
			Method method = (Method) AnnotationObjectCache.getObject(AnnotationWriter.SETTINGS_FILE, fData);
			if( method == null ) {
				File fldr = new File( sTempFolder );			
				if( ! fldr.exists() ) {
					fldr.mkdirs();
					fldr.deleteOnExit();
				}
				fData = extractXMLFile(filePath, sTempFolder, AnnotationWriter.SETTINGS_FILE);
				
				List<Class> contextList = new ArrayList<>(Arrays.asList(filterClassContext));
				contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
				contextList.add(FilterSetting.class);
				contextList.add(Method.class);
				JAXBContext jaxbContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				method = (Method) jaxbUnmarshaller.unmarshal(fData);
//				method = (Method) AnnotationObjectCache.unmarshalObject( AnnotationWriter.SETTINGS_FILE, fData, jaxbUnmarshaller );
				AnnotationObjectCache.cacheObject(AnnotationWriter.SETTINGS_FILE, fData, method);
				fData.deleteOnExit();

				//				f.delete();
			}
			return method;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in AnnotationReader:readMethod." , e);
			return null;
		}
	}

	public DataHeader readDataHeader(String filePath){
		try{
			File fArchive = new File(filePath);
			if( ! fArchive.exists() ) {
				throw new IOException("Can't find archive: " + filePath);				
			}
			String sArchive = fArchive.getName();
			int dotInx = sArchive.lastIndexOf(".");
			String sTempFolder = AnnotationObjectCache.getTempPath( fArchive.getParent(), sArchive.substring(0, dotInx) );

			String sTempFile = sTempFolder + File.separator + AnnotationWriter.DATA_HEADER;
			File fData = new File( sTempFile );
			DataHeader dataHeader = (DataHeader) AnnotationObjectCache.getObject(AnnotationWriter.DATA_HEADER, fData);;
			if( dataHeader == null ) {
				File fldr = new File( sTempFolder );			
				if( ! fldr.exists() ) {
					fldr.mkdirs();
					fldr.deleteOnExit();
				}
				fData = extractXMLFile(filePath, sTempFolder, AnnotationWriter.DATA_HEADER);
				
				List<Class> contextList = new ArrayList<>(Arrays.asList(filterClassContext));
				contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
				contextList.add(FilterSetting.class);
				contextList.add(DataHeader.class);
				JAXBContext jaxbContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				dataHeader = (DataHeader) jaxbUnmarshaller.unmarshal(fData);
//				dataHeader = (DataHeader) AnnotationObjectCache.unmarshalObject( AnnotationWriter.DATA_HEADER, fData, jaxbUnmarshaller );
				AnnotationObjectCache.cacheObject(AnnotationWriter.DATA_HEADER, fData, dataHeader);
				fData.deleteOnExit();

				//				f.delete();
			}
			return dataHeader;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in AnnotationReader:readDataHeader." , e);
			return null;
		}
	}

	public ScansAnnotation readAnnotation(String filePath, String annId, Class<?> destClass){
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(destClass);
			ScansAnnotation annotations = null;
			File f = new File(filePath + File.separator + annId + ".xml");
			if(f != null && f.exists()){
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				annotations = (ScansAnnotation) jaxbUnmarshaller.unmarshal(f);   
				return annotations;  
			}
			else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


}
