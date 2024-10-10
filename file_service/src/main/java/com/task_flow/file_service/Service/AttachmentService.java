package com.task_flow.file_service.Service;

import com.task_flow.file_service.Dto.ResponseDto;
import com.task_flow.file_service.Dto.Status;
import com.task_flow.file_service.Entity.Attachment;
import com.task_flow.file_service.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    public Attachment uploadAttachment(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(filename.contains(".."))
                throw new Exception("Filename contains invalid path sequence: " +filename);
            Attachment attachment = new Attachment(filename,file.getContentType(),file.getSize(),file.getBytes());
            attachmentRepository.save(attachment);
            return attachment;
        }
        catch (Exception e)
        {
            throw new Exception("Unable to Upload the file: " + filename);
        }
    }

    public ResponseDto prepareResponseDto(Attachment attachment)
    {
      //String downloadUrl = getDownloadUrl(attachment);
      return new ResponseDto(attachment.getId(),attachment.getFileName(), attachment.getFileType(),attachment.getFileSize(), Status.Completed );
    }

    public Attachment getAttachment(String fileId) throws Exception {
         return  attachmentRepository.findById(fileId).orElseThrow(()->new Exception("File Not Fount for the id" + fileId));
    }

     public String getDownloadUrl(Attachment attachment)
     {
         return ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path("/download")
                 .path(attachment.getId())
                 .toUriString();
     }
}
