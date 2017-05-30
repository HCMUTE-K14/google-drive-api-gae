package com.infamous.fdsa.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.infamous.fdsa.service.GoogleDriveService;
import com.infamous.fdsa.bean.News;
import com.infamous.fdsa.model.news.NewsModel;

public class CreateNewsController extends HttpServlet{
	private boolean isMultipart;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;

	/**
	 * 
	 */
	public static String DOWNLOAD_LINK = "";
	private static final long serialVersionUID = 1L;
	private GoogleDriveService serviceGoogle;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		isMultipart = ServletFileUpload.isMultipartContent((HttpServletRequest) resp);
		String title=null;
		String content = null;
		String attactlink = null;
		String flagupload = null;
		if(!isMultipart){
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
					"transitional//en\">\n";
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(docType +
					"<html>\n" +
					"<head><title>Can't Upload with no multipart</title></head>\n"+			      
					"<body>Can't create news without attachment file</body>"			         
					+ "</html>");
		}
		NewsModel model=new NewsModel();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("c:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax( maxFileSize );
		try {
			List fileItems = null;
			try {
				fileItems = upload.parseRequest(req);
			} catch (FileUploadException e) {			
				System.out.println("FileUploadException cause this exception search google for more information"+e.getMessage());
			}

			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			while ( i.hasNext () ) {
				FileItem fi = (FileItem)i.next();
				if ( !fi.isFormField () )	
				{			
					String fileName = fi.getName();
					String contentType = fi.getContentType();				
					flagupload = serviceGoogle.uploadFile(fileName, fi.getInputStream(),contentType);					
				}else{
					InputStream input = fi.getInputStream();
					if(fi.getFieldName().equals("title")){
						byte[] str = new byte[input.available()];
						input.read(str);
						title = new String(str,"UTF8");

					}
					if(fi.getFieldName().equals("content")){
						byte[] str = new byte[input.available()];
						input.read(str);
						content = new String(str,"UTF8");
					}

				}
			}
				
			if (flagupload != null) {
				attactlink=DOWNLOAD_LINK + flagupload;
			}
			String id = UUID.randomUUID().toString();
			model.addNews(new News(id,title,content,attactlink));
			req.getRequestDispatcher("upload.jsp").forward(req, resp);
		} catch (SQLException e) {			
			System.out.println("Exception "+e.getMessage());
		}

	}



}
