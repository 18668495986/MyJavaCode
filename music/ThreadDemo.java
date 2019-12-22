package homework.music;

import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月22日
 *
 * @author 徐威
 * @version : 1.0
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player("src/main/java/homework/music/地铁等待.mp3");
        mp3Player.setDaemon(true);
        mp3Player.start();

        //主线程的任务         
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            while (true) {
                try {
                    Thread.sleep(10000);
                    System.out.println("over!");
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class MP3Player extends Thread{
    private String filename;
    private Player player;

    public MP3Player(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
            player = new Player(buffer);
            player.play();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//播放声音的类  播放wav
class AePlayWave extends Thread {
    private String filename;

    public AePlayWave(String wavfile) {
        filename = wavfile;
    }

    public void run() {

        File soundFile = new File(filename);

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[512];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }
}

