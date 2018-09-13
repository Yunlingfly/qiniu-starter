package cn.yunlingfly.qiniuspringbootstarter.controller;

import cn.yunlingfly.qiniuspringbootstarter.util.AccountMgr;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * 七牛 java sdk 简单上传
 * @author yunlingfly
 */
public class SimpleUpload {

    private Auth auth = Auth.create(AccountMgr.ACCESS_KEY, AccountMgr.SECRET_KEY);
    private UploadManager uploadManager = new UploadManager();
    private BucketManager bucketMgr = new BucketManager(auth);
    private Logger logger = Logger.getLogger(this.getClass());	//log4j日志
    /**
     * 获取凭证
     * @param bucketName 空间名称
     * @param key 如果需要覆盖上传则设置此参数
     * @return
     */
    public String getUpToken(String bucketName,String key) {
        return auth.uploadToken(bucketName);
    }

    /**
     * 上传方法1
     * @param filePath 文件路径  （也可以是字节数组、或者File对象）
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
    public void upload(String filePath, String key, String bucketName) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken(bucketName,key));

            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            if(e.response.statusCode==612){
                System.out.println("文件已存在");
            }
            else{
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传方法2
     * @param file 文件
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
    public void upload(File file, String key, String bucketName) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken(bucketName,key));
            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }

    /**
     * 上传方法3 覆盖上传
     * @param path
     *            上传文件路径
     * @param bucketName
     *            空间名
     * @param key
     *            文件名
     */
    public void overrideUpload(String path, String key, String bucketName) {
        try {
            String token = getUpToken(bucketName, key);//获取 token
            Response response = uploadManager.put(path, key, token);//执行上传，通过token来识别 该上传是“覆盖上传”
            System.out.println(response.toString());
        } catch (QiniuException e) {
            System.out.println(e.response.statusCode);
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param key
     * @param bucketName
     */
    public void deleteFile(String key, String bucketName) {
        try {
//            String token = getUpToken(bucketName, key);//获取 token
            bucketMgr.delete(bucketName, key);
//            Response response = uploadManager.put(path, key, token);//执行上传，通过token来识别 该上传是“覆盖上传”
//            System.out.println(response.toString());
        } catch (QiniuException e) {
            if(e.response.statusCode==612){
                logger.info("文件："+key+"不存在");
                System.out.println("文件不存在");
            }
            else{
                e.printStackTrace();
            }
        }
    }

    public static String helloworld(){
        System.out.println("hello world");
        return "hello world";
    }
    /**
     * 主函数：程序入口，测试功能
     * @param args
     */
//    public static void main(String[] args) {
//        // 上传文件的路径
//        String filePath = "images/qishi.png";
//        // 要上传的空间
//        // 上传到七牛后保存的文件名
//        String key = "qishi.png";
//        // 这里的filepath可以直接替换成File如下注释所示
//        // File file=new File(filePath);
//        // new SimpleUpload().upload(file, key, bucketName);
//        new SimpleUpload().upload(filePath, key, AccountMgr.BUCKETNAME);
//        System.out.println("上传完成");
////        new SimpleUpload().deleteFile(key, bucketName);
////        System.out.println("删除完成");
////        new SimpleUpload().upload(filePath, key, bucketName);
//
//    }
}