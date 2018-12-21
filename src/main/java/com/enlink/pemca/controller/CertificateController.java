package com.enlink.pemca.controller;

import com.enlink.pemca.entity.Certificate;
import com.enlink.response.AjaxResults;
import com.enlink.response.ResultCode;
import com.enlink.response.Results;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:30 2018/9/5
 */

@RestController
@RequestMapping("cer")
public class CertificateController {
    private static final String FILEPATH = "/usr/local/enlink/upload/download/logs/";

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public AjaxResults addCertificate(Certificate certificate, @RequestParam("file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        System.out.println(name);
        File file2 = new File(FILEPATH);
        if (!file2.exists()) {
            file2.mkdir();
        }
        OutputStream os = new FileOutputStream(file2);
        File file3 = new File(file2, name);
        file.transferTo(file3);
        os.close();


        return Results.resultOf(ResultCode.OK, null);
    }

    @RequestMapping(value = "/import2", method = RequestMethod.POST)
    public AjaxResults upload1(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2,
                               HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(fileName);
        //先要判断文件格式，不是pem还要转,然后再传到目标目录
        Runtime rt = Runtime.getRuntime(); // 运行时系统获取

        try {
            Process proc = rt.exec("cmd");// 执行命令
            File dir = new File("E:\\OpenSSL-Win64\\bin\\demoCA\\newcerts");//此处是指定路径
            String cmd ="openssl x509 -inform der -in "+file.getName()+"-out "+"fullchain222.pem";
            StringBuffer stringBuffer = new StringBuffer("openssl x509 -inform der -in "+file.getName()+"-out "+"fullchain222.pem");
            System.out.println(stringBuffer);
            // cmd[2]是要执行的dos命令
            System.out.println(cmd);
            Process process = Runtime.getRuntime().exec(cmd,null,dir);

            // 记录dos命令的返回信息
            StringBuffer resStr = new StringBuffer();
            // 获取返回信息的流
            InputStream in = process.getInputStream();
            Reader reader = new InputStreamReader(in);
            BufferedReader bReader = new BufferedReader(reader);
            for (String res = ""; (res = bReader.readLine()) != null;) {
                resStr.append(res + "\n");
            }
            System.out.println(resStr.toString());
            bReader.close();
            reader.close();
            process.getOutputStream().close();  // 不要忘记了一定要关

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ext.equals("der")||ext.equals("cer")){
            //Runtime.getRuntime().exec(new String[]{"cmd","/c",cmds});
        }
        try {
            uploadFile(file.getBytes(), FILEPATH, "fullchain.pem");
            uploadFile(file2.getBytes(), FILEPATH, "privkey.pem");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return Results.resultOf(ResultCode.OK, null);
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
