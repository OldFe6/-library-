package com.aliang.library.common;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class FileUploadUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    // 上传配置
    public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    private static String UPLOAD_DIRECTORY = null;

    @Value("${spring.web.resources.static-locations}")
    public void setUploadDirectory(String uploadLocationURI) {
        FileUploadUtil.UPLOAD_DIRECTORY = createUploadDirectory(uploadLocationURI);
    }

    private static String createUploadDirectory(String uploadLocationURI) {
        File uploadDir;
        try {
            logger.info(String.format("configured uploadLocationURI[%s]", uploadLocationURI));
            uploadLocationURI = String.format("%s/upload", uploadLocationURI.split(",")[0].trim());
            uploadDir = new File(new URI(uploadLocationURI));
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format(
                    "创建上传目录[%s]失败，目录格式不对，必须是符合URI规范的字符串，", uploadLocationURI) +
                    "比如：file:///D:/git/library/src/main/webapp/upload");
        }
        // 如果目录不存在则创建
        if (!uploadDir.exists()) {
            boolean succeed = uploadDir.mkdir();
            if (!succeed) {
                throw new RuntimeException(String.format("创建上传目录[%s]失败", uploadDir.getAbsolutePath()));
            }
        }
        logger.info(String.format("uploadDir[%s] is available", uploadDir.getAbsolutePath()));
        return uploadDir.getAbsolutePath();
    }

    public static String getUploadPath() {
        if (UPLOAD_DIRECTORY == null) {
            throw new RuntimeException("在使用UPLOAD_DIRECTORY前需要先创建该目录");
        }
        return UPLOAD_DIRECTORY;
    }

    /**
     * 检测HttpServletRequest是否为多媒体上传，也就是form表单数据的enctype=multipart/form-data
     *
     * @param request HttpServletRequest
     */
    public static void checkMultipartRequest(HttpServletRequest request) {
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是多媒体上传，则抛出异常
            throw new IllegalArgumentException("表单必须包含 enctype=multipart/form-data");
        }
    }

    /**
     * 创建并配置一个ServletFileUpload对象，用于解析文件上传表单的数据
     *
     * @return ServletFileUpload对象
     */
    public static ServletFileUpload prepareFileUpload() {
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        return upload;
    }
}
