package cn.yunlingfly.qiniuspringbootstarter.api.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

/**
 * IQiniuService接口
 */

public interface IQiniuService {
    /**
     * 上传文件
     * <p>文件上传</p>
     *
     * @param file 填写上传文件File类型
     * @param key 添加上传的key值
     * @param existed 是否已经存在
     * @return 返回com.qiniu.http.Response
     * @throws QiniuException 抛出QiniuException异常
     */
    Response uploadFile(File file, String key, boolean existed) throws QiniuException;

    /**
     * 上传文件
     * <p>文件路径上传</p>
     *
     * @param filePath 填写上传文件的位置
     * @param key 添加上传的key值
     * @param existed 是否已经存在
     * @return 返回com.qiniu.http.Response
     * @throws QiniuException 抛出QiniuException异常
     */
    Response uploadFile(String filePath, String key, boolean existed) throws QiniuException;

    /**
     * 删除
     *
     * @param key 添加上传的key值
     * @throws QiniuException 抛出QiniuException异常
     */
    void deleteFile(String key) throws QiniuException;

    /**
     * 获取上传token
     * <p>获取token</p>
     *
     * @return 返回String
     * @throws QiniuException 抛出QiniuException异常
     */
    String getUploadToken() throws QiniuException;
}
