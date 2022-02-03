package egovframework.example.ivory.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	
    /**
     * 브라우저 구분 얻기.
     * 
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
    
    /**
     * Disposition 지정하기.
     * 
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
	String browser = getBrowser(request);
	
	String dispositionPrefix = "attachment; filename=";
	String encodedFilename = null;
	
	if (browser.equals("MSIE")) {
	    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	} else if (browser.equals("Firefox")) {
	    //encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		//encodedFilename = new String(filename.getBytes("euc-kr"), "utf-8");
	    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	} else if (browser.equals("Opera")) {
	    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	} else if (browser.equals("Chrome")) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < filename.length(); i++) {
		char c = filename.charAt(i);
		if (c > '~') {
		    sb.append(URLEncoder.encode("" + c, "UTF-8"));
		} else {
		    sb.append(c);
		}
	    }
	    encodedFilename = sb.toString();
	} else {
	    //throw new RuntimeException("Not supported browser");
	    throw new IOException("Not supported browser");
	}
	
	response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

	if ("Opera".equals(browser)){
	    response.setContentType("application/octet-stream;charset=UTF-8");
	}
    }

    
    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
     * 
     * @param commandMap
     * @param response
     * @throws Exception
     */
    public void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

	    File file = (File) model.get("downloadFile");
	    String fileName = (String)model.get("file_name");
	    
	    if (file.exists() && file.isFile()) {
		String mimetype = "application/x-msdownload";

		response.setContentType(mimetype);
		setDisposition(fileName, request, response);
		response.setContentLength((int)file.length());

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
		    in = new BufferedInputStream(new FileInputStream(file));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (Exception ex) {
		} finally {
		    if (in != null) {
			try {
			    in.close();
			} catch (Exception ignore) {
			}
		    }
		    if (out != null) {
			try {
			    out.close();
			} catch (Exception ignore) {
			}
		    }
		}
	    } else {
	    	throw new FileNotFoundException("file not found error.\nfileName=" + fileName + "\nfile=" + file.getAbsolutePath());
	    }
	}

}
