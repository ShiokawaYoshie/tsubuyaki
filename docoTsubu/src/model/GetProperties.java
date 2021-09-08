package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
	public String[] getProperties() {
		String[] info = new String[3];
		FileReader fr = null;
		Properties p = null;
		try {
			fr = new FileReader("C:\\config\\dokotsubu.properties");
			p = new Properties();
			p.load(fr);
			info[0] =p.getProperty("URL");
			info[1] = p.getProperty("dbUser");
			info[2] = p.getProperty("password");

		} catch (IOException | RuntimeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

			}
		}
		return info;
	}
}
