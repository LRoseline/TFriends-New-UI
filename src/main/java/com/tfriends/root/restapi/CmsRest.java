package com.tfriends.root.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.cms.DefaultDTOv2;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.service.CmsServiceV2;
import com.tfriends.service.ShortService;

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
    private CmsServiceV2 cms;

    @DeleteMapping("/v2/{hash}/delete/{arcno}")
    public ResponseEntity<SecureDTO> defaultBoardDelete(@PathVariable("hash") String hash,
            @PathVariable("arcno") int no) {
        SecureDTO secure = cms.deleteArticle(hash, no);

        return new ResponseEntity<SecureDTO>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }

    @Autowired
    private ShortService shorts;

    @GetMapping("/v2/shorts")
    public ResponseEntity<Object> shortBoardPreview() {
		return new ResponseEntity<Object>(shorts.previewShorts(), HttpStatus.OK);
    }
}