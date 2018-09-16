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
      secret-key: E2HvV26ShjnWihyfj8GOfJ2zQGByRAcJuIdVulMx
      access-key: U7vfxXTqFQ2D6qAzrzz9bguthIfd8dw2TZT2Hge9
      bucket-name: test

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