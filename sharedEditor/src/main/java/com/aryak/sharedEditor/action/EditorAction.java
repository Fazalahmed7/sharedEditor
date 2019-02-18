package com.aryak.sharedEditor.action;

import static com.aryak.sharedEditor.constant.Constants.*;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aryak.sharedEditor.service.SharedEditorService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class EditorAction {
 
    public static final Logger logger = LoggerFactory.getLogger(EditorAction.class);

 
    @Autowired
    SharedEditorService sharedEditorService; //Service which will do all data retrieval/manipulation work
 
 
    @RequestMapping(value = CREATE_TEXT_EDITOR, method = RequestMethod.GET)
    public ResponseEntity<String> createTextEditor(@PathVariable String name) {
    	logger.trace("Create Text Editor");
    	if(!name.contains(DELIMITER)) {
    		name = name+DELIMITER+UUID.randomUUID().toString().toUpperCase();
    	}
        sharedEditorService.createTextEditor(name);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
    
    @RequestMapping(value = CLEAR_CACHE, method = RequestMethod.GET)
    public ResponseEntity<String> clearCache() {
    	logger.trace("clearCache");
        sharedEditorService.clearCache();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = CLEAR_FILE, method = RequestMethod.GET)
    public ResponseEntity<String> clearFile(@PathVariable String name) {
    	logger.trace("Create Text Editor");
        sharedEditorService.clearFile(name);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
 
 
 
}