package cn.ganlixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 针对IO进行的
 * @author ganlixin
 *
 */
public class IOUtil {
	
	public static void close(InputStream _is) {
		if (_is != null) {
			try {
				_is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(OutputStream _os) {
		if (_os != null) {
			try {
				_os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
