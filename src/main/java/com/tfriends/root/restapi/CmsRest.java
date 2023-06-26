package com.tfriends.root.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.cms.DefaultDTOv2;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.MenuDTO;
import com.tfriends.service.CmsServiceV2;
import com.tfriends.service.HomeService;

@RestController
@RequestMapping("/rss")
public class CmsRest {

    @GetMapping("/normal")
    public ResponseEntity<Object> requestMethodName() {
        DefaultDTOv2 dto = new DefaultDTOv2();
        dto.setContent("[h1]Hello, world![/h1]");
        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @Autowired
    private HomeService service;

    @Autowired
    private CmsServiceV2 cms;

    @GetMapping("/menu")
    public ResponseEntity<Object> menuLoading() {
        List<MenuDTO> dto = service.homeMenu();

        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @GetMapping("/v2/{hash}")
    public ResponseEntity<Object> getBoardRest(PaginationDTOV2 page, @PathVariable("hash") String hash) {
        SecureDTO secure = cms.userBoardList(hash, page);

        return new ResponseEntity<Object>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }

    @GetMapping("/v2/{hash}/read/{arcno}")
    public ResponseEntity<SecureDTO> defaultBoardRead(@PathVariable("hash") String hash, @PathVariable("arcno") int no,
            PaginationDTOV2 page) {
        SecureDTO secure = cms.userBoardRead(hash, no, page);

        return new ResponseEntity<SecureDTO>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }

    @PostMapping("/v2/{hash}/write")
    public ResponseEntity<SecureDTO> defaultBoardWrite(@PathVariable("hash") String hash,
            @RequestBody DefaultDTOv2 dto) {
        SecureDTO secure = cms.regArticle(hash, dto);

        return new ResponseEntity<SecureDTO>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }

    @PostMapping("/v2/{hash}/edit/{arcno}")
    public ResponseEntity<SecureDTO> defaultBoardEdit(@PathVariable("hash") String hash, @RequestBody DefaultDTOv2 dto,
            @PathVariable("arcno") int no) {
        SecureDTO secure = cms.editArticle(hash, dto, no);

        return new ResponseEntity<SecureDTO>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }
}