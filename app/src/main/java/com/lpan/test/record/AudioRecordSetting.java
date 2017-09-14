package com.lpan.test.record;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;


public class AudioRecordSetting {

    public  MediaRecorder initRecorder(String path,String number,String ContactName) {
        File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
      //  File file = new File(sdCardDir, TimeUtils.getFormedDate() + ".3gp");
        MediaRecorder mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
       // mediaRecorder.setOutputFile(file.getPath());

      //   LogUtil.i("===record=====file.getPath()" + file.getPath());
        return mediaRecorder;
    }
}
