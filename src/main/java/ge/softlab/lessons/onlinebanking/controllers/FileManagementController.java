package ge.softlab.lessons.onlinebanking.controllers;

import jakarta.activation.MimetypesFileTypeMap;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("files")
public class FileManagementController {

    @Value("${upload_files_path:/tmp/}")
    public String uploadDir;

    @Value("${allow_upload:false}")
    public Boolean allowUpload;

    @PostConstruct
    public void postCon(){
        System.out.println(uploadDir);
        System.out.println(allowUpload);
    }

    @GetMapping
    public List<String> getFiles(){
        File file = new File(uploadDir);
        var ans = new ArrayList<String>();
        if (file.isDirectory() && file.listFiles()!=null){
            for (var f : file.listFiles()){
                ans.add(f.getName());
            }
        }
        return ans;
    }

    @GetMapping("{fileName}")
    public ResponseEntity<?> getFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        var prefix = uploadDir;
        File f = new File(prefix + fileName);
        var abs = f.getCanonicalPath();

        if (!abs.startsWith(prefix)){
            return ResponseEntity.notFound().build();
        }

        if (!f.exists()){
            return ResponseEntity.notFound().build();
        }

        try {
            MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

            response.setHeader(HttpHeaders.CONTENT_TYPE, fileTypeMap.getContentType(f));
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+f.getName()+"\"");
            response.getOutputStream().write(new FileInputStream(f).readAllBytes());
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("test")
    public void test(){

        File f = new File(uploadDir + "test.txt");

        try(var out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f,true)), true)) {
            out.println(59);
            out.println("test");
            out.println("ქართული ტექსტი");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file){
        if (file == null || file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        if (!allowUpload) {
            throw new RuntimeException("Upload is disabled");
        }
        try {
            var prefix = uploadDir;
            File f = new File(prefix + file.getOriginalFilename());
            var abs = f.getCanonicalPath();

            if (!abs.startsWith(prefix)){
                return ResponseEntity.notFound().build();
            }

            try(var is = file.getInputStream()){
                Files.copy(is, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

}
