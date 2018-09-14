package cn.yunlingfly.qiniuspringbootstarter.api.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

public interface IQiniuService {
    /**
     * 上传文件
     * <p>文件覆盖上传</p>
     *
     * @param file
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file, String name) throws QiniuException;

    /**
     * 上传文件
     * <p>文件普通上传</p>
     *
     * @param file
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file) throws QiniuException;

    /**
     * 上传文件
     * <p>文件路径上传</p>
     *
     * @param filePath
     * @return
     * @throws QiniuException
     */
    Response uploadFile(String filePath) throws QiniuException;

    /**
     * 删除
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    void delete(String key) throws QiniuException;
}
