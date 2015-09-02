package com.asiainfo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {

	//���url��Ӧmap



	public static void main(String[] args) throws Exception {
			File createFile=new File("/interface/yangsy/filelog/urllog_.txt");
			InputStream inputStream = new FileInputStream(createFile);
			boolean boo=uploadFile("10.25.88.75",-1,"push","push123","/thetabin/push/","urlconfig.txt",inputStream);
			if(boo){
				System.out.println("�ϴ��ɹ���");
			}else{
				System.out.println("�ϴ�ʧ�ܣ�");
			}
	}

	/**
     * Description: ��FTP�������ϴ��ļ�
     * @param url FTP������hostname
     * @param port FTP�������˿ڣ����Ĭ�϶˿���д-1
     * @param username FTP��¼�˺�
     * @param password FTP��¼����
     * @param path FTP����������Ŀ¼
     * @param filename �ϴ���FTP�������ϵ��ļ���
     * @param input ������
     * @return �ɹ�����true�����򷵻�false
     */
    public static boolean uploadFile(String url, int port, String username, String password, String path,
        String filename, InputStream input){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try{
            int reply;
            // ����FTP������
            if (port > -1){
                ftp.connect(url, port);
            }else{
                ftp.connect(url);
            }

            // ��¼FTP
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        }catch (IOException e){
            success = false;
            System.out.println(e);
        }finally{
            if (ftp.isConnected()){
                try{
                    ftp.disconnect();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
        return success;
    }


    /**
     * Description: ��FTP�����������ļ�
     * @param url FTP������hostname
     * @param port FTP�������˿�
     * @param username FTP��¼�˺�
     * @param password FTP��¼����
     * @param remotePath FTP�������ϵ����·��
     * @param fileName Ҫ���ص��ļ���
     * @param localPath ���غ󱣴浽���ص�·��
     * @return
     */
    public static boolean downloadFile(String url, int port, String username, String password, String remotePath,
        String fileName, String localPath){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try{
            int reply;
            // ����FTP������
            if (port > -1){
                ftp.connect(url, port);
            }else{
                ftp.connect(url);
            }

            ftp.login(username, password);//��¼
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//ת�Ƶ�FTP������Ŀ¼
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs){
                if ((null==fileName||"".equals(fileName))||ff.getName().equals(fileName)){
                    File localFile = new File(localPath + "" + ff.getName());
                    System.out.println("localFile:"+localFile);
                    if(!localFile.exists()) localFile.createNewFile();
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        }catch (IOException e){
            System.out.println(e);
        }finally{
            if (ftp.isConnected()){
                try{
                    ftp.disconnect();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
        return success;
    }

}
