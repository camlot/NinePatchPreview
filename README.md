# NinePatchPreview

1. 由于应用程序内部需要引用编译过的.9图片，故需要使用/tool中的aapt进行.9.png图片的编译，提供了一个编译脚本，使用方式如下  
* 将.9图片放置到/tool目录下  
* 终端进入/tool目录，cd /[your_project_path]/tool  
* 使用chmod +x transform_script修改脚本为可执行文件  
* 运行脚本，按照脚本提示输入.9文件前缀名，进行图片的转换  

2. 将图片添加到指定路径，目前是针对Genymotion模拟器使用的，默认路径为/sdcard/Download/，路径不同可修改Config.java下的默认路径。
