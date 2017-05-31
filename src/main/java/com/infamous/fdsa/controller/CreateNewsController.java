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
	// upload settings                                                   
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	/**
	 * 
	 */
	public static String DOWNLOAD_LINK = "drive.google.com/open?id=";
	private static final long serialVersionUID = 1L;
	private GoogleDriveService serviceGoogle;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(req)) {
			// if not, we stop here
			PrintWriter writer = resp.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

	
		String flagUpload = "";
		String title="";
		String content="";
		String attactLink="";
		serviceGoogle=new GoogleDriveService();
		NewsModel model=new NewsModel();
		
		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(req);
			System.out.println(formItems.size());
			System.out.println(formItems.size());
			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField()) {
						if(item==null){
							req.setAttribute("message", "Upload fail");
							return;
						}
						flagUpload=serviceGoogle.uploadFile(item.getName(), item.getInputStream(), item.getContentType());
					}else{
						InputStream input = item.getInputStream();
						if(item.getFieldName().equals("title")){
							byte[] str = new byte[input.available()];
							input.read(str);
							title = new String(str,"UTF8");

						}
						if(item.getFieldName().equals("content")){
							byte[] str = new byte[input.available()];
							input.read(str);
							content = new String(str,"UTF8");
						}

					}
				}
			}
			if(flagUpload!=null){
				String id = UUID.randomUUID().toString();
				model.addNews(new News(id,title,content,DOWNLOAD_LINK+flagUpload));
				req.setAttribute("message", "Upload successful");
			}else{
				req.setAttribute("message", "Upload fail");
			}
			req.getRequestDispatcher("upload.jsp").forward(req, resp);
		} catch (Exception ex) {
		
		}
	}



}
