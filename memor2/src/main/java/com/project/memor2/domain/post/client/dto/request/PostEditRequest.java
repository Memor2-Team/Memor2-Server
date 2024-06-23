package com.project.memor2.domain.post.client.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEditRequest {

    private Long idx;
    private String title;
    private String content;

}