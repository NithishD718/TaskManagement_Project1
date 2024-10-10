package com.task_flow.file_service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String fileName;
    private String fileType;
    private long fileSize;
    @Lob
    private byte[] data;

    public Attachment(String filename, String contentType,long fileSize, byte[] bytes) {
        this.fileName = filename;
        this.fileType = contentType;
        this.fileSize = fileSize;
        this.data = bytes;
    }
}
