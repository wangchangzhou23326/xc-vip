package com.dksvip.core.web.util;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ReUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName CommonUtils
 * @Description TODO
 * @Author couqiu
 * @Date 2024/9/7 13:55
 **/
@Log4j2
public class CommonUtils {

    /**
     * 从txt文本中提取出sid
     * @param files
     * @return
     */
    public static Set<String> readSid(MultipartFile[] files){
        Set<String> sidSet = new HashSet<>();
        for (MultipartFile file : files) {
            InputStream inputStream = null;
            try {
                List<String> lines = new ArrayList<>();
                inputStream = file.getInputStream();
                IoUtil.readUtf8Lines(inputStream, lines);
                for (String line : lines) {
                    String reg = "([a-z0-9]{32}_)";
                    String group0 = ReUtil.getGroup0(reg, line);
                    if (Validator.isNotEmpty(group0)) {
                        sidSet.add(group0);
                    }
                }
            } catch (Exception e) {
                log.error("read sid error");
            }finally {
                if(inputStream !=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return sidSet;
    }

    // substring方法，用于安全地截取字符串
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }
        // 确保start和end在字符串长度范围内
        int strLength = str.length();
        if (start < 0) {
            start = 0;
        }
        if (end > strLength) {
            end = strLength;
        }
        // 截取字符串
        return str.substring(start, end);
    }

    /**
     * 是否包含字符串
     *
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 去空格
     */
    public static String trim(String str)
    {
        return (str == null ? "" : str.trim());
    }

}
