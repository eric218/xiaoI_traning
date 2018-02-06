package eastrobot.robotdev.ticketsystem.utils;

import java.io.File;

public class CodeViewGenerator {
	public static void main(String[] args){
		generate("G:\\MyEclipse\\WorkSpace\\robot-ocj-interface");
	}
	
	public static void generate(String path){
		File file = new File(path);
		if(file.exists()){
			File[] files = file.listFiles();
			if(files.length == 0){
				return;
			}else{
				for(File file2 : files){
					if(file2.isDirectory()){
						System.out.println("----文件夹----：" + file2.getAbsolutePath().substring(43));
						generate(file2.getAbsolutePath());
					}else{
						System.out.println(file2.getAbsolutePath().substring(43));
					}
				}
			}
		}else{
			System.out.println("文件不存在!");
		}
	}
}
