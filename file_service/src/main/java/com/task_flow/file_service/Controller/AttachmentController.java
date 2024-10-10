package com.task_flow.file_service.Controller;

import com.task_flow.file_service.Dto.ResponseDto;
import com.task_flow.file_service.Entity.Attachment;
import com.task_flow.file_service.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
@RestController
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseDto uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
       Attachment attachment = attachmentService.uploadAttachment(file);
        return attachmentService.prepareResponseDto(attachment);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFIle(@PathVariable String fileId) throws Exception {
       Attachment attachment = attachmentService.getAttachment(fileId);
       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(attachment.getFileType()))
               .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + attachment.getFileName() +"\"")
               .body(new ByteArrayResource(attachment.getData()));
    }
}
