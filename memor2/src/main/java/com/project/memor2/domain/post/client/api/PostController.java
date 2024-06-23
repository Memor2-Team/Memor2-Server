package com.project.memor2.domain.post.client.api;

import com.project.memor2.domain.post.application.query.PostQueryService;
import com.project.memor2.domain.post.application.service.PostService;
import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.domain.post.client.dto.request.PostEditRequest;
import com.project.memor2.domain.post.client.dto.request.PostRegisterRequest;
import com.project.memor2.global.common.dto.request.PageRequest;
import com.project.memor2.global.common.dto.response.BaseResponse;
import com.project.memor2.global.common.dto.response.BaseResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "게시글", description = "게시글 API")
public class PostController {

    private final PostService postService;
    private final PostQueryService postQueryService;

    @PostMapping("")
    @Operation(summary = "게시글 등록", description = "게시글이 등록되었습니다.")
    public BaseResponse registerPost(PostRegisterRequest request){
        postService.registerPost(request);
        return BaseResponse.created("게시글이 등록되었습니다.");
    }

    @GetMapping("/list-all")
    @Operation(summary = "게시글 목록", description = "게시글 목록을 불러옵니다.")
    public BaseResponseData<List<Post>> postList(PageRequest pageRequest){
        return BaseResponseData.ok(
                "전체 게시글 리스트를 불러왔습니다.",
                postQueryService.postList(pageRequest));
    }

    @GetMapping("/list-my")
    @Operation(summary = "나의 게시글 목록", description = "나의 게시글 목록을 불러옵니다.")
    public BaseResponseData<List<Post>> myPostList(PageRequest pageRequest){
        return BaseResponseData.ok(
                "나의 게시글 리스트를 불러왔습니다.",
                postQueryService.myPostList(pageRequest));
    }

    @GetMapping("/find")
    public BaseResponseData<Post> findPostById(Long idx){
        return BaseResponseData.ok(
                "게시글 조회에 성공했습니다.",
                postService.findPostByIdx(idx));
    }

    @PatchMapping("")
    @Operation(summary = "게시글 수정", description = "게시글이 수정되었습니다.")
    public BaseResponse editPost(PostEditRequest request){
        postService.editPost(request);
        return BaseResponse.ok("게시글이 수정되었습니다.");
    }

    @DeleteMapping("")
    @Operation(summary = "게시글 삭제", description = "게시글이 삭제되었습니다.")
    public BaseResponse deletePost(Long idx){
        postService.deletePost(idx);
        return BaseResponse.ok("게시글이 삭제되었습니다");
    }

}