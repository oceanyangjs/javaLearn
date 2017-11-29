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
		//�����ļ���СΪ1MB
		factory.setSizeThreshold(1024*1024);
		
		//��������
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
				//����ʱ�������ͷ���ļ�
				filename = System.currentTimeMillis() + ".jpg";
				
				//ͨ��getrealpath��ȡ�ϴ��ļ��У������Ŀ��e:/project/j2ee/web���Զ���λ��e:/project/j2ee/web/uploaded
				String photoFolder = request.getServletContext().getRealPath("uploaded");
				
				File file = new File(photoFolder,filename);
				file.getParentFile().mkdirs();
				
				//ͨ��item.getinputstream��ȡ������ϴ��ļ���������
				try {
					InputStream is = item.getInputStream();
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					//�����ļ�
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
