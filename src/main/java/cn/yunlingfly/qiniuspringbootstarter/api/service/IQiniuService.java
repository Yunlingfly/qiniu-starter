package cn.yunlingfly.qiniuspringbootstarter.api.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

public interface IQiniuService {
    /**
     * 上传文件
     * <p>文件上传</p>
     *
     * @param file
     * @param key
     * @param existed
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file, String key, boolean existed) throws QiniuException;

    /**
     * 上传文件
     * <p>文件路径上传</p>
     *
     * @param filePath
     * @param key
     * @param existed
     * @return
     * @throws QiniuException
     */
    Response uploadFile(String filePath, String key, boolean existed) throws QiniuException;

    /**
     * 删除
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    void deleteFile(String key) throws QiniuException;
}
