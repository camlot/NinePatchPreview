# NinePatchPreview
应用程序提供了.9图片的预览操作，一开始做的目的是设计使用ps生成了.9图片但无法预览效果。  

## 使用方法
1. 拥有一部Android5.0及以上的手机或安装一个安卓模拟器如Genymotion
2. 将编译完成的App安装到手机上
3. 由于应用程序内部需要引用编译过的.9图片，故需要使用/tool中的aapt进行.9.png图片的编译，提供了一个编译脚本，使用方式如下：  
* 将.9图片放置到/tool目录下  
* 终端进入/tool目录，cd /[your_project_path]/tool  
* 使用chmod +x transform_script修改脚本为可执行文件  
* 运行脚本，按照脚本提示输入.9文件前缀名，进行图片的转换  

4. 将图片添加到指定路径，目前是针对Genymotion模拟器使用的，默认路径为/sdcard/Download/，路径不同可修改Config.java下的默认路径。
