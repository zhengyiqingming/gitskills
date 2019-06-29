package com.baizhi.imgutil;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Map;


public class DurationUtil {
    public static String getDuration(String fileUrl){
        File file=new File(fileUrl);
        long total=0;
        try{
            AudioFileFormat aff= AudioSystem.getAudioFileFormat(file);
            Map props=aff.properties();
            if(props.containsKey("duration")){
                total=(long)Math.round((((Long)props.get("duration")).longValue())/1000);
            }
            Long mm=total/1000/60;
            Long ss=total/1000%60;
            return "0:"+mm+":"+ss;
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件格式异常");
        }
    }
}
