package com.jmper.sigar;

import com.jfinal.kit.PathKit;
import org.hyperic.sigar.Sigar;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 20:19:22)
 */
public class SigarUtils {

    public static Sigar initSigar() {
        try {
            //此处只为得到依赖库文件的目录，可根据实际项目自定义
            String file = Paths.get(PathKit.getWebRootPath(), "files", "sigar", ".sigar_shellrc").toString();
            File classPath = new File(file).getParentFile();

            String path = System.getProperty("java.library.path");
            String sigarLibPath = classPath.getCanonicalPath();
            //为防止java.library.path重复加，此处判断了一下
            if (!path.contains(sigarLibPath)) {
                if (isOSWin()) {
                    path += ";" + sigarLibPath;
                } else {
                    path += ":" + sigarLibPath;
                }
                System.setProperty("java.library.path", path);
            }
            return new Sigar();
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isOSWin() {//OS 版本判断
        String oS = System.getProperty("os.name").toLowerCase();
        return oS.contains("win");
    }
}