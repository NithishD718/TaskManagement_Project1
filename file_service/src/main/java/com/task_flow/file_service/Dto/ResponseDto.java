package com.task_flow.file_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String fileId;
    private String fileName;
    private String fileType;
    private long fileSize;
    //private String downloadUrl;
    private Status status;
}


