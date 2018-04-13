#注意点
spring-cloud-config默认是用的git做配置源

如果使用本地文件，需要在application.properties中配置

    spring.profiles.active=native
    
 配置文件的命名需要遵循以下规则：
 
 ${name}-${profile}-${label}.yml
 
 或者：
 
 ${name}-${profile}.yml
 
 其中:
 
 name:配置文件的名称。和client的application-name一致
 
 profile：配置文件的运行环境说明如dev、prod
 
 label:类似和git对应的分支名称，如master
 
 在config-client中需要配置对应的属性，以获取对应的文件.
 
 如本例中的config-client的配置中有如下配置：
 
    spring.cloud.config.profile=dev
 