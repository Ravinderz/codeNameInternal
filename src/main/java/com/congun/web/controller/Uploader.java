package com.congun.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.congun.web.util.ApplicationConstants;
import com.congun.web.util.ResponseConstants;

@RestController
@RequestMapping("upload")
public class Uploader {
	
	 private static Logger logger = Logger.getLogger(Uploader.class);

	@RequestMapping(value = "/uploadimage/{uploaderid}/{type}", method = RequestMethod.POST)
	public String uploadImage(@RequestParam MultipartFile[] files,@PathVariable String uploaderid, @PathVariable String type){
		logger.info("Entered Image upload");
		logger.info("Entered to Upload Image with name :"+uploaderid);
		String filename="";
		int i=1;
		String uploadedimages="";
		//MultipartFile[] files = upload.getFiles();
        logger.info("List of Images :"+files.length);
        if(files.length > 0){
		for(MultipartFile file:files){
			
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
                filename=file.getOriginalFilename();
                String fileExt = filename.substring(filename.lastIndexOf(".")+1,filename.length());
                String name = filename.substring(0,filename.lastIndexOf(".")).replaceAll(" ", "_");
                name = name+"_"+System.currentTimeMillis();
                filename = name+"."+fileExt;
                uploadedimages=uploadedimages+filename+",";
                logger.info("Uploading file for Id :"+uploaderid+" Filename:"+filename);
				// Creating the directory to store file
				//String rootPath = ApplicationConstants.PROD_ROOT_PATH;
				//comment below line of rootpath when deploying in server
				String rootPath = ApplicationConstants.PROD_ROOT_PATH;
				File dir = new File(rootPath + File.separator +type);
				if (!dir.exists()){
					dir.mkdirs();
				}
				
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + filename);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				i++;
				logger.info( "You successfully uploaded file=" + uploaderid);
			} catch (Exception e) {
				logger.info( " Exception occured while uploading Image " + uploaderid + " => " + e.getMessage());
			}
			
			
		} else {
			i++;
			logger.info( "You failed to upload " + uploaderid
					+ " because the file was empty");
		}
	
		}
		
		logger.info("Succefully Completed uploading all Images for "+uploaderid);
		uploadedimages = uploadedimages.substring(0,uploadedimages.lastIndexOf(","));
		logger.info("Uploaded Images : "+uploadedimages);
		return uploadedimages;
        }else{
        	logger.info("Received Empty files hence aborting for :"+uploaderid);
        	return ResponseConstants.UPLOAD_FAILURE;
        }
		
	}
	
	@RequestMapping(value="/getimages/{uploaderid}/{type}")
	public String getUploadedImages(@PathVariable String uploaderid,@PathVariable String type){
		logger.info("Entered to get Uploaded Images for Id :"+uploaderid+" Type : "+type);
		
		
		return null;
	}
	
	@ExceptionHandler({Exception.class})
	public void resolveException(Exception e) {
	    e.printStackTrace();
	}
}
