import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadPhotoServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		String filename = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置文件大小为1MB
		factory.setSizeThreshold(1024*1024);
		
		//解析请求
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator iter = items.iterator();
		while(iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if(!item.isFormField()) {
				//根据时间戳创建头像文件
				filename = System.currentTimeMillis() + ".jpg";
				
				//通过getrealpath获取上传文件夹，如果项目在e:/project/j2ee/web则自动定位到e:/project/j2ee/web/uploaded
				String photoFolder = request.getServletContext().getRealPath("uploaded");
				
				File file = new File(photoFolder,filename);
				file.getParentFile().mkdirs();
				
				//通过item.getinputstream获取浏览器上传文件的输入流
				try {
					InputStream is = item.getInputStream();
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					//复制文件
					byte b[] = new byte[1024*1024];
					int length = 0;
					while(-1 != (length = is.read(b))) {
						fileOutputStream.write(b,0,length);
					}
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}else {
				
				System.out.println(item.getFieldName());
				String value = item.getString();
				try {
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println(value);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
        String html = "<img width='200' height='150' src='uploaded/%s' />";
        response.setContentType("text/html");
        PrintWriter pw;
		try {
			pw = response.getWriter();
	        pw.format(html, filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
