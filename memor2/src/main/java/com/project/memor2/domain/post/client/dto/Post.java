package com.project.memor2.domain.post.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long idx;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

}