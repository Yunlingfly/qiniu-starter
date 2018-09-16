**qiniu-spring-boot-starter**

制作by: yunlingfly@CSDN

2018-9-12

引入方式：

	<!-- https://mvnrepository.com/artifact/cn.yunlingfly/qiniu-spring-boot-starter -->
	<dependency>
		<groupId>cn.yunlingfly</groupId>
		<artifactId>qiniu-spring-boot-starter</artifactId>
		<version>0.1-RELEASE</version>
	</dependency>

使用：

修改application.yml添加一下参数：

    qiniu:
      secret-key: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
      access-key: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
      bucket-name: xxxx

Controller里引用接口：

    @Autowired
    IQiniuService qiniuService;
    
    @RequestMapping(value = "qiniu",method = RequestMethod.GET)
    public String hello(){
        try {
            qiniuService.uploadFile("images/1.png","1.png",true);
        }catch (QiniuException e){
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
 
目前版本qiniuService有三个方法：

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
