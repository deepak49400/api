package com.blog.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "Post title should not be empty")
    private  String title;
    private  String url;
    @NotEmpty(message = "Post content should not be empty")
    private  String content;
    @NotEmpty(message = "Post short description should not be empty")
    private  String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
