package cn.yunlingfly.qiniuspringbootstarter.api.service.impl;

import cn.yunlingfly.qiniuspringbootstarter.api.service.IQiniuService;
import cn.yunlingfly.qiniuspringbootstarter.infra.config.QiNiuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class QiniuServiceImpl implements IQiniuService {
    private Logger logger = Logger.getLogger(this.getClass());	//log4j日志

    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;
    @Autowired
    private QiNiuProperties qiNiuProperties;

    private StringMap putPolicy;

    @Override
    public Response uploadFile(File file, String name) throws QiniuException {
        Response response = this.uploadManager.put(file, name, getUploadToken(name));
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, name, getUploadToken());
            retry++;
        }
        return response;
    }

    @Override
    public Response uploadFile(File file) throws QiniuException {
        return null;
    }

    @Override
    public Response uploadFile(String filePath) throws QiniuException {
        return null;
    }

    @Override
    public void delete(String key) throws QiniuException {
        try {
            bucketManager.delete(qiNiuProperties.getBucket(), key);
        } catch (QiniuException e) {
            if (e.response.statusCode == 612) {
                logger.info("文件：" + key + "不存在");
                System.out.println("文件不存在");
            } else {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void init() {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    /**
     * 获取上传凭证，覆盖上传
     *
     * @return
     */
    private String getUploadToken(String fileName) {
        return this.auth.uploadToken(qiNiuProperties.getBucket(), fileName);
    }

    /**
     * 获取上传凭证，普通上传
     *
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(qiNiuProperties.getBucket(), null);
    }
}
